package com.cm.venuebooking.controller.apis.groundconfig;

import com.cm.common.annotation.CheckRequestBodyAnnotation;
import com.cm.common.base.AbstractController;
import com.cm.common.component.SecurityComponent;
import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.pojo.dtos.CurrentUserIdInfoDTO;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundConfigDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundInfoDTO;
import com.cm.venuebooking.pojo.vos.groundconfig.GroundInfoVO;
import com.cm.venuebooking.service.groundconfig.IGroundConfigService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: GroundConfigController
 * @Description: 场地及配置管理
 * @Author: WenG
 * @Date: 2020-04-26 12:07
 * @Version: 1.0
 **/
@Api(tags = ISystemConstant.API_TAGS_SYSTEM_PREFIX + "场地及配置接口")
@RestController
@RequestMapping(ISystemConstant.API_PREFIX + "/groundconfig")
public class GroundConfigController extends AbstractController {

    @Autowired
    private IGroundConfigService groundConfigService;

    @Autowired
    private SecurityComponent securityComponent;

    @ApiOperation(value = "场地及配置", notes = "场地及配置接口")
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PostMapping("savegroundconfig")
    @CheckRequestBodyAnnotation
    public SuccessResult saveGroundConfig(@RequestBody Map<String,Object> reqParams) throws Exception {
        return groundConfigService.saveGroundConfig(reqParams);
    }

    @ApiOperation(value = "场地信息表分页列表", notes = "场地信息表分页列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "显示数量", paramType = "form", dataType = "Integer", defaultValue = "20"),
            @ApiImplicitParam(name = "keywords", value = "关键字", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "form", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listpagegroundinfo")
    public SuccessResultList<List<GroundInfoDTO>> listPageGroundInfo(ListPage page) throws SearchException {
        Map<String, Object> params = requestParams();
        page.setParams(params);
        return groundConfigService.listPageGroundInfo(page);
    }

    @ApiOperation(value = "修改场地信息表", notes = "修改场地信息表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groundInfoId", value = "场地信息表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PutMapping("updategroundinfo/{groundInfoId}")
    @CheckRequestBodyAnnotation
    public SuccessResult updateGroundInfo(@PathVariable("groundInfoId") String groundInfoId, @RequestBody GroundInfoVO groundInfoVO) throws Exception {
        return groundConfigService.updateGroundInfo(groundInfoId, groundInfoVO);
    }

    @ApiOperation(value = "场地信息表详情(通过ID)", notes = "场地信息表详情(通过ID)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groundInfoId", value = "场地信息表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getgroundinfobyid/{groundInfoId}")
    public GroundInfoDTO getGroundInfoById(@PathVariable("groundInfoId") String groundInfoId) throws SearchException {
        return groundConfigService.getGroundInfoById(groundInfoId);
    }

    @ApiOperation(value = "删除场地(id列表)", notes = "删除场地(id列表)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ID列表，用下划线分隔", paramType = "path", example = "1_2_3")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @DeleteMapping("removegroundinfo/{ids}")
    public SuccessResult removeGroundInfo(@PathVariable("ids") String ids) throws RemoveException {
        return groundConfigService.removeGroundInfo(ids);
    }

    @ApiOperation(value = "查询场地配置信息(通过场地ID)", notes = "查询场地配置信息(通过场地ID)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groundInfoId", value = "场地信息表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getgroundconfig/{groundInfoId}")
    public List<GroundConfigDTO> getGroundConfig(@PathVariable("groundInfoId") String groundInfoId) throws SearchException {
        return groundConfigService.getGroundConfig(groundInfoId);
    }

    @ApiOperation(value = "修改场地配置信息", notes = "修改场地配置信息接口")
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PostMapping("updategrounddailyinfo")
    public SuccessResult updateGroundDailyInfo(@RequestBody Map<String,Object> reqParams) throws Exception {
        return groundConfigService.updateGroundDailyInfo(reqParams);
    }


    @ApiOperation(value = "当前用户id信息", notes = "当前用户id信息接口")
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getcurrentuseridinfo")
    public CurrentUserIdInfoDTO getCurrentUserIdInfo() {
        return securityComponent.getCurrentUserIdInfo();
    }

}