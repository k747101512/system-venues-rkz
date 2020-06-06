package com.cm.venuebooking.controller.apis.register;

import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.SaveException;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResult;
import com.cm.venuebooking.pojo.vos.register.RegisterInfoVo;
import com.cm.venuebooking.service.register.IRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xwangs
 * @create 2020-05-21 16:13
 * @description
 */
@Api(tags = ISystemConstant.API_TAGS_SYSTEM_PREFIX + "提交入驻申请接口")
@RestController
@RequestMapping(ISystemConstant.API_PREFIX + "/webregister")
public class RegisterController {

    @Autowired
    private IRegisterService registerService;

    @ApiOperation(value = "保存入驻申请信息", notes = "保存入驻申请信息接口")
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PostMapping("saveregisterinfo")
    public SuccessResult saveRegisterInfo(@RequestBody RegisterInfoVo registerInfoVo) throws Exception {
        return registerService.saveRegisterInfo(registerInfoVo);
    }
}
