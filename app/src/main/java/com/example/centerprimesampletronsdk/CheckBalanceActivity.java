package com.example.centerprimesampletronsdk;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.centerprime.tronsdk.sdk.TronWalletManager;
import com.example.centerprimesampletronsdk.databinding.ActivityCheckBalanceBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CheckBalanceActivity extends AppCompatActivity {
    ActivityCheckBalanceBinding balanceBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        balanceBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_balance);

        /**
         * Using this getBalanceTrx function you can check balance of provided walletAddress.
         *
         * @params walletAddress, context
         *
         * @return balance
         */

        TronWalletManager tronWalletManager = TronWalletManager.getInstance();
        tronWalletManager.init(this);
        balanceBinding.checkBtn.setOnClickListener(v -> {
            String address = balanceBinding.address.getText().toString();
//            if (!address.startsWith("0x")) {
//                address = "0x" + address;
//            }

            tronWalletManager.getBalanceTrx(address, this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(balance -> {

                        balanceBinding.balanceTxt.setText("Trx balance: " + balance.toString());
                        balanceBinding.balanceTxt.setVisibility(View.VISIBLE);
                     //   Toast.makeText(this, "Eth Balance : " + balance, Toast.LENGTH_SHORT).show();

                    }, error -> {
                        balanceBinding.balanceTxt.setVisibility(View.INVISIBLE);

                        System.out.println(error.getMessage());

                        Toast.makeText(this,  error.getMessage(), Toast.LENGTH_SHORT).show();

                    });
        });
    }
}
