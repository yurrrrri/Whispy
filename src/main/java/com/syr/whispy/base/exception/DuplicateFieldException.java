package com.syr.whispy.base.exception;

import com.syr.whispy.base.code.Code;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateFieldException extends RuntimeException {

    private String code;

    public DuplicateFieldException(Code code) {
        super(code.getMsg());
        this.code = code.getCode();
    }

}
