import java.io.*;
public class Caesar{
	public static String path;
	public static String estr="";
	public static char c;
	public static void Encode(String str,int n){
		
		for(int i=0;i<str.length();i++){
		  c=str.charAt(i);
		  if(c>='a'&&c<='z') 
		    if(c+n%26<='z') estr+=(char)(c+n%26); else estr+=(char)('a'+((n-('z'-c)-1)%26));
		  else if(c>='A'&&c<='Z') 
		    if(c+n%26<='Z') estr+=(char)(c+n%26); else estr+=(char)('A'+((n-('Z'-c)-1)%26));
		  else if(c>='0'&&c<='9') 
		    if(c+n%10<='9') estr+=(char)(c+n%10); else estr+=(char)('0'+((n-('9'-c)-1)%10));
		  else estr+=c;
		}
	}
	public static void Decode(String str,int n){
    for (int i = 0; i <str.length(); i++) {
    	c=str.charAt(i);
      if (c>= 'a'&&c<='z')
   	    if(c-n%26>='a') estr+=(char)(c-n%26); else estr+=(char)('z'-(n-(c-'a')-1)%26);
   	  else if(c>='A'&&c<='Z')
   	    if(c-n%26>='A') estr+=(char)(c-n%26); else estr+=(char)('Z'-(n-(c-'A')-1)%26);
   	  else if(c>='0'&&c<='9')
   	    if(c-n%10>='0') estr+=(char)(c-n%10); else estr+=(char)('9'-(n-(c-'0')-1)%10);
   	  else estr+=c;
  }
	}
	
  public static void main(String args[]){
    String array = "";
    int num;
    System.out.println("�������������룭����");
    System.out.println("(��֧��Ӣ�ĺͰ���������--zhuang)");
    try {
      System.out.println("1������");
      System.out.println("2������");
      System.out.println("3�������ƽ�");
      System.out.print("��ѡ��");
      BufferedReader input_ed = new BufferedReader(new InputStreamReader(System.in));
      String ed=input_ed.readLine();
      if(ed.equals("1")||ed.equals("2")||ed.equals("3")){
      System.out.print("�ļ�·��(*.txt)��");
      BufferedReader input_path = new BufferedReader(new InputStreamReader(System.in));
      path=input_path.readLine();
      File file = new File(path);        
      FileInputStream rdf = new FileInputStream(file);
      byte[] s = new byte[rdf.available()];
      int b = rdf.available();
      while((b=rdf.read(s, 0, b))!=-1){
      String content = new String(s,0,b);
      array = array+content;
      }
      rdf.close();      
      	if(ed.equals("3")){
      		for(int k=1;k<=25;k++){
      		 Decode(array,k);
      		 System.out.println("��ԿΪ"+k+"ʱ�������"+estr);
      		 estr="";
      		 if(k>=10){
      		  for(int j=1;j<=9;j++){Decode(array,k+26*j);System.out.println("��ԿΪ"+(k+26*j)+"ʱ�����Ϊ"+estr);estr="";}
      		}
      		}
      		System.out.print("��ȷ����ԿΪ(����)��");
      		BufferedReader input_result = new BufferedReader(new InputStreamReader(System.in));
          int result=Integer.valueOf(input_result.readLine()).intValue();
          Decode(array,result);
      	}else{
      System.out.print("��Կ(����)��");
      BufferedReader input_num = new BufferedReader(new InputStreamReader(System.in));
      num=Integer.valueOf(input_num.readLine()).intValue();   
      if(ed.equals("1"))
      Encode(array,num);
      else
      Decode(array,num);
    }
      File f=new File(path);
      FileWriter outFile=new FileWriter(f);
      BufferedWriter bufferOut=new BufferedWriter(outFile);
      bufferOut.write(estr);
      bufferOut.newLine();
      bufferOut.flush();
      bufferOut.close();
      System.out.print("��鿴ԭ�ļ��Ƿ��ѳɹ����ܻ��߽��ܡ�");
    
}else System.out.print("����������");
  }catch(Exception e){System.out.print("�������");}	
  }
}