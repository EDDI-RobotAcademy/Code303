package consoleui.repository;

import Account.service.AccountServiceImpl;
import consoleui.entity.ConsoleUiMessage;
import consoleui.entity.UIActionResult;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConsoleUiRepositoryImpl implements ConsoleUiRepository {
    private static ConsoleUiRepositoryImpl instance;
    private final  AccountServiceImpl accountService;
    public static ConsoleUiRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ConsoleUiRepositoryImpl();
        }

        return instance;
    }
    /*
    EnumMap은 키가 enum일 때 최적화된 맵 구현입니다.
    키 타입은 ConsoleUiMessage (SIGNUP, SIGNIN, EXIT), 값 타입은 Runnable (실행 가능한 메서드 참조)입니다.
     */
//    private final Map<ConsoleUiMessage, Runnable> actionMap
//            = new EnumMap<>(ConsoleUiMessage.class);
    //기존 Runnable은 return을 가져올수가없다.
    //그렇기에 Supplier를 사용해서 처리한다 현재코드는 integer를 기준으로 처리한다
    /*사용할 enum을 Supplier형식으로 연동처리한다*/
    private final Map<ConsoleUiMessage, Supplier<UIActionResult>>actionMap=
            new EnumMap<>(ConsoleUiMessage.class);

    private ConsoleUiRepositoryImpl() {
        accountService=AccountServiceImpl.getInstance();
        //리턴값int를 Runnable로 바꾸기위해서 람다로 감싸기
//      actionMap.put(ConsoleUiMessage.SIGNUP, ()->accountService.register());
        actionMap.put(ConsoleUiMessage.SIGNUP, accountService::register);
        actionMap.put(ConsoleUiMessage.SIGNIN, accountService::signIn);
        actionMap.put(ConsoleUiMessage.EXIT,   () -> { displayExitMessage(); return UIActionResult.EXIT; });
    }

    @Override
    public void displayWelcomeMessage() {
        System.out.println("주사위 게임에 오신것을 환영합니다");
    }

    @Override
    public void displayInitialMessage() {
        System.out.println("1.회원가입\n2.로그인\n3.게임종료");
    }

    @Override
    public UIActionResult displayMessageFromUserInput(ConsoleUiMessage selectedMessage) {
        Supplier<UIActionResult> action = actionMap.get(selectedMessage);
        if (action != null) {
            return action.get();    // ❗ 반환값을 호출부에 그대로 전달
        }
        System.out.println("알 수 없는 명령입니다.");
        return UIActionResult.FAILURE;
    }
    //    public void displayMessageFromUserInput(ConsoleUiMessage message) {
//        Runnable action = actionMap.get(message);  // ①
//        if (action != null) {                      // ②
//            action.run();                          // ③
//            return;                                // ④
//        }
//        System.out.println("알 수 없는 명령을 요청하였습니다.");
//    }//supply는 대체 또 뭐야

    @Override
    public void displayExitMessage() {
        System.out.println("게임을 종료합니다");
        System.exit(0);
    }

    @Override
    public void showGameMenu() {

    }

    @Override
    public void displayErrorMessage() {

    }
}
