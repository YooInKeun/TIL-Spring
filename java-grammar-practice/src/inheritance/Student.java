package inheritance;

public class Student extends Person {
    private String school;

    public Student(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }

    public void introduce(int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
            System.out.println("저는 학생입니다.");
        }
    }
}








