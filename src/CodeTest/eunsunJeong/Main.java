package codetest.eunsunjeong;

import java.util.Scanner;

public class Main {
	
	//메인화면 보여주기
	public static void showMain() {
		System.out.println("======================");
		System.out.println("1. 플레이어 등록");
		System.out.println("2. 기록 열람");
		System.out.println("0. 종료");
		System.out.println("======================");
	}
	
	//게임화면 보여주기
	public static void showPlay(String player1, String player2) {
		System.out.println("======================");
		System.out.println("1번 플레이어 [" + player1 + "]님의 결과");
		diceRoll();
		System.out.println("1번 플레이어 [" + player2 + "]님의 결과");
		diceRoll();
		System.out.println("======================");
	}
	
	//주사위 3개 굴리기
	public static void diceRoll() {
		System.out.println("1번 주사위를 굴립니다.");
		int dice1 = (int)(Math.random() * 6) + 1;
		System.out.println("결과 : " + dice1);
		
		System.out.println("2번 주사위를 굴립니다.");
		int dice2 = (int)(Math.random() * 6) + 1;
		System.out.println("결과 : " + dice2);
		
		System.out.println("3번 주사위를 굴립니다.");
		int dice3 = (int)(Math.random() * 6) + 1;
		System.out.println("결과 : " + dice3);
		
		int reuslt = diceSkill(dice1, dice2, dice3);
	}
	
	//3번 주사위 스킬 발동하기(3번, 4번 구현하기)
	public static int diceSkill(int dice1, int dice2, int dice3) {
		if(dice3 == 3) {
			System.out.println("상대방의 점수를 빼앗습니다.");
			return 0;
		} else if(dice3 == 4) {
			System.out.println("자신이 즉사합니다.");
			return 0;
		} else {
			return dice1 + dice2 + dice3;
		}
	}
	
	//메인 메소드
	public static void main(String args[]) {
		int status = 1;
		Scanner sc = new Scanner(System.in);
		String[] playerName = new String[2];
		
		showMain();
		System.out.print("작업을 선택하세요 : ");
		status = Integer.parseInt(sc.nextLine());
		
		while(status != 0) {
			
			//플레이어 등록
			if(status == 1) {
				System.out.print("1번 플레이어 이름을 입력하세요 : ");
				playerName[0] = sc.nextLine();
				System.out.print("2번 플레이어 이름을 입력하세요 : ");
				playerName[1] = sc.nextLine();
				
				showPlay(playerName[0], playerName[1]);
			}
			
			if(status == 2) {
				System.out.println("기록을 열람합니다.");
			}
		}
		
		System.out.println("게임을 종료합니다.");
	}
}
