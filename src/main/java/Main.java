import Dice.service.DiceService;
import Dice.service.DiceServiceImpl;

public class Main {
    public static void main(String[] args) {
        // 메뉴 흐름 제어용
        DiceService diceService = DiceServiceImpl.getInstance();
        diceService.startMenu();
        /*
        메뉴콘솔출력
        입력값을 받음
        문자열을 enum으로변환처리
        변환처리된값을 displayMesageFromUserInput을 통해서 결과값 return 받음(UIactionResult)
        return된 결과값 result를 기반으로 switch문을 사용해서 처리

        현재 코드는 한명을 기준으로 동작하고있다. 본 코드는 두명이 signin을 하는것을 기준으로 동작한다.
        첫번째 유저가 Signup 후 signin을 하면 그 userid값을 return해서 DiceServiceImpl에서 값을 저장,
        두번째 유저가 Signup 후 signin을 하면 그 userid값을 return해서 DiceServiceImpl에서 값을 저장 후 첫번째유저와 두번째 유저의 userid를 Account에 등록처리한다.
        Account p1 = new Account(sc.nextLine().trim(), "");
        Account p2 = new Account(sc.nextLine().trim(), "");
        이때 등록을 signin에서 Account을 등록하는게 맞는지 아니면 return해서 DiceServiceImpl에서 등록을 하는게 맞는지 모르겠다

         */
    }
}