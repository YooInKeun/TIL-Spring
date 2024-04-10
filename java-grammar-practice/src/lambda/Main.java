package lambda;

import inheritance.Person;

public class Main {
    public static void main(String args[]) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();

        new Thread(() -> {
            System.out.println("hello world");
        }).start();

        Math plusLambda = (first, second) -> first + second;
        System.out.println(plusLambda.Calc(3, 5));

        Math minusLambda = (first, second) -> first - second;
        System.out.println(minusLambda.Calc(6, 5));


        Integer number = new Integer(3);
        Person person = new Person("홍길동", 20);


        System.out.println(number);
        System.out.println(person);
    }
}