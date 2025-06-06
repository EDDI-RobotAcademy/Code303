package CodeTest.seungheyonLee;
import java.util.Scanner;

public class orchard_test {
    public static void main(String[] args) {
        int[][][] gameRecord=new int[2][100][4];//객체적용전 임시용 1,2,3번째,총합
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Player 1 name:");
        String Player1=scan.nextLine();
        System.out.print("Enter Player 2 name:");
        String Player2=scan.nextLine();
        int Max=6;
        int Min=1;
        System.out.println();
        int gameCount=0;
        while (true){
            for(int i=0;i<3;i++){
                int roll1=RandomNumber();
                int roll2=RandomNumber();
                gameRecord[0][gameCount][i]=roll1;
                gameRecord[1][gameCount][i]=roll2;
                System.out.println((i+1)+"번째 주사위 결과");
                System.out.println(Player1+" : "+roll1+" , "+Player2+" : "+roll2);


            }
            System.out.print("종료할꺼야? y/n :");
            String exit=scan.nextLine();
            if(exit.equals("y")){
                break;
            }
            gameCount++;
            System.out.println(gameRecord);
        }
    }
    public static int RandomNumber(){
        int Max=6;
        int Min=1;
        int randomNumber=(int)(Math.random()*Max-Min+1)+Min;
        return randomNumber;
    }
}
