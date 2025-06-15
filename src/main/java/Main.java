
import java.util.Scanner;

import Account.entity.Account;
import Dice.entity.DiceGame;
import Dice.service.DiceService;
import Dice.service.DiceServiceImpl;
import Dice.entity.DiceRoll;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Player 1 이름을 입력하세요: ");
        String name1 = sc.nextLine().trim();
        Account player1 = new Account(name1, "");  // 비밀번호는 빈 문자열로 처리

        System.out.print("Player 2 이름을 입력하세요: ");
        String name2 = sc.nextLine().trim();
        Account player2 = new Account(name2, "");

        //인스턴스 가져오기
        DiceService gameService = DiceServiceImpl.getInstance();

        //새로운 게임 시작
        DiceGame game = gameService.startGame(player1, player2);
        System.out.println("게임 #" + game.getGameId() + " 시작!");

        //턴별 주사위 굴리기 반복
        String input;
        do {
            //플레이어1 턴
            DiceRoll roll1 = gameService.playTurn(game, player1);
            System.out.println(player1.getUserId() + " 턴 결과: " + roll1);

            //플레이어2 턴
            DiceRoll roll2 = gameService.playTurn(game, player2);
            System.out.println(player2.getUserId() + " 턴 결과: " + roll2);

            //계속 진행 여부 묻기
            System.out.print("계속 진행하려면 [y], 종료하려면 [n]: ");
            input = sc.nextLine().trim().toLowerCase();
        } while ("y".equals(input));

        System.out.println("\n=== 최종 게임 기록 (모든 턴) ===");
        for (DiceRoll r : game.getRolls()) {
            System.out.println(r);
        }
        
        sc.close();
        System.out.println("게임 종료. 수고하셨습니다!");
    }
}