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
		content="����HAHA����Ϊ��"+num+"������Ϊ"+pwd+".���½�������������룬��ɾ�����ʼ����Ա�֤����İ�ȫ��";
		to=to_mail;
		send();
	}
	public void send()
	{
		try{
			int a=0;
		MimeMessage mimeMsg; //MIME�ʼ����� 
		Multipart mp; //Multipart����,�ʼ�����,����,���������ݾ���ӵ����к�������MimeMessage���� 
		Session session; //�ʼ��Ự����
		Properties props; //ϵͳ���� 
		props = System.getProperties(); //���ϵͳ���Զ��� 
		props.put("mail.smtp.host",smtp);
		session = Session.getDefaultInstance(props,null); //����ʼ��Ự���� 
		mimeMsg = new MimeMessage(session); //����MIME�ʼ����� 
		mp = new MimeMultipart(); 
		mimeMsg.setSubject("HAHA������Ϣ");//�ʼ���������
		props.put("mail.smtp.auth","false"); 
		//�����ʼ�����
		BodyPart bp = new MimeBodyPart(); 
		bp.setContent(content,"text/html;charset=GB2312"); 
		mp.addBodyPart(bp);
		mimeMsg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		mimeMsg.setFrom(new InternetAddress(from)); //���÷�����
		mimeMsg.setContent(mp); 
		mimeMsg.saveChanges();
		System.out.println("���ڷ����ʼ�...."); 
		Session mailSession = Session.getInstance(props,null); 
		Transport transport = mailSession.getTransport("smtp"); 
		transport.connect((String)props.get("mail.smtp.host"),from,pwd); 
		transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO)); 
		//transport.send(mimeMsg); 
		transport.close(); 
		a++;
			
		}catch(Exception e2){System.out.println("����ʧ��");}
	}
}
