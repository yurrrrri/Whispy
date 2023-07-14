package com.syr.whispy.base.exception;

import com.syr.whispy.base.code.Code;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

    private String code;

    public DataNotFoundException(Code code) {
        super(code.getMsg());
        this.code = code.getCode();
    }

}
