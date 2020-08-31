package com.haibuzou.wallet

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.haibuzou.wallet.ui.ImportWalletActivity

class MainActivity : AppCompatActivity() {

    lateinit var importWalletBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        importWalletBtn = findViewById(R.id.btn_import_wallet)
        importWalletBtn.setOnClickListener {
            ImportWalletActivity.gotoImportWallet(this)
        }
    }

}