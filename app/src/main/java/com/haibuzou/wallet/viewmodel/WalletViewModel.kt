package com.haibuzou.wallet.viewmodel

import androidx.lifecycle.ViewModel
import com.haibuzou.wallet.WalletApplication
import com.haibuzou.wallet.dao.WalletDaoManager
import com.haibuzou.wallet.domain.ETHWallet
import com.haibuzou.wallet.kotlin.md5
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.wallet.DeterministicSeed
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import org.web3j.crypto.Wallet
import org.web3j.crypto.WalletFile
import java.io.File

class WalletViewModel : ViewModel() {

    companion object {
        @JvmStatic
        var ETH_JAXX_TYPE = "m/44'/60'/0'/0/0"
    }

    //通过助记词导入钱包
    fun importWalletFromMnemonic(path: String, list: List<String> , pwd: String) : ETHWallet? {
        if (!path.startsWith("m") && !path.startsWith("M")) {
            return null
        }
        val pathArray = path.split("/".toRegex()).toTypedArray()
        if (pathArray.size <= 1) {
            return null
        }
        val passphrase = ""
        val creationTimeSeconds = System.currentTimeMillis() / 1000
        val ds = DeterministicSeed(list, null, passphrase, creationTimeSeconds)
        return _generaWallet(generateNewWalletName(), ds,
            pathArray,
            pwd
        )
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
        var mnemonicWords = convertMnemonicList(mnemonic)
        val ethWallet = generateWallet(walletName, pwd, keyPair, mnemonicWords)
        ethWallet?.mnemonic = mnemonicWords

        return ethWallet
    }

    private fun generateWallet(walletName: String?, pwd: String?, ecKeyPair: ECKeyPair, mnemonicWords: String?): ETHWallet? {
        val walletFile: WalletFile
        walletFile = try {
            Wallet.create(pwd, ecKeyPair, 1024, 1) // WalletUtils. .generateNewWalletFile();
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        val wallet_dir = WalletApplication.getInstance?.externalCacheDir?.path
        val destination = File(wallet_dir, "keystore_$walletName.json")
        return ETHWallet(Math.random().toLong(), Keys.toChecksumAddress(walletFile.address),walletName,pwd?.md5(),destination.absolutePath,mnemonicWords,false)
    }

    private fun convertMnemonicList(mnemonics: List<String>?): String? {
        if (mnemonics.isNullOrEmpty()) {
            return ""
        }

        val sb = StringBuilder()
        for (mnemonic in mnemonics) {
            sb.append(mnemonic)
            sb.append(" ")
        }
        return sb.toString()
    }

    private fun generateNewWalletName(): String? {
        var letter1 = (Math.random() * 26 + 97).toInt().toChar()
        var letter2 = (Math.random() * 26 + 97).toInt().toChar()
        var walletName = "$letter1$letter2-新钱包"
        while (WalletDaoManager.walletNameChecking(walletName)) {
            letter1 = (Math.random() * 26 + 97).toInt().toChar()
            letter2 = (Math.random() * 26 + 97).toInt().toChar()
            walletName = "$letter1$letter2-新钱包"
        }
        return walletName
    }

}




