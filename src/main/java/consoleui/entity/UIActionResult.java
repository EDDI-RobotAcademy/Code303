package consoleui.entity;
//기존 BOOLEAN 또는 INT형식으로는 명확한 처리 정의가 불가능하다고 판단하여 ENUM을 추가선언
public enum UIActionResult {
    SUCCESS,    // 처리 성공
    FAILURE,    // 처리 실패
    EXIT        // 종료 요청
}
