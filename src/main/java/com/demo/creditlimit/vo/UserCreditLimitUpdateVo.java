
package com.demo.creditlimit.vo;

import lombok.Data;

@Data
public class UserCreditLimitUpdateVo {

    private Long userId;

    private Integer type;

    private Double incr;
}
