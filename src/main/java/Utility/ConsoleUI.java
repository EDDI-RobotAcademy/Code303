package Utility;

import Account.service.AccountService;
import Account.service.AccountServiceImpl;
import DiceLog.entity.DiceLog;
import DiceLog.service.DiceLogService;
import DiceLog.service.DiceLogServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleUI {
    //버퍼더 리더 쓰셧으니 같이 ㄱㄱ
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final AccountService accountService = AccountServiceImpl.getInstance();
    private final DiceLogService diceLogService = DiceLogServiceImpl.getInstance();

    //계정 2개 생성해서 식별
    private int player1Id;
    private int player2Id;

    public void start() {
        while (true) {
            System.out.println("===== 주사위 게임 =====");
            System.out.println("1. 계정 생성");
            System.out.println("2. 게임 종료");
            System.out.print("선택 > ");
            try {
                String input = br.readLine();
                switch (input) {
                    case "1" -> {
                        //회원 가입
                        player1Id = accountService.register();
                        player2Id = accountService.register();
                        gameMenu();
                    }
                    case "2" -> {
                        System.out.println("게임을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            } catch (IOException e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

    private void gameMenu() throws IOException {
        while (true) {
            System.out.println("\n1. 게임 시작");
            System.out.println("2. 기록 열람");
            System.out.println("3. 게임 종료");
            System.out.print("선택 > ");
            String input = br.readLine();
            switch (input) {
                case "1" -> playGame();
                case "2" -> viewLogs();
                case "3" -> {
                    System.out.println("게임을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }

    //이건 웃선 작동 하게 만들라고 대충 만든 주사위 굴리기
    private void playGame() {
        Random random = new Random();
        List<Integer> p1Rolls = new ArrayList<>();
        List<Integer> p2Rolls = new ArrayList<>();
        boolean p1Stole = false, p2Stole = false, p1Dead = false, p2Dead = false;

        int p1First = random.nextInt(6) + 1;
        int p1Second = random.nextInt(6) + 1;
        p1Rolls.add(p1First);
        p1Rolls.add(p1Second);

        if (p1First % 2 == 0) {
            int p1Third = random.nextInt(6) + 1;
            p1Rolls.add(p1Third);
            if (p1Third == 3) p1Stole = true;
            else if (p1Third == 4) p1Dead = true;
        }

        int p2First = random.nextInt(6) + 1;
        int p2Second = random.nextInt(6) + 1;
        p2Rolls.add(p2First);
        p2Rolls.add(p2Second);

        if (p2First % 2 == 0) {
            int p2Third = random.nextInt(6) + 1;
            p2Rolls.add(p2Third);
            if (p2Third == 3) p2Stole = true;
            else if (p2Third == 4) p2Dead = true;
        }

        int p1Sum = p1Rolls.stream().mapToInt(Integer::intValue).sum();
        int p2Sum = p2Rolls.stream().mapToInt(Integer::intValue).sum();

        if (p1Stole) {
            p1Sum += p2Sum;
            p2Sum = 0;
            System.out.println("플레이어 1이 상대방의 점수를 훔쳤습니다!");
        }
        if (p2Stole) {
            p2Sum += p1Sum;
            p1Sum = 0;
            System.out.println("플레이어 2가 상대방의 점수를 훔쳤습니다!");
        }
        if (p1Dead) {
            p1Sum = 0;
            System.out.println("플레이어 1 즉사!");
        }
        if (p2Dead) {
            p2Sum = 0;
            System.out.println("플레이어 2 즉사!");
        }

        System.out.println("플레이어 1 주사위: " + p1Rolls + " | 합계: " + p1Sum);
        System.out.println("플레이어 2 주사위: " + p2Rolls + " | 합계: " + p2Sum);

        boolean p1Win = p1Sum > p2Sum;
        boolean tie = p1Sum == p2Sum;

        if (tie) {
            System.out.println("무승부입니다!");
        } else if (p1Win) {
            System.out.println("플레이어 1 승리!");
        } else {
            System.out.println("플레이어 2 승리!");
        }

        DiceLog log1 = new DiceLog(0, player1Id, p1Rolls, p1Sum, p1Stole, p1Dead, p1Win);
        DiceLog log2 = new DiceLog(0, player2Id, p2Rolls, p2Sum, p2Stole, p2Dead, !p1Win && !tie);
        diceLogService.recordGameLogs(log1, log2);
    }

    //로그모든 기록 불러오기
    private void viewLogs() {
        List<DiceLog> logs = diceLogService.viewAllLogs();
        if (logs.isEmpty()) {
            System.out.println("기록이 없습니다.");
            return;
        }

        //stream을 이용해서 반복문 없이 list값을 처리 stream 생성 -> collect와 forEach로 결과 반환
        logs.stream()
                //n번째 게임인 게임 번호별로 로그를 그룹화 시켜줌
                //.collect() -> stream결과를 수집한다! 라는 목적으로 사용
                //Collectors.groupingBy 데이터를 특정 기준으로 그룹화
                //DiceLog::getGameNumber: 각 로그에서 gameNumber 값을 기준으로 묶음
                //TreeMap::new: 결과를 TreeMap에 저장하겠다는 뜻 -> 게임 번호 순서대로 자동 정렬(hashmap 대신 treemap 인 이유는 자동으로 키 값 기준으로 오름차순 정렬이 되서 게임 순서대로 정렬하기 좋음)
                //Collectors.toList(): 같은 게임 번호의 로그들을 리스트로 저장
                .collect(Collectors.groupingBy(DiceLog::getGameNumber, TreeMap::new, Collectors.toList()))
                //위에서 정렬한걸 들고와서 gameNum별로 순회하며 처리(= gameLogs -> 그 게임 번호에 해당하는 플레이어들의 로그 리스트 전체)
                .forEach((gameNum, gameLogs) -> {
                    System.out.println("\n" + gameNum + "번째 게임 결과:");
                    //각 플레이어들의 로그 정보 순회 (향상된 for문으로 그냥 출력)
                    for (DiceLog log : gameLogs) {
                        List<Integer> rolls = log.getDiceRolls();
                        int score = (log.isInstantDeath() || log.isStolePoints()) ? 0 : log.getTotalScore();
                        System.out.println("플레이어 ID: " + log.getPlayerId() +
                                " | 주사위: " + rolls +
                                " | 총합: " + score +
                                " | 결과: " + (log.isWinner() ? "승" : "패"));
                    }
                });
    }
}