package poc.jdk11;

public class OuterClass {

    public void outerPublic() {
        System.out.println("outer private method");
    }

    private void outerPrivate() {
        System.out.println("outer private method");
    }

     class InnerClass {
        public void innerPublic() {
            outerPrivate();
        }
    }
}
