package com.haibuzou.wallet

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.haibuzou.wallet.dao.AppDatabase

class WalletApplication : Application() {

    var walletDB: RoomDatabase? = null

    override fun onCreate() {
        super.onCreate()
        walletApplicationInstance = this
        walletDB = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    companion object {
        @JvmStatic
        var walletApplicationInstance : WalletApplication? = null
    }

}