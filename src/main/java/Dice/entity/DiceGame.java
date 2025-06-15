package Dice.entity;

import java.util.ArrayList;
import java.util.List;

import Account.entity.Account;

//한 판의 게임 정보를 담는 클래스

public class DiceGame {

    private final long gameId;// 고유 게임 ID
    private final Account player1;// 참여 플레이어 (Account 패키지의 Account 사용)
    private final Account player2;
    private final List<DiceRoll> rolls = new ArrayList<>();// 이 판에서 굴린 모든 턴의 DiceRoll 목록

    public DiceGame(long gameId, Account player1, Account player2) {
        this.gameId = gameId;
        this.player1 = player1;
        this.player2 = player2;
    }

    public long getGameId()           { return gameId; }
    public Account getPlayer1()       { return player1; }
    public Account getPlayer2()       { return player2; }
    public List<DiceRoll> getRolls()  { return rolls; }

    public void addRoll(DiceRoll roll) { //한 턴의 주사위 결과를 기록 목록에 추가
        rolls.add(roll);
    }
}