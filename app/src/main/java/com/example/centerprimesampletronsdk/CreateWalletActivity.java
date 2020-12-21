package com.example.centerprimesampletronsdk;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.centerprime.tronsdk.sdk.TronWalletManager;
import com.example.centerprimesampletronsdk.databinding.ActivityCreateWalletBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CreateWalletActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    ActivityCreateWalletBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_wallet);

        /**
         * Using this createWallet function user can create a wallet.
         *
         * @params password, Context
         *
         * @return walletAddress
         */

        TronWalletManager tronWalletManager = TronWalletManager.getInstance();
        tronWalletManager.init(this);

        binding.createWallet.setOnClickListener(v -> {


            if(!TextUtils.isEmpty(binding.password.getText().toString()) && !TextUtils.isEmpty(binding.confirmPassword.getText().toString())
                    && binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())
                    && binding.password.getText().toString().trim().length() >= 6
                    && binding.confirmPassword.getText().toString().trim().length() >= 6) {

                progressDialog = ProgressDialog.show(CreateWalletActivity.this, "",
                        "Creating TRX wallet address...", true);


            tronWalletManager.createWallet(binding.password.getText().toString(), this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(walletAddress -> {

                        progressDialog.dismiss();

                        binding.address.setText(walletAddress.getAddress());

                        binding.copy.setVisibility(View.VISIBLE);

                        System.out.println(walletAddress);


                    }, error -> {

                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    });
            } else {
                Toast.makeText(this, "Please insert password correctly.", Toast.LENGTH_SHORT).show();
            }
        });


        binding.copy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", binding.address.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied!", Toast.LENGTH_SHORT).show();
        });

    }
}
