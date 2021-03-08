package io.usoamic.commons.crossplatform.usecases

import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import io.reactivex.Single
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.repositories.api.PreferencesRepository

import javax.inject.Inject

class DepositUseCases @Inject constructor(
    private val mPreferencesRepository: PreferencesRepository
) {
    fun getAddress(): String {
        return mPreferencesRepository.getAddress()
    }

    fun generateQrCode(content: String): Single<BitMatrix> {
        return Single.fromCallable {
            val writer = QRCodeWriter()
            writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
        }
            .addDebugDelay()
    }
}