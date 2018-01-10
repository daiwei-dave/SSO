package com.github.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具
 * Created by daiwei on 2017/2/13.
 */
public class QrCodeUtils {


    public static void writeToStream(String content, int width, int height, String format, OutputStream outputStream) throws Exception {
        Writer writer = new MultiFormatWriter();
        Map<EncodeHintType, String> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hintMap);
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
    }

    public static BufferedImage writeToBufferImage(String content, int width, int height) throws Exception {
        Writer writer = new MultiFormatWriter();
        Map<EncodeHintType, String> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hintMap);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
