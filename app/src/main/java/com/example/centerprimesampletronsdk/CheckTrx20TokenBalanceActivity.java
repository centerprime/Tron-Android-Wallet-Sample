package com.example.centerprimesampletronsdk;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.centerprime.tronsdk.sdk.TronWalletManager;
import com.example.centerprimesampletronsdk.databinding.ActivityErc20TokenBalanceBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CheckTrx20TokenBalanceActivity extends AppCompatActivity {
    ActivityErc20TokenBalanceBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_erc20_token_balance);

        /**
         * Using this getTokenTRX20Balance function you can check balance of provided walletAddress and contractAddress.
         *
         * @params walletAddress, contractAddress, context
         *
         * @return balance
         */

        TronWalletManager tronWalletManager = TronWalletManager.getInstance();
        tronWalletManager.init(this);
        binding.checkBtn.setOnClickListener(v -> {

            String walletAddress = binding.address.getText().toString().trim();
            String trx20TokenBalance = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";
            tronWalletManager.getTokenTRX20Balance(walletAddress, trx20TokenBalance, this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(balance -> {

                        binding.balanceTxt.setText("Token Balance :" + balance.toString());
                        Toast.makeText(this, "Token Balance : " + balance, Toast.LENGTH_SHORT).show();

                    }, error -> {
                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
