/*
 * CreditLimitVo.java
 * Copyright 2024 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.demo.creditlimit.vo;

import lombok.Data;

@Data
public class UserCreditLimitUpdateVo {

    private Long userId;

    private Integer type;

    private Double incr;
}
