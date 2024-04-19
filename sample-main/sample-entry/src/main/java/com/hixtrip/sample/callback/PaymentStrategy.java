package com.hixtrip.sample.callback;

public interface PaymentStrategy {
    void process(PaymentContext context);
}

