package Dice.repository;

import Dice.entity.DiceGame;

import java.util.*;

/**
 * 메모리 기반 저장소 구현 (싱글톤)
 */
public class DiceRepositoryImpl implements DiceRepository {
    private static DiceRepositoryImpl instance;

    // HashMap은여러 스레드가 동시에 put(), get() → 경합 발생, 데이터 오류 가능
    // ConcurrentHashMap은 동시에 접근해도 안전하게 작동
    // 또한 ConcurrentHashMap은 Null허용X
    //hashMap.entrySet().removeIf(e -> e.getKey() == null || e.getValue() == null);
    //을 통한 HashMap의 null값 체크 필수
    private final Map<Long, DiceGame> store = new HashMap<>();

    private DiceRepositoryImpl() {}

    public static DiceRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DiceRepositoryImpl();
        }

        return instance;
    }

    // gameId를 기준으로 저장소에서 DiceGame을 찾아 Optional로 반환
    @Override
    public long save(DiceGame game) {
        store.put(game.getGameId(), game);
        return game.getGameId();//찾기위한 gameId반환
    }

    // 모든 DiceGame 데이터를 리스트로 반환하려고 시도
    @Override
    public Optional<DiceGame> findById(long gameId) {
        //찾고자하는 게임을 gameId를 키값으로 가져오기
        //Optional해서 안전하게 가져오기
        return Optional.ofNullable(store.get(gameId));
    }

    @Override
    public List<DiceGame> findAll() {
        //저장된 모든 DiceGame객체들 뽑아내기
        //반환형식=List<DiceGame>
        return new ArrayList<>(store.values());
    }
}