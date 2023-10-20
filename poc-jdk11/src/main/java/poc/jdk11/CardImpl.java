package poc.jdk11;

public class CardImpl implements Card {

    @Override
    public String createCard() {
        return String.format("crated card ID: %s", createID());
    }

    private Long createID() {
        return 4L;
    }
}

