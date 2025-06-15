package Dice.entity;

import java.util.*;

import Account.entity.Account;

//한 판의 게임 정보를 담는 클래스

public class DiceGame {

    private final long gameId;// 고유 게임 ID
    private final Account p1,p2;
    private final Map<Account, List<DiceRoll>> rolls = new HashMap<>();
    private final Map<Account, Integer> totalScores = new HashMap<>();

    public DiceGame(long gameId, Account p1, Account p2) {
        this.gameId = gameId;
        this.p1 = p1; this.p2 = p2;
        rolls.put(p1, new ArrayList<>());
        rolls.put(p2, new ArrayList<>());
        totalScores.put(p1, 0);
        totalScores.put(p2, 0);
    }

    public long getGameId()           { return gameId; }
    public Account getPlayer1()       { return p1; }
    public Account getPlayer2()       { return p2; }


    /** 턴 기록 추가 */
    public void addRoll(Account player, DiceRoll roll) {
        rolls.get(player).add(roll);
    }

    /** 주사위 값에 따라 점수 누적 (스킬 적용 전까지) */
    public void updateScore(Account player, DiceRoll roll) {
        int sum = totalScores.get(player) + roll.getValue();
        totalScores.put(player, sum);
    }
    public Map<Account, List<DiceRoll>> getRolls() {
        // 외부에서 수정되지 않게 unmodifiableMap 사용
        Map<Account, List<DiceRoll>> copy = new HashMap<>();
        copy.put(p1, Collections.unmodifiableList(rolls.get(p1)));
        copy.put(p2, Collections.unmodifiableList(rolls.get(p2)));
        return Collections.unmodifiableMap(copy);
    }
    public void resetAllScores() {
        totalScores.put(p1, 0);
        totalScores.put(p2, 0);
    }
    public void resetScore(Account player) {
        if (totalScores.containsKey(player)) {
            totalScores.put(player, 0);
        }
    }
    /** 3라운드 스킬 판단 & 적용 */
    public String applyThirdRoundSkill() {
        List<DiceRoll> r1 = rolls.get(p1);
        List<DiceRoll> r2 = rolls.get(p2);
        if (r1.size() < 3 || r2.size() < 3) return "ONGOING";

        int v1 = r1.get(2).getValue();  // 3번째 라운드 값
        int v2 = r2.get(2).getValue();

        // 3/3 무승부, 점수 0/0
        if (v1 == 3 && v2 == 3) {
            totalScores.put(p1, 0);
            totalScores.put(p2, 0);
            return "DRAW";
        }
        // — 0) 단독 3 → 상대 점수 훔치기
        if (v1 == 3 && v2 != 3) {
            int opp = totalScores.get(p2);
            totalScores.put(p1, totalScores.get(p1) + opp);
            totalScores.put(p2, 0);
            return "P1_ACTIVE3";
        }
        if (v2 == 3 && v1 != 3) {
            int opp = totalScores.get(p1);
            totalScores.put(p2, totalScores.get(p2) + opp);
            totalScores.put(p1, 0);
            return "P2_ACTIVE3";
        }

        // 4/4 무승부, 점수 유지
        if (v1 == 4 && v2 == 4) {
            return "BOTH_ACTIVE4";
        }
        // 단독 4: 해당 유저 패배, 점수 0
        if (v1 == 4) {
            totalScores.put(p1, 0);
            return "P1_ACTIVE4";
        }
        if (v2 == 4) {
            totalScores.put(p2, 0);
            return "P2_ACTIVE4";
        }
        return "ONGOING";
    }

    public int getTotalScore(Account player) {
        return totalScores.getOrDefault(player, 0);
    }
}