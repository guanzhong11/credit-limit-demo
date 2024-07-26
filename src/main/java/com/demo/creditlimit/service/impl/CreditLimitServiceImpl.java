

package com.demo.creditlimit.service.impl;

import com.demo.creditlimit.common.RedisLock;
import com.demo.creditlimit.dao.mapper.CreditLimitMapper;
import com.demo.creditlimit.dao.po.CreditLimitPo;
import com.demo.creditlimit.data.enums.CreditLimitTypeEnum;
import com.demo.creditlimit.exception.CustomException;
import com.demo.creditlimit.service.CreditLimitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class CreditLimitServiceImpl implements CreditLimitService {

    private final CreditLimitMapper creditLimitMapper;
    private final RedisLock redisLock;

    public CreditLimitServiceImpl(
            final CreditLimitMapper creditLimitMapper,
            final RedisLock redisLock) {
        this.creditLimitMapper = creditLimitMapper;
        this.redisLock = redisLock;
    }

    private static final String INIT_LOCK_KEY = "credit-limit:init:%d:%d";
    private static final String INCR_LOCK_KEY = "credit-limit:incr:%d:%d";

    /**
     * 权限校验预留方法。
     * 校验用户是否存在，是否有权限等。
     */
    private boolean checkAuth() throws CustomException {
        return true;
    }

    /**
     * 初始化预留方法
     */
    private Double getInitAmount(final long userId, final int type) {
        return (double) new Random().nextInt(10000);
    }

    /**
     * 记录操作日志预留方法
     */
    private void addOperateLog() {

    }


    @Override
    @Transactional
    public void initUserCreditLimit(final long userId, final int type) throws CustomException {
        final String lockKey = String.format(INIT_LOCK_KEY, userId, type);
        if (!redisLock.lock(lockKey)) {
            throw new CustomException("正在初始化中，请稍后");
        }
        try {
            checkCreditLimitType(type);
            checkAuth();
            if (creditLimitMapper.getByUserIdAndType(userId, type) != null) {
                throw new CustomException("已经初始化过");
            }

            final CreditLimitPo po = new CreditLimitPo();
            po.setUserId(userId);
            po.setType(type);
            po.setAmount(getInitAmount(userId, type));
            creditLimitMapper.addCreditLimit(po);

            addOperateLog();
        } finally {
            redisLock.unlock(lockKey);
        }
    }

    private void checkCreditLimitType(final int type) throws CustomException {
        if (CreditLimitTypeEnum.toEnum(type) == null) {
            throw new CustomException("未知类型");
        }
    }

    @Override
    public Double getUserCreditLimit(final long userId, final int type) throws CustomException {
        checkCreditLimitType(type);
        checkAuth();

        final CreditLimitPo po = creditLimitMapper.getByUserIdAndType(userId, type);
        return Optional.ofNullable(po)
                .map(CreditLimitPo::getAmount)
                .orElse(null);
    }

    @Override
    @Transactional
    public void incrUserCreditLimit(final long userId, final int type, final double incr)
            throws CustomException {
        final String lockKey = String.format(INCR_LOCK_KEY, userId, type);
        if (!redisLock.lock(lockKey)) {
            throw new CustomException("正在更新额度，请稍后");
        }
        try {
            checkCreditLimitType(type);
            checkAuth();
            final CreditLimitPo po = creditLimitMapper.getByUserIdAndType(userId, type);
            if (po == null) {
                throw new CustomException("未初始化");
            }

            checkFinalAmount();
            final double amount = po.getAmount() + incr;
//            if (amount < 0) {
//                throw new CustomException("额度不得低于0");
//            }
            creditLimitMapper.updateUserCreditLimit(userId, type, amount);

            addOperateLog();
        } finally {
            redisLock.unlock(lockKey);
        }
    }

    /**
     * 额度校验预留方法
     */
    private void checkFinalAmount() {

    }


}
