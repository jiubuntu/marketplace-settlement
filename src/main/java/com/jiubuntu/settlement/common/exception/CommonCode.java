package com.jiubuntu.settlement.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonCode {

    // =====================
    // Common (0001 ~ 0499)
    // =====================
    INTERNAL_SERVER_ERROR("0001", "서버 내부 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_INPUT("0002", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("0003", "인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("0004", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // =====================
    // Member (0500 ~ 1000)
    // =====================
    MEMBER_NOT_FOUND("0500", "존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND),
    MEMBER_EMAIL_DUPLICATED("0501", "이미 사용중인 이메일입니다.", HttpStatus.CONFLICT),
    MEMBER_INVALID_PASSWORD("0502", "비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    MEMBER_BANNED("0503", "정지된 회원입니다.", HttpStatus.FORBIDDEN),
    MEMBER_INACTIVE("0504", "비활성화된 회원입니다.", HttpStatus.FORBIDDEN),

    // =====================
    // Product (1001 ~ 1500)
    // =====================
    PRODUCT_NOT_FOUND("1001", "존재하지 않는 상품입니다.", HttpStatus.NOT_FOUND),
    PRODUCT_OUT_OF_STOCK("1002", "재고가 부족합니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_ON_SALE("1003", "판매중인 상품이 아닙니다.", HttpStatus.BAD_REQUEST),

    // =====================
    // Order (1501 ~ 2000)
    // =====================
    ORDER_NOT_FOUND("1501", "존재하지 않는 주문입니다.", HttpStatus.NOT_FOUND),
    ORDER_CANNOT_CANCEL("1502", "취소할 수 없는 주문입니다.", HttpStatus.BAD_REQUEST),
    ORDER_ALREADY_PAID("1503", "이미 결제된 주문입니다.", HttpStatus.CONFLICT),

    // =====================
    // Payment (2001 ~ 2500)
    // =====================
    PAYMENT_NOT_FOUND("2001", "존재하지 않는 결제입니다.", HttpStatus.NOT_FOUND),
    PAYMENT_ALREADY_COMPLETED("2002", "이미 완료된 결제입니다.", HttpStatus.CONFLICT),
    PAYMENT_FAILED("2003", "결제에 실패했습니다.", HttpStatus.BAD_REQUEST),

    // =====================
    // Coupon (2501 ~ 3000)
    // =====================
    COUPON_NOT_FOUND("2501", "존재하지 않는 쿠폰입니다.", HttpStatus.NOT_FOUND),
    COUPON_ALREADY_USED("2502", "이미 사용된 쿠폰입니다.", HttpStatus.BAD_REQUEST),
    COUPON_EXPIRED("2503", "만료된 쿠폰입니다.", HttpStatus.BAD_REQUEST),
    COUPON_NOT_APPLICABLE("2504", "적용 불가능한 쿠폰입니다.", HttpStatus.BAD_REQUEST),

    // =====================
    // Refund (3001 ~ 3500)
    // =====================
    REFUND_NOT_FOUND("3001", "존재하지 않는 환불입니다.", HttpStatus.NOT_FOUND),
    REFUND_ALREADY_PROCESSED("3002", "이미 처리된 환불입니다.", HttpStatus.CONFLICT),
    REFUND_NOT_ALLOWED("3003", "환불이 불가능한 주문입니다.", HttpStatus.BAD_REQUEST),

    // ========================
    // Settlement (3501 ~ 4000)
    // ========================
    SETTLEMENT_NOT_FOUND("3501", "존재하지 않는 정산입니다.", HttpStatus.NOT_FOUND),
    SETTLEMENT_ALREADY_COMPLETED("3502", "이미 완료된 정산입니다.", HttpStatus.CONFLICT),
    SETTLEMENT_FAILED("3503", "정산 처리에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
