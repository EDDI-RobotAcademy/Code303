package DiceLog.entity;

import java.util.List;

public class DiceLog {
    //private로 캡슐화를 final로  할당된 값을 고정해주기
    //몇 번째 게임인지 구별용
    private final int gameNumber;
    //플레이어가 누군지 구별용
    private final int playerId;
    //플레이어가 굴린 주사위들을 정수의 리스트에 담기
    private final List<Integer> diceRolls;
    //최종 합계
    private final int totalScore;
    //3번째 주사위가 3이 나와서 점수를 훔칠때 불린 값으로 상대방 점수 훔치면 true
    private final boolean stolePoints;
    //세번째 주사위 4나와서 죽었을때 true
    private final boolean instantDeath;
    //게임당 승자를 저장하기
    private final boolean isWinner;

    public DiceLog(int gameNumber, int playerId, List<Integer> diceRolls, int totalScore,
                   boolean stolePoints, boolean instantDeath, boolean isWinner) {
        this.gameNumber = gameNumber;
        this.playerId = playerId;
        this.diceRolls = diceRolls;
        this.totalScore = totalScore;
        this.stolePoints = stolePoints;
        this.instantDeath = instantDeath;
        this.isWinner = isWinner;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public int getPlayerId() {
        return playerId;
    }

    public List<Integer> getDiceRolls() {
        return diceRolls;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public boolean isStolePoints() {
        return stolePoints;
    }

    public boolean isInstantDeath() {
        return instantDeath;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
