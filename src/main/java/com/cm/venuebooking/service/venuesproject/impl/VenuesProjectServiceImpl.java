package com.cm.venuebooking.service.venuesproject.impl;

import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SaveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.common.utils.HashMapUtil;
import com.cm.common.utils.UUIDUtil;
import com.cm.venuebooking.dao.venuesproject.IVenuesProjectDao;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.pojo.dtos.venuesproject.VenuesProjectDTO;
import com.cm.venuebooking.pojo.dtos.venuesproject.VenuesProjectForListDTO;
import com.cm.venuebooking.pojo.vos.venuesproject.VenuesProjectVO;
import com.cm.venuebooking.service.BaseService;
import com.cm.venuebooking.service.venuesproject.IVenuesProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName: VenuesProjectServiceImpl
 * @Description: 场馆项目表
 * @Author: WenG
 * @Date: 2020-04-26 12:07
 * @Version: 1.0
 **/
@Service
public class VenuesProjectServiceImpl extends BaseService implements IVenuesProjectService {

    @Autowired
    private IVenuesProjectDao venuesProjectDao;

    @Override
    public SuccessResult saveVenuesProject(VenuesProjectVO venuesProjectVO) throws Exception {
        saveVenuesProjectInfo(null, venuesProjectVO);
        return new SuccessResult();
    }

    @Override
    public SuccessResult saveVenuesProjectByToken(String token, VenuesProjectVO venuesProjectVO) throws Exception {
        saveVenuesProjectInfo(token, venuesProjectVO);
        return new SuccessResult();
    }

    /**
     * 新增场馆项目表
     *
     * @param token
     * @param venuesProjectVO
     * @throws Exception
     */
    private void saveVenuesProjectInfo(String token, VenuesProjectVO venuesProjectVO) throws Exception {
        Map<String, Object> params = HashMapUtil.beanToMap(venuesProjectVO);
        params.put("venuesProjectId", UUIDUtil.getUUID());
        if (token != null) {
            setSaveInfo(token, params);
        } else {
            setSaveInfo(params);
        }
        venuesProjectDao.saveVenuesProject(params);
    }

    @Override
    public SuccessResult removeVenuesProject(String ids) throws RemoveException {
        removeVenuesProjectInfo(null, ids);
        return new SuccessResult();
    }

    @Override
    public SuccessResult removeVenuesProjectByToken(String token, String ids) throws RemoveException {
        removeVenuesProjectInfo(token, ids);
        return new SuccessResult();
    }

    /**
     * 删除场馆项目表
     *
     * @param token
     * @param ids
     */
    private void removeVenuesProjectInfo(String token, String ids) {
        Map<String, Object> params = getHashMap(3);
        params.put("venuesProjectIds", Arrays.asList(ids.split("_")));
        if (token != null) {
            setUpdateInfo(token, params);
        } else {
            setUpdateInfo(params);
        }
        venuesProjectDao.removeVenuesProject(params);
    }

    @Override
    public SuccessResult updateVenuesProject(String venuesProjectId, VenuesProjectVO venuesProjectVO) throws Exception {
        updateVenuesProjectInfo(null, venuesProjectId, venuesProjectVO);
        return new SuccessResult();
    }

    @Override
    public SuccessResult updateVenuesProjectByToken(String token, String venuesProjectId, VenuesProjectVO venuesProjectVO) throws Exception {
        updateVenuesProjectInfo(token, venuesProjectId, venuesProjectVO);
        return new SuccessResult();
    }

    /**
     * 修改场馆项目表
     *
     * @param token
     * @param venuesProjectId
     * @param venuesProjectVO
     */
    private void updateVenuesProjectInfo(String token, String venuesProjectId, VenuesProjectVO venuesProjectVO) throws Exception {
        Map<String, Object> params = HashMapUtil.beanToMap(venuesProjectVO);
        params.put("venuesProjectId", venuesProjectId);
        if (token != null) {
            setUpdateInfo(token, params);
        } else {
            setUpdateInfo(params);
        }
        venuesProjectDao.updateVenuesProject(params);
    }

    @Override
    public VenuesProjectDTO getVenuesProjectById(String venuesProjectId) throws SearchException {
        Map<String, Object> params = super.getHashMap(1);
        params.put("venuesProjectId", venuesProjectId);
        return venuesProjectDao.getVenuesProject(params);
    }

    @Override
    public List<VenuesProjectDTO> listVenuesProject(Map<String, Object> params) throws SearchException {
        return venuesProjectDao.listVenuesProject(params);
    }

    @Override
    public Object listVenuesProjectWeChatProgram(String token, Map<String, Object> params) {
        String id = StringUtils.isEmpty(params.get("id")) ? "f59ebf77-c2b7-49bc-8f58-2928bd2b450d" : params.get("pId").toString();
        params.put("id",id);
        List<Map<String,Object>> list = venuesProjectDao.listVenuesProjectWeChatProgram(params);
        Map<String, Object> result = getHashMap(0);
        result.put("list",list);
        return result;
    }

    @Override
    public SuccessResultList<List<VenuesProjectDTO>> listPageVenuesProject(ListPage page) throws SearchException {
        setDataAuthorityInfo(page.getParams());
        String creator = securityComponent.getCurrentUser().getUserId();
        page.getParams().put("creator",creator);
        PageHelper.startPage(page.getPage(), page.getRows());
        List<VenuesProjectDTO> list = venuesProjectDao.listVenuesProject(page.getParams());
        PageInfo<VenuesProjectDTO> pageInfo = new PageInfo<>(list);
        return new SuccessResultList<>(list, pageInfo.getPageNum(), pageInfo.getTotal());
    }

    @Override
    public SuccessResultList<List<VenuesProjectForListDTO>> listPageVenuesProjectLocal(ListPage page) throws SearchException{
        page.getParams().put("creator",securityComponent.getCurrentUser().getUserId());
        PageHelper.startPage(page.getPage(), page.getRows());
        List<VenuesProjectForListDTO> venuesProjectForListDTOS = venuesProjectDao.listVenuesProjectLocal(page.getParams());
        PageInfo<VenuesProjectForListDTO> pageInfo = new PageInfo<>(venuesProjectForListDTOS);
        return new SuccessResultList<>(venuesProjectForListDTOS, pageInfo.getPageNum(), pageInfo.getTotal());
    }

    /**
     * app 查询场馆下所有项目
     * @param token
     * @param venuesInfoId
     * @return
     */
    @Override
    public List<VenuesProjectDTO> listProjectInfoForApp(String token, String venuesInfoId) {
        Map<String,Object> param = getHashMap(2);
        param.put("venuesInfoId",venuesInfoId);
        List<VenuesProjectDTO> list = venuesProjectDao.listVenuesProjectExt(param);
        return list;
    }

    /**
     * 查询项目详情
     * @param token
     * @param venuesProjectId
     * @return
     */
    @Override
    public SuccessResultData getVenuesProjectDetail(String token, String venuesProjectId) {
        Map<String,Object> param = getHashMap(2);
        param.put("venuesProjectId",venuesProjectId);
        VenuesProjectDTO venuesProjectDTO = venuesProjectDao.getVenuesProjectDetailById(param);
        return new SuccessResultData(venuesProjectDTO);
    }
}
