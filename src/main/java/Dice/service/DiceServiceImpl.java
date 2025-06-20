package Dice.service;

import Account.entity.Account;
import Dice.entity.DiceGame;
import Dice.entity.DiceRoll;
import Dice.repository.DiceRepository;
import Dice.repository.DiceRepositoryImpl;
import Utility.InputStream;
import consoleui.entity.ConsoleUiMessage;
import consoleui.repository.ConsoleUiRepository;
import consoleui.repository.ConsoleUiRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public DiceGame startGame(Account p1, Account p2) {
        DiceGame dicegame = new DiceGame(gameCounter++, p1, p2);
        // 변경 이전: gameRepo.save(game);
        diceRepository.save(dicegame); // DiceRepository 사용으로 변경
        return dicegame;
    }

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
    public void startMenu() {
        // 신규 메서드: 콘솔 UI 시작 메뉴 출력 기능 추가
        //맨처음 환영문구 출력
        consoleUiRepository.displayWelcomeMessage();
        while (true) {
            //이후 사용자로부터 값입력받는 콘솔 출력
            consoleUiRepository.displayInitialMessage();
            //이제 사용자로부터 값을 받아오는거 구현
            String userInput = InputStream.getStringInput("입력: ");
            //받아온 값을 기준으로 enum형식으로 연동
            // 사용자가 입력한 문자열을 enum으로 변환
            ConsoleUiMessage selectedMessage = ConsoleUiMessage.fromUserInput(userInput);
            // 변환된 enum 값을 전달해 UI 출력 로직 호출
            //리턴값이 2(로그인이 되면 리턴값)가 나오면 showGameMenu쪽으로 처리
            int result=consoleUiRepository.displayMessageFromUserInput(selectedMessage);
            if(result==2){
                consoleUiRepository.displayshowGameMenu()
            }
            // ConsoleUiMessage.SIGNUP
            // ConsoleUiMessage.SIGNIN
            // ConsoleUiMessage.EXIT
            //회원가입을 했다는 기준이 뭐야
            //로그인을 했다는 기준을 무엇으로 잡아야하는가
            //로그인했으면 게임하기,기록보기,종료하기를 출력하는 콘솔 출력
            //다른거 다 배제하고 일단 로그인한 사람한테 게임하기 출력하는 콘솔 출력하기

            // 추후: 메뉴 구조 분기 로직 구현 예정
        }
    }
}
