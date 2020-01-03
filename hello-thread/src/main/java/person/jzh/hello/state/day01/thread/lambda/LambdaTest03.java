package person.jzh.hello.thread.lambda;

/**
 * @author jzh
 * @version 1.0.0
 * @title LambdaTest01
 * @date 2019/12/11 17:32
 * @description：
 */
public class LambdaTest03 {
    public static void main(String[] args) {
        IInterest iInterest = (int a, int c) -> {
            System.out.println("i like lambda " + a);
            return a + c;
        };
        System.out.println(iInterest.lambda(100, 200));

        iInterest = (a, c) -> {
            System.out.println("i like lambda " + a);
            return a + c;
        };
        System.out.println(iInterest.lambda(101, 202));

        iInterest = (a, c) -> {
            return a + c;
        };
        System.out.println(iInterest.lambda(103, 204));

        iInterest = (a, c) -> a + c;

        System.out.println(iInterest.lambda(105, 206));


    }
}

interface IInterest {
    int lambda(int a, int b);
}

class Interest implements IInterest {
    @Override
    public int lambda(int a, int c) {
        System.out.println("i like lambda " + a);
        return a + c;
    }
}

