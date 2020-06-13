package com.cm.venuebooking.controller.app.apis.venuesmap;

import com.cm.common.constants.ISystemConstant;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResultData;
import com.cm.venuebooking.controller.BaseController;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.service.venuesinfo.IVenuesInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xwangs
 * @create 2020-06-13 17:30
 * @description 根据百度地图定位获取指定半径内所有场馆
 */
@Api(tags = ISystemConstant.API_TAGS_APP_PREFIX + "百度地图定位获取指定半径内场馆接口")
@RestController
@RequestMapping(ISystemConstant.APP_PREFIX + "/venuesmap")
public class VenuesMapAppController extends BaseController {

    @Autowired
    private IVenuesInfoService venuesInfoService;

    @ApiOperation(value = "获取指定距离内所有场馆", notes = "获取指定距离内所有场馆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "longitude", value = "经度", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "latitude", value = "纬度", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "range", value = "距离(米)", paramType = "from", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listrangevenuesinfo" + ISystemConstant.RELEASE_SUFFIX)
    public SuccessResultData<List<VenuesInfoDTO>> listRangeVenuesInfo(){
        Map<String, Object> params = requestParams();
        return venuesInfoService.listRangeVenuesInfo(params);
    }

}
