package com.norasoft.generator.generator;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class ColourfulAscii {
    private final String content;

    public ColourfulAscii(String content) {
        this.content = content;
    }

    public ColourfulAscii(ByteArrayOutputStream baos) {
        try {
            this.content = baos.toString("UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is unsupported", e);
        }
    }

    public String asString() {
        return this.content;
    }
}
