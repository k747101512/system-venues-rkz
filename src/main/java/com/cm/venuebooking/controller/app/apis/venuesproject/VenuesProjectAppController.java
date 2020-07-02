package com.cm.venuebooking.controller.app.apis.venuesproject;

import com.cm.common.annotation.CheckRequestBodyAnnotation;
import com.cm.common.base.AbstractController;
import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.pojo.dtos.venuesproject.VenuesProjectDTO;
import com.cm.venuebooking.pojo.vos.venuesproject.VenuesProjectVO;
import com.cm.venuebooking.service.venuesproject.IVenuesProjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VenuesProjectAppController
 * @Description: 场馆项目接口
 * @Author: WenG
 * @Date: 2020-04-26 12:07
 * @Version: 1.0
 **/
@Api(tags = ISystemConstant.API_TAGS_APP_PREFIX + "场馆项目接口")
@RestController
@RequestMapping(ISystemConstant.APP_PREFIX + "/venuesproject")
public class VenuesProjectAppController extends AbstractController {

    @Autowired
    private IVenuesProjectService venuesProjectService;

    @ApiOperation(value = "APP场馆所有项目列表", notes = "APP场馆所有项目列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "venuesInfoId", value = "场馆的主键ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listprojectinfo" + ISystemConstant.APP_RELEASE_SUFFIX + "/{venuesInfoId}")
    public List<VenuesProjectDTO> listProjectInfoForApp(@PathVariable("venuesInfoId") String venuesInfoId) throws SearchException {
        return venuesProjectService.listProjectInfoForApp(null,venuesInfoId);
    }

    @ApiOperation(value = "项目详情信息", notes = "项目详情信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "venuesProjectId", value = "项目ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getvenuesprojectdetail" + ISystemConstant.APP_RELEASE_SUFFIX + "/{venuesProjectId}")
    public SuccessResultData getVenuesProjectDetail(@PathVariable("venuesProjectId") String venuesProjectId) throws SearchException {
        return venuesProjectService.getVenuesProjectDetail(null,venuesProjectId);
    }


}