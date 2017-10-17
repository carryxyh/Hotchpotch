package com.iydsj.sw.common.utils;

import com.iydsj.sw.common.utils.exceptions.BizException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Iterator;

public class ImageUtils {

    public static OutputStream cutPic(InputStream in,
                                      int width, int height) {
        return cutPic(in, width, height, "jpg");
    }

    public static OutputStream cutPic(InputStream in,
                                      int width, int height, String format) {
        try {
            Iterator iterator = ImageIO.getImageReadersByFormatName(format);
            ImageReader reader = (ImageReader) iterator.next();
            ImageInputStream iis = ImageIO.createImageInputStream(in);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            int imageIndex = 0;
            Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - width) / 2, (reader.getHeight(imageIndex) - height) / 2, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            OutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bi, format, outputStream);
            return outputStream;
        } catch (Exception e) {
            throw new BizException(e.getMessage(), e);
        }
    }
}

