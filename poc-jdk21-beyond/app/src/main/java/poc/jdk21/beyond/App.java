package poc.jdk21.beyond;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        int a = 2;
        int b = 3;
        String interpolated = STR."\{a} times \{b} = \{a * b}";

        System.out.println(interpolated);
    }
}
