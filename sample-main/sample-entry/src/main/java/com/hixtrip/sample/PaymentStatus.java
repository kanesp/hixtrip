package com.hixtrip.sample;

public enum PaymentStatus {
    SUCCESS(1),
    FAILURE(-1),
    DUPLICATE(2);

    private final int code;

    PaymentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentStatus fromCode(int code) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid payment status code: " + code);
    }
}
