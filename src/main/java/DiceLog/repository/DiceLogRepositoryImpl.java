package DiceLog.repository;

import DiceLog.entity.DiceLog;

import java.util.*;
//데이터를 List에저장하기 위한 구동부분
public class DiceLogRepositoryImpl implements DiceLogRepository {
    //DiceLogRepositoryImpl getInstance()를 통해 이 필드 하나로만 인스턴스를 저장하고 추적하게 해서 싱글턴화시킴
    private static DiceLogRepositoryImpl instance;
    //게임 기록을 싹다 저장할 리스트 생성
    private final List<DiceLog> logList = new ArrayList<>();
    //게임 카운터 1부터 시작
    private int gameCounter = 1;
    //getInstance로만 접근 가능하게 private로 싱글턴 패턴
    private DiceLogRepositoryImpl() {}


    public static DiceLogRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DiceLogRepositoryImpl();
        }
        return instance;
    }

    @Override
    //logList에 전달받은 DiceLog객체를 전달해서 저장
    public void saveLog(DiceLog log) {
        logList.add(log);
    }

    @Override
    //저장된 게임 결과 불러오는 메서드
    public List<DiceLog> getAllLogs() {
        return new ArrayList<>(logList);
    }

    @Override
    //n번째 게임 만들기 위해 ++시켜줌
    public int getNextGameNumber() {
        return gameCounter++;
    }
}
