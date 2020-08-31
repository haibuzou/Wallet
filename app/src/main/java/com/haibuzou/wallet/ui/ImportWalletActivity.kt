package com.haibuzou.wallet.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.haibuzou.wallet.R
import com.haibuzou.wallet.kotlin.obtainViewModel
import com.haibuzou.wallet.viewmodel.WalletViewModel
import com.haibuzou.wallet.viewmodel.WalletViewModel.Companion.ETH_JAXX_TYPE
import kotlinx.android.synthetic.main.activity_import_wallet.*

class ImportWalletActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_wallet)
        walletViewModel = obtainViewModel(WalletViewModel::class.java)
        btn_import_wallet.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_import_wallet -> {
                if (getMnemonicWord().size != 12) {
                    Toast.makeText(this, "请输入正确数量的助记词", Toast.LENGTH_LONG).show()
                }
                if (checkPassWord()) {
                    walletViewModel.importWalletFromMnemonic(ETH_JAXX_TYPE, getMnemonicWord(), edt_pwd_input.text.toString())
                }
            }
        }
    }

    fun getMnemonicWord() : List<String> = edt_mnemonic_input.text.toString().split(" ")

    fun checkPassWord() : Boolean {
        if (edt_pwd_input.text.isNullOrEmpty() || edt_pwd_sure_input.text.isNullOrEmpty()) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show()
            return false
        }

        if (edt_pwd_input.text != edt_pwd_sure_input.text) {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    companion object {
        @JvmStatic
        fun gotoImportWallet(context: Context) {
            context.startActivity(Intent(context, ImportWalletActivity::class.java))
        }
    }
}