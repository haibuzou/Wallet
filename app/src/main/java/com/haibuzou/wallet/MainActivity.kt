package com.haibuzou.wallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haibuzou.wallet.ui.ImportWalletActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        btn_import_wallet.setOnClickListener {
            ImportWalletActivity.gotoImportWallet(this)
        }
    }

}