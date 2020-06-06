package com.cm.venuebooking.service.groundconfig;

import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SearchException;
import com.cm.common.exception.UpdateException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundConfigDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundInfoDTO;
import com.cm.venuebooking.pojo.vos.groundconfig.GroundInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @author xwangs
 * @create 2020-05-01 18:25
 * @description
 */
public interface IGroundConfigService {

    SuccessResult saveGroundConfig(Map<String,Object> reqParams) throws Exception;

    SuccessResultList<List<GroundInfoDTO>> listPageGroundInfo(ListPage page) throws SearchException;

    SuccessResult updateGroundInfo(String groundInfoId, GroundInfoVO groundInfoVO) throws Exception;

    GroundInfoDTO getGroundInfoById(String groundInfoId) throws SearchException;

    SuccessResult removeGroundInfo(String ids) throws RemoveException;

    List<GroundConfigDTO> getGroundConfig(String groundInfoId) throws SearchException;

    SuccessResult updateGroundDailyInfo(Map<String, Object> reqParams) throws Exception;

    SuccessResult removeGroundDailyInfo(String ids) throws  RemoveException;

    List<GroundInfoDTO> listGroundInfoByProject(Map<String, Object> params) throws  RemoveException;
}
