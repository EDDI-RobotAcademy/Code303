package CodeTest.seungheyonLee;
import java.util.Scanner;

public class orchard_test {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int[][][] gameRecord=new int[2][100][4];//객체적용전 임시용 1,2,3번째,총합
        String Player1=inputPlayerName(1);
        String Player2=inputPlayerName(2);
        System.out.println(Player1+" vs "+Player2);

        int gameCount=0;
        while (true){
            int player1Score=0;
            int player2Score=0;
            System.out.println((gameCount+1)+"번째 게임");
            for(int i=0;i<3;i++){
                int roll1=randomNumber();
                int roll2=randomNumber();
                gameRecord[0][gameCount][i]=roll1;
                gameRecord[1][gameCount][i]=roll2;
                System.out.println((i+1)+"번째 주사위 결과");
                System.out.println(Player1+" : "+roll1+" , "+Player2+" : "+roll2);
                player1Score+=roll1;
                player2Score+=roll2;
            }
            gameRecord[0][gameCount][3]=player1Score;
            gameRecord[1][gameCount][3]=player2Score;

            boolean isContinue=handleMenuInput(gameRecord,Player1,Player2,gameCount);
            if(!isContinue){
                break;
            }
            gameCount++;
        }
    }
    public static String inputPlayerName(Integer Player_count){
        System.out.print(Player_count+"번째 플레이어 이름 : ");
        try{
             return scan.nextLine();
        }catch(Exception e){
            System.out.println("잘못된 입력값이잖아 다시입력해");
            return inputPlayerName(Player_count);
        }
    }
    public static boolean handleMenuInput(int[][][] gameRecord,String Player1,String Player2,Integer gameCount){
        System.out.println("계속할거면 1\n기록출력은 2\n종료는 3");
        String input=scan.nextLine();
        if(input.equals("1")){
            System.out.println("다음게임을 시작합니다");
            return true;
        }else if(input.equals("2")){
            System.out.println("기록 출력");
            printRecord(gameRecord,Player1,Player2,gameCount);
            return true;
        } else if (input.equals("3")) {
            return false;
        }else{
            System.out.println("잘못된입력이잖아");
            return handleMenuInput(gameRecord,Player1,Player2,gameCount);
        }
    }
    public static int randomNumber(){
        int Max=6;
        int Min=1;
        return (int)(Math.random()*(Max-Min+1))+Min;
    }
    public static void printRecord(int[][][] gameRecord,String Player1,String Player2,Integer gameCount){
        for(int i=0;i<gameCount;i++){
            System.out.println((i+1)+"번째 게임 결과");
            for(int j=0;j<3;j++){
                System.out.println((j+1)+"번째 주사위 결과");
                System.out.println(Player1+" : "+gameRecord[0][i][j]+" , "+Player2+" : "+gameRecord[1][i][j]);
            }
            System.out.print((i+1)+"번째 게임 결과는 : ");
            if(gameRecord[0][i][3]>gameRecord[1][i][3]){
                System.out.println(Player1+" 승리");
            }
            else if(gameRecord[0][i][3]<gameRecord[1][i][3]){
                System.out.println(Player2+" 승리");
            }
            else{
                System.out.println("무승부");
            }
            System.out.println();
        }
    }
}
