package com.rfereloan.testingtask.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.rfereloan.testingtask.ui.helper.PaymentRepository;
import com.rfereloan.testingtask.ui.viewmodel.AutoPayViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final PaymentRepository paymentRepository;

    private static ViewModelFactory instance;

    public ViewModelFactory(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public static synchronized ViewModelFactory getInstance(PaymentRepository paymentRepository) {
        if (instance == null) {
            instance = new ViewModelFactory(paymentRepository);
        }
        return instance;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AutoPayViewModel.class)) {
            return (T) new AutoPayViewModel(paymentRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
