package com.haibuzou.wallet.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.haibuzou.wallet.domain.ETHWallet

@Dao
interface WalletDao {
    @Query("SELECT * FROM ethwallet")
    fun getAll(): List<ETHWallet>

    @Query("SELECT * FROM ethwallet WHERE id IN (:walletIds)")
    fun loadAllByIds(walletIds: IntArray): List<ETHWallet>

    @Insert
    fun insertAll(vararg ETHWallets: ETHWallet)

    @Delete
    fun delete(ETHWallet: ETHWallet)

}