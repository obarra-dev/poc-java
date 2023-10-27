package poc.jdk21;

/**
 * {@snippet :
 * try (BufferedWriter writer = Files.newBufferedWriter(path)) {
 *   writer.write(text);  // @highlight substring="text"
 * }
 * }
 */

/**
 * {@snippet :
 * // @highlight region regex="\bwrite.*?\b" type="highlighted"
 * try (BufferedWriter writer = Files.newBufferedWriter(path)) {
 *   writer.write(text);
 * }
 * // @end
 * }
 */
class JEP413CodeSnippetsInJavaAPIDocumentationTest {}
