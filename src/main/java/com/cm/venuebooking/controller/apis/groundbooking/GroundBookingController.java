package com.cm.venuebooking.controller.apis.groundbooking;

import com.cm.common.base.AbstractController;
import com.cm.common.component.SecurityComponent;
import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.SaveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.pojo.dtos.CurrentUserIdInfoDTO;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.groundbooking.GroundBookingInfoListDTO;
import com.cm.venuebooking.pojo.dtos.groundconfig.GroundConfigDTO;
import com.cm.venuebooking.service.groundbooking.IGroundBookingService;
import com.cm.venuebooking.service.groundconfig.IGroundConfigService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: GroundConfigController
 * @Description: 场地预订
 * @Author: WenG
 * @Date: 2020-04-26 12:07
 * @Version: 1.0
 **/
@Api(tags = ISystemConstant.API_TAGS_SYSTEM_PREFIX + "场地预订接口")
@RestController
@RequestMapping(ISystemConstant.API_PREFIX + "/groundbooking")
public class GroundBookingController extends AbstractController {

    @Autowired
    private IGroundBookingService groundBookingService;

    @Autowired
    private SecurityComponent securityComponent;

    @ApiOperation(value = "查询场地场次信息", notes = "查询场地场次信息(通过场地ID)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groundInfoId", value = "场地信息表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listgroundbooking")
    public Object listGroundBooking() throws SearchException {
        Map<String, Object> params = requestParams();
        return groundBookingService.listGroundBooking(params);
    }

    @ApiOperation(value = "保存预订信息", notes = "保存预订信息接口")
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PostMapping("savebookinginfo")
    public SuccessResult saveBookingInfo(@RequestBody Map<String,Object> params) throws SaveException {
        return groundBookingService.saveBookingInfo(params);
    }

    @ApiOperation(value = "选择预订场地树", notes = "选择预订场地树接口")
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("gettreegroundinfo")
    public Object getTreeGroundInfo() throws SearchException {
        Map<String, Object> param = requestParams();
        return groundBookingService.getTreeGroundInfo(param);
    }

    @ApiOperation(value = "场馆信息表分页列表", notes = "场馆信息表分页列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "显示数量", paramType = "form", dataType = "Integer", defaultValue = "20"),
            @ApiImplicitParam(name = "keywords", value = "关键字", paramType = "form", dataType = "String"),
    })
    @GetMapping("listpagebookingorder")
    public SuccessResultList<List<GroundBookingInfoListDTO>> listpageBookingOrder(ListPage page) throws SearchException {
        Map<String, Object> param = requestParams();
        page.setParams(param);
        return groundBookingService.listPageBookingOrder(page);
    }


    @ApiOperation(value = "当前用户id信息", notes = "当前用户id信息接口")
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getcurrentuseridinfo")
    public CurrentUserIdInfoDTO getCurrentUserIdInfo() {
        return securityComponent.getCurrentUserIdInfo();
    }

}