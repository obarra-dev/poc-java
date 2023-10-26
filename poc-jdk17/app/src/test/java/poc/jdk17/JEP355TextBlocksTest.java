package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// since java 13
// JEP355, JEP368, JEP378
class JEP355TextBlocksTest {

    @Test
    void blocKTextJSON() {
        // three double-quote marks
        // allows whitespaces and a newline
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

    @Test
    void blocKTextSQL() {
        // there is no need to add new lines
        // easy copy and paste
        String blockSQL = """
                SELECT request_mnth,
                       round(avg(result), 2) AS number
                FROM
                  (SELECT request_mnth,
                          CAST(abs(division_result_one-division_result_two) AS decimal) AS result
                   FROM
                     (SELECT to_char(CAST(request_date AS date), 'YYYY-MM') AS request_mnth,
                             distance_to_travel/monetary_cost AS division_result_one,
                             sum(distance_to_travel) OVER (PARTITION BY to_char(CAST(request_date AS date), 'YYYY-MM')) / sum(monetary_cost) OVER (PARTITION BY to_char(CAST(request_date AS date), 'YYYY-MM')) AS division_result_two
                      FROM uber_request_logs) a) b
                GROUP BY request_mnth""";

        Assertions.assertEquals("SELECT request_mnth,\n" +
                "       round(avg(result), 2) AS number\n" +
                "FROM\n" +
                "  (SELECT request_mnth,\n" +
                "          CAST(abs(division_result_one-division_result_two) AS decimal) AS result\n" +
                "   FROM\n" +
                "     (SELECT to_char(CAST(request_date AS date), 'YYYY-MM') AS request_mnth,\n" +
                "             distance_to_travel/monetary_cost AS division_result_one,\n" +
                "             sum(distance_to_travel) OVER (PARTITION BY to_char(CAST(request_date AS date), 'YYYY-MM')) / sum(monetary_cost) OVER (PARTITION BY to_char(CAST(request_date AS date), 'YYYY-MM')) AS division_result_two\n" +
                "      FROM uber_request_logs) a) b\n" +
                "GROUP BY request_mnth", blockSQL);
    }

    @Test
    void blocKTextHTML() {
        String blockHTML = """
                <!DOCTYPE html>
                <html>
                <body>
                                
                <h2>The href Attribute</h2>
                                
                <p>HTML links are defined with the a tag. The link address is specified in the href attribute:</p>
                                
                <a href="https://www.obarra.com">Visit Obarra page</a>
                                
                </body>
                </html>
                """;
        Assertions.assertEquals("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h2>The href Attribute</h2>\n" +
                "\n" +
                "<p>HTML links are defined with the a tag. The link address is specified in the href attribute:</p>\n" +
                "\n" +
                "<a href=\"https://www.obarra.com\">Visit Obarra page</a>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n", blockHTML);
    }

    // since java 14
    @Test
    void blocKTextEscapeSequencesOne() {
        //  \ does not add a new line
        // This improves the readability of the sentence for a human eyes
        String blockCURL = """
                curl --location --request POST 'http://localhost:3000/users' \
                --header 'Content-Type: application/json' \
                --header 'Authorization: Basic token' \
                --data-raw '{
                        "name": "Omar",
                        "website": "https://www.obarra.com/"
                                
                }'""";

        Assertions.assertEquals("curl --location --request POST 'http://localhost:3000/users' --header 'Content-Type: application/json' --header 'Authorization: Basic token' --data-raw '{\n" +
                "        \"name\": \"Omar\",\n" +
                "        \"website\": \"https://www.obarra.com/\"\n" +
                "\n" +
                "}'", blockCURL);
    }

    // since java 14
    @Test
    void blocKTextEscapeSequencesTwo() {
        //  \s indicate a single space
        String blockJSON = """
                line 1·······
                line 2·······\s
                """;
        ;
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

    // TODO make it works
    // since java 15
    @Test
    @Disabled
    void stripIndent() {
        String htmlTextBlock = "<html>   \n" +
                "\t<body>\t\t \n" +
                "\t\t<p>Hello</p>  \t \n" +
                "\t</body> \n" +
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
