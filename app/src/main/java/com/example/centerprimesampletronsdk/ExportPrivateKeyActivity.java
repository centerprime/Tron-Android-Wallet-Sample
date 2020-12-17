package com.example.centerprimesampletronsdk;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.centerprime.tronsdk.sdk.TronWalletManager;
import com.example.centerprimesampletronsdk.databinding.ActivityExportPrivateKeyBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExportPrivateKeyActivity extends AppCompatActivity {
    ActivityExportPrivateKeyBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_export_private_key);

        /**
         * Using this exportPrivateKey function user can export walletAddresses privateKey.
         *
         * @params walletAddress, password, Context
         *
         * @return privateKey
         */

        TronWalletManager tronWalletManager = TronWalletManager.getInstance();
        tronWalletManager.init(this);

//
//        binding.button.setOnClickListener(v -> {
//
//            String walletAddress = binding.address.getText().toString();
//            if(walletAddress.startsWith("0x")){
//                walletAddress = walletAddress.substring(2);
//            }
//            String password = binding.password.getText().toString();
//            tronWalletManager.exportPrivateKey(walletAddress, password,this)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(privatekey -> {
//
//                        binding.privateKey.setText(privatekey);
//
//                        binding.copy.setVisibility(View.VISIBLE);
//
//                    }, error -> {
//                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                    });
//        });

        binding.copy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", binding.privateKey.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied!", Toast.LENGTH_SHORT).show();
        });
    }
}
