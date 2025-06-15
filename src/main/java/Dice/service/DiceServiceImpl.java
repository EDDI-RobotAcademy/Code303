package Dice.service;

import Account.entity.Account;
import Dice.entity.DiceGame;
import Dice.entity.DiceRoll;
import Dice.repository.DiceRepository;
import Dice.repository.DiceRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        int first = roll();
        int second = roll();
        Integer third = isFirstDiceEven(first) ? roll() : null;

        DiceRoll diceRoll = new DiceRoll(first, second, third);

        // Game 엔티티에 턴 결과 추가
        game.addRoll(diceRoll);
        // 변경된 Game을 저장소에 업데이트
        gameRepo.save(game);

        return diceRoll;
    }

    @Override
    public boolean isFirstDiceEven(int first) {
        return first % 2 == 0;
    }

    @Override
    public List<DiceRoll> getAllRolls(DiceGame game) {
        return game.getRolls();
    }

    private int roll() {
        //(최댓값-최소값+1) + 최소값
        return (int)(Math.random()*(maxDice-minDice+1))+minDice;
        //return ThreadLocalRandom.current().nextInt(1, 7);
    }
}

