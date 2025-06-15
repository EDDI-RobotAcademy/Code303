

import java.util.Scanner;

import Account.entity.Account;
import Dice.entity.DiceRoll;
import Dice.entity.DiceGame;
import Dice.service.DiceService;
import Dice.service.DiceServiceImpl;



public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DiceService svc = DiceServiceImpl.getInstance();

        // 1) 플레이어 등록
        System.out.print("Player1 이름: ");
        Account p1 = new Account(sc.nextLine().trim(), "");
        System.out.print("Player2 이름: ");
        Account p2 = new Account(sc.nextLine().trim(), "");

        // 2) 게임 반복
        String again;
        do {
            DiceGame game = svc.startGame(p1, p2);
            System.out.println("\n=== Game#" + game.getGameId() + " 시작 ===");

            // 3) 최대 3라운드 진행
            for (int round = 1; round <= 3; round++) {
                System.out.println("-- 라운드 " + round + " --");

                DiceRoll r1 = svc.playTurn(game, p1);
                DiceRoll r2 = svc.playTurn(game, p2);

                // 3라운드일 때만 스킬 적용
                if (round == 3) {
                    // 첫 라운드 주사위 값 가져오기
                    int p1_first = game.getRolls().get(p1).get(0).getValue();
                    int p2_first = game.getRolls().get(p2).get(0).getValue();
                    // 두 플레이어 중 한 명이라도 첫 라운드가 짝수면 3라운드 활성화
                    if (!(svc.isFirstDiceEven(p1_first) || svc.isFirstDiceEven(p2_first))) {
//                        System.out.println("▶ 3라운드 스킬 조건(첫 라운드 짝수) 미충족, 스킬 비활성화.");
                        break;  // 3라운드 종료
                    }
                    String result = svc.evaluateRound(game);
                    if (!"ONGOING".equals(result)) {
                        System.out.println("▶ 스킬 발동!");

                        switch (result) {
                            case "P1_ACTIVE3":
                                System.out.printf("▶ %s가 특수스킬 3을 발동!%n상대 점수를 모두 훔쳐왔습니다!%n",
                                        p1.getUserId());
                                break;
                            case "P2_ACTIVE3":
                                System.out.printf("▶ %s가 특수스킬 3을 발동!%n상대 점수를 모두 훔쳐왔습니다!%n",
                                        p2.getUserId());
                                break;
                            case "DRAW":
                                System.out.println("▶ 양쪽 특수스킬 3/3을 발동!%n 무승부 처리!");
                                break;
                            case "BOTH_ACTIVE4":
                                System.out.println("▶ 4/4 무승부 처리!");
                            case "P1_ACTIVE4":
                                System.out.printf("▶ %s가 특수스킬 4를 발동!%n%s 승리! (%s 패배)",
                                        p1.getUserId(), p2.getUserId(),p1.getUserId() );
                                break;
                            case "P2_ACTIVE4":
                                System.out.printf("▶ %s가 특수스킬 4를 발동!%n%s 승리! (%s 패배)",
                                        p2.getUserId(), p1.getUserId(),p2.getUserId() );
                                break;
                        }
                        break;
                    }
                }
                System.out.printf("[%s 굴림: %d , %s 굴림: %d]%n[누적점수: %d , 누적점수: %d%n]",
                        p1.getUserId(), r1.getValue(),p2.getUserId(), r2.getValue(),game.getTotalScore(p1) ,game.getTotalScore(p2));

            }

            // 4) 스킬 미발동 시 최종 점수 비교
            int s1 = game.getTotalScore(p1), s2 = game.getTotalScore(p2);
            if (s1 != s2) {
                System.out.printf("▶ 최종 승리: %s%n",
                        s1 > s2 ? p1.getUserId() : p2.getUserId());
            } else {
                System.out.println("▶ 최종 무승부!");
            }

            System.out.print("새 게임? (y/n): ");
            again = sc.nextLine().trim().toLowerCase();
        } while ("y".equals(again));

        sc.close();
        System.out.println("게임 종료!");
    }
}