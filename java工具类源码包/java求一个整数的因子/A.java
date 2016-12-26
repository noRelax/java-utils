import java.io.*;
public class A
{

   public static void main(String[] args)
   {
       String ch;
   
         System.out.print("enter a integer number: ");
         BufferedReader   br=new   BufferedReader(new   InputStreamReader   (System.in));
      int a=0;  
 try{
     
        ch=br.readLine();
       a= Integer.parseInt(ch);     
    }catch(IOException e){};
    
    System.out.println("The number is:    "+a); 
    System.out.print("It's factors are:");
   for(int b=1;b<=a;b++)
    if(a%b==0)
     System.out.print(b+"\t");
     System.out.println("\n");

}
}