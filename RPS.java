import java.util.Scanner;
import java.text.DecimalFormat;

class RPS{
    int computer;
    int user;
    int userScore;
    int computerScore;
    double userWin;
    boolean userWinLast = false;
    int userLast;
    
    public static void main(String[] args){
        RPS rpsGame = new RPS();
        rpsGame.go();
    }
    
    void go(){
        //instructions on how to play
        System.out.println("Enter: 0 = Rock; 1 = Paper; 2 = Scissor; -1 = Exit");
        computer = (int)(Math.random()*3);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            userLast = user;
            user = sc.nextInt();
            if(user < -1 || user > 2){
                System.out.println("Invalid choice. Please enter a valid input.");
                System.out.println("Enter: 0 = Rock; 1 = Paper; 2 = Scissor; -1 = Exit");
                continue;
            }
            if(user == -1){
                System.exit(0);
            }
            if(userWinLast == false){
                computer = userLast;
            }
            if(userWinLast == true){
                if(userLast == 0){
                    computer = 1;
                } else if (userLast == 1){
                    computer = 2;
                } else {
                    computer = 0;
                }
            }
            if(user == 0) System.out.println("User chose rock.");
            if(user == 1) System.out.println("User chose paper.");
            if(user == 2) System.out.println("User chose scissor.");
            if(computer == 0) System.out.println("Computer chose rock.");
            if(computer == 1) System.out.println("Computer chose paper.");
            if(computer == 2) System.out.println("Computer chose scissor.");
            if(user == computer) {
                System.out.println("User and computer have a tie.");
                continue;
            } else if(user==0 && computer==1 || user==1 && computer==2 || user==2 && computer==0){
                System.out.println("Computer won!");
                computerScore++;
                userWinLast = false;
            } else {
                System.out.println("User won!");
                userScore++;
                userWinLast = true;
            }
            userWin = roundTwoDecimals(((double)userScore/(double)(userScore+computerScore))*100);
            System.out.println("User win rate is: "+userWin + " %");
            System.out.println("Enter: 0 = Rock; 1 = Paper; 2 = Scissor; -1 = Exit");
        }
    }
    
    double roundTwoDecimals(double d){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
        
}
        