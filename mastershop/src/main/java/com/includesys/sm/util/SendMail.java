package com.includesys.sm.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
	
	private static final String emailHost = "smtp.gmail.com";
	private static final String emailId = "center.mastershop";
	private static final String emailPw = "123qweas!";

	public void sendEmail(String from, String to, String subject, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailHost);
		props.put("mail.smtp.auth", "true");

		EmailAuthenticator authenticator = new EmailAuthenticator(emailId, emailPw);

		Session session = Session.getInstance(props, authenticator);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			msg.setSubject(subject);
			msg.setContent(content, "text/html; charset=utf-8");
			msg.setSentDate(new Date());

			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	class EmailAuthenticator extends Authenticator {
		private String id;
		private String pw;

		public EmailAuthenticator(String id, String pw) {
			this.id = id;
			this.pw = pw;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(id, pw);
		}
	}

	public String  mailTemplate(String name, String pw){		
		
		StringBuffer str = new StringBuffer();
		
		str.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		str.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		str.append("<head>");
		str.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		str.append("<title>Master Shop</title>");
		str.append("</head>");
		str.append("<body>");
		str.append("<div style=\"width:680px; margin:0 auto;\">");
		str.append("<table width=\"640\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		str.append("<tr>");
		str.append("<td height=\"60\"><img src=\"http://www.mastershop.kr/resources/images/shopmaster/mail/mail_logo.gif\" /></td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<td background=\"http://www.mastershop.kr/resources/images/shopmaster/mail/mail_bg.gif\">");
		str.append("<div><img src=\"http://www.mastershop.kr/resources/images/shopmaster/mail/mail_topimg.gif\" /></div>");
		str.append("<div style=\"width:560px; height:36px; background-color:#d8d8d8; margin-left:30px; padding:24px 0px 0px 20px; font-size:16px; font-weight:bold; font-family:돋움; \">");		
		str.append(name + " 회원님 임시비밀번호는 " + pw +" 입니다.");
		str.append("</div>");
		str.append("<div style=\"width:600px; height:90px; margin:20px 0px 0px 30px;\"><img src=\"http://www.mastershop.kr/resources/images/shopmaster/mail/mail_txt.gif\" /></div>");
		str.append("<div style=\"width:600px; height:20px; margin-left:30px;\"><img src=\"http://www.mastershop.kr/resources/images/shopmaster/mail/mail_bottom_txt.gif\" /></div>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<td height=\"50\" bgcolor=\"#222222\"><img src=\"http://www.mastershop.kr/resources/images/shopmaster/mail/mail_bottom_logo.gif\" style=\"margin-left:30px;\"></td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("</div>");
		str.append("</body>");
		str.append("</html>");
	
		return str.toString();
				
				
	}
	
	/*
	public static void main(String[] args) {
		String subject = "[마스터샵] 비밀번호 초기화 메일 발송";
		String content = "고객님의 비밀번호가 초기화 되었습니다. 고객님님의 비밀번호는 입니다.";
		String from = "webmaster.shopmaster.co.kr";//보내는 이메일 주소
		String to = "hasiki30@daum.net";
		// 받을 이메일 주소는 반드시 ","로 구분해준다.

		new SendMail().sendEmail(from, to, subject, content);	
	}
	*/
}
