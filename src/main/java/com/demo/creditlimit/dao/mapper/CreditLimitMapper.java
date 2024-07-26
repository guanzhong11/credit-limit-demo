

package com.demo.creditlimit.dao.mapper;

import com.demo.creditlimit.dao.po.CreditLimitPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CreditLimitMapper {

    /**
     * 新增额度记录
     *
     * @param po 额度信息
     * @return 结果
     */
    int addCreditLimit(final CreditLimitPo po);

    /**
     * 更新用户额度
     *
     * @param userId 用户id
     * @param type   额度类型
     * @param amount 额度
     * @return 结果
     */
    int updateUserCreditLimit(
            @Param("userId") final long userId,
            @Param("type") final int type,
            @Param("amount") final double amount
    );

    /**
     * 查询用户额度
     *
     * @param userId 用户 id
     * @param type   额度类型
     * @return 额度信息
     */
    CreditLimitPo getByUserIdAndType(@Param("userId") final long userId, @Param("type") int type);
}
