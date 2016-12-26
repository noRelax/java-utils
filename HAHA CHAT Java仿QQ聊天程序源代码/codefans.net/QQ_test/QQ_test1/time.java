package QQ_test1;
import java.text.*; 
import java.util.*; 
public final class time { // Session keys 
public static final String gettime(){ 
Calendar cal = Calendar.getInstance(); 
java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
String cdate = sdf.format(cal.getTime()); 
return cdate; 
}
public static void main(String args[])
{
	System.out.println(time.gettime());
}
} 