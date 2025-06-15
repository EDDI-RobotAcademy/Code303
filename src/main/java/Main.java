import Utility.ConsoleUI;

import java.util.Scanner;

public class Main {
    // 게임 중단 조건
    static boolean isGameOver = false;

    public static void main(String[] args) {
        // 메뉴 흐름 제어용
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
    }
}