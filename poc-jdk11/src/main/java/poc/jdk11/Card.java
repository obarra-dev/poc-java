package poc.jdk11;

// since java 9 private method in interfaces
public interface Card {
    String createCard();

    default String defaultCreateCard() {
        return String.format("crated card ID: %s using private interface method", createID());
    }
    private Long createID() {
        return 2L;
    }

    static String staticCreateCard() {
        return String.format("crated card ID: %s using static private interface method", staticCreateID());
    }
    private static Long staticCreateID() {
        return 3L;
    }
}
