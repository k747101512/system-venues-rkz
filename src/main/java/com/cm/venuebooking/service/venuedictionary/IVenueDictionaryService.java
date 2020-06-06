package com.cm.venuebooking.service.venuedictionary;

import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.venuebooking.pojo.dtos.venuedictionary.VenueDictionaryDTO;
import com.cm.venuebooking.pojo.vos.venuedictionary.VenueDictionaryVO;

import java.util.List;

/**
 * @author xwangs
 * @create 2020-05-27 14:24
 * @description
 */
public interface IVenueDictionaryService {

    /**
     * 新增方法
     * @param venueDictionaryVO
     * @return
     */
    SuccessResult save(VenueDictionaryVO venueDictionaryVO) throws Exception;

    /**
     * 根据ID查询
     * @param dictionaryId
     * @return
     */
    VenueDictionaryDTO getVenueDictionaryById(String dictionaryId);

    /**
     * 修改
     * @param dictionaryId
     * @param venueDictionaryDTO
     * @return
     */
    SuccessResult update(String dictionaryId, VenueDictionaryDTO venueDictionaryDTO) throws Exception;

    /**
     * 根据parentId查询列表
     * @param dictionaryParentId
     * @return
     */
    SuccessResultData<List<VenueDictionaryDTO>> listVenueDictionaryByParentId(String dictionaryParentId);
}
