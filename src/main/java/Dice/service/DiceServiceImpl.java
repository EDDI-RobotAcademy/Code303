package Dice.service;

import Account.entity.Account;
import Dice.entity.DiceGame;
import Dice.entity.DiceRoll;
import Dice.repository.DiceRepository;
import Dice.repository.DiceRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiceServiceImpl implements DiceService{
    private long gameCounter=1;
    private final int minDice = 1;
    private final int maxDice = 6;
    private static DiceServiceImpl instance;
    private DiceServiceImpl() {}
    public static DiceServiceImpl getInstance() {
        if (instance == null) {
            instance = new DiceServiceImpl();
        }
        return instance;
    }
    private static final Map<Integer, DiceRoll> DiceHashMap = new HashMap<>();
    private final DiceRepository gameRepo = DiceRepositoryImpl.getInstance();

    @Override
    public DiceGame startGame(Account p1, Account p2) {
        DiceGame game = new DiceGame(gameCounter++, p1, p2);
        gameRepo.save(game);
        return game;
    }

    @Override
    public DiceRoll playTurn(DiceGame game, Account player) {
        // 한 턴당 한 번만 주사위 굴리기
        int value = roll();
        DiceRoll roll = new DiceRoll(value);

        // 기록 추가 및 점수 누적
        game.addRoll(player, roll);
        game.updateScore(player, roll);
        gameRepo.save(game);
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
        // 엔티티에 있는 applyThirdRoundSkill()만 호출
        return game.applyThirdRoundSkill();
    }

    private int roll() {
        //(최댓값-최소값+1) + 최소값
        return (int)(Math.random()*(maxDice-minDice+1))+minDice;
        //return ThreadLocalRandom.current().nextInt(1, 7);
    }
}

