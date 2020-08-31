package com.haibuzou.wallet.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haibuzou.wallet.R
import com.haibuzou.wallet.domain.WalletModel


class WalletListActivity : AppCompatActivity() {

    lateinit var walletListRecycleView: RecyclerView
    lateinit var adapter: WalletListAdapter
    var walletList = ArrayList<WalletModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_list)
        initIntent()
        initView()
    }

    fun initIntent() {
        val intentWalletName = intent.getStringExtra(WALLET_NAME)
        walletList.add(WalletModel().apply {
            walletName = intentWalletName
        })
    }

    fun initView() {
        walletListRecycleView = findViewById(R.id.rv_wallet)
        walletListRecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = WalletListAdapter(walletList)
        walletListRecycleView.adapter = adapter
    }

    inner class WalletListAdapter(var walletList: ArrayList<WalletModel>) : RecyclerView.Adapter<WalletViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
            return WalletViewHolder(LayoutInflater.from(this@WalletListActivity).inflate(R.layout.view_holder_wallet, parent, false))
        }

        override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
            holder.bindViewHolder(walletList[position])
        }

        override fun getItemCount(): Int = walletList.size

        override fun getItemViewType(position: Int): Int = WALLET_TYPE
    }

    inner class WalletViewHolder(var rootView: View) : RecyclerView.ViewHolder(rootView), View.OnClickListener {

        var walletNameTxt: TextView

        init {
            walletNameTxt = rootView.findViewById(R.id.tv_wallet_name)
        }

        fun bindViewHolder(walletModel: WalletModel) {
            walletNameTxt.text = walletModel.walletName
            walletNameTxt.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }
    }

    companion object {
        val WALLET_TYPE = 1
        val WALLET_NAME = "wallet_name"

        fun gotoWalletList(context: Context, walletName: String) {
            context.startActivity(Intent(context, WalletListActivity::class.java).apply {
                putExtra(WALLET_NAME, walletName)
            })
        }
    }
}