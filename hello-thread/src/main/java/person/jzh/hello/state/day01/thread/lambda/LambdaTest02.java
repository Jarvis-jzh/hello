package person.jzh.hello.thread.lambda;

/**
 * @author jzh
 * @version 1.0.0
 * @title LambdaTest01
 * @date 2019/12/11 17:32
 * @description：
 */
public class LambdaTest02 {
    public static void main(String[] args) {
        ILove love = a -> {
            System.out.println("i like lambda " + a);
        };
        love.lambda(100);

        love = a -> System.out.println("i like lambda " + a);
        love.lambda(50);
    }
}

interface ILove {
    void lambda(int a);
}

class Love implements ILove {
    @Override
    public void lambda(int a) {
        System.out.println("i like lambda " + a);
    }
}
