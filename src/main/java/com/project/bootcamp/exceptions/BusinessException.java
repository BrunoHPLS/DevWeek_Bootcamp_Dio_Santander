package com.project.bootcamp.exceptions;

import com.project.bootcamp.util.MessageUtils;

public class BusinessException extends RuntimeException{
    public BusinessException(){
       super(MessageUtils.STOCK_ALREADY_EXISTS);
    }

}
