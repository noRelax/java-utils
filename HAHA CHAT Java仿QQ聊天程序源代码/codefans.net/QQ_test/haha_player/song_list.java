package haha_player;

public class song_list {

int first,second,n=20,current;
public static song[] s=new song[20];
public song_list()
{
first=0;second=1;n=20;current=first;
for(int i=0;i<n;i++)
	s[i]=new song();
}
public void add(song ss)
{
	s[second].name=ss.name;
	s[second].url=ss.url;
	second=(second+1)%n;
}

public void add(String sname,String surl)
{
	s[second].name=sname;
	s[second].url=surl;
	second=(second+1)%n;
}
public String geturl()
{
	if(current+1==second) current=first;
	current=(current+1)%n;
	return s[current%n].url;
}
public String getpreurl()
{
	if(current-1==first) current=second;
	if(current==0) current=n-1;
	current=current-1;
	return s[current].url;
}
public String geturl(String name)
{
	int f=first,se=second;
	while(se!=(f+1)%n&&!s[(f+1)%n].name.equals(name))
	{f=(f+1)%n;}
		return s[(f+1)%n].url;
}

public String getname()
{
		return s[(first+1)%n].name;
}

public void del()
{
	first=(first+1)%n;current=first+1;
}
public boolean isfull()
{
	if(second==first)
		return true;
	else
		return false;
}
public boolean isempty()
{if(second==(first+1)%n)
	return true;
else
	return false;
}
public void print()
{
	int f=first,se=second;
	while(se!=(f+1)%n)
	{f=(f+1)%n;
	System.out.println(f+"  "+s[f].name+"  "+s[f].url+"     ");}
}

private class song
{
	String name;
	String url;
}

}
