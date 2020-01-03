package person.jzh.hello.thread.lambda;

/**
 * @author jzh
 * @version 1.0.0
 * @title LambdaTest01
 * @date 2019/12/11 17:32
 * @description：
 */
public class LambdaTest01 {
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("i like lambda2");
        }
    }
    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("i like lambda3");
            }
        };
        like.lambda();

        like = () -> {
            System.out.println("i like lambda4");
        };
        like.lambda();
    }
}

interface ILike {
    void lambda();
}
class Like implements ILike {
    @Override
    public void lambda() {
        System.out.println("i like lambda");
    }
}
