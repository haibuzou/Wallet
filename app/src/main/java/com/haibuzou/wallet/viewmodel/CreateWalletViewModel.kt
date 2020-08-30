package com.haibuzou.wallet.viewmodel

import androidx.lifecycle.ViewModel
import com.haibuzou.wallet.WalletApplication
import com.haibuzou.wallet.domain.ETHWallet
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.wallet.DeterministicSeed
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import org.web3j.crypto.Wallet
import org.web3j.crypto.WalletFile
import java.io.File

class CreateWalletViewModel : ViewModel() {

    companion object {
        @JvmStatic
        var ETH_JAXX_TYPE = "m/44'/60'/0'/0/0"
    }

    //创建钱包
    fun createWallet() {

    }

    private fun _generaWallet(walletName: String?, ds: DeterministicSeed, pathArray: Array<String>, pwd: String?): ETHWallet? {
        val seedBytes = ds.seedBytes
        //助记词
        val mnemonic = ds.mnemonicCode
        if (seedBytes == null)
            return null

        var dkKey = HDKeyDerivation.createMasterPrivateKey(seedBytes)
        for (i in 1 until pathArray.size) {
            var childNumber: ChildNumber
            childNumber = if (pathArray[i].endsWith("'")) {
                val number = pathArray[i].substring(
                    0,
                    pathArray[i].length - 1
                ).toInt()
                ChildNumber(number, true)
            } else {
                val number = pathArray[i].toInt()
                ChildNumber(number, false)
            }
            dkKey = HDKeyDerivation.deriveChildKey(dkKey, childNumber)
        }
        val keyPair = ECKeyPair.create(dkKey.privKeyBytes)
        val ethWallet = generateWallet(walletName, pwd, keyPair)
        if (ethWallet != null) {
            ethWallet.setMnemonic(convertMnemonicList(mnemonic))
        }
        return ethWallet
    }

    private fun generateWallet(walletName: String?, pwd: String?, ecKeyPair: ECKeyPair): ETHWallet? {
        val walletFile: WalletFile
        walletFile = try {
            Wallet.create(pwd, ecKeyPair, 1024, 1) // WalletUtils. .generateNewWalletFile();
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        val publicKey = ecKeyPair.publicKey
        val s = publicKey.toString()
        val wallet_dir = WalletApplication.walletApplicationInstance?.getExternalCacheDir().toString() + "/downloads/"
        val keystorePath = "keystore_$walletName.json"
        val destination = File(wallet_dir, "keystore_$walletName.json")

        //目录不存在则创建目录，创建不了则报错
//    if (!createParentDir(destination)) {
//        return null
//    }
//    try {
//       objectMapper.writeValue(destination, walletFile)
//    } catch (e: IOException) {
//        e.printStackTrace()
//        return null
//    }
        val ethWallet = ETHWallet(Keys.toChecksumAddress(walletFile.address))
        ethWallet.setName(walletName)
        ethWallet.setAddress(Keys.toChecksumAddress(walletFile.address))
        ethWallet.setKeystorePath(destination.absolutePath)
        ethWallet.setPassword(Md5Utils.md5(pwd))
        return ethWallet
    }

    private fun convertMnemonicList(mnemonics: List<String>): String? {
        val sb = StringBuilder()
        for (mnemonic in mnemonics) {
            sb.append(mnemonic)
            sb.append(" ")
        }
        return sb.toString()
    }

}




