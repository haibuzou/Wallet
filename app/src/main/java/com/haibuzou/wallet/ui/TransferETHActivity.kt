package com.haibuzou.wallet.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haibuzou.wallet.R

class TransferETHActivity : AppCompatActivity() {

    var walletName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_eth)
        initIntent()
        initView()
    }

    fun initIntent() {
        walletName = intent.getStringExtra(WALLET_NAME)
    }

    fun initView() {

    }

    companion object {

        val WALLET_NAME = "wallet_name"

        fun gotoTransfer(context: Context, walletName: String?) {
            context.startActivity(Intent(context, TransferETHActivity::class.java).apply {
                putExtra(WALLET_NAME, walletName)
            })
        }
    }
}