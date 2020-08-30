package com.haibuzou.wallet.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ETHWallet(
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "address") var address: String?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "keystorePath") var keystorePath: String?,
    @ColumnInfo(name = "mnemonic") var mnemonic: String?,
    @ColumnInfo(name = "isBackup") var isBackup: Boolean?
)
