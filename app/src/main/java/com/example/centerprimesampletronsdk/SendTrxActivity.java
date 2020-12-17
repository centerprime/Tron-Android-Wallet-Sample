package com.example.centerprimesampletronsdk;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.centerprime.tronsdk.sdk.TronWalletManager;
import com.example.centerprimesampletronsdk.databinding.ActivitySendEthBinding;
import java.math.BigDecimal;
import java.math.BigInteger;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SendTrxActivity extends AppCompatActivity {
    ActivitySendEthBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_send_eth);

        /**
         * Using this sendEther function you can send TRX from walletAddress to another walletAddress.
         *
         * @params Context, senderWalletAddress, password, receiverWalletAddress, trxAmount
         *
         * @return transactionHash
         */


        TronWalletManager tronWalletManager = TronWalletManager.getInstance();
        tronWalletManager.init(this);

        binding.sendEth.setOnClickListener(v -> {
            if(!TextUtils.isEmpty(binding.address.getText().toString().trim()) && !TextUtils.isEmpty(binding.trxAmount.getText().toString().trim())
                    && !TextUtils.isEmpty(binding.gasLimit.getText().toString().trim())
                    && !TextUtils.isEmpty(binding.receiverAddress.getText().toString().trim())
                    && !TextUtils.isEmpty(binding.password.getText().toString().trim())) {

                String walletAddress = binding.address.getText().toString();
                String password = binding.password.getText().toString();

                BigDecimal trxAmount = new BigDecimal(binding.trxAmount.getText().toString().trim());
                String receiverAddress = binding.receiverAddress.getText().toString().trim();

                tronWalletManager.sendTRX(this, walletAddress, password, receiverAddress, trxAmount)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(tx -> {

                            Toast.makeText(this, "TX : " + tx, Toast.LENGTH_SHORT).show();

                        }, error -> {

                            binding.result.setText(error.getMessage() + ". Please check balance of provided walletaddress!");

                            error.printStackTrace();
                            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }

        });
    }
}
