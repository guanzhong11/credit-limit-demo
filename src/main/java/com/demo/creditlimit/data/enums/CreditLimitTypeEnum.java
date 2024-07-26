package com.demo.creditlimit.data.enums;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum CreditLimitTypeEnum {

    TYPE_1(1, "type 1"),

    TYPE_2(2, "type 2"),

    ;

    private final int type;

    private final String description;


    private static final Map<Integer, CreditLimitTypeEnum> ENUM_MAP =
            Stream.of(CreditLimitTypeEnum.values())
                    .collect(Collectors.toMap(CreditLimitTypeEnum::getType, Function.identity()));

    public static CreditLimitTypeEnum toEnum(int type) {
        return ENUM_MAP.get(type);
    }
}
