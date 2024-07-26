
package com.demo.creditlimit.service;

import com.demo.creditlimit.exception.CustomException;

public interface CreditLimitService {

    /**
     * 初始化用户额度
     *
     * @param userId 用户id
     * @param type 额度类型
            {@link com.demo.creditlimit.data.enums.CreditLimitTypeEnum}
     * @throws CustomException 业务异常
     */
    void initUserCreditLimit(long userId, int type) throws CustomException;

    /**
     * 查询用户额度
     *
     * @param userId 用户id
     * @param type 用户id
            {@link com.demo.creditlimit.data.enums.CreditLimitTypeEnum}
     * @return 额度。若没初始化返回 null
     * @throws CustomException 业务异常
     */
    Double getUserCreditLimit(long userId, int type) throws CustomException;

    /**
     * 新增或者扣减额度
     * 作为 demo，简单将两个方法和在一起。有较大差异可以拆分
     *
     * @param userId 用户id
     * @param type 用户id
            {@link com.demo.creditlimit.data.enums.CreditLimitTypeEnum}
     * @param incr 新增或者扣减的额度
     * @throws CustomException 业务异常
     */
    void incrUserCreditLimit(long userId, int type, double incr) throws CustomException;



}
