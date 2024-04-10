package inheritance;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        System.out.println("저는 " + age + "살 " + name + "입니다.");
    }
}









