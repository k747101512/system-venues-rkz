package com.cm.venuebooking.service.venuesinfo;

import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SaveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.pojo.dtos.ZTreeDTO;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.pojo.vos.venuesinfo.VenuesInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IVenuesInfoService
 * @Description: 场馆信息表
 * @Author: WenG
 * @Date: 2020-04-22 11:46
 * @Version: 1.0
 **/
public interface IVenuesInfoService {

    /**
     * 新增场馆信息表
     *
     * @param venuesInfoVO
     * @return
     * @throws Exception
     */
    SuccessResult saveVenuesInfo(VenuesInfoVO venuesInfoVO) throws Exception;

    /**
     * 新增场馆信息表(APP)
     *
     * @param token
     * @param venuesInfoVO
     * @return
     * @throws Exception
     */
    SuccessResult saveVenuesInfoByToken(String token, VenuesInfoVO venuesInfoVO) throws Exception;

    /**
     * 删除场馆信息表
     *
     * @param ids
     * @return
     * @throws RemoveException
     */
    SuccessResult removeVenuesInfo(String ids) throws RemoveException;

    /**
     * 删除场馆信息表(APP)
     *
     * @param token
     * @param ids
     * @return
     * @throws RemoveException
     */
    SuccessResult removeVenuesInfoByToken(String token, String ids) throws RemoveException;

    /**
     * 修改场馆信息表
     *
     * @param venuesInfoId
     * @param venuesInfoVO
     * @return
     * @throws Exception
     */
    SuccessResult updateVenuesInfo(String venuesInfoId, VenuesInfoVO venuesInfoVO) throws Exception;

    /**
     * 修改场馆信息表(APP)
     *
     * @param token
     * @param venuesInfoId
     * @param venuesInfoVO
     * @return
     * @throws Exception
     */
    SuccessResult updateVenuesInfoByToken(String token, String venuesInfoId, VenuesInfoVO venuesInfoVO) throws Exception;

    /**
     * 场馆信息表详情(通过ID)
     *
     * @param venuesInfoId
     * @return
     * @throws SearchException
     */
    VenuesInfoDTO getVenuesInfoById(String venuesInfoId) throws SearchException;

    /**
     * 场馆信息表列表
     *
     * @param params
     * @return
     * @throws SearchException
     */
    List<VenuesInfoDTO> listVenuesInfo(Map<String, Object> params) throws SearchException;

    /**
     * 场馆信息表分页列表
     *
     * @param page
     * @return
     * @throws SearchException
     */
    SuccessResultList<List<VenuesInfoDTO>> listPageVenuesInfo(ListPage page) throws SearchException;

    /**
     * 小程序-根据项目分类查询场馆
     * @param token
     * @param params
     * @return
     */
    SuccessResultData listVenuesByKeyWords(String token, Map<String, Object> params) throws SearchException;

    /**
     * 小程序-场馆详情
     * @param token
     * @param params
     * @return
     */
    Object getVenuesDetailById(String token, Map<String, Object> params) throws SearchException;

    /**
     * 小程序-场馆项目详情
     *
     * @param token
     * @param params
     * @return
     */
    Object getVenuesProjectDetailById(String token, Map<String, Object> params) throws SearchException;

    Object getCityInfoByName(Map<String, Object> param) throws SearchException;

    SuccessResultList<List<VenuesInfoDTO>> listPageVenuesInfoFroApp(String token, ListPage page) throws SearchException;

    /**
     * 查询场馆详情
     * @param token
     * @param param
     * @return
     */
    VenuesInfoDTO getVenuesInfoByIdForApp(String token, Map<String, Object> param);

    /**
     * 获取指定距离内所有场馆
     * @param params
     * @return
     */
    SuccessResultData<List<VenuesInfoDTO>> listRangeVenuesInfo(Map<String, Object> params);

    /**
     * 后台-场馆zTree
     * 有数据权限校验
     * @return
     */
    List<ZTreeDTO> listVenuesInfoZTree();

    /**
     * resources-场馆分页列表
     * @param page
     * @return
     */
    SuccessResultList<List<VenuesInfoDTO>> listPageVenuesInfoResources(ListPage page);
}
