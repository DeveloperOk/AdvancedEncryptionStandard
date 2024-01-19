package com.enterprise.advancedencryptionstandard.aes

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESManager {

    companion object {

        fun encrypt(plainText: String, keyBase64Encoded: String, initializationVectorBase64Encoded: String): String {

            val secretKeySpec = SecretKeySpec(Base64.decode(keyBase64Encoded, Base64.DEFAULT), "AES")
            val initializationVectorParameterSpec = IvParameterSpec(Base64.decode(initializationVectorBase64Encoded, Base64.DEFAULT))

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, initializationVectorParameterSpec)

            val encryptedBytes = cipher.doFinal(plainText.toByteArray())

            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)

        }

        fun decrypt(encryptedText: String, keyBase64Encoded: String, initializationVectorBase64Encoded: String): String {

            val secretKeySpec = SecretKeySpec(Base64.decode(keyBase64Encoded, Base64.DEFAULT), "AES")
            val initializationVectorParameterSpec = IvParameterSpec(Base64.decode(initializationVectorBase64Encoded, Base64.DEFAULT))

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, initializationVectorParameterSpec)

            val encryptedBytes = Base64.decode(encryptedText, Base64.DEFAULT)

            val decryptedBytes = cipher.doFinal(encryptedBytes)

            return String(decryptedBytes)

        }

        fun generateRandomKey():String {

            val key = ByteArray(16)
            SecureRandom().nextBytes(key)

            return Base64.encodeToString(key, Base64.DEFAULT)

        }

        fun generateRandomInitializationVector():String {

            val intializationVector = ByteArray(16)
            SecureRandom().nextBytes(intializationVector)

            return Base64.encodeToString(intializationVector, Base64.DEFAULT)

        }

    }

}