package CodeTest.eunsunJeong;

import java.util.Scanner;

public class Main {

	// 게임화면 보여주기
	public static void showPlay(String player1, String player2) {
		System.out.println("======================");
		System.out.println("1번 플레이어 [" + player1 + "]님의 결과");
		diceRoll();
		System.out.println();

		System.out.println("2번 플레이어 [" + player2 + "]님의 결과");
		diceRoll();
		System.out.println("======================");
	}

	// 주사위 2개 굴리기
	public static void diceRoll() {
		int[] dice = new int[2];

		System.out.println("1번 주사위를 굴립니다.");
		dice[0] = (int) (Math.random() * 6) + 1;
		System.out.println("결과 : " + dice[0]);

		System.out.println("2번 주사위를 굴립니다.");
		dice[1] = (int) (Math.random() * 6) + 1;
		System.out.println("결과 : " + dice[1]);

		if (dice[0] % 2 == 0) {
			System.out.println("스킬 발동!");
			int dice3 = diceSkill();
		}
	}

	// 3번 주사위 스킬 발동하기(3번, 4번 구현하기)
	public static int diceSkill() {
		System.out.println("3번 주사위를 굴립니다.");
		int dice3 = (int) (Math.random() * 6) + 1;
		System.out.println("결과 : " + dice3);

		if (dice3 == 3) {
			// 상대방의 점수를 빼앗으려면 상대방의 점수를 가져와야 하는데
			System.out.println("상대방의 점수를 빼앗습니다.");
			return 0;
		} else if (dice3 == 4) {
			// 바로 게임 종료? status 0으로 설정해서? 그러면 status를 전역변수로 선언해야 하나?
			System.out.println("즉사합니다.");
			return 0;
		} else {
			System.out.println("1, 2, 3번 주사위를 합산합니다.");
			return dice3;
		}
	}

	// 메인 메소드
	public static void main(String args[]) {
		int status = 1;
		Scanner sc = new Scanner(System.in);
		String[] playerName = new String[2];

		System.out.println("======================");
		System.out.println("1. 플레이어 등록");
		System.out.println("0. 종료");
		System.out.println("======================");
		System.out.print("작업을 선택하세요 : ");
		status = Integer.parseInt(sc.nextLine());

		while (status != 0) {
			// 플레이어 등록은 처음 한 번만 실행되어야지
			if (status == 1) {
				System.out.print("1번 플레이어 이름을 입력하세요 : ");
				playerName[0] = sc.nextLine();
				System.out.print("2번 플레이어 이름을 입력하세요 : ");
				playerName[1] = sc.nextLine();

				while (status != 0) {
					System.out.println("======================");
					System.out.println("1. 게임 시작");
					System.out.println("2. 기록 열람");
					System.out.println("0. 종료");
					System.out.println("======================");
					System.out.print("작업을 선택하세요 : ");
					status = Integer.parseInt(sc.nextLine());

					if (status == 1) {
						// 플레이어 등록 이후에는 종료 전까지 게임만 플레이 되도록
						showPlay(playerName[0], playerName[1]);
					} else if (status == 2) {
						// 기록 열람하기
					} else {
						// 게임 종료
						break;
					}
				}
			}
		}

		System.out.println("게임을 종료합니다.");
	}
}
