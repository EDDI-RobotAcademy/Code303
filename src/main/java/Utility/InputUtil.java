package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    // 생성자 비공개 → 객체 생성 방지
    private InputUtil() {}

    // 문자열 입력
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // 정수 입력 (예외 처리 포함)
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // 개행 제거
                return value;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ 숫자를 입력해야 합니다. 다시 시도하세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }
    }
}
