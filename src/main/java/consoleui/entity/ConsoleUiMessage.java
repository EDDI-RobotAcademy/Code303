package consoleui.entity;
/*
열거형 상수 생성 시 키 할당
SIGNUP("1"), SIGNIN("2"), EXIT("3") 구문을 통해 각각의 상수에 "1", "2", "3" 키가 userInputKey 필드에 저장됩니다.
fromUserInput() 호출
사용자가 콘솔에 뭔가 입력하면 그 문자열이 fromUserInput(input)의 input 파라미터로 넘어옵니다.
모든 상수 순회 및 비교
ConsoleUiMessage.values()로 SIGNUP, SIGNIN, EXIT 세 상수를 배열 형태로 가져와
각 상수의 userInputKey.equals(input)를 호출해, 입력값과 미리 할당된 키가 같은지 확인합니다.
매칭 시 해당 상수 반환
키가 같으면 그 순간 해당 ConsoleUiMessage 상수를 반환합니다.
(SIGNUP("1")과 입력이 "1"이라면 SIGNUP 반환)
매칭 실패 시 예외
세 상수 모두 키가 일치하지 않으면 IllegalArgumentException을 던져 “잘못된 입력”을 알립니다.
 */
// 사용자 입력에 따라 콘솔 UI의 각 메뉴를 나타내는 열거형(enum) 클래스
public enum ConsoleUiMessage {
    // 열거형 상수 정의: 회원가입, 로그인, 종료 메뉴를 각각의 키와 매핑
    SIGNUP("1"),   // 사용자 입력 '1'이면 회원가입(Sign Up) 메뉴 선택
    SIGNIN("2"),   // 사용자 입력 '2'이면 로그인(Sign In) 메뉴 선택
    EXIT("3");     // 사용자 입력 '3'이면 프로그램 종료(Exit) 메뉴 선택

    // 각 상수에 연결된 사용자 입력 키를 저장하는 필드
    private final String userInputKey;

    // 생성자: enum 상수가 생성될 때 호출되어 키 값을 초기화
    ConsoleUiMessage(String userInputKey) {
        // this.userInputKey 필드에 매핑된 입력 키 저장
        this.userInputKey = userInputKey;
    }

    /**
     * 사용자가 입력한 문자열(input)에 해당하는
     * ConsoleUiMessage enum 상수를 찾아 반환.
     *
     * @param input 콘솔에서 사용자가 입력한 문자열
     * @return 매칭되는 ConsoleUiMessage 상수
     * @throws IllegalArgumentException 매핑되는 상수가 없을 경우 예외 발생
     */
    public static ConsoleUiMessage fromUserInput(String input) {
        // 모든 열거형 상수 순회
        for (ConsoleUiMessage message : ConsoleUiMessage.values()) {
            // 상수에 저장된 키와 사용자의 입력이 일치하는지 비교
            if (message.userInputKey.equals(input)) {
                return message;  // 일치하면 해당 enum 상수 반환
            }
        }
        // 일치하는 값이 없으면 잘못된 입력이라는 예외를 던짐
        throw new IllegalArgumentException("Invalid user input: " + input);
    }
}
