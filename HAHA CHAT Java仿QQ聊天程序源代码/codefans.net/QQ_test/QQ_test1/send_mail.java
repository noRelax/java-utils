package QQ_test1;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class send_mail
{
	private String from="yinheyu@mail.sdu.edu.cn";
	private String to="";
	private String smtp="mail.sdu.edu.cn";
	private String pwd="15966209001";
	private String content="";
	public send_mail(String num,String pwd,String to_mail)
	{
		content="您的HAHA号码为："+num+"，密码为"+pwd+".请登陆后立即更改密码，并删除该邮件，以保证号码的安全性";
		to=to_mail;
		send();
	}
	public void send()
	{
		try{
			int a=0;
		MimeMessage mimeMsg; //MIME邮件对象 
		Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象 
		Session session; //邮件会话对象
		Properties props; //系统属性 
		props = System.getProperties(); //获得系统属性对象 
		props.put("mail.smtp.host",smtp);
		session = Session.getDefaultInstance(props,null); //获得邮件会话对象 
		mimeMsg = new MimeMessage(session); //创建MIME邮件对象 
		mp = new MimeMultipart(); 
		mimeMsg.setSubject("HAHA号码信息");//邮件标题设置
		props.put("mail.smtp.auth","false"); 
		//设置邮件内容
		BodyPart bp = new MimeBodyPart(); 
		bp.setContent(content,"text/html;charset=GB2312"); 
		mp.addBodyPart(bp);
		mimeMsg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		mimeMsg.setFrom(new InternetAddress(from)); //设置发信人
		mimeMsg.setContent(mp); 
		mimeMsg.saveChanges();
		System.out.println("正在发送邮件...."); 
		Session mailSession = Session.getInstance(props,null); 
		Transport transport = mailSession.getTransport("smtp"); 
		transport.connect((String)props.get("mail.smtp.host"),from,pwd); 
		transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO)); 
		//transport.send(mimeMsg); 
		transport.close(); 
		a++;
			
		}catch(Exception e2){System.out.println("发送失败");}
	}
}
