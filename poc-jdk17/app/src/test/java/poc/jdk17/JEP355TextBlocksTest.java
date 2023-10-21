package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// JEP355
class JEP355TextBlocksTest {

    @Test
    void blocKText() {
        // there is no need to escape double quote
        String blockJSON = """
                {
                    "name" : "Omar",
                    "website" : "https://www.obarra.com/"
                }
                """;
        Assertions.assertEquals("{\n" +
                "    \"name\" : \"Omar\",\n" +
                "    \"website\" : \"https://www.obarra.com/\"\n" +
                "}\n", blockJSON);
    }

    // TODO work for just string?
    // TODO test stripIndent(),translateEscapes(), formatted()
    // since java 15
    @Test
    void formatted() {
        // there is no need to escape double quote
        String blockJSON = """
                {
                    "name" : "%s",
                    "website" : "https://www.%s.com/"
                }
                """.formatted("Omar", "obarra");

        Assertions.assertEquals("{\n" +
                "    \"name\" : \"Omar\",\n" +
                "    \"website\" : \"https://www.obarra.com/\"\n" +
                "}\n", blockJSON);
    }

}
