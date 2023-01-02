package fracCalc;
import java.util.*;
// Impana Chimmlalagi 
// Period 2

public class FracCalc {

    public static int w;
    public static int n;
    public static int d;

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner console = new Scanner(System.in);
        System.out.print("Enter your fractions (quit to end): ");
        String input = console.nextLine();
        while (!(input.equals("quit"))){
          produceAnswer(input);
          System.out.print("Enter your fractions (quit to end): ");
          input = console.nextLine();
        } 

    }

////////////////////////////////////////////////////////////////////    
    public static String produceAnswer(String input){ 
        // TODO: Implement this function to produce the solution to the input
        String op = "";
        int num_unred = 0;
        int den_unred = 0;
        int count = 0;
        boolean check = false;
      
        for(int i=0; i<input.length(); i++){
          if((input.charAt(i) == ' ')){
            op = Character.toString(input.charAt(i+1));
            if(((op=="+")||(op=="-")||(op=="*")||(op=="/"))&&(input.charAt(i+2) == ' ')){
              check=true;
            }
            count = i+1;
            break;
          }
        }
        
        if(check==false){
          System.out.println("ERROR: Input is in an invalid format.");
        }

        String first = input.substring(0,count-1);
        strip(first);
        int whole_1st = w;
        int num_1st = n;
        int den_1st = d;
        int improper_1st = 0;
        if (whole_1st<0){
          improper_1st = ((whole_1st * den_1st) - num_1st);
        } else {
          improper_1st = ((whole_1st * den_1st) + num_1st);
        }

        String second = input.substring(count+2,input.length());
        strip(second);
        int whole_2nd = w;
        int num_2nd = n;
        int den_2nd = d;
        int improper_2nd = 0;
        if (whole_2nd<0){
          improper_2nd = ((whole_2nd * den_2nd) - num_2nd);
        } else {
          improper_2nd = ((whole_2nd * den_2nd) + num_2nd);
        }

        if((den_1st==0)||(den_2nd==0)){
          System.out.println("ERROR: Cannot divide by zero.");
        }
        if (op.equals("+")){
          num_unred = ((improper_1st * den_2nd)+(improper_2nd * den_1st));
          den_unred = (den_1st * den_2nd);
        }
        if (op.equals("-")){
          num_unred = ((improper_1st * den_2nd) - (improper_2nd * den_1st));
          den_unred = (den_1st * den_2nd);
        }
        if (op.equals("*")){
          num_unred = (improper_1st * improper_2nd);
          den_unred = (den_1st * den_2nd);
        }
        if (op.equals("/")){
          num_unred = (improper_1st * den_2nd); 
          den_unred = (den_1st * improper_2nd);
        }
        //System.out.println("" + num_unred + "/" + den_unred);

        int factor = GCF(num_unred,den_unred);
        int num_red = num_unred / factor;
        int den_red = (den_unred / factor);

        if(num_red==0){
          return("0");
        }
        else if(den_red==1){
          return("" + num_red);
        }
        else if (Math.abs(num_red) > Math.abs(den_red)){
          int x = num_red/den_red;
          int whole_red = x;
          num_red = num_red-(x*den_red);
          if (num_red == 0){
            return("" + whole_red);
          }
          else {
            return(whole_red+"_"+Math.abs(num_red)+"/"+Math.abs(den_red));
          }
        }
        else{
          return(num_red+"/"+Math.abs(den_red));
        }
        
    
  }
////////////////////////////////////////////////////////////////////
 
    public static void strip(String frac){
      String wholex = "";
      String denx = "";
      String numx = "";
      String part = "";
      int count2 = 0;
      int count3 = 0;
      for(int i=0; i<frac.length(); i++){
          if(frac.charAt(i) == '/'){
            //String x = Character.toString(frac.charAt(i+1));
            count2 = i+1;
            part = frac.substring(0,count2-1);
            denx = frac.substring(count2, frac.length());
            break;
          }
          else {
            denx = "1";
            numx = "0";
          }
      }
      for(int i=0; i<part.length(); i++){
          if(part.charAt(i) == '_'){
            //String y = Character.toString(frac.charAt(i+1));
            count3 = i+1;
            wholex = frac.substring(0,count3-1);
            numx = frac.substring(count3, part.length());
            break;
          }
          else {
            numx = part;
            wholex = "0";
          }
      }
      if (numx == "0"){
        wholex = frac;
      }
      
      w = Integer.valueOf(wholex);
      n = Integer.valueOf(numx);
      d = Integer.valueOf(denx);
    }

//////////////////////////////////////////////////////////////////// 
 
  public static int GCF(int a, int b) {
    if (b == 0) {
      return a;
    } else {
      return (GCF(b, (a % b)));
    }
  }

}
