package Lambda;

public class Lambda {
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
    }
}