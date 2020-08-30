package com.haibuzou.wallet.dao

import com.haibuzou.wallet.WalletApplication

object WalletDaoManager {

    fun walletNameChecking(name: String) : Boolean{
        WalletApplication.getInstance?.walletDB?.walletDao()?.let {
            return it.getAll().find { ethWallet -> ethWallet.name === name } == null
        }
        return false
    }
}