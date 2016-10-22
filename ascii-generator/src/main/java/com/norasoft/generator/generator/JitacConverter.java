package com.norasoft.generator.generator;

import org.roqe.jitac.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.Vector;

@Component
class JitacConverter implements PictureToAsciiConverter{
    private final static boolean DEBUG_OFF = false;
    private final static boolean ROUND = true;
    private final static double NOISE = 0d;
    private final static boolean WITH_HTML = false;
    private final static boolean WITH_COLOR_HTML = true;
    private final static boolean SMOOTH = false;
    private final static int IN_COLOR_MASK = 0;
    private final static int OUT_COLOR_MASK = 1|2|3;
    private final static int CHAR_START = 32;
    private final static int CHAR_END = 126;


    public ColourfulAscii convertToASCII(File sourceFile) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);

        URL fontUrl = Jitac.class.getResource("fonts/courier10.bdf");

        try {
            Image img = new Image(sourceFile.getAbsolutePath(), DEBUG_OFF);

            FixedFont font = BDFFont.load(fontUrl, DEBUG_OFF);
            Vector inputs = font.getVektors(CHAR_START, CHAR_END);

            Jitac jitac = new Jitac(img, font, inputs);
            jitac.convert(out, ROUND, DEBUG_OFF, NOISE,
                    WITH_HTML, WITH_COLOR_HTML, SMOOTH,
                    IN_COLOR_MASK, OUT_COLOR_MASK);

        } catch (Exception e) {
            throw new ConversionException("Error during conversion", e);
        }

        return new ColourfulAscii(baos);
    }
}
