package DiceLog.service;

import DiceLog.entity.DiceLog;
import DiceLog.repository.DiceLogRepository;
import DiceLog.repository.DiceLogRepositoryImpl;

import java.util.List;

public class DiceLogServiceImpl implements DiceLogService {
    private static DiceLogServiceImpl instance;
    //레포지토리에 저장하는거 객체화 해서 재사용성 업(repository의 타입은 DiceLogRepository)
    private final DiceLogRepository repository;

    //DiceLogRepositoryImpl의 인스턴스를 가져와서 repository에 담기
    private DiceLogServiceImpl() {
        this.repository = DiceLogRepositoryImpl.getInstance();
    }

    //값이 담긴 repository를 instance에 넣기
    public static DiceLogServiceImpl getInstance() {
        if (instance == null) {
            instance = new DiceLogServiceImpl();
        }
        return instance;
    }

    // 먼저 게임 번호 변수에 할당
    // 그후 우선 콘솔에 만들어둔 다이스 작동값을 플레이어 1,2로 나눠서 각각 저장
    @Override
    public void recordGameLogs(DiceLog log1, DiceLog log2) {
        //repository의 타입이 DiceLogRepository이기 때문에 DiceLogRepositoryImpl의 savelog사용
        //타입이 DiceLogRepositoryImpl여도 작동은 함... 근데 인터페이스로 분리한 이유가 사라지고 유연성도 떨어지고 비추
        int gameNumber = repository.getNextGameNumber();
        repository.saveLog(new DiceLog(gameNumber, log1.getPlayerId(), log1.getDiceRolls(),
                log1.getTotalScore(), log1.isStolePoints(), log1.isInstantDeath(), log1.isWinner()));
        repository.saveLog(new DiceLog(gameNumber, log2.getPlayerId(), log2.getDiceRolls(),
                log2.getTotalScore(), log2.isStolePoints(), log2.isInstantDeath(), log2.isWinner()));
    }

    //위와 동일하게 getAllLogs 사용
    @Override
    public List<DiceLog> viewAllLogs() {
        return repository.getAllLogs();
    }
}
