package callbyvalue;

public class Main {
    public static void main(String[] args) {
        Number number = new Number(1);
        addOne(number);
        System.out.println(number.value);
    }

    public static void addOne(Number number) {
        number.value += 1;
    }

    public static class Number {
        int value;

        public Number(int value) {
            this.value = value;
        }
    }
}


