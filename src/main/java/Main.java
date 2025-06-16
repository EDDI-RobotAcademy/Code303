import Utility.ConsoleUI;

import java.util.Scanner;

import Account.entity.Account;
import Dice.entity.DiceRoll;
import Dice.entity.DiceGame;
import Dice.service.DiceService;
import Dice.service.DiceServiceImpl;

    public static void main(String[] args) {
        // 메뉴 흐름 제어용
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
    }
}