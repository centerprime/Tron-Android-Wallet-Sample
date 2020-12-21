package com.example.centerprimesampletronsdk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.centerprimesampletronsdk.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.createBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CreateWalletActivity.class));
        });

        binding.importBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ImportWalletFromKeyStoreActivity.class));
        });

        binding.checkBalance.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CheckBalanceActivity.class));
        });

        binding.exportKeystore.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ExportKeyStoreActivity.class));
        });


        binding.sendTrx.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SendTrxActivity.class));
        });
        binding.checkERCTokenkBalance.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CheckERCTokenBalanceActivity.class));
        });



    }
}