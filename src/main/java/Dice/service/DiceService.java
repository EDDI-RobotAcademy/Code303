package Dice.service;

import Account.entity.Account;
import Dice.entity.DiceGame;
import Dice.entity.DiceRoll;

import java.util.List;

public interface DiceService {
    DiceGame startGame(Account p1, Account p2); //새로운 게임 시작
    DiceRoll playTurn(DiceGame game, Account player);
    boolean isFirstDiceEven(int first); //첫번째 주사위 짝수 판별
    List<DiceRoll> getAllRolls(DiceGame game);


    String evaluateRound(DiceGame game);
}
