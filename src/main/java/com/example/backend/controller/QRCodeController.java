package com.example.backend.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/qr")
public class QRCodeController {

    @GetMapping(value = "/**", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateQrCode(HttpServletRequest request){
        try {
            String fullPath = request.getRequestURI();
            String barcode = fullPath.substring(fullPath.indexOf("/qr/") + 4);

            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(barcode, BarcodeFormat.QR_CODE, 200, 200);

            BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            return ResponseEntity.ok(qrCodeImage);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
