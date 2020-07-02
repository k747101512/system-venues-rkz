package com.cm.venuebooking.service.groundbooking.impl;

import com.cm.common.exception.SaveException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.common.token.app.AppTokenManager;
import com.cm.common.token.app.entity.AppTokenUser;
import com.cm.common.utils.DateUtil;
import com.cm.common.utils.HashMapUtil;
import com.cm.common.utils.UUIDUtil;
import com.cm.manager.sms.manager.VerificationCodeManager;
import com.cm.venuebooking.dao.groundbooking.IGroundBookingDao;
import com.cm.venuebooking.pojo.dtos.bookingorder.*;
import com.cm.venuebooking.pojo.dtos.groundinfo.GroundItemDTO;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoOwDTO;
import com.cm.venuebooking.pojo.vos.groundbooking.GroundTicketItemVO;
import com.cm.venuebooking.pojo.vos.groundbooking.GroundTicketVO;
import com.cm.venuebooking.service.BaseService;
import com.cm.venuebooking.service.groundbooking.IGroundBookingService;
import com.cm.venuebooking.service.groundinfo.IGroundInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xwangs
 * @create 2020-06-04 21:49
 * @description
 */
@Service
public class GroundBookingServiceImpl extends BaseService implements IGroundBookingService {

    @Autowired
    private IGroundBookingDao groundBookingDao;

    @Autowired
    private IGroundInfoService groundInfoService;

    @Override
    public SuccessResultList listPageBookingOrder(ListPage page) {
        Map<String, Object> param = page.getParams();
        setDataAuthorityInfo(param);
        String userId = securityComponent.getCurrentUser().getUserId();
        param.put("userId",userId);
        //查询当前用户所管理的场馆
        List<VenuesInfoOwDTO> owList =  groundBookingDao.listVenuesInfoOw(param);
        String venuesInfoIds = "";
        if(owList != null || owList.size() >= 0){
            for(VenuesInfoOwDTO item : owList){
                venuesInfoIds += item.getVenuesInfoId() + ",";
            }
        }
        param.put("venuesInfoIds",venuesInfoIds);
        PageHelper.startPage(page.getPage(), page.getRows());
        List<GroundBookingInfoDTO> list = groundBookingDao.listPageBookingOrder(page.getParams());
        PageInfo<GroundBookingInfoDTO> pageInfo = new PageInfo<>(list);
        return new SuccessResultList(list, pageInfo.getPageNum(), pageInfo.getTotal());
    }

    @Override
    public SuccessResult saveBookingInfoForApp(String token, GroundTicketVO groundTicketVO) throws Exception{
        //校验短信验证码
        VerificationCodeManager.getInstance().checkVerificationCode(groundTicketVO.getPhoneNumber(), groundTicketVO.getSmsCode());
        AppTokenUser appTokenUser = AppTokenManager.getInstance().getToken(token).getAppTokenUser();
        //预订订单保存
        GroundBookingInfoDTO bookingInfo = new GroundBookingInfoDTO();
        String bookingId = UUIDUtil.getUUID();
        String serial = generateSerialNumber();
        bookingInfo.setGroundBookingId(bookingId);
        bookingInfo.setSerial(serial);
        //查询场馆项目信息
        VenueProjectDTO venueProjectDTO = groundBookingDao.getVenueFromProject(groundTicketVO.getVenuesProjectId());
        bookingInfo.setVenuesInfoId(venueProjectDTO.getVenuesInfoId());
        bookingInfo.setVenuesName(venueProjectDTO.getVenueName());
        bookingInfo.setVenuesProjectId(venueProjectDTO.getVenuesProjectId());
        bookingInfo.setProjectName(venueProjectDTO.getProjectName());
        bookingInfo.setUserId(appTokenUser.getId());
        bookingInfo.setNickName(appTokenUser.getName());
        bookingInfo.setIdCardNumber("");
        bookingInfo.setPhoneNumber(groundTicketVO.getPhoneNumber());
        bookingInfo.setOrderType("0");
        Map<String, Object> param = HashMapUtil.beanToMap(bookingInfo);
        setSaveInfo(token,param);
        List<GroundTicketItemVO> itemList = groundTicketVO.getBookingItemList();
        if(itemList !=null && itemList.size() > 0){
            //保存所选预订项
            GroundBookingItemDTO bookingItem = new GroundBookingItemDTO();
            for(GroundTicketItemVO itemInfo : itemList){
                String bookingItemId = UUIDUtil.getUUID();
                bookingItem.setBookingItemId(bookingItemId);
                bookingItem.setBookingInfoId(bookingId);
                bookingItem.setGroundItemId(itemInfo.getGroundItemId());
                bookingItem.setBookingOrderDate(itemInfo.getBookingOrderDate());
                //查询场次时刻信息
                GroundItemDTO groundItemDTO = groundInfoService.getGroundItem(itemInfo.getGroundItemId());
                bookingItem.setGroundName(groundItemDTO.getGroundName());
                bookingItem.setTimeStr(groundItemDTO.getTimeStr());
                bookingItem.setTimeEnd(groundItemDTO.getTimeEnd());
                bookingItem.setPrice(groundItemDTO.getPrice());
                bookingItem.setArriveType("0");
                bookingItem.setOrderType("0");
                Map<String, Object> itemParam = HashMapUtil.beanToMap(bookingItem);
                setSaveInfo(token,itemParam);
                groundBookingDao.saveBookingItem(itemParam);
            }
        }
        groundBookingDao.saveBookingInfo(param);
        return new SuccessResult();
    }

    private String generateSerialNumber(){
        String dateStamp = DateUtil.getDays();
        int randomNum = (int)((Math.random() * 9 + 1) * 100000);
        return dateStamp + randomNum + "";
    }

    @Override
    public SuccessResultList<List<MyTicketListDTO>> listPageMyTicket(String token, ListPage page) {
        AppTokenUser appTokenUser = AppTokenManager.getInstance().getToken(token).getAppTokenUser();
        page.getParams().put("userId",appTokenUser.getId());
        PageHelper.startPage(page.getPage(),page.getRows());
        List<MyTicketListDTO> list = groundBookingDao.listPageMyTicket(page.getParams());
        if(list != null && list.size() > 0){
            Map<String, Object> param = getHashMap(2);
            for (MyTicketListDTO ticket : list){
                ticket.setGmtCreate(ticket.getGmtCreate().substring(0,19));
                param.put("bookingInfoId",ticket.getGroundBookingId());
                param.put("nowDateTime", DateUtil.getTime());
                if(!"1".equals(ticket.getOrderType())){
                    //判断订单是否超时
                    List<GroundItemDTO> lastItemList = groundBookingDao.getItemByDateTime(param);
                    if(lastItemList == null || lastItemList.size() == 0){
                        ticket.setOrderType("2");
                    }
                }
                //预订时段集合
                List<GroundBookingItemDTO> itemList = groundBookingDao.listMyBookingItem(param);
                for(GroundBookingItemDTO myItem : itemList ){
                    if(!"1".equals(myItem.getOrderType())){
                        String nowDataTime = DateUtil.getTime();
                        String lastDateTime = myItem.getBookingOrderDate() + " " + myItem.getTimeEnd();
                        boolean overdue = DateUtil.compareDate(nowDataTime, lastDateTime);
                        if(overdue){
                            myItem.setOrderType("2");
                        }
                    }
                    myItem.setTimeStr(myItem.getTimeStr().substring(0,5));
                    myItem.setTimeEnd(myItem.getTimeEnd().substring(0,5));
                }
                ticket.setItemDTOList(itemList);
            }
        }
        PageInfo<MyTicketListDTO> pageInfo = new PageInfo<>(list);
        return new SuccessResultList<>(list,pageInfo.getPageNum(),pageInfo.getTotal());
    }

    @Override
    public SuccessResultData<MyTicketDetailDTO> getMyTicketDetail(String token, Map<String, Object> params) {
        AppTokenUser appTokenUser = AppTokenManager.getInstance().getToken(token).getAppTokenUser();
        params.put("userId",appTokenUser.getId());
        MyTicketDetailDTO myTicketDetailDTO = new MyTicketDetailDTO();
        //查询订单信息
        GroundBookingInfoDTO bookingInfoDTO = groundBookingDao.getBookingInfo(params);
        myTicketDetailDTO.setBookingInfoId(bookingInfoDTO.getGroundBookingId());
        myTicketDetailDTO.setSerial(bookingInfoDTO.getSerial());
        myTicketDetailDTO.setVenuesName(bookingInfoDTO.getVenuesName());
        myTicketDetailDTO.setProjectName(bookingInfoDTO.getProjectName());
        //查询预订项列表
        params.put("bookingInfoId", bookingInfoDTO.getGroundBookingId());
        List<GroundBookingItemDTO> itemList = groundBookingDao.listMyBookingItem(params);
        for(GroundBookingItemDTO myItem : itemList ){
            if(!"1".equals(myItem.getOrderType())){
                String nowDataTime = DateUtil.getTime();
                String lastDateTime = myItem.getBookingOrderDate() + " " + myItem.getTimeEnd();
                boolean overdue = DateUtil.compareDate(nowDataTime, lastDateTime);
                if(overdue){
                    myItem.setOrderType("2");
                }
            }
            myItem.setTimeStr(myItem.getTimeStr().substring(0,5));
            myItem.setTimeEnd(myItem.getTimeEnd().substring(0,5));
        }
        myTicketDetailDTO.setItemDTOList(itemList);
        return new SuccessResultData<>(myTicketDetailDTO);
    }

    @Override
    public SuccessResult removeMyTicket(String token, String groundBookingId) {
        AppTokenUser appTokenUser = AppTokenManager.getInstance().getToken(token).getAppTokenUser();
        Map<String, Object> params = getHashMap(4);
        params.put("userId",appTokenUser.getId());
        params.put("groundBookingId",groundBookingId);
        setUpdateInfo(token,params);
        //修改订单状态
        groundBookingDao.removeMyTicket(params);
        //修改订单下所有预订场次状态
        groundBookingDao.removeMyItemTicket(params);
        return new SuccessResult();
    }

    @Override
    public SuccessResult removeMyTicketItem(String token,String groundBookingId, String bookingItemId) {
        AppTokenUser appTokenUser = AppTokenManager.getInstance().getToken(token).getAppTokenUser();
        Map<String, Object> params = getHashMap(4);
        params.put("userId",appTokenUser.getId());
        params.put("bookingItemId",bookingItemId);
        setUpdateInfo(token,params);
        groundBookingDao.removeMyTicketItem(params);
        return new SuccessResult();
    }
}
