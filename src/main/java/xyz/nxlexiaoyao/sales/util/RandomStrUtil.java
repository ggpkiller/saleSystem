package xyz.nxlexiaoyao.sales.util;

import java.util.Random;

public class RandomStrUtil {
    private static final int MAX_NUMBER = 123;

    public final static String getRandomStr(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        int number;
        line:
        for (int i = 0; i < length; i++) {
            do {
                number = random.nextInt(MAX_NUMBER);
                if (number < 10) {
                    builder.append(number);
                    continue line;
                } else if (number > 64 && number < 91) {
                    builder.append((char) number);
                    continue line;
                } else if (number > 96 && number < 123) {
                    builder.append((char) number);
                    continue line;
                }
            } while (true);
        }
        return builder.toString();
    }


    public static String getRandomNumberStr(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        for(int i=0;i<length;i++){
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomStr(32));
    }
}
