package com.enterprise.advancedencryptionstandard

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enterprise.advancedencryptionstandard.aes.AESConstants
import com.enterprise.advancedencryptionstandard.aes.AESManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    val TAG = "ExampleInstrumentedTest"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.enterprise.advancedencryptionstandard", appContext.packageName)
    }


    @Test
    fun testKeyGeneration() {

        val key = AESManager.generateRandomKey()

    }

    @Test
    fun testInitializationVectorGeneration() {

        val initializationVector = AESManager.generateRandomInitializationVector()

    }

    @Test
    fun testEncryptionDecryption(){

        val plainText = "Test Successful!"

        val encryptedText = AESManager.encrypt(plainText, AESConstants.Key, AESConstants.InitializationVector)
        Log.d(TAG, "encryptedText: " + encryptedText)

        val decryptedText = AESManager.decrypt(encryptedText, AESConstants.Key, AESConstants.InitializationVector)
        Log.d(TAG, "decryptedText: " + decryptedText)

        assertEquals(plainText, decryptedText)

    }



}