package Dice.entity;

// 한 턴의 주사위 결과(단일 주사위)만 저장하는 클래스
public class DiceRoll {
    // 1~6 눈금
    private final int value;

    public DiceRoll(int value) {
        this.value = value;
    }

    /** 주사위 눈금 반환 */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("[%d]", value);
    }
}