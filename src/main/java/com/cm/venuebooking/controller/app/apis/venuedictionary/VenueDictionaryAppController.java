package com.cm.venuebooking.controller.app.apis.venuedictionary;

import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.SearchException;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResultData;
import com.cm.venuebooking.controller.BaseController;
import com.cm.venuebooking.pojo.dtos.venuedictionary.VenueDictionaryDTO;
import com.cm.venuebooking.service.venuedictionary.IVenueDictionaryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 场馆数据字典
 * @author xwangs
 * @create 2020-06-02 11:53
 * @description
 */
@Api(tags = ISystemConstant.API_TAGS_APP_PREFIX + "数据字典")
@RestController
@RequestMapping(ISystemConstant.APP_PREFIX + "/venuedictionary")
public class VenueDictionaryAppController extends BaseController {

    @Autowired
    private IVenueDictionaryService venueDictionaryService;

    @ApiOperation(value = "APP场馆字典列表", notes = "APP场馆字典列表列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictionaryParentId", value = "父级ID", paramType = "form", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listvenuedictionarybyparentid" + ISystemConstant.APP_RELEASE_SUFFIX + "/{dictionaryParentId}")
    public SuccessResultData<List<VenueDictionaryDTO>> listVenueDictionaryByParentId(@PathVariable("dictionaryParentId") String dictionaryParentId) throws SearchException {
        return venueDictionaryService.listVenueDictionaryByParentId(dictionaryParentId);
    }
}
