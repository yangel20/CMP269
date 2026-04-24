package com.example.myapp

import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import java.nio.file.FileSystems

fun saveQRCode(content: String, fileName: String) {
    val width = 500
    val height = 500

    // Step 1: Encode the content into a BitMatrix
    val bitMatrix = QRCodeWriter().encode(
        content,
        BarcodeFormat.QR_CODE,
        width,
        height
    )

    // Step 2: Define the output path
    val path = FileSystems.getDefault().getPath(fileName)

    // Step 3: Write the BitMatrix to a PNG file
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path)

    println("QR code saved to $fileName")
}

fun main() {
    saveQRCode(
        content = "yourname@lehman.cuny.edu",  // Replace with your Lehman email
        fileName = "my_email.png"
    )
}