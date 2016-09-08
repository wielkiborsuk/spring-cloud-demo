package pl.com.bms.generator

import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

import static Ascii.expectedAscii

class AsciiGeneratorSpec extends Specification {

    def "Should generate colourful ascii"() {
        given:
        JitacConverter jitacConverter = new JitacConverter()
        File testImageFile = new File(new ClassPathResource("logo.jpg").getURI())

        when:
        ColourfulAscii ascii = jitacConverter.convertToASCII(testImageFile);

        then:
        ascii.asString() == expectedAscii()
    }
}