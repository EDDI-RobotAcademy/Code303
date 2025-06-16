package Utility;

import java.util.Random;

public class DiceUtil {
    private static final Random random = new Random();

    public static int rollDice() {
        try {
            int result = random.nextInt(6) + 1; // 1~6
            return result;
        } catch (Exception e) {
            System.out.println("⚠️ 주사위 굴리기 중 오류 발생: " + e.getMessage());
            return 1; // 기본값 반환
        }
    }

    // 조건부 스킬 주사위 (짝수일 경우만 허용)
    public static int rollSkillDice(int condition) {
        try {
            if (condition % 2 == 0) {
                return rollDice();
            } else {
                return 0; // 조건 미충족
            }
        } catch (Exception e) {
            System.out.println("⚠️ 스킬 주사위 오류: " + e.getMessage());
            return 0;
        }
    }
}
