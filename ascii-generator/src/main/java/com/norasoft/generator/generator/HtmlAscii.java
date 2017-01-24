package com.norasoft.generator.generator;

public class HtmlAscii {
    private final ColourfulAscii ascii;
    private final String label;


    public HtmlAscii(ColourfulAscii ascii, String label) {
        this.ascii = ascii;
        this.label = label;
    }

    public HtmlAscii(ColourfulAscii ascii) {
        this(ascii, "");
    }

    public String asString() {
        return "<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n"+
                "                       \"http://www.w3.org/TR/html4/loose.dtd\">\n"+
                "<html>\n\n"+
                "<head>\n"+
                "</head>\n\n" +
                "<body bgcolor=\"white\" text=\"black\">" +
                "<tt>\n" +
                "<font size=\"2\">" +

                "<div>"+label+"</div>" +

                ascii.asString() +

                "\n" +
                "</font>\n" +
                "</tt>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
