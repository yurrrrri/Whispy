package com.syr.whispy.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateFieldException extends RuntimeException {

    public DuplicateFieldException(String message) {
        super(message);
    }
}
