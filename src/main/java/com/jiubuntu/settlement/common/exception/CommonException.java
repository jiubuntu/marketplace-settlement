package com.jiubuntu.settlement.common.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private final CommonCode commonCode;

    public CommonException(CommonCode commonCode) {
        super(commonCode.getMessage());
        this.commonCode = commonCode;
    }
}
