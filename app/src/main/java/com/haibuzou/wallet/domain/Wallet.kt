package com.haibuzou.wallet.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "keystorePath") val keystorePath: String?,
    @ColumnInfo(name = "mnemonic") val mnemonic: String?,
    @ColumnInfo(name = "isCurrent") val isCurrent: Boolean?,
    @ColumnInfo(name = "isBackup") val isBackup: Boolean?
)
