package com.norasoft.generator.generator;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.norasoft.generator.generator.ColourfulAscii;
import com.norasoft.generator.generator.HtmlAscii;
import com.norasoft.generator.generator.PictureToAsciiConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
public class AsciiService {

    private final PictureToAsciiConverter converter;
    private final String pictureLabel;

    @Autowired
    public AsciiService(PictureToAsciiConverter converter,
                        @Value("${info.picture-label}") String pictureLabel) {
        this.converter = converter;
        this.pictureLabel = pictureLabel;
    }

    public HtmlAscii generateAscii(URL imageUrl) {

        File imageFile = saveImageToTemporaryFile(imageUrl);

        ColourfulAscii colourfulAscii = converter.convertToASCII(imageFile);

        return new HtmlAscii(colourfulAscii, pictureLabel);
    }

    private File saveImageToTemporaryFile(URL imageUrl) {
        String fileName = UUID.randomUUID() + ".jpg";
        String tempDir = System.getProperty("java.io.tmpdir");
        File imageFile = new File(tempDir, fileName);

        try {
            FileUtils.copyURLToFile(imageUrl, imageFile);
        } catch (IOException e) {
            throw new RuntimeException("Cannot download image.", e);
        }

        return imageFile;
    }
}
