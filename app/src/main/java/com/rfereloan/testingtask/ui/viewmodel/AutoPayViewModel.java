package com.rfereloan.testingtask.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rfereloan.testingtask.ui.helper.PaymentRepository;
import com.rfereloan.testingtask.ui.model.PaymentResponse;

public class AutoPayViewModel extends ViewModel {

    private PaymentRepository paymentRepository;

    public AutoPayViewModel(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public LiveData<PaymentResponse> makePayment(String paymentGateway, double amount) {
        return paymentRepository.makePayment(paymentGateway, amount);
    }
}
