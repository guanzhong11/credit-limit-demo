/*
 * Result.java
 * Copyright 2024 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.demo.creditlimit.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private int c;

    private String m;

    private T d;

    public static final int OK_CODE = 1;

    public static final int GENERAL_ERROR_CODE = -1;



    public static <T> Result<T> ok(T d) {
        return new Result<>(OK_CODE, "", d);
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(GENERAL_ERROR_CODE, message, null);
    }
}
