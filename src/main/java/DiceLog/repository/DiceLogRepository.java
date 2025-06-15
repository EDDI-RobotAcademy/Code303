package DiceLog.repository;

import DiceLog.entity.DiceLog;

import java.util.List;
//인터페이스로 필요한 객체 값들을 사용할 기능들을 정의 - 구현은 Impl에서
public interface DiceLogRepository {
    void saveLog(DiceLog log);
    List<DiceLog> getAllLogs();
    int getNextGameNumber();
}
