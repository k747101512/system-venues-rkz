package com.cm.venuebooking.controller.app.apis.venuesinfo;

import com.cm.common.base.AbstractController;
import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.service.venuesinfo.IVenuesInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: VenuesInfoAppController
 * @Description: 场馆信息-App
 * @Author: WenG
 * @Date: 2020-04-22 11:46
 * @Version: 1.0
 **/
@Api(tags = ISystemConstant.API_TAGS_APP_PREFIX + "场馆信息-App")
@RestController
@RequestMapping(ISystemConstant.APP_PREFIX + "/venuesinfo")
public class VenuesInfoAppController extends AbstractController {

    @Autowired
    private IVenuesInfoService venuesInfoService;

    @ApiOperation(value = "APP场馆信息分页列表", notes = "APP场馆信息分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "显示数量", paramType = "form", dataType = "Integer", defaultValue = "10"),
            @ApiImplicitParam(name = "keywords", value = "场馆名字", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "orderKey", value = "排序方式(默认：'';热度:'hot';距离:'apart';时间:'timeDesc')", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "longitude", value = "经度", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "latitude", value = "纬度", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "venueType", value = "场馆类型ID", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "venueCity", value = "场馆类型ID", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "venueArea", value = "场馆类型ID", paramType = "form", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listpagevenuesinfo" + ISystemConstant.APP_RELEASE_SUFFIX)
    public SuccessResultList<List<VenuesInfoDTO>> listPageVenuesInfo(ListPage page) throws SearchException {
        Map<String, Object> params = requestParams();
        page.setParams(params);
        return venuesInfoService.listPageVenuesInfoFroApp(null,page);
    }

    @ApiOperation(value = "APP场馆信息分页列表(token)", notes = "APP场馆信息分页列表(token)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "显示数量", paramType = "form", dataType = "Integer", defaultValue = "10"),
            @ApiImplicitParam(name = "keywords", value = "关键字", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "orderKey", value = "排序方式(默认：'';热度:'hot';距离:'apart';时间:'timeDesc')", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "longitude", value = "经度", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "latitude", value = "纬度", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "venueType", value = "场馆类型ID", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "venueCity", value = "场馆类型ID", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "venueArea", value = "场馆类型ID", paramType = "form", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listpagevenuesinfo")
    public SuccessResultList<List<VenuesInfoDTO>> listPageVenuesInfoToken(@RequestHeader("token") String token, ListPage page) throws SearchException {
        Map<String, Object> params = requestParams();
        page.setParams(params);
        return venuesInfoService.listPageVenuesInfoFroApp(token,page);
    }

    @ApiOperation(value = "APP场馆信息详情(通过ID)", notes = "APP场馆信息详情(通过ID)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "venuesInfoId", value = "场馆信息表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getvenuesinfobyid" + ISystemConstant.APP_RELEASE_SUFFIX + "/{venuesInfoId}")
    public VenuesInfoDTO getVenuesInfoById(@PathVariable("venuesInfoId") String venuesInfoId) throws SearchException {
        return venuesInfoService.getVenuesInfoByIdForApp(null,venuesInfoId);
    }

    @ApiOperation(value = "APP场馆信息详情(通过ID,token)", notes = "APP场馆信息详情(通过ID)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "venuesInfoId", value = "场馆信息表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getvenuesinfobyid/{venuesInfoId}")
    public VenuesInfoDTO getVenuesInfoByIdToken(@RequestHeader("token") String token, @PathVariable("venuesInfoId") String venuesInfoId) throws SearchException {
        return venuesInfoService.getVenuesInfoByIdForApp(token,venuesInfoId);
    }

    @ApiOperation(value = "场馆信息表列表", notes = "场馆信息表列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listvenuesinfo")
    public List<VenuesInfoDTO> listVenuesInfo(@RequestHeader("token") String token) throws SearchException {
        Map<String, Object> params = requestParams();
        return venuesInfoService.listVenuesInfo(params);
    }

}