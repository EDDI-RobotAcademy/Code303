package Dice.repository;

import Dice.entity.DiceGame;
import Dice.entity.DiceRoll;

import java.util.List;
import java.util.Optional;

public interface DiceRepository {
    long save(DiceGame game);
    //Optional타입은 단일 객체만 감쌀수 있음
    Optional<DiceGame> findById(long id);//ID로 게임 조회
    //반환을 리스트로 하기위해 Optional포기
    List<DiceGame> findAll();//전체 게임 목록 조회
}
