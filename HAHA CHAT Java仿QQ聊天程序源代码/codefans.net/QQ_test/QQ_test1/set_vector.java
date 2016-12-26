package QQ_test1;
import java.util.*;
public class set_vector {
public String msg="";
public Vector v;
public Vector set(String msg,int count)
{
	 StringTokenizer str;
	 str=new StringTokenizer(msg,"~");
	 Vector v,data=new Vector();
	 boolean isend=false;
	 while(str.hasMoreTokens())
	 {
		 v=new Vector();
		 for(int i=0;i<count;i++)
		 {
			 if(str.hasMoreTokens())
			 {
				 v.add(str.nextElement());
			 }
			 else
			 {
				 isend=true;
			 }
		 }
		 if(!isend)
			 data.add(v);
	 }
	 return data;
}
}
