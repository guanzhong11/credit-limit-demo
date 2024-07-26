

package com.demo.creditlimit.dao.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreditLimitPo {

    private Integer id;

    private Long userId;

    private Integer type;

    private Double amount;

    private Boolean deleted;

    private Timestamp created;

    private Timestamp lastmodified;

}
