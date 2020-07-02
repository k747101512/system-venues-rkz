package com.cm.venuebooking.controller.resources.venuesinfo;

import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.controller.BaseController;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.service.venuesinfo.IVenuesInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xwangs
 * @create 2020-07-01 15:24
 * @description
 */
@Api(tags = ISystemConstant.API_TAGS_RESOURCE_PREFIX + "场馆信息表接口")
@RestController
@RequestMapping(ISystemConstant.RESOURCE_PREFIX + "/venuesinfo")
public class VenuesInfoResourcesController extends BaseController {

    @Autowired
    private IVenuesInfoService venuesInfoService;

    @ApiOperation(value = "场馆信息表列表", notes = "场馆信息表列表接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form", dataType = "Integer", defaultValue = "1"),
        @ApiImplicitParam(name = "rows", value = "显示数量", paramType = "form", dataType = "Integer", defaultValue = "20"),
        @ApiImplicitParam(name = "keywords", value = "关键字", paramType = "form", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listpagevenuesinfo")
    public List<VenuesInfoDTO> listPageVenuesInfo(ListPage page) throws SearchException {
        Map<String, Object> params = requestParams();
        page.setParams(params);
        return venuesInfoService.listPageVenuesInfoResources(page);
    }
}
