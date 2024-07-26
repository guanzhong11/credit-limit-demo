/*
 * CreditLimitController.java
 * Copyright 2024 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.demo.creditlimit.controller;

import com.demo.creditlimit.exception.CustomException;
import com.demo.creditlimit.common.Result;
import com.demo.creditlimit.service.CreditLimitService;
import com.demo.creditlimit.vo.UserCreditLimitInitVo;
import com.demo.creditlimit.vo.UserCreditLimitUpdateVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户额度控制器
 *
 * 简单demo, userId通过参数传递, 而不是上下文获取
 */
@RestController
public class UserCreditLimitController {

    private final CreditLimitService creditLimitService;

    public UserCreditLimitController(
            final CreditLimitService creditLimitService) {
        this.creditLimitService = creditLimitService;
    }

    /**
     * 查询用户额度
     */
    @GetMapping("/credit-limit/user-limit")
    public Result<Double> getUserCreditLimit(
            final Long userId, final Integer type) throws CustomException {
        if (userId == null || type == null) {
            throw new CustomException("userId, type不能为 null");
        }
        Double limit = creditLimitService.getUserCreditLimit(userId, type);
        return Result.ok(limit);
    }

    /**
     * 初始化用户额度
     */
    @PostMapping("/credit-limit/user-limit/init")
    public Result<Void> initUserCreditLimit(@RequestBody UserCreditLimitInitVo vo) throws CustomException {
        if (vo.getUserId() == null || vo.getType() == null) {
            throw new CustomException("userId, type不能为 null");
        }
        creditLimitService.initUserCreditLimit(vo.getUserId(), vo.getType());
        return Result.ok();
    }

    /**
     * 增加或扣减用户额度
     */
    @PutMapping("/credit-limit/user-limit/update")
    public Result<Void> updateUserCreditLimit(@RequestBody UserCreditLimitUpdateVo vo) throws CustomException {
        if (vo.getUserId() == null || vo.getType() == null || vo.getIncr() == null) {
            throw new CustomException("userId,type,incr不能为null");
        }
        creditLimitService.incrUserCreditLimit(vo.getUserId(), vo.getType(), vo.getIncr());
        return Result.ok();
    }





}
