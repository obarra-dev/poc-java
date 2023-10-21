package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// JEP355 (java13), JEP368
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

    // since java 14
    @Test
    void blocKTextEscapeSequencesOne() {
        //  \ does not add a new line
        // This improves the readability of the sentence for a human eye
        String blockJSON = """
                {
                    "name" : \
                "Omar",
                    "website" : "https://www.obarra.com/"
                }
                """;
        Assertions.assertEquals("{\n" +
                "    \"name\" : \"Omar\",\n" +
                "    \"website\" : \"https://www.obarra.com/\"\n" +
                "}\n", blockJSON);
    }

    // since java 14
    @Test
    void blocKTextEscapeSequencesTwo() {
        //  \s indicate a single space
        String blockJSON =  """
            line 1·······
            line 2·······\s
            """;;
        Assertions.assertEquals("line 1·······\n" +
                "line 2······· \n", blockJSON);
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

    // since java 15
    @Test
    void stripIndent() {
        String htmlTextBlock = "<html>   \n"+
                "\t<body>\t\t \n"+
                "\t\t<p>Hello</p>  \t \n"+
                "\t</body> \n"+
                "</html>";
        System.out.println(htmlTextBlock.replace(" ", "*"));
        System.out.println(htmlTextBlock.stripIndent().replace(" ", "*"));

        Assertions.assertEquals(htmlTextBlock.replace(" ", "*"), htmlTextBlock.stripIndent().replace(" ", "*"));
    }

    // since java 15
    @Test
    @Disabled("Disabled! I dont find the expected")
    void translateEscapes() {
        String str1 = "Hi\t\nHello' \" /u0022 Pankaj\r";

        Assertions.assertEquals("Hi\t\n" +
                "Hello' \" /u0022 Pankaj\n", str1.translateEscapes());
    }

}
