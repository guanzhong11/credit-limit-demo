/*
 * CreditLimitServiceTest.java
 * Copyright 2024 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.demo.creditlimit;

import com.demo.creditlimit.common.RedisLock;
import com.demo.creditlimit.dao.mapper.CreditLimitMapper;
import com.demo.creditlimit.dao.po.CreditLimitPo;
import com.demo.creditlimit.data.enums.CreditLimitTypeEnum;
import com.demo.creditlimit.exception.CustomException;
import com.demo.creditlimit.service.CreditLimitService;
import com.demo.creditlimit.service.impl.CreditLimitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.ref.PhantomReference;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@SpringBootTest
public class CreditLimitServiceTest {

    private CreditLimitService creditLimitService;

    @MockBean
    private RedisLock redisLock;

    @MockBean
    private CreditLimitMapper creditLimitMapper;

    @BeforeEach
    public void init() {
        creditLimitService = new CreditLimitServiceImpl(creditLimitMapper, redisLock);
    }

    private static final long USER_ID = 1;
    private static final int TYPE = CreditLimitTypeEnum.TYPE_1.getType();


    @Test
    public void testGetUserCreditLimitNormal() throws CustomException {
        final CreditLimitPo po = new CreditLimitPo();
        po.setId(1);
        po.setUserId(1L);
        po.setType(1);
        po.setAmount(100d);
        given(creditLimitMapper.getByUserIdAndType(anyLong(), anyInt())).willReturn(po);
        assertThat(creditLimitService.getUserCreditLimit(1L, 1)).isNotNull();
    }

    @Test
    public void testGetUserCreditLimitUnknownType() {
        final int unknownType = -1;
        assertThrows(CustomException.class,
                () -> creditLimitService.getUserCreditLimit(USER_ID, unknownType));
    }

    @Test
    public void testGetUserCreditLimitUnInit() throws CustomException {
        given(creditLimitMapper.getByUserIdAndType(anyLong(), anyInt())).willReturn(null);
        assertThat(creditLimitService.getUserCreditLimit(USER_ID, TYPE)).isNull();
    }



    @Test
    public void testInitUserCreditLimitNormal() throws CustomException {
        given(redisLock.lock(anyString())).willReturn(true);
        given(creditLimitMapper.getByUserIdAndType(anyLong(), anyInt())).willReturn(null);

        creditLimitService.initUserCreditLimit(USER_ID, TYPE);

        assertThat(true).isTrue();
    }

    @Test
    public void testInitUserCreditLimitLocked() throws CustomException {
        given(redisLock.lock(anyString())).willReturn(false);
        assertThrows(CustomException.class,
                () -> creditLimitService.initUserCreditLimit(USER_ID, TYPE));
    }

    @Test
    public void testInitUserCreditLimitUnknownType() throws CustomException {
        final int unknownType = -1;
        assertThrows(CustomException.class,
                () -> creditLimitService.initUserCreditLimit(USER_ID, unknownType));
    }

    @Test
    public void testInitUserCreditLimitAlreadyInit() throws CustomException {
        given(redisLock.lock(anyString())).willReturn(true);
        given(creditLimitMapper.getByUserIdAndType(anyLong(), anyInt())).willReturn(new CreditLimitPo());

        assertThrows(CustomException.class,
                () -> creditLimitService.initUserCreditLimit(USER_ID, TYPE));
    }



    @Test
    public void testIncrUserCreditLimitNormal() throws CustomException {
        given(redisLock.lock(anyString())).willReturn(true);
        final CreditLimitPo po = new CreditLimitPo();
        po.setId(1);
        po.setUserId(USER_ID);
        po.setType(TYPE);
        po.setAmount(100d);
        given(creditLimitMapper.getByUserIdAndType(anyLong(), anyInt())).willReturn(po);

        creditLimitService.incrUserCreditLimit(USER_ID, TYPE, 1);

        assertThat(true).isTrue();
    }

    @Test
    public void testIncrUserCreditLimitUnInit() throws CustomException {
        given(redisLock.lock(anyString())).willReturn(true);
        given(creditLimitMapper.getByUserIdAndType(anyLong(), anyInt())).willReturn(null);

        assertThrows(CustomException.class,
                () -> creditLimitService.incrUserCreditLimit(USER_ID, TYPE,1));
    }
}
