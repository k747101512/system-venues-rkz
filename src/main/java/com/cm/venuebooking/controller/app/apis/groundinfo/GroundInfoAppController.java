package com.cm.venuebooking.controller.app.apis.groundinfo;

import com.cm.common.annotation.CheckRequestBodyAnnotation;
import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.SaveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.controller.BaseController;
import com.cm.venuebooking.pojo.dtos.bookingorder.GroundBookingInfoDTO;
import com.cm.venuebooking.pojo.dtos.bookingorder.MyTicketDetailDTO;
import com.cm.venuebooking.pojo.dtos.bookingorder.MyTicketListDTO;
import com.cm.venuebooking.pojo.dtos.groundinfo.GroundInfoDTO;
import com.cm.venuebooking.pojo.vos.groundbooking.GroundTicketVO;
import com.cm.venuebooking.service.groundbooking.IGroundBookingService;
import com.cm.venuebooking.service.groundinfo.IGroundInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * GroundInfoAppController
 * @author xwangs
 * @create 2020-06-07 15:11
 * @description 预订相关接口
 */
@Api(tags = ISystemConstant.API_TAGS_APP_PREFIX + "预订相关接口")
@RestController
@RequestMapping(ISystemConstant.APP_PREFIX + "/booking")
public class GroundInfoAppController extends BaseController {

    @Autowired
    private IGroundInfoService groundInfoService;

    @Autowired
    IGroundBookingService groundBookingService;

    @ApiOperation(value = "查询预订场次列表", notes = "查询预订场次接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "venuesProjectId", value = "venuesProjectId", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listgrouninfoanditem" + ISystemConstant.RELEASE_SUFFIX +"/{venuesProjectId}")
    public SuccessResultData<List<GroundInfoDTO>> listGroundInfoAndItem(@PathVariable("venuesProjectId") String venuesProjectId) throws SearchException {
        Map<String, Object> params = requestParams();
        params.put("venuesProjectId",venuesProjectId);
        return groundInfoService.listGroundInfoApp(params);
    }

    @ApiOperation(value = "保存预订订单", notes = "保存预定订单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
    })
    @PostMapping("savebookinginfo")
    @CheckRequestBodyAnnotation
    public SuccessResult saveBookingInfo(@RequestHeader("token") String token, @RequestBody GroundTicketVO groundTicketVO) throws Exception {
        return groundBookingService.saveBookingInfoForApp(token,groundTicketVO);
    }

    @ApiOperation(value = "我的订单列表", notes = "我的订单列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "显示数量", paramType = "form", dataType = "Integer", defaultValue = "20"),
            @ApiImplicitParam(name = "showSwitch", value = "normal仅正常,cancel仅取消,overdue仅逾期)", paramType = "path"),
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listpagemyticket")
    public SuccessResultList<List<MyTicketListDTO>> listPageMyTicket(@RequestHeader("token") String token, ListPage page) throws SearchException {
        Map<String, Object> params = requestParams();
        page.setParams(params);
        return groundBookingService.listPageMyTicket(token,page);
    }

    @ApiOperation(value = "我的订单详情", notes = "我的订单详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "groundBookingId", value = "订单ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getmyticketdetail/{groundBookingId}")
    public SuccessResultData<MyTicketDetailDTO> getMyTicketDetail(@RequestHeader("token") String token,
                                                                  @PathVariable("groundBookingId") String groundBookingId) throws SearchException {
        Map<String, Object> params = getParams();
        params.put("groundBookingId", groundBookingId);
        return groundBookingService.getMyTicketDetail(token,params);
    }

    @ApiOperation(value = "取消订单(取消所有预订场次)", notes = "取消订单(取消所有预订场次)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "groundBookingId", value = "订单ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @DeleteMapping("removemyticket/{groundBookingId}")
    public SuccessResult removeMyTicket(@RequestHeader("token") String token,@PathVariable String groundBookingId) throws SearchException {
        return groundBookingService.removeMyTicket(token,groundBookingId);
    }

    @ApiOperation(value = "取消订单下某个预订场次", notes = "取消订单下某个预订场次接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "groundBookingId", value = "订单ID", paramType = "path"),
            @ApiImplicitParam(name = "bookingItemId", value = "订单下场次ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @DeleteMapping("removemyticketitem/{groundBookingId}/{bookingItemId}")
    public SuccessResult removeMyTicketItem(@RequestHeader("token") String token,
                                            @PathVariable("groundBookingId") String groundBookingId,
                                            @PathVariable String bookingItemId
                                            ) throws SearchException {
        return groundBookingService.removeMyTicketItem(token,groundBookingId,bookingItemId);
    }

}
