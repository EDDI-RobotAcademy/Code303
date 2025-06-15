package Dice.entity;
//한 턴의 주사위 결과(3번의 주사위)를 저장하는 클래스
public class DiceRoll {

    private final int first;
    private final int second;
    private final Integer third; // 첫 번째 주사위가 짝수일 때만 값을 가짐, 아닐 땐 null
    
    public DiceRoll(int first, int second, Integer third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Integer getThird() {
        return third;
    }

    public int total() {
        if (third != null) {
            return first + second + third;
        }
        return first + second;
    }

    @Override
    public String toString() {
        // 디버깅용: [f, s, t] 또는 [f, s]
        if (third != null) {
            return String.format("[%d, %d, %d] => 총합: %d", first, second, third, total());
        }
        return String.format("[%d, %d] => 총합: %d", first, second, total());
    }
}

