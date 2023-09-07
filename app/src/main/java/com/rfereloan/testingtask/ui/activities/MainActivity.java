package com.rfereloan.testingtask.ui.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.rfereloan.testingtask.ui.R;
import com.rfereloan.testingtask.ui.databinding.ActivityDashboardBinding;
import com.rfereloan.testingtask.ui.helper.PaymentRepository;
import com.rfereloan.testingtask.ui.helper.PaymentRepositoryImpl;
import com.rfereloan.testingtask.ui.viewmodel.AutoPayViewModel;
import com.rfereloan.testingtask.ui.viewmodel.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private AutoPayViewModel viewModel;
    TextView tv_valid_till_date, tv_valid_upto_date, tv_frequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using DataBindingUtil
        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        tv_valid_till_date = (TextView)findViewById(R.id.tv_valid_till_date);
        String date_ = "09-Sep-2023";
        String date_till = "Valid till- " + "<b>" + date_  + "</b>";
        tv_valid_till_date.setText(Html.fromHtml(date_till));

        tv_valid_upto_date = (TextView)findViewById(R.id.tv_valid_upto_date);
        String date_upto = "UGX 10,000";
        String date_upto_ = "Upto Amount- " + "<b>" + date_upto  + "</b>";
        tv_valid_upto_date.setText(Html.fromHtml(date_upto_));

        tv_frequency = (TextView)findViewById(R.id.tv_frequency);
        String frequency = "Presented";
        String frequency_ = "As " + "<b>" + frequency  + "</b>";
        tv_frequency.setText(Html.fromHtml(frequency_));

        // Create or retrieve an instance of PaymentRepository (You need to implement this)
        PaymentRepository paymentRepository = getPaymentRepositoryInstance();

        // Create ViewModelFactory with the PaymentRepository instance
        ViewModelFactory viewModelFactory = new ViewModelFactory(paymentRepository);

        // Get an instance of AutoPayViewModel using ViewModelProvider
        viewModel = new ViewModelProvider(this, viewModelFactory).get(AutoPayViewModel.class);

        // Bind the ViewModel to the layout
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Handle UI interactions, e.g., payment gateway selection and payment amount entry.
        binding.payButton.setOnClickListener(v -> {
            String selectedPaymentGateway = getSelectedPaymentGateway(); // Implement this method
            double amount = getPaymentAmount(); // Implement this method
            viewModel.makePayment(selectedPaymentGateway, amount)
                    .observe(this, paymentResponse -> {
                        if (paymentResponse.isSuccess()) {
                            // Payment successful, handle accordingly.
                            showToast(paymentResponse.getMessage());
                        } else {
                            // Payment failed, show an error message.
                            showToast("Payment failed: " + paymentResponse.getMessage());
                        }
                    });
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private PaymentRepository getPaymentRepositoryInstance() {
        // Create and return an instance of your PaymentRepository implementation here
        return new PaymentRepositoryImpl();
    }


    private String getSelectedPaymentGateway() {
        // Implement this method to return the selected payment gateway
        // Example:
         return "Airtel";
    }

    private double getPaymentAmount() {
        // Implement this method to return the payment amount
        // Example:
         return 100.0;
    }
}
