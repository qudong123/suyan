package com.suyan.mmc.controller;

import com.suyan.mmc.req.UserCouponDTO;
import com.suyan.mmc.req.UserCouponQueryDTO;
import com.suyan.mmc.resp.UserCouponODTO;
import com.suyan.mmc.resp.base.QueryResultODTO;
import com.suyan.mmc.result.MmcResult;
import com.suyan.mmc.result.MmcResultCode;
import com.suyan.mmc.result.ValidationResult;
import com.suyan.mmc.service.IUserCouponService;
import com.suyan.mmc.util.ValidationUtils;
import com.suyan.mmc.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("userCoupon")
@RestController
@Api(value = "userCoupon", description = "用户优惠券管理接口")
public class UserCouponController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserCouponController.class);

    @Autowired
    private IUserCouponService userCouponService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 401, message = "用户不存在"),
            @ApiResponse(code = 402, message = "没有此操作权限"),
    })
    @ApiOperation(value = "deleteUserCoupon/{id}", notes = "删除用户优惠券")
    @RequestMapping(value = "deleteUserCoupon/{id}", method = {RequestMethod.POST})
    public MmcResult<Integer> deleteUserCoupon(@PathVariable Long id) {
        MmcResult<Integer> result = MmcResult.newSuccess();
        try {
            result = userCouponService.deleteUserCoupon(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(MmcResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 401, message = "用户不存在"),
            @ApiResponse(code = 402, message = "没有此操作权限"),
    })
    @ApiOperation(value = "createUserCoupon", notes = "创建用户优惠券")
    @RequestMapping(value = "createUserCoupon", method = {RequestMethod.POST})
    public MmcResult<Long> createUserCoupon(@Valid @RequestBody UserCouponDTO userCouponDTO) {
        MmcResult<Long> result = MmcResult.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userCouponDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(MmcResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            UserVO user = getUser();
            userCouponDTO.setCreateUser(user.getOpenId());
            userCouponDTO.setCreateUserName(user.getNickName());
            userCouponDTO.setUpdateUser(user.getOpenId());
            userCouponDTO.setUpdateUserName(user.getNickName());
            result = userCouponService.createUserCoupon(userCouponDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(MmcResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 401, message = "用户不存在"),
            @ApiResponse(code = 402, message = "没有此操作权限"),
    })
    @ApiOperation(value = "updateUserCoupon", notes = "更新用户优惠券")
    @RequestMapping(value = "updateUserCoupon", method = {RequestMethod.POST})
    public MmcResult<Integer> updateUserCoupon(@Valid @RequestBody UserCouponDTO userCouponDTO) {
        MmcResult<Integer> result = MmcResult.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userCouponDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(MmcResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            UserVO user = getUser();
            userCouponDTO.setCreateUser(null);
            userCouponDTO.setCreateUserName(user.getNickName());
            userCouponDTO.setUpdateUser(null);
            userCouponDTO.setUpdateUserName(user.getNickName());
            result = userCouponService.updateUserCoupon(userCouponDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(MmcResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 401, message = "用户不存在"),
            @ApiResponse(code = 402, message = "没有此操作权限"),
    })
    @ApiOperation(value = "getUserCoupon/{id}", notes = "根据用户优惠券ID获取用户优惠券信息")
    @RequestMapping(value = "getUserCoupon/{id}", method = {RequestMethod.GET})
    public MmcResult<UserCouponODTO> getUserCoupon(@PathVariable Long id) {
        MmcResult<UserCouponODTO> result = MmcResult.newSuccess();
        try {
            result = userCouponService.getUserCoupon(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(MmcResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 401, message = "用户不存在"),
            @ApiResponse(code = 402, message = "没有此操作权限"),
    })
    @ApiOperation(value = "queryUserCoupon", notes = "分页获取用户优惠券列表信息")
    @RequestMapping(value = "queryUserCoupon", method = {RequestMethod.POST})
    MmcResult<QueryResultODTO<UserCouponODTO>> queryUserCoupon(@Valid @RequestBody UserCouponQueryDTO userCouponQueryDTO) {
        MmcResult<QueryResultODTO<UserCouponODTO>> result = MmcResult.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userCouponQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(MmcResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = userCouponService.queryUserCoupon(userCouponQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(MmcResultCode.SYS_ERROR);
        }
        return result;
    }
}
