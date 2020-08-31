package com.haibuzou.wallet

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.haibuzou.wallet.ui.ImportWalletActivity

class MainActivity : AppCompatActivity() {

    lateinit var importWalletBtn: Button
    lateinit var createWalletBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        importWalletBtn = findViewById(R.id.btn_import_wallet)
        createWalletBtn = findViewById(R.id.btn_create_wallet)
        importWalletBtn.setOnClickListener {
            ImportWalletActivity.gotoImportWallet(this)
        }

        createWalletBtn.setOnClickListener {
            Toast.makeText(this, "敬请期待", Toast.LENGTH_LONG).show()
        }
    }

}