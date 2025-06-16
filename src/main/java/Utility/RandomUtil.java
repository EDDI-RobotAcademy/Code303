package util;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    // 생성자 private → 외부에서 객체 생성 차단
    private RandomUtil() {}

    // 범위 지정 랜덤 정수 반환 (예: 1 ~ 6)
    public static int getRandomInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min은 max보다 작아야 합니다.");
        }
        return random.nextInt(max - min + 1) + min;
    }

    // 주사위용 메소드: 1~6
    public static int rollDice() {
        return getRandomInt(1, 6);
    }
}
