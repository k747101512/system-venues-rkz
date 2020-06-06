package com.cm.venuebooking.service.groundconfig.impl;

import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SearchException;
import com.cm.common.exception.UpdateException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultList;
import com.cm.common.utils.HashMapUtil;
import com.cm.common.utils.UUIDUtil;
import com.cm.venuebooking.dao.groundconfig.IGroundConfigDao;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundConfigDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundInfoDTO;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.pojo.vos.groundconfig.GroundInfoVO;
import com.cm.venuebooking.service.BaseService;
import com.cm.venuebooking.service.groundconfig.IGroundConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xwangs
 * @create 2020-05-01 18:28
 * @description
 */
@Service
public class GroundConfigServiceImpl extends BaseService implements IGroundConfigService {

    @Autowired
    private IGroundConfigDao groundConfigDao;

    @Override
    public SuccessResult saveGroundConfig(Map<String,Object> reqParams) throws Exception {
        //保存场地
        String groundInfoId = UUIDUtil.getUUID();
        reqParams.put("groundInfoId", groundInfoId);
        //open close
        reqParams.put("openCloseType","open");
        setSaveInfo(reqParams);
        groundConfigDao.saveGroundInfo(reqParams);
        //初始化场地配置文件
        saveGroundConfigInit(reqParams);
        return new SuccessResult();
    }

    @Override
    public SuccessResultList<List<GroundInfoDTO>> listPageGroundInfo(ListPage page) throws SearchException {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<GroundInfoDTO> groundInfoDTOS = groundConfigDao.listGroundInfo(page.getParams());
        PageInfo<GroundInfoDTO> pageInfo = new PageInfo<>(groundInfoDTOS);
        return new SuccessResultList<>(groundInfoDTOS, pageInfo.getPageNum(), pageInfo.getTotal());
    }

    @Override
    public SuccessResult updateGroundInfo(String groundInfoId, GroundInfoVO groundInfoVO) throws Exception {
        Map<String, Object> params = HashMapUtil.beanToMap(groundInfoVO);
        params.put("groundInfoId",groundInfoId);
        setUpdateInfo(params);
        groundConfigDao.updateGroundInfo(params);
        return new SuccessResult();
    }

    @Override
    public GroundInfoDTO getGroundInfoById(String groundInfoId) throws SearchException {
        Map<String, Object> params = super.getHashMap(1);
        params.put("groundInfoId",groundInfoId);
        return groundConfigDao.getGroundInfo(params);
    }

    @Override
    public List<GroundInfoDTO> listGroundInfoByProject(Map<String, Object> params) throws RemoveException {
        return groundConfigDao.listGroundInfo(params);
    }

    @Override
    public SuccessResult removeGroundInfo(String ids) {
        Map<String, Object> params = getHashMap(3);
        params.put("groundInfoIds", Arrays.asList(ids.split("_")));
        setUpdateInfo(params);
        groundConfigDao.removeGroundInfo(params);
        return new SuccessResult();
    }

    @Override
    public List<GroundConfigDTO> getGroundConfig(String groundInfoId) throws SearchException {
        Map<String, Object> params = super.getHashMap(1);
        params.put("groundInfoId",groundInfoId);
        return groundConfigDao.getGroundConfig(params);
    }

    @Override
    public SuccessResult updateGroundDailyInfo(Map<String, Object> reqParams) throws Exception {
        //删除之前的配置clean
        String ids = StringUtils.isEmpty(reqParams.get("groundInfoId")) ? "" : reqParams.get("groundInfoId").toString();
        removeGroundDailyInfo(ids);
        saveGroundConfigInit(reqParams);
        return new SuccessResult();
    }

    @Override
    public SuccessResult removeGroundDailyInfo(String ids) throws RemoveException {
        Map<String, Object> params = getHashMap(3);
        params.put("groundInfoIds", Arrays.asList(ids.split("_")));
        groundConfigDao.removeGroundDailyInfo(params);
        return new SuccessResult();
    }

    public void saveGroundConfigInit(Map<String,Object> param) throws Exception{
        GroundConfigDTO groundConfigDTO = new GroundConfigDTO();
        groundConfigDTO.setGroundInfoId(param.get("groundInfoId").toString());
        groundConfigDTO.setVenuesProjectId(param.get("venuesProjectId").toString());
        Map<String, Object> params;
        //Mon.
        groundConfigDTO.setGroundConfigId(UUIDUtil.getUUID());
        groundConfigDTO.setWeek("Mon");
        groundConfigDTO.setOrderIndex("1");
        groundConfigDTO.setTimeAMStr(StringUtils.isEmpty(param.get("timeAMStrMon")) ? "08:00:00" : param.get("timeAMStrMon").toString());
        groundConfigDTO.setTimeAMEnd(StringUtils.isEmpty(param.get("timeAMEndMon")) ? "12:00:00" : param.get("timeAMEndMon").toString());
        groundConfigDTO.setTimePMStr(StringUtils.isEmpty(param.get("timePMStrMon")) ? "14:00:00" : param.get("timePMStrMon").toString());
        groundConfigDTO.setTimePMEnd(StringUtils.isEmpty(param.get("timePMEndMon")) ? "18:00:00" : param.get("timePMEndMon").toString());
        groundConfigDTO.setLengthTime(StringUtils.isEmpty(param.get("lengthTimeMon")) ? "1" : param.get("lengthTimeMon").toString());
        groundConfigDTO.setRestTime(StringUtils.isEmpty(param.get("restTimeMon")) ? "" : param.get("restTimeMon").toString());
        groundConfigDTO.setSinglePrice(StringUtils.isEmpty(param.get("singlePriceMon")) ? "0" : param.get("singlePriceMon").toString());
        params = HashMapUtil.beanToMap(groundConfigDTO);
        setSaveInfo(params);
        groundConfigDao.saveGroundConfig(params);
        //Tue.
        groundConfigDTO.setGroundConfigId(UUIDUtil.getUUID());
        groundConfigDTO.setWeek("Tue");
        groundConfigDTO.setOrderIndex("2");
        groundConfigDTO.setTimeAMStr(StringUtils.isEmpty(param.get("timeAMStrTue")) ? "08:00:00" : param.get("timeAMStrTue").toString());
        groundConfigDTO.setTimeAMEnd(StringUtils.isEmpty(param.get("timeAMEndTue")) ? "12:00:00" : param.get("timeAMEndTue").toString());
        groundConfigDTO.setTimePMStr(StringUtils.isEmpty(param.get("timePMStrTue")) ? "14:00:00" : param.get("timePMStrTue").toString());
        groundConfigDTO.setTimePMEnd(StringUtils.isEmpty(param.get("timePMEndTue")) ? "18:00:00" : param.get("timePMEndTue").toString());
        groundConfigDTO.setLengthTime(StringUtils.isEmpty(param.get("lengthTimeTue")) ? "1" : param.get("lengthTimeTue").toString());
        groundConfigDTO.setRestTime(StringUtils.isEmpty(param.get("restTimeTue")) ? "" : param.get("restTimeTue").toString());
        groundConfigDTO.setSinglePrice(StringUtils.isEmpty(param.get("singlePriceTue")) ? "0" : param.get("singlePriceTue").toString());
        params = HashMapUtil.beanToMap(groundConfigDTO);
        setSaveInfo(params);
        groundConfigDao.saveGroundConfig(params);
        //Wed.
        groundConfigDTO.setGroundConfigId(UUIDUtil.getUUID());
        groundConfigDTO.setWeek("Wed");
        groundConfigDTO.setOrderIndex("3");
        groundConfigDTO.setTimeAMStr(StringUtils.isEmpty(param.get("timeAMStrWed")) ? "08:00:00" : param.get("timeAMStrWed").toString());
        groundConfigDTO.setTimeAMEnd(StringUtils.isEmpty(param.get("timeAMEndWed")) ? "12:00:00" : param.get("timeAMEndWed").toString());
        groundConfigDTO.setTimePMStr(StringUtils.isEmpty(param.get("timePMStrWed")) ? "14:00:00" : param.get("timePMStrWed").toString());
        groundConfigDTO.setTimePMEnd(StringUtils.isEmpty(param.get("timePMEndWed")) ? "18:00:00" : param.get("timePMEndWed").toString());
        groundConfigDTO.setLengthTime(StringUtils.isEmpty(param.get("lengthTimeWed")) ? "1" : param.get("lengthTimeWed").toString());
        groundConfigDTO.setRestTime(StringUtils.isEmpty(param.get("restTimeWed")) ? "" : param.get("restTimeWed").toString());
        groundConfigDTO.setSinglePrice(StringUtils.isEmpty(param.get("singlePriceWed")) ? "0" : param.get("singlePriceWed").toString());
        params = HashMapUtil.beanToMap(groundConfigDTO);
        setSaveInfo(params);
        groundConfigDao.saveGroundConfig(params);
        //Thu.
        groundConfigDTO.setGroundConfigId(UUIDUtil.getUUID());
        groundConfigDTO.setWeek("Thu");
        groundConfigDTO.setOrderIndex("4");
        groundConfigDTO.setTimeAMStr(StringUtils.isEmpty(param.get("timeAMStrThu")) ? "08:00:00" : param.get("timeAMStrThu").toString());
        groundConfigDTO.setTimeAMEnd(StringUtils.isEmpty(param.get("timeAMEndThu")) ? "12:00:00" : param.get("timeAMEndThu").toString());
        groundConfigDTO.setTimePMStr(StringUtils.isEmpty(param.get("timePMStrThu")) ? "14:00:00" : param.get("timePMStrThu").toString());
        groundConfigDTO.setTimePMEnd(StringUtils.isEmpty(param.get("timePMEndThu")) ? "18:00:00" : param.get("timePMEndThu").toString());
        groundConfigDTO.setLengthTime(StringUtils.isEmpty(param.get("lengthTimeThu")) ? "1" : param.get("lengthTimeThu").toString());
        groundConfigDTO.setRestTime(StringUtils.isEmpty(param.get("restTimeThu")) ? "" : param.get("restTimeThu").toString());
        groundConfigDTO.setSinglePrice(StringUtils.isEmpty(param.get("singlePriceThu")) ? "0" : param.get("singlePriceThu").toString());
        params = HashMapUtil.beanToMap(groundConfigDTO);
        setSaveInfo(params);
        groundConfigDao.saveGroundConfig(params);
        //Fir.
        groundConfigDTO.setGroundConfigId(UUIDUtil.getUUID());
        groundConfigDTO.setWeek("Fir");
        groundConfigDTO.setOrderIndex("5");
        groundConfigDTO.setTimeAMStr(StringUtils.isEmpty(param.get("timeAMStrFir")) ? "08:00:00" : param.get("timeAMStrFir").toString());
        groundConfigDTO.setTimeAMEnd(StringUtils.isEmpty(param.get("timeAMEndFir")) ? "12:00:00" : param.get("timeAMEndFir").toString());
        groundConfigDTO.setTimePMStr(StringUtils.isEmpty(param.get("timePMStrFir")) ? "14:00:00" : param.get("timePMStrFir").toString());
        groundConfigDTO.setTimePMEnd(StringUtils.isEmpty(param.get("timePMEndFir")) ? "18:00:00" : param.get("timePMEndFir").toString());
        groundConfigDTO.setLengthTime(StringUtils.isEmpty(param.get("lengthTimeFir")) ? "1" : param.get("lengthTimeFir").toString());
        groundConfigDTO.setRestTime(StringUtils.isEmpty(param.get("restTimeFir")) ? "" : param.get("restTimeFir").toString());
        groundConfigDTO.setSinglePrice(StringUtils.isEmpty(param.get("singlePriceFir")) ? "0" : param.get("singlePriceFir").toString());
        params = HashMapUtil.beanToMap(groundConfigDTO);
        setSaveInfo(params);
        groundConfigDao.saveGroundConfig(params);
        //Sat.
        groundConfigDTO.setGroundConfigId(UUIDUtil.getUUID());
        groundConfigDTO.setWeek("Sat");
        groundConfigDTO.setOrderIndex("6");
        groundConfigDTO.setTimeAMStr(StringUtils.isEmpty(param.get("timeAMStrSat")) ? "08:00:00" : param.get("timeAMStrSat").toString());
        groundConfigDTO.setTimeAMEnd(StringUtils.isEmpty(param.get("timeAMEndSat")) ? "12:00:00" : param.get("timeAMEndSat").toString());
        groundConfigDTO.setTimePMStr(StringUtils.isEmpty(param.get("timePMStrSat")) ? "14:00:00" : param.get("timePMStrSat").toString());
        groundConfigDTO.setTimePMEnd(StringUtils.isEmpty(param.get("timePMEndSat")) ? "18:00:00" : param.get("timePMEndSat").toString());
        groundConfigDTO.setLengthTime(StringUtils.isEmpty(param.get("lengthTimeSat")) ? "1" : param.get("lengthTimeSat").toString());
        groundConfigDTO.setRestTime(StringUtils.isEmpty(param.get("restTimeSat")) ? "" : param.get("restTimeSat").toString());
        groundConfigDTO.setSinglePrice(StringUtils.isEmpty(param.get("singlePriceSat")) ? "0" : param.get("singlePriceSat").toString());
        params = HashMapUtil.beanToMap(groundConfigDTO);
        setSaveInfo(params);
        groundConfigDao.saveGroundConfig(params);
        //Sun.
        groundConfigDTO.setGroundConfigId(UUIDUtil.getUUID());
        groundConfigDTO.setWeek("Sun");
        groundConfigDTO.setOrderIndex("7");
        groundConfigDTO.setTimeAMStr(StringUtils.isEmpty(param.get("timeAMStrSun")) ? "08:00:00" : param.get("timeAMStrSun").toString());
        groundConfigDTO.setTimeAMEnd(StringUtils.isEmpty(param.get("timeAMEndSun")) ? "12:00:00" : param.get("timeAMEndSun").toString());
        groundConfigDTO.setTimePMStr(StringUtils.isEmpty(param.get("timePMStrSun")) ? "14:00:00" : param.get("timePMStrSun").toString());
        groundConfigDTO.setTimePMEnd(StringUtils.isEmpty(param.get("timePMEndSun")) ? "18:00:00" : param.get("timePMEndSun").toString());
        groundConfigDTO.setLengthTime(StringUtils.isEmpty(param.get("lengthTimeSun")) ? "1" : param.get("lengthTimeSun").toString());
        groundConfigDTO.setRestTime(StringUtils.isEmpty(param.get("restTimeSun")) ? "" : param.get("restTimeSun").toString());
        groundConfigDTO.setSinglePrice(StringUtils.isEmpty(param.get("singlePriceSun")) ? "0" : param.get("singlePriceSun").toString());
        params = HashMapUtil.beanToMap(groundConfigDTO);
        setSaveInfo(params);
        groundConfigDao.saveGroundConfig(params);
    }
}
