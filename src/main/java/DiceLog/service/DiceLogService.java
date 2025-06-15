package DiceLog.service;

import DiceLog.entity.DiceLog;
import java.util.List;

public interface DiceLogService {
    //플레이어 1 플레이어 2의 각각 주사위 결과를 저장만 하고 반환은 필요 없으니 void
    void recordGameLogs(DiceLog log1, DiceLog log2);
    //저장된 로그 불러 오기
    List<DiceLog> viewAllLogs();
}
