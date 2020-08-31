package com.haibuzou.wallet.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.haibuzou.wallet.R
import com.haibuzou.wallet.kotlin.obtainViewModel
import com.haibuzou.wallet.viewmodel.WalletViewModel
import com.haibuzou.wallet.viewmodel.WalletViewModel.Companion.ETH_JAXX_TYPE

class ImportWalletActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var walletViewModel: WalletViewModel
    lateinit var importWalletConfirmBtn: Button
    lateinit var mnemonicInputEdt: EditText
    lateinit var passwordInputEdt: EditText
    lateinit var passwordSureInputEdt: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_wallet)
        walletViewModel = obtainViewModel(WalletViewModel::class.java)
        initView()
    }

    fun initView() {
        importWalletConfirmBtn = findViewById(R.id.btn_import_wallet)
        mnemonicInputEdt = findViewById(R.id.edt_mnemonic_input)
        passwordInputEdt = findViewById(R.id.edt_pwd_input)
        passwordSureInputEdt = findViewById(R.id.edt_pwd_sure_input)
        importWalletConfirmBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_import_wallet -> {
                if (getMnemonicWord().size != 12) {
                    Toast.makeText(this, "请输入正确数量的助记词", Toast.LENGTH_LONG).show()
                }
                if (checkPassWord()) {
                    var ethWallet = walletViewModel.importWalletFromMnemonic(ETH_JAXX_TYPE, getMnemonicWord(), passwordInputEdt.text.toString())
                    ethWallet?.name?.let {
                        WalletListActivity.gotoWalletList(this, it)
                    }
                }
            }
        }
    }

    fun getMnemonicWord() : List<String> = mnemonicInputEdt.text.toString().split(" ")

    fun checkPassWord() : Boolean {
        if (passwordInputEdt.text.isNullOrEmpty() || passwordSureInputEdt.text.isNullOrEmpty()) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show()
            return false
        }

        if (passwordSureInputEdt.text.toString() != passwordInputEdt.text.toString()) {
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