package com.cm.venuebooking.dao.groundconfig;

import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SaveException;
import com.cm.common.exception.SearchException;
import com.cm.common.exception.UpdateException;
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
public interface IGroundConfigDao {

    void saveGroundInfo(Map<String, Object> params) throws SaveException;

    List<GroundInfoDTO> listGroundInfo(Map<String, Object> params) throws SearchException;

    void saveGroundConfig(Map<String, Object> params) throws SaveException;

    void updateGroundInfo(Map<String, Object> params) throws UpdateException;

    GroundInfoDTO getGroundInfo(Map<String, Object> params) throws SearchException;

    void removeGroundInfo(Map<String, Object> params) throws RemoveException;

    List<GroundConfigDTO> getGroundConfig(Map<String, Object> params) throws SearchException;

    void removeGroundDailyInfo(Map<String, Object> params) throws RemoveException;
}
