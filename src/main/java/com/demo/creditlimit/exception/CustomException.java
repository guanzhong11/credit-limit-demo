package com.demo.creditlimit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends Exception{

    private String message;

}
