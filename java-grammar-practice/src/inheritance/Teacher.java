package inheritance;

public class Teacher extends Person {
    private String school;

    public Teacher(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }

    public void teach() {
        System.out.println(school + "에서 가르칩니다.");
    }
}


