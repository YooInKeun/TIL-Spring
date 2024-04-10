package abstractclass;

public abstract class Person {
    protected String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract void introduce();
}



