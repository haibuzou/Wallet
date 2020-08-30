package com.haibuzou.wallet.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haibuzou.wallet.domain.Wallet

@Database(entities = arrayOf(Wallet::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
}