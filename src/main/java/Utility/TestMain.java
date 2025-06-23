package Utility;

public class TestMain {
    public static void main(String[] args) {
        String name = util.InputUtil.readString("이름을 입력하세요: ");
        int age = util.InputUtil.readInt("나이를 입력하세요: ");
        System.out.println("이름: " + name + ", 나이: " + age);

        int dice = DiceUtil.rollDice();
        System.out.println("🎲 주사위 결과: " + dice);

        int skillDice = DiceUtil.rollSkillDice(dice);
        System.out.println("✨ 스킬 주사위 결과: " + skillDice);
    }
}
