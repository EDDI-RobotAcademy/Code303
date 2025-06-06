package CodeTest.seungheyonLee;
import java.util.Scanner;

public class orchard_test {
    public static Scanner scan = new Scanner(System.in);
    public static final int Dice_count=3;
    public static final int MinDiceValue=1;
    public static final int MaxDiceValue=6;
    public static void main(String[] args) {
        int[][][] gameRecord=new int[2][100][4];//객체적용전 임시용 1,2,3번째,총합
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 100; j++) {
                //초기값0에서 오류발생 방지용 --본프로젝트에서는 쓸일 절대없음
                gameRecord[i][j][2] = -1; // 3번째 주사위 초기화
            }
        }
        String Player1=inputPlayerName(1);
        String Player2=inputPlayerName(2);
        System.out.println(Player1+" vs "+Player2);

        int gameCount=0;
        while (true){
            int player1Score=0;
            int player2Score=0;
            int player1Roll=0;
            int player2Roll=0;
            System.out.println((gameCount+1)+"번째 게임");
            for(int diceIndex=0;diceIndex<Dice_count;diceIndex++){
                System.out.println((diceIndex+1)+"번째 주사위 결과");
                if(diceIndex==2){
                    boolean isPlayer1Even=gameRecord[0][gameCount][0]%2==0;
                    boolean isPlayer2Even=gameRecord[1][gameCount][0]%2==0;

                    if(!isPlayer1Even){
                        System.out.print(Player1+"의 3번째 주사위 미발동");
                    }else{
                        player1Roll=rollAndRecord(gameRecord,1,gameCount,diceIndex,Player1);

                    }
                    System.out.print(" , ");
                    if(!isPlayer2Even){
                        System.out.print(Player2+"의 3번째 주사위 미발동");
                    }else{
                        player2Roll=rollAndRecord(gameRecord,2,gameCount,diceIndex,Player2);

                    }
                    System.out.println();


                    if(player1Roll==3){
                        player1Score+=player2Score;
                        player2Score=0;//상대점수 제거
                    }else if(player2Roll==3){
                        player2Score+=player1Score;
                        player1Score=0;
                    }
                    if(player1Roll==4){
                        player1Score=0;
                    }else if(player2Roll==4){
                        player2Score=0;
                    }
                    if(player1Roll!=3 && player1Roll!=4){
                        player1Score+=player1Roll;
                    }
                    if(player2Roll!=3 && player2Roll !=4){
                        player2Score+=player2Roll;
                    }
                    continue;
                }
                //gameRecord,player(n)번째 유저,gameCount번째 게임,i번째 주사위
                player1Roll=rollAndRecord(gameRecord,1,gameCount,diceIndex,Player1);
                player2Roll=rollAndRecord(gameRecord,2,gameCount,diceIndex,Player2);
                player1Score+=player1Roll;
                player2Score+=player2Roll;
                System.out.println();
            }
            gameRecord[0][gameCount][3]=player1Score;//총합점수 입력
            gameRecord[1][gameCount][3]=player2Score;

            boolean isContinue=handleMenuInput(gameRecord,Player1,Player2,gameCount);
            if(!isContinue){//handleMenuInput 결과값이 종료이면
                break;
            }
            gameCount++;
        }
    }
    //gameRecord,player(n)번째 유저,gameCount번째 게임,i번째 주사위
    public static int rollAndRecord(int[][][] gameRecord,int playerIndex,int gameCount,int diceIndex,String playerName){
        int roll=randomNumber();
        gameRecord[playerIndex-1][gameCount][diceIndex]=roll;
        System.out.print(playerName+" : "+roll+"     ");
        return roll;
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
        switch (input) {
            case "1" -> {
                System.out.println("다음게임을 시작합니다");
                return true;
            }
            case "2" -> {
                //한번도 게임안한거 쳐내기위함
                if (gameRecord[0][0][0] == 0 && gameRecord[1][0][0] == 0) {
                    System.out.println("한번도 게임을 안했잖아 다시 입력해");
                    return handleMenuInput(gameRecord, Player1, Player2, gameCount);
                }
                System.out.println("기록 출력");
                printRecord(gameRecord, Player1, Player2, gameCount);
                return true;
            }
            case "3" -> {
                return false;
            }
            default -> {
                System.out.println("잘못된입력이잖아");
                return handleMenuInput(gameRecord, Player1, Player2, gameCount);
            }
        }
    }
    public static int randomNumber(){
        return (int)(Math.random()*(MaxDiceValue-MinDiceValue+1))+MinDiceValue;
    }
    public static void printRecord(int[][][] gameRecord,String Player1,String Player2,Integer gameCount){
        //gameCount는 1부터 시작이니까 +1
        for(int i=0;i<gameCount+1;i++){
            System.out.println((i+1)+"번째 게임 결과");
            for(int j=0;j<Dice_count;j++){
                //배열의 경우 기본값을 0으로 잡기에 입력이 없어도 0으로 값이 안들어온걸로 처리가능
                if(j==2){
                    if(gameRecord[0][i][j]==-1){
                        System.out.print(Player1+"의 3번째 주사위 미발동");
                    }else{
                        System.out.print(Player1+" : "+gameRecord[0][i][j]);
                    }
                    System.out.print("     ");
                    if(gameRecord[1][i][j]==-1){
                        System.out.print(Player2+"의 3번째 주사위 미발동");
                    }else{
                        System.out.print(Player2+" : "+gameRecord[1][i][j]);
                    }
                    System.out.println();
                    continue;
                }
                System.out.println((j+1)+"번째 주사위 결과");
                System.out.println(Player1+" : "+gameRecord[0][i][j]+"     "+Player2+" : "+gameRecord[1][i][j]);
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
            System.out.println("======================================");
        }
    }
}
