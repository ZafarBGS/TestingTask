package com.rfereloan.testingtask.ui.helper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rfereloan.testingtask.ui.model.PaymentResponse;

// PaymentRepositoryImpl.java
public class PaymentRepositoryImpl implements PaymentRepository {

    private MutableLiveData<PaymentResponse> mockResponseLiveData = new MutableLiveData<>();

    @Override
    public LiveData<PaymentResponse> makePayment(String paymentGateway, double amount) {
        // In a real app, you would make an API call to the selected payment gateway.
        // For now, return the mock response.
        return getMockPaymentResponse(paymentGateway);
    }

    @Override
    public LiveData<PaymentResponse> getMockPaymentResponse(String paymentGateway) {
        // Simulate a mock response based on the selected payment gateway.
        PaymentResponse mockResponse = new PaymentResponse();

        switch (paymentGateway) {
            case "Airtel":
                mockResponse.setMessage("Airtel Payment Successful");
                mockResponse.setSuccess(true);
                break;
            case "FlexiPay":
                mockResponse.setMessage("FlexiPay Payment Successful");
                mockResponse.setSuccess(true);
                break;
            case "MTN":
                mockResponse.setMessage("MTN Payment Successful");
                mockResponse.setSuccess(true);
                break;
            default:
                mockResponse.setMessage("Invalid payment gateway");
                mockResponse.setSuccess(false);
        }

        mockResponseLiveData.postValue(mockResponse);
        return mockResponseLiveData;
    }
}
