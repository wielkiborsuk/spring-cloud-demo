package pl.com.bms.generator;

import java.io.File;

public interface PictureToAsciiConverter {
    ColourfulAscii convertToASCII(File sourcePath);
}
