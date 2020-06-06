package com.cm.venuebooking.service.groundbooking.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cm.common.exception.SaveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.common.token.app.AppTokenManager;
import com.cm.common.token.app.entity.AppTokenUser;
import com.cm.common.utils.HashMapUtil;
import com.cm.common.utils.UUIDUtil;
import com.cm.venuebooking.dao.groundbooking.IGroundBookingDao;
import com.cm.venuebooking.pojo.dtos.groundbooking.GroundBookingInfoDTO;
import com.cm.venuebooking.pojo.dtos.groundbooking.GroundBookingInfoListDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundConfigDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundConfigSplitDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundInfoDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundInfoIncConfigListDTO;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.pojo.vos.groundbooking.GroundBookingInfoVO;
import com.cm.venuebooking.service.BaseService;
import com.cm.venuebooking.service.groundbooking.IGroundBookingService;
import com.cm.venuebooking.service.groundconfig.IGroundConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import springfox.documentation.service.ApiListing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xwangs
 * @create 2020-05-01 18:28
 * @description
 */
@Service
public class GroundBookingServiceImpl extends BaseService implements IGroundBookingService {

    @Autowired
    private IGroundBookingDao groundBookingDao;

    @Autowired
    private IGroundConfigService groundConfigService;

    @Override
    public Object getTreeGroundInfo(Map<String, Object> param) throws SearchException {
        Map<String, Object> nodeData = new HashMap<>();
        nodeData.put("id","0");
        nodeData.put("pId","-1");
        nodeData.put("name","选择场馆场地");
        nodeData.put("isGround","0");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(nodeData);
        //场馆
        List<Map<String, Object>> venueList = groundBookingDao.listVenues(param);
        for (Map<String, Object> item : venueList) {
            nodeData = new HashMap<>(0);
            nodeData.put("id",item.get("venues_info_id").toString());
            nodeData.put("pId","0");
            nodeData.put("name",item.get("venue_name").toString());
            nodeData.put("isGround","0");
            list.add(nodeData);
            //项目
            List<Map<String,Object>> projectList = groundBookingDao.listProjectByVenue(item);
            if(projectList != null && projectList.size() > 0 ){
                for (Map<String,Object> projectItem : projectList ){
                    nodeData = new HashMap<>();
                    nodeData.put("id",projectItem.get("venues_project_id").toString());
                    nodeData.put("pId",projectItem.get("venue_id").toString());
                    nodeData.put("name",projectItem.get("project_name").toString());
                    nodeData.put("isGround","0");
                    list.add(nodeData);
                    //场地
                    List<Map<String,Object>> groundList = groundBookingDao.listGroundByProject(projectItem);
                    if(groundList != null && groundList.size() > 0){
                        for (Map<String,Object> groundItem : groundList){
                            nodeData = new HashMap<>();
                            nodeData.put("id",groundItem.get("ground_info_id").toString());
                            nodeData.put("pId",groundItem.get("venues_project_id").toString());
                            nodeData.put("name",groundItem.get("ground_name").toString());
                            nodeData.put("isGround","1");
                            list.add(nodeData);
                        }
                    }
                }
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("zTreeList",list);
        return result;
    }

    @Override
    public Object listGroundBooking(Map<String, Object> reqParams) throws SearchException {
        Map<String,Object> result = new HashMap<>(0);
        String groundInfoId = StringUtils.isEmpty(reqParams.get("groundInfoId")) ?
                "" : reqParams.get("groundInfoId").toString();
        if("".equals(groundInfoId)){
            return new SuccessResultList<>();
        }
        String bookingOrderDate = StringUtils.isEmpty(reqParams.get("bookingOrderDate")) ?
                "" : reqParams.get("bookingOrderDate").toString();
        String week = getWeekByDate(bookingOrderDate);
        //找到对应的配置信息
        reqParams.put("week",week);
        GroundConfigDTO groundConfigDTO = groundBookingDao.getGroundConfig(reqParams);
        //根据配置信息分割场次
        List<GroundConfigSplitDTO> groundList = splitGroundConfigInfos(groundConfigDTO);
        result.put("list",groundList);
        return result;
    }

    @Override
    public SuccessResult saveBookingInfo(Map<String, Object> params) throws SaveException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String groundBookingId = UUIDUtil.getUUID();
        try {
            Date bookingOrderDate = sf.parse(params.get("bookingOrderDate").toString());
            params.put("bookingOrderDate",sf.format(bookingOrderDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        params.put("groundBookingId",groundBookingId);
        params.put("idCardNumber","");
        setSaveInfo(params);
        groundBookingDao.saveBookingInfo(params);
        return new SuccessResult();
    }

    @Override
    public SuccessResultData listBookingByDate(Map<String, Object> params) throws SearchException {
        String venuesProjectId = StringUtils.isEmpty(params.get("venuesProjectId")) ?
                "" : params.get("venuesProjectId").toString();
        String bookingOrderDate = StringUtils.isEmpty(params.get("bookingOrderDate")) ?
                "" : params.get("bookingOrderDate").toString();
        if ("".equals(venuesProjectId)) {
            return new SuccessResultData<>(new ArrayList<>());
        }
        if ("".equals(bookingOrderDate)) {
            return new SuccessResultData(new ArrayList<>());
        }
        String week = getWeekByDate(bookingOrderDate);
        //查询所有场地
        List<GroundInfoDTO> groundInfoList = groundConfigService.listGroundInfoByProject(params);
        List<GroundInfoIncConfigListDTO> resultList = new ArrayList<>();
        for (GroundInfoDTO item : groundInfoList){
            GroundInfoIncConfigListDTO groundInfoIncConfigListDTO = new GroundInfoIncConfigListDTO();
            //查询场地对应周的场次安排
            params.put("groundInfoId", item.getGroundInfoId());
            params.put("week",week);
            GroundConfigDTO groundConfigDTO = groundBookingDao.getGroundConfig(params);
            //根据配置信息分割场次
            List<GroundConfigSplitDTO> groundList = splitGroundConfigInfos(groundConfigDTO);
            groundInfoIncConfigListDTO.setVenuesProjectId(item.getVenuesProjectId());
            groundInfoIncConfigListDTO.setGroundInfoId(item.getGroundInfoId());
            groundInfoIncConfigListDTO.setGroundName(item.getGroundName());
            groundInfoIncConfigListDTO.setGroundName(item.getGroundName());
            groundInfoIncConfigListDTO.setConfigSplitDTOS(groundList);
            resultList.add(groundInfoIncConfigListDTO);
        }
        return new SuccessResultData(resultList);
    }

    @Override
    public void saveBookingItems(String token, JSONObject param) throws Exception {
        JSONObject obj = param.getJSONObject("submitInfo");
        String bookingOrderDate = StringUtils.isEmpty(obj.get("date")) ?
                "" : obj.getString("date");
        AppTokenUser appTokenUser = AppTokenManager.getInstance().getToken(token).getAppTokenUser();
        if("".equals(bookingOrderDate)){
            throw new SaveException("保存失败");
        }
        JSONArray array = obj.getJSONArray("resultList");
        if(array == null || array.size() <= 0){
            throw new SaveException("保存失败");
        }
        String groundBookingId = UUIDUtil.getUUID();
        GroundBookingInfoVO bookingInfo = new GroundBookingInfoVO();
        for (int i = 0; i < array.size(); i++) {
            JSONObject item = array.getJSONObject(i);
            bookingInfo.setGroundInfoId(item.getString("ground"));
            bookingInfo.setUserName(appTokenUser.getName());
            bookingInfo.setIdCardNumber("");
            bookingInfo.setPhoneNumber(appTokenUser.getPhone());
            bookingInfo.setBookingOrderDate(bookingOrderDate);
            bookingInfo.setTimeStr(item.getString("start") + ":00");
            bookingInfo.setTimeEnd(item.getString("end") + ":00");
            bookingInfo.setSinglePrice(item.getString("price"));
            Map<String, Object> beanMap = HashMapUtil.beanToMap(bookingInfo);
            beanMap.put("groundBookingId",groundBookingId);
            setSaveInfo(token,beanMap);
            groundBookingDao.saveBookingInfo(beanMap);
        }
    }

    @Override
    public SuccessResultData listMyBookingOrder(String token) throws SearchException {
        AppTokenUser appTokenUser = AppTokenManager.getInstance().getToken(token).getAppTokenUser();
        Map<String, Object> param = getHashMap(0);
        param.put("phoneNumber",appTokenUser.getPhone());
        List<GroundBookingInfoListDTO> list = groundBookingDao.listMyBookingOrder(param);
        for (GroundBookingInfoListDTO item : list) {
            item.setTimeStr(item.getTimeStr().substring(0,5));
            item.setTimeEnd(item.getTimeEnd().substring(0,5));
        }
        return new SuccessResultData(list);
    }

    @Override
    public SuccessResultList<List<GroundBookingInfoListDTO>> listPageBookingOrder(ListPage page) throws SearchException {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<GroundBookingInfoListDTO> list = groundBookingDao.listPageBookingOrder(page.getParams());
        PageInfo<GroundBookingInfoListDTO> pageInfo = new PageInfo<>(list);
        return new SuccessResultList<>(list,pageInfo.getPageNum(), pageInfo.getTotal());
    }

    private String getWeekByDate(String dateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String week;
        try {
            Date queryDate;
            queryDate = format.parse(dateStr);
            String[] weeks = {"Sun","Mon","Tue","Wed","Thu","Fir","Sat"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(queryDate);
            int weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if(weekIndex<0){
                weekIndex = 0;
            }
            week = weeks[weekIndex];
        } catch (Exception e){
            return "";
        }
        return week;
    }

    private List<GroundConfigSplitDTO> splitGroundConfigInfos(GroundConfigDTO groundConfigDTO){
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        sf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        List<GroundConfigSplitDTO> configList = new ArrayList<>();
        //上午场次
        String[] amTimeStr = groundConfigDTO.getTimeAMStr().split(":");
        String[] amTimeEnd = groundConfigDTO.getTimeAMEnd().split(":");
        long amTimeStrSec = Integer.parseInt(amTimeStr[0]) * 3600
                + Integer.parseInt(amTimeStr[1]) * 60
                + Integer.parseInt(amTimeStr[2]);
        long amTimeEndSec = Integer.parseInt(amTimeEnd[0]) * 3600
                + Integer.parseInt(amTimeEnd[1]) * 60
                + Integer.parseInt(amTimeEnd[2]);
        Long timeLength = Long.parseLong(groundConfigDTO.getLengthTime()) * 3600;
        long nowTimeNode = amTimeStrSec;
        long nextTimeNode = nowTimeNode + timeLength;
        while (amTimeEndSec >= nextTimeNode){
            GroundConfigSplitDTO groundConfigSplitDTO = new GroundConfigSplitDTO();
            groundConfigSplitDTO.setTimeStr(sf.format(nowTimeNode * 1000));
            groundConfigSplitDTO.setTimeEnd(sf.format(nextTimeNode * 1000));
            groundConfigSplitDTO.setSinglePrice(groundConfigDTO.getSinglePrice());
            groundConfigSplitDTO.setBookingType("true");
            configList.add(groundConfigSplitDTO);
            nowTimeNode = nextTimeNode;
            nextTimeNode +=  timeLength;
        }
        //下午场次
        String[] pmTimeStr = groundConfigDTO.getTimePMStr().split(":");
        String[] pmTimeEnd = groundConfigDTO.getTimePMEnd().split(":");
        long pmTimeStrSec = Integer.parseInt(pmTimeStr[0]) * 3600
                + Integer.parseInt(pmTimeStr[1]) * 60
                + Integer.parseInt(pmTimeStr[2]);
        long pmTimeEndSec = Integer.parseInt(pmTimeEnd[0]) * 3600
                + Integer.parseInt(pmTimeEnd[1]) * 60
                + Integer.parseInt(pmTimeEnd[2]);
        nowTimeNode = pmTimeStrSec;
        nextTimeNode = nowTimeNode + timeLength;
        while (pmTimeEndSec >= nextTimeNode){
            GroundConfigSplitDTO groundConfigSplitDTO = new GroundConfigSplitDTO();
            groundConfigSplitDTO.setTimeStr(sf.format(nowTimeNode * 1000));
            groundConfigSplitDTO.setTimeEnd(sf.format(nextTimeNode * 1000));
            groundConfigSplitDTO.setSinglePrice(groundConfigDTO.getSinglePrice());
            groundConfigSplitDTO.setBookingType("true");
            groundConfigSplitDTO.setStatus("");
            configList.add(groundConfigSplitDTO);
            nowTimeNode = nextTimeNode;
            nextTimeNode +=  timeLength;
        }
        return configList;
    }
}
