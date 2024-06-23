package com.paymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentService.entity.TransactionDetails;
import com.paymentService.model.PaymentRequest;
import com.paymentService.repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;

	
	@Override
	public Long doPayment(PaymentRequest paymentRequest) {
		log.info("Recording payment details: {}" ,paymentRequest);
		TransactionDetails transactionDetails = TransactionDetails.builder()
				.paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.paymentStatus("SUCCESS")
				.orderId(paymentRequest.getOrderId())
				.referenceNumber(paymentRequest.getRefrenceNumber())
				.amount(paymentRequest.getAmount())
				.build();
		
		transactionDetailsRepository.save(transactionDetails);
		
		log.info("Transaction completed with Id : {}",transactionDetails.getId() );
		return transactionDetails.getId();
	}

}
