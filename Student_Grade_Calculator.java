import java.util.*;

public class Student_Grade_Calculator{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("**********Student Grade Calculator**********");
        System.out.print("Enter the number of Subjects:");

        int s=sc.nextInt();
        int total=0;
        System.out.println("Enter the marks obtained in "+s+" Subjects respectively: ");
        for(int i=0;i<s;i++){
            int marks=sc.nextInt();
            total=total+marks;
        }
         double AP=(double)total/s;
         String Grade;
         if (AP>=90){
           Grade="A+"; 
         }
         else if(AP>=80){
            Grade="A";
         }
         else if(AP>=70){
            Grade="B";
         }
         else if(AP>=60){
            Grade="C";
         }
         else if(AP>=50){
            Grade="D";
         }
         else if(AP>=40){
            Grade="E";
         }
         else{
            Grade="Fail(F)";
         }
         System.out.println("Total Marks obtain : "+total);
         System.out.println("Avreage Percentage : "+AP+" %");
         System.out.println("Grade is : "+Grade);
       sc.close();
    }
}