package com.paymentService.service;

import com.paymentService.model.PaymentRequest;

public interface PaymentService {

	Long doPayment(PaymentRequest paymentRequest);

}
