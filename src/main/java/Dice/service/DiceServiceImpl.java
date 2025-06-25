package Dice.service;

import Account.entity.Account;
import Account.service.AccountServiceImpl;
import Dice.entity.DiceGame;
import Dice.entity.DiceRoll;
import Dice.repository.DiceRepository;
import Dice.repository.DiceRepositoryImpl;
import Utility.InputStream;
import consoleui.entity.ConsoleUiMessage;
import consoleui.entity.UIActionResult;
import consoleui.repository.ConsoleUiRepository;
import consoleui.repository.ConsoleUiRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static consoleui.entity.ConsoleUiMessage.SIGNIN;

public class DiceServiceImpl implements DiceService {
    private static DiceServiceImpl instance;
    private final DiceRepository diceRepository;
    private final ConsoleUiRepository consoleUiRepository;

    private DiceServiceImpl() {
        // 이전: directly used gameRepo = new GameRepositoryImpl();
        this.diceRepository = DiceRepositoryImpl.getInstance(); // 리포지토리 싱글톤으로 변경
        this.consoleUiRepository = ConsoleUiRepositoryImpl.getInstance();
    }

    public static DiceServiceImpl getInstance() {
        if (instance == null) {
            instance = new DiceServiceImpl();
        }
        return instance;
    }

    private static final Map<Integer, DiceRoll> DiceHashMap = new HashMap<>(); // 사용되지 않는 HashMap 제거 검토 가능
    private long gameCounter = 1;
    private final int minDice = 1;
    private final int maxDice = 6;



    @Override
    public DiceRoll playTurn(DiceGame game, Account player) {
        int value = roll();
        DiceRoll roll = new DiceRoll(value);

        game.addRoll(player, roll);
        game.updateScore(player, roll);
        // 변경 이전: gameRepo.save(game);
        diceRepository.save(game); // 일관된 리포지토리 호출
        return roll;
    }

    @Override
    public boolean isFirstDiceEven(int first) {
        return first % 2 == 0;
    }

    @Override
    public List<DiceRoll> getAllRolls(DiceGame game) {
        return game.getRolls().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String evaluateRound(DiceGame game) {
        // 변경 이전: 직접 로직 구현
        return game.applyThirdRoundSkill(); // 도메인 메서드 호출로 위임
    }

    private int roll() {
        return (int) (Math.random() * (maxDice - minDice + 1)) + minDice;
    }

    @Override
    public DiceGame startGame(Account p1, Account p2) {
        DiceGame dicegame = new DiceGame(gameCounter++, p1, p2);
        // 변경 이전: gameRepo.save(game);
        diceRepository.save(dicegame); // DiceRepository 사용으로 변경
        return dicegame;
    }

    @Override
    public void startMenu() {
        consoleUiRepository.displayWelcomeMessage();
        AccountServiceImpl accountServiceImpl = AccountServiceImpl.getInstance();
        DiceService diceService = DiceServiceImpl.getInstance();

        // 1) 두 플레이어 리스트 준비
        List<Account> players = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            System.out.printf("== %d번 플레이어: 회원가입 → 로그인 ==%n", i);
            performUntilSuccess(ConsoleUiMessage.SIGNUP);
            performUntilSuccess(ConsoleUiMessage.SIGNIN);
            // 로그인 성공한 Account 객체 취득
            players.add(accountServiceImpl.getCurrentUser());
        }

        // 2) 두 플레이어로 게임 시작
        Account p1 = players.get(0), p2 = players.get(1);
        diceService.startGame(p1, p2);
    }

    private void performUntilSuccess(ConsoleUiMessage expected) {
        UIActionResult result = UIActionResult.FAILURE;//초기 값 부여
        do {
            consoleUiRepository.displayInitialMessage();
            String input = InputStream.getStringInput("입력: ").trim();
            ConsoleUiMessage msg = ConsoleUiMessage.fromUserInput(input);

            // 사용자가 정확한 메뉴를 선택했는지 먼저 검사
            if (msg != expected) {
                consoleUiRepository.displayErrorMessage();
                continue;
            }

            // 실제 액션 실행
            result = consoleUiRepository.displayMessageFromUserInput(msg);
            if (result == UIActionResult.FAILURE) {
                consoleUiRepository.displayErrorMessage();
            }
        } while (result != UIActionResult.SUCCESS);
    }
}
/*
    @Override
    public void startMenu() {
        consoleUiRepository.displayWelcomeMessage();
        while (true) {
            consoleUiRepository.displayInitialMessage();//이후 사용자로부터 값입력받는 콘솔 출력
            String userInput = InputStream.getStringInput("입력: ");//이제 사용자로부터 값을 받아오는거 구현
            ConsoleUiMessage selectedMessage = ConsoleUiMessage.fromUserInput(userInput);// 사용자가 입력한 문자열을 enum으로 변환
            UIActionResult result = consoleUiRepository.displayMessageFromUserInput(selectedMessage);
            switch (result) {
                case SUCCESS://result return값이 SUCCESS일때
                    if (selectedMessage == ConsoleUiMessage.SIGNIN) {
                        consoleUiRepository.showGameMenu();    // 로그인 성공 시 다음 단계
                    }
                    break;
                case FAILURE:
                    consoleUiRepository.displayErrorMessage();  // 실패 시 재시도 안내
                    break;
                case EXIT:
                    consoleUiRepository.displayExitMessage();
                    return;  // 프로그램 종료
            }
            // ConsoleUiMessage.SIGNUP
            // ConsoleUiMessage.SIGNIN
            // ConsoleUiMessage.EXIT

//          메뉴콘솔출력
//          입력값을 받음
//          문자열을 enum으로변환처리
//          변환처리된값을 displayMesageFromUserInput을 통해서 결과값 return 받음(UIactionResult)
//          return된 결과값 result를 기반으로 switch문을 사용해서 처리

            //회원가입을 했다는 기준이 뭐야
            //로그인을 했다는 기준을 무엇으로 잡아야하는가
            //로그인했으면 게임하기,기록보기,종료하기를 출력하는 콘솔 출력
            //다른거 다 배제하고 일단 로그인한 사람한테 게임하기 출력하는 콘솔 출력하기

            // 추후: 메뉴 구조 분기 로직 구현 예정
        }

    }
*/
