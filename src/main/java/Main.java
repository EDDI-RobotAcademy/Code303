import Dice.service.DiceService;
import Dice.service.DiceServiceImpl;

public class Main {
    public static void main(String[] args) {
        // 메뉴 흐름 제어용
        DiceService diceService = DiceServiceImpl.getInstance();
        diceService.startMenu();
    }
}