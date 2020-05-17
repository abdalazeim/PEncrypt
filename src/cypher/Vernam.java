
package cypher;
  
 import java.io.*;
class Vernam
{
 public static int getCharValue(char x)
 {
  int y=(int)'a';
  return ((int)x-y);
 }

 public static char getNumberValue(int x)
 {
  int z=x+(int)'a';
  return ((char)z);
 }
 
 public static void main(String args[])throws Exception
 {
 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 System.out.println("Enter your plain text");
 String accept=br.readLine();
 System.out.println("\nEnter your one time pad text");
 String pad=br.readLine();
 int aval[]=new int[accept.length()];
 int pval[]=new int[pad.length()];
 int initval[]=new int[pad.length()];
  if(pad.length()!=accept.length())
  {
   System.out.println("Invalid one time pad. Application terminates.");
   return;
  }
  for(int i=0;i<accept.length();i++)
  {
   int k=getCharValue(accept.charAt(i));
   aval[i]=k;
  }
  for(int i=0;i<pad.length();i++)
  {
   int k=getCharValue(pad.charAt(i));
   pval[i]=k;
  }
  for(int i=0;i<pad.length();i++)
  {
   initval[i]=aval[i]+pval[i];
   if(initval[i]>25)
    initval[i]-=26;
  }
 System.out.println("\nCipher text is : ");
 String cipher="";
  for(int i=0;i<pad.length();i++)
  {
  cipher+=getNumberValue(initval[i]);  
  }
 System.out.print(cipher);
 }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
