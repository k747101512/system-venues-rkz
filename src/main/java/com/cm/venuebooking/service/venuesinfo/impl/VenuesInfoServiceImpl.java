package com.cm.venuebooking.service.venuesinfo.impl;

import com.cm.common.component.SecurityComponent;
import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.pojo.dtos.ZTreeDTO;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.common.utils.DateUtil;
import com.cm.common.utils.HashMapUtil;
import com.cm.common.utils.UUIDUtil;
import com.cm.common.utils.point.Point;
import com.cm.common.utils.point.PointUtil;
import com.cm.venuebooking.dao.venuesinfo.IVenuesInfoDao;
import com.cm.venuebooking.dao.venuesproject.IVenuesProjectDao;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.pojo.dtos.venuesproject.VenuesProjectDTO;
import com.cm.venuebooking.pojo.vos.venuesinfo.VenuesInfoVO;
import com.cm.venuebooking.service.BaseService;
import com.cm.venuebooking.service.venuesinfo.IVenuesInfoService;
import com.cm.venuebooking.singledata.VenuesListSingleData;
import com.cm.venuebooking.utils.MapLocationTransformUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: VenuesInfoServiceImpl
 * @Description: 场馆信息表
 * @Author: WenG
 * @Date: 2020-04-22 11:46
 * @Version: 1.0
 **/
@Service
public class VenuesInfoServiceImpl extends BaseService implements IVenuesInfoService {

    private static final String VENUE_SEARCH_HOT = "hot";
    private static final String VENUE_SEARCH_APART = "apart";
    private static final String VENUE_SEARCH_LONGITUDE = "longitude";
    private static final String VENUE_SEARCH_LATITUDE = "latitude";
    private static final String MAP_TYPE_TX = "TX";

    @Autowired
    private IVenuesInfoDao venuesInfoDao;

    @Autowired
    private IVenuesProjectDao venuesProjectDao;

    @Autowired
    private SecurityComponent securityComponent;

    @Override
    public SuccessResult saveVenuesInfo(VenuesInfoVO venuesInfoVO) throws Exception {
        saveVenuesInfoInfo(null, venuesInfoVO);
        return new SuccessResult();
    }

    @Override
    public SuccessResult saveVenuesInfoByToken(String token, VenuesInfoVO venuesInfoVO) throws Exception {
        saveVenuesInfoInfo(token, venuesInfoVO);
        return new SuccessResult();
    }

    /**
     * 新增场馆信息表
     *
     * @param token
     * @param venuesInfoVO
     * @throws Exception
     */
    private void saveVenuesInfoInfo(String token, VenuesInfoVO venuesInfoVO) throws Exception {
        Map<String, Object> params = HashMapUtil.beanToMap(venuesInfoVO);
        params.put("venuesInfoId", UUIDUtil.getUUID());
        if (token != null) {
            setSaveInfo(token, params);
        } else {
            setSaveInfo(params);
        }
        VenuesListSingleData.getInstance().setVenuesList(null);
        venuesInfoDao.saveVenuesInfo(params);
    }

    @Override
    public SuccessResult removeVenuesInfo(String ids) throws RemoveException {
        removeVenuesInfoInfo(null, ids);
        return new SuccessResult();
    }

    @Override
    public SuccessResult removeVenuesInfoByToken(String token, String ids) throws RemoveException {
        removeVenuesInfoInfo(token, ids);
        return new SuccessResult();
    }

    /**
     * 删除场馆信息表
     *
     * @param token
     * @param ids
     */
    private void removeVenuesInfoInfo(String token, String ids) {
        Map<String, Object> params = getHashMap(3);
        params.put("venuesInfoIds", Arrays.asList(ids.split("_")));
        if (token != null) {
            setUpdateInfo(token, params);
        } else {
            setUpdateInfo(params);
        }
        venuesInfoDao.removeVenuesInfo(params);
        VenuesListSingleData.getInstance().setVenuesList(null);
    }

    @Override
    public SuccessResult updateVenuesInfo(String venuesInfoId, VenuesInfoVO venuesInfoVO) throws Exception {
        updateVenuesInfoInfo(null, venuesInfoId, venuesInfoVO);
        return new SuccessResult();
    }

    @Override
    public SuccessResult updateVenuesInfoByToken(String token, String venuesInfoId, VenuesInfoVO venuesInfoVO) throws Exception {
        updateVenuesInfoInfo(token, venuesInfoId, venuesInfoVO);
        return new SuccessResult();
    }

    /**
     * 修改场馆信息表
     *
     * @param token
     * @param venuesInfoId
     * @param venuesInfoVO
     */
    private void updateVenuesInfoInfo(String token, String venuesInfoId, VenuesInfoVO venuesInfoVO) throws Exception {
        Map<String, Object> params = HashMapUtil.beanToMap(venuesInfoVO);
        params.put("venuesInfoId", venuesInfoId);
        if (token != null) {
            setUpdateInfo(token, params);
        } else {
            setUpdateInfo(params);
        }
        venuesInfoDao.updateVenuesInfo(params);
        VenuesListSingleData.getInstance().setVenuesList(null);
    }

    @Override
    public VenuesInfoDTO getVenuesInfoById(String venuesInfoId) throws SearchException {
        Map<String, Object> params = super.getHashMap(1);
        params.put("venuesInfoId", venuesInfoId);
        return venuesInfoDao.getVenuesInfo(params);
    }

    @Override
    public List<VenuesInfoDTO> listVenuesInfo(Map<String, Object> params) throws SearchException {
        setDataAuthorityInfo(params);
        params.put("creator",securityComponent.getCurrentUser().getUserId());
        return venuesInfoDao.listVenuesInfo(params);
    }

    @Override
    public SuccessResultList<List<VenuesInfoDTO>> listPageVenuesInfo(ListPage page) throws SearchException {
        setDataAuthorityInfo(page.getParams());
        String creator = securityComponent.getCurrentUser().getUserId();
        page.getParams().put("creator",creator);
        PageHelper.startPage(page.getPage(), page.getRows());
        List<VenuesInfoDTO> venuesInfoDTOs = venuesInfoDao.listVenuesInfo(page.getParams());
        PageInfo<VenuesInfoDTO> pageInfo = new PageInfo<>(venuesInfoDTOs);
        return new SuccessResultList<>(venuesInfoDTOs, pageInfo.getPageNum(), pageInfo.getTotal());
    }

    @Override
    public List<VenuesInfoDTO> listPageVenuesInfoResources(ListPage page) {
        PageHelper.offsetPage(page.getPage(),page.getRows());
        List<VenuesInfoDTO> venuesInfoDTOs = venuesInfoDao.listVenuesInfo(page.getParams());
        PageInfo<VenuesInfoDTO> pageInfo = new PageInfo<>(venuesInfoDTOs);
        return venuesInfoDTOs;
    }

    @Override
    public SuccessResultData listVenuesByKeyWords(String token, Map<String, Object> params) throws SearchException {
        String projectCategory = StringUtils.isEmpty(params.get("categoryId")) ? "" : params.get("categoryId").toString();
        String venueCity = StringUtils.isEmpty(params.get("cityId")) ? "" : params.get("cityId").toString();
        String venueArea = StringUtils.isEmpty(params.get("areaId")) ? "" : params.get("areaId").toString();
        params.put("projectCategory",projectCategory);
        params.put("venueCity",venueCity);
        params.put("venueArea",venueArea);
        List<VenuesInfoDTO> list = venuesInfoDao.listVenuesByKeyWords(params);
        for (VenuesInfoDTO item : list){
            item.setVenuePanorama(item.getVenuePanorama().split(",")[0]);
        }
        return new SuccessResultData(list);
    }

    @Override
    public Object getVenuesDetailById(String token, Map<String, Object> params) throws SearchException {
        Map<String,Object> result = getHashMap(0);
        if(StringUtils.isEmpty(params.get("venuesInfoId"))){
            return result;
        }
        //场馆列表
        Map<String,Object> venuesInfo = venuesInfoDao.getVenuesInfoForWeChatProgram(params);
        if (venuesInfo != null && venuesInfo.size() > 0){
            venuesInfo.put("venueBanner",venuesInfo.get("venueBanner").toString().replaceAll(",",","));
        }
        result.put("venueInfo",venuesInfo);
        //所有项目
        List<Map<String,Object>> projectList = venuesProjectDao.listVenuesProjectForWeChatProgram(params);
        result.put("projectList",projectList);
        return result;
    }

    @Override
    public Map<String, Object> getVenuesProjectDetailById(String token, Map<String, Object> params) {
        Map<String,Object> result = getHashMap(0);
        if(StringUtils.isEmpty(params.get("venuesProjectId"))){
            return result;
        }
        VenuesProjectDTO venuesProjectDTO = venuesProjectDao.getVenuesProjectDetailById(params);
        result.put("venuesProjectInfo",venuesProjectDTO);
        //返回近三天的预定状态
        List<Map<String, Object>> dayList = new ArrayList<>(0);
        SimpleDateFormat sf = new SimpleDateFormat("M月d日");
        String[] dayGroup = new String[]{DateUtil.getTime(),DateUtil.getAfterDayDate("1"),DateUtil.getAfterDayDate("2")};
        for (int i = 0; i < dayGroup.length;i++) {
            Map<String, Object> dayMap = getHashMap(0);
            dayMap.put("week",DateUtil.getAfterDayWeek(i + ""));
            dayMap.put("dateFormat",sf.format(DateUtil.fomatDate(dayGroup[i])));
            dayMap.put("date",dayGroup[i].substring(0,10));
            //判断是否可以预订
            dayMap.put("bookingType","true");
            if(i == 0){
                dayMap.put("week","今天");
            }
            dayList.add(dayMap);
        }
        result.put("dayList",dayList);
        return result;
    }

    @Override
    public Object getCityInfoByName(Map<String, Object> param) throws SearchException {
        String cityName = StringUtils.isEmpty(param.get("cityName")) ? "" :  param.get("cityName").toString();
        param.put("cityName",cityName);
        return venuesInfoDao.getCityInfoByName(param);
    }

    @Override
    public SuccessResultList<List<VenuesInfoDTO>> listPageVenuesInfoFroApp(String token,ListPage page) throws SearchException {
        Map<String, Object> params = page.getParams();
        List<VenuesInfoDTO> venuesInfoDTOs;
        String orderKye = StringUtils.isEmpty(params.get("orderKey"))? "" : params.get("orderKey").toString();
        orderKye = orderKye.trim();
        Point myPoint = setPoint(params);
        //如果传入的定位来自于腾讯地图，需要转换，否则会导致距离计算出现1公里左右偏差
        String selfLocation = StringUtils.isEmpty(params.get("selfLocation")) ? "" : params.get("selfLocation").toString();
        if(MAP_TYPE_TX.equals(selfLocation)
                && myPoint.getX() == 0
                && myPoint.getY() == 0){
            MapLocationTransformUtil util = new MapLocationTransformUtil();
            myPoint = util.map_tx2bd(myPoint);
        }
        //按热度查询
        if(VENUE_SEARCH_HOT.equals(orderKye)){
            //TODO 访问量功能实现后添加
        }
        //根据定位排序
        if(VENUE_SEARCH_APART.equals(orderKye)){
            VenuesListSingleData venuesListSingleData = VenuesListSingleData.getInstance();
            venuesInfoDTOs = venuesListSingleData.getVenuesList(venuesInfoDao);
            formatPosition(myPoint,venuesInfoDTOs);
            pointListCompareToAse(venuesInfoDTOs);
            List<VenuesInfoDTO> subList = venuesInfoDTOs.subList(0,venuesInfoDTOs.size() > 10 ? 10 : venuesInfoDTOs.size());
            return new SuccessResultList<>(subList, page.getPage(), Long.parseLong(subList.size()+ ""));
        }
        PageHelper.startPage(page.getPage(), page.getRows());
        venuesInfoDTOs = venuesInfoDao.listVenuesByKeyWords(page.getParams());
        formatPosition(myPoint,venuesInfoDTOs);
        PageInfo<VenuesInfoDTO> pageInfo = new PageInfo<>(venuesInfoDTOs);
        return new SuccessResultList<>(venuesInfoDTOs, pageInfo.getPageNum(), pageInfo.getTotal());
    }

    @Override
    public SuccessResultData<List<VenuesInfoDTO>> listRangeVenuesInfo(Map<String, Object> params) {
        VenuesListSingleData venuesListSingleData = VenuesListSingleData.getInstance();
        List<VenuesInfoDTO> venuesInfoDTOs = venuesListSingleData.getVenuesList(venuesInfoDao);
        Point myPoint = setPoint(params);
        formatPosition(myPoint, venuesInfoDTOs);
        List<VenuesInfoDTO> screeningResult = new ArrayList<>(16);
        double range = Double.parseDouble(params.get("range").toString());
        double apart;
        if(venuesInfoDTOs != null && venuesInfoDTOs.size() > 0){
            for (VenuesInfoDTO item : venuesInfoDTOs){
                apart = Double.parseDouble(item.getApart());
                if(apart <= range){
                    screeningResult.add(item);
                }
            }
        }
        return new SuccessResultData(screeningResult);
    }

    /**
     * 计算距离
     * @param list
     */
    private void formatPosition(Point myPoint, List<VenuesInfoDTO> list){
        Point point = new Point();
        for (VenuesInfoDTO item : list){
            if(myPoint.getX() == 0 && myPoint.getY() == 0){
                item.setApart("0");
                continue;
            }
            if(StringUtils.isEmpty(item.getLongitude())
                    && StringUtils.isEmpty(item.getLatitude())){
                item.setApart("0");
                continue;
            }
            point.setX(Double.parseDouble(item.getLatitude()));
            point.setY(Double.parseDouble(item.getLongitude()));
            double lineApart = PointUtil.getDistance(myPoint,point);
            BigDecimal bg = new BigDecimal(lineApart);
            item.setApart(bg.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        }
    }

    /**
     * 距离排序
     * @param list
     */
    private void pointListCompareToAse(List<VenuesInfoDTO> list){
        Collections.sort(list, new Comparator<VenuesInfoDTO>() {
            @Override
            public int compare(VenuesInfoDTO o1, VenuesInfoDTO o2) {
                double apart1 = Double.parseDouble(o1.getApart());
                double apart2 = Double.parseDouble(o2.getApart());
                if(apart1 == apart2){
                    return 0;
                }
                return apart1 > apart2 ? 1 : -1;
            }
        });
    }

    private Point setPoint(Map<String, Object> params){
        double longitude = StringUtils.isEmpty(params.get(VENUE_SEARCH_LONGITUDE)) ?
                0 : Double.parseDouble(params.get(VENUE_SEARCH_LONGITUDE).toString());
        double latitude = StringUtils.isEmpty(params.get(VENUE_SEARCH_LATITUDE)) ?
                0 : Double.parseDouble(params.get(VENUE_SEARCH_LATITUDE).toString());
        Point Point = new Point();
        Point.setX(latitude);
        Point.setY(longitude);
        return Point;
    }

    /**
     * 后台-场馆zTree
     * 有数据权限校验
     * @return 返回场馆zTree集合
     */
    @Override
    public List<ZTreeDTO> listVenuesInfoZTree() {
        Map<String, Object> params = getHashMap(16);
        setDataAuthorityInfo(params);
        params.put("creator",securityComponent.getCurrentUser().getUserId());
        List<VenuesInfoDTO> venuesInfoDTO = venuesInfoDao.listVenuesInfo(params);
        List<ZTreeDTO> zTreeDTOs = new ArrayList<>();
        for (VenuesInfoDTO item : venuesInfoDTO){
            ZTreeDTO zTreeDTO = new ZTreeDTO();
            zTreeDTO.setName(item.getVenueName());
            if("arrive".equals(item.getVenueCharge())){
                zTreeDTO.setName(item.getVenueName() + "(直接到场)");
            }
            if("booking".equals(item.getVenueCharge())){
                zTreeDTO.setName(item.getVenueName() + "(场次预订)");
            }
            if("ticket".equals(item.getVenueCharge())){
                zTreeDTO.setName(item.getVenueName() + "(门票预订)");
            }
            zTreeDTO.setpId("0");
            zTreeDTO.setId(item.getVenuesInfoId());
            zTreeDTOs.add(zTreeDTO);
        }
        return zTreeDTOs;
    }

    @Override
    public VenuesInfoDTO getVenuesInfoByIdForApp(String token, Map<String, Object> param) throws SearchException {
        VenuesInfoDTO venuesInfoDTO = venuesInfoDao.getVenuesInfoForApp(param);
        String resultLocation = StringUtils.isEmpty(param.get("resultLocation")) ? "" : param.get("resultLocation").toString();
        //返回的场馆定位需要转换为腾讯地图坐标
        if(MAP_TYPE_TX.equals(resultLocation)){
            Point point = new Point();
            MapLocationTransformUtil util = new MapLocationTransformUtil();
            point.setX(Double.parseDouble(venuesInfoDTO.getLatitude()));
            point.setY(Double.parseDouble(venuesInfoDTO.getLongitude()));
            point = util.map_bd2tx(point);
            venuesInfoDTO.setLatitude(point.getX() + "");
            venuesInfoDTO.setLongitude(point.getY() + "");
        }
        return venuesInfoDTO;
    }
}
