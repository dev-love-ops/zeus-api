package com.devloveops.zeus.support.exception.system;

import com.devloveops.zeus.support.exception.BaseException;

/**
 * @author RockyWu
 * @date 2019-07-17 21:51
 */
public class UserExistsException extends BaseException {
    public UserExistsException(String message){
        super(message);
    }
}
