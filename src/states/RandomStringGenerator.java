package states;

import java.util.Random;

public class RandomStringGenerator {
    private static final String name = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(name.length());
            randomString.append(name.charAt(randomIndex));
        }

        return randomString.toString();
    }

}
