package com.jiubuntu.settlement.common.exception;

import com.jiubuntu.settlement.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonResponse<Void>> handleCommonException(CommonException e) {
        CommonCode code = e.getCommonCode();
        log.warn("[CommonException] code={}, message={}", code.getCode(), code.getMessage());
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(CommonResponse.error(code));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleException(Exception e) {
        log.error("[Exception] message={}", e.getMessage(), e);
        return ResponseEntity
                .status(CommonCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(CommonResponse.error(CommonCode.INTERNAL_SERVER_ERROR));
    }
}
