package com.cm.venuebooking.dao.groundbooking;

import com.cm.common.exception.SaveException;
import com.cm.common.exception.SearchException;
import com.cm.venuebooking.pojo.dtos.groundbooking.GroundBookingInfoListDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundConfigDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author xwangs
 * @create 2020-05-01 18:30
 * @description
 */
@Repository
public interface IGroundBookingDao {

    GroundConfigDTO getGroundConfig(Map<String, Object> reqParams) throws SearchException;

    void saveBookingInfo(Map<String, Object> params) throws SaveException;

    List<Map<String, Object>> listVenues(Map<String, Object> param) throws SearchException;

    List<Map<String, Object>> listProjectByVenue(Map<String, Object> param) throws SearchException;

    List<Map<String, Object>> listGroundByProject(Map<String, Object> param)throws SearchException;

    GroundConfigDTO getGroundConfigByProjectId(Map<String, Object> params) throws SearchException;

    List<GroundBookingInfoListDTO> listMyBookingOrder(Map<String, Object> param) throws SearchException;

    List<GroundBookingInfoListDTO> listPageBookingOrder(Map<String, Object> params) throws SearchException;
}
