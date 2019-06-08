package Utilities;

public class RandomUtil {
    public static int getRandom(int a, int b){
        return (int) (a+Math.random()*(b-a));
    }
}
