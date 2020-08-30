package com.haibuzou.wallet.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.haibuzou.wallet.domain.Wallet

@Dao
interface WalletDao {
    @Query("SELECT * FROM wallet")
    fun getAll(): List<Wallet>

    @Query("SELECT * FROM wallet WHERE id IN (:walletIds)")
    fun loadAllByIds(walletIds: IntArray): List<Wallet>

    @Insert
    fun insertAll(vararg wallets: Wallet)

    @Delete
    fun delete(wallet: Wallet)

}