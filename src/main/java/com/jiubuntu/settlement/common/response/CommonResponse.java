package com.jiubuntu.settlement.common.response;

import com.jiubuntu.settlement.common.exception.CommonCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse<T> {

    private String code;
    private String message;
    private T data;

    private CommonResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>("0000", "성공", data);
    }

    public static CommonResponse<Void> success() {
        return new CommonResponse<>("0000", "성공", null);
    }

    public static CommonResponse<Void> error(CommonCode commonCode) {
        return new CommonResponse<>(commonCode.getCode(), commonCode.getMessage(), null);
    }
}
