import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Janken {
    Map<Integer, String> hands = new HashMap<>();
    List<String> results = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int winCount;
    int loseCount;
    int draw;
 
    public void playGame() {
        Janken();
        String input;
        int playerHand;
        do {
            System.out.println("じゃんけんゲーム");
            System.out.println("出したい手を選択してください。");
            System.out.println("1:グー 2:チョキ 3:パー");
            
            input = scanner.next();
            if (input.matches("[1-3]")) {
                playerHand=Integer.parseInt(input);
                break;
            }
        }while (true);

        String Hand = hands.get(playerHand); 
        int cpuHandNumber = cpuHand();
        String cpuHand = hands.get(cpuHandNumber); 
        String judge = judge(playerHand, cpuHandNumber); 

        System.out.println("Player : " + Hand); 
        System.out.println("CPU : " + cpuHand);
        System.out.println("Result : " + judge); 

        resultCount(judge); 

        continueGame();
    } 
    
    public void Janken() {
        hands.put(1,"グー");
        hands.put(2,"チョキ");
        hands.put(3,"パー");
        
        results.add("あいこ");
        results.add("勝ち");
        results.add("負け");
    }

    public int cpuHand() {
        Random random = new Random();
        int randomNumber =random.nextInt(3)+1;
        return randomNumber;
    }

    public String judge(int player, int cpu) {
        if (player == cpu) {
            return results.get(0);  //あいこ          
        }else if ((player == 1 && cpu == 2) || (player ==2 && cpu == 3) || (player ==3 && cpu == 1)) {
            return results.get(1);  //勝ち  
        }else {
            return results.get(2);   //負け    
        }
    }

    public void resultCount(String judge) {
        switch (judge) {
            case "勝ち":
                winCount += 1;
                break;
            case "負け":
                loseCount += 1;
                break;
            default: //あいこ
                draw += 1;
                break;
        }
    }
    
    public void continueGame() {
        String input;
        int number;
        do {
            System.out.println("もう一度挑戦しますか？");
            System.out.println("該当の選択番号を入力してください");
            System.out.println("1:再挑戦2:終了する");
            
            input = scanner.next();
            if (input.matches("[1-2]")) {
                number=Integer.parseInt(input);
                break;
            }

        }while (true);

        if (number ==1) {
            playGame();
        }else {
            System.out.println("判定結果："+winCount +"勝"+loseCount+"負"+draw+"引き分け");
        }
    }
}
