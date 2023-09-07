package com.rfereloan.testingtask.ui.helper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rfereloan.testingtask.ui.model.PaymentResponse;

// PaymentRepository.java
public interface PaymentRepository {
    LiveData<PaymentResponse> makePayment(String paymentGateway, double amount);
    LiveData<PaymentResponse> getMockPaymentResponse(String paymentGateway);
}

