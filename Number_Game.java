import java.util.*;
class range{
    public int genrate(int max, int min){
        return (int) (Math.random()*(max-min+1)+min);
    }
}

public class Number_Game{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        range rg = new range();
        int TA=0;
        int win=0;

        while(true){
            System.out.print("Enter the minimum number:");
            int min=sc.nextInt();
            System.out.print("Enter the maximum number:");
            int max=sc.nextInt();
            sc.nextLine();
            int c=rg.genrate(max,min);
            int A=0;

            while(true){
                System.out.println("Guesss a number between "+min+" and "+max);
                int g=sc.nextInt();
                A++;

                if(g>c){
                    System.out.println("You are guessing the high number");
                }
                else if(g<c){
                    System.out.println("You are guessing the low number");
                          
                }
                else{
                    System.out.println("Your Guess Was Correct");
                    win++;
                    break;
                }
            }
            TA=TA+A;
            System.out.println("Attempt="+A);
            System.out.println("Wins="+win);

            double winrate=(double) win/TA*100;
            System.out.printf("Your winning rate is %.2f%%\n",winrate);
            System.out.println("Do you want to play again? (y / n)");
            String PA=sc.next();
            if(!PA.equalsIgnoreCase("y")){
                sc.close();
                System.exit(0);

            }
          sc.nextLine();
        }
    }
}