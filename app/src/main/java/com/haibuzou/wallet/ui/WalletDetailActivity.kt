package com.haibuzou.wallet.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.haibuzou.wallet.R

class WalletDetailActivity: AppCompatActivity() {

    lateinit var walletNameTxt: TextView
    var walletName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_detail)
        initIntent()
        initView()
    }

    fun initIntent() {
        walletName = intent.getStringExtra(WALLET_NAME)
    }

    fun initView() {
        walletNameTxt = findViewById(R.id.tv_wallet_name)
        walletNameTxt.text = walletName
    }

    companion object {

        val WALLET_NAME = "wallet_name"

        fun gotoWalletDetail(context: Context, walletName: String) {
            context.startActivity(Intent(context, WalletDetailActivity::class.java).apply {
                putExtra(WALLET_NAME, walletName)
            })
        }
    }
}