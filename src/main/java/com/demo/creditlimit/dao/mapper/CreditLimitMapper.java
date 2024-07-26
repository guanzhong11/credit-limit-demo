/*
 * CreditLimitPo.java
 * Copyright 2024 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.demo.creditlimit.dao.mapper;

import com.demo.creditlimit.dao.po.CreditLimitPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CreditLimitMapper {

    int addCreditLimit(final CreditLimitPo po);

    int updateUserCreditLimit(
            @Param("userId") final long userId,
            @Param("type") final int type,
            @Param("amount") final double amount
    );

    CreditLimitPo getByUserIdAndType(@Param("userId") final long userId, @Param("type") int type);
}
