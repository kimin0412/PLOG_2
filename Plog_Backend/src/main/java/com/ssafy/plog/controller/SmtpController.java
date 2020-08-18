package com.ssafy.plog.controller;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plog.models.User;
import com.ssafy.plog.repository.UserRepository;

@RestController
public class SmtpController {
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/findPW")
	public void pwMailSend(@RequestParam String findmyid, @RequestParam String findemail) {
		String host = "smtp.naver.com";
		String user = "s48012@naver.com";
		String password = "test1234";
		System.out.println(findmyid + " " + findemail);
		boolean first = userRepository.existsByEmail(findemail);
		boolean second = userRepository.existsByUsername(findmyid);
		
		if (first && second) {

			Optional<User> finduser = userRepository.findByEmail(findemail);
			if (finduser.isPresent()) {
				System.out.println("chk1");
				System.out.println();
				if (finduser.get().getUsername().equals(findmyid) && finduser.get().getEmail().equals(findemail)) {
					
					Properties props = new Properties();
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", 587);
					props.put("mail.smtp.auth", "true");

					StringBuffer randompw = new StringBuffer();
					Random rnd = new Random();
					for (int i = 0; i < 10; i++) {
						int rIndex = rnd.nextInt(3);
						switch (rIndex) {
						case 0:
							// a-z
							randompw.append((char) ((int) (rnd.nextInt(26)) + 97));
							break;
						case 1:
							// A-Z
							randompw.append((char) ((int) (rnd.nextInt(26)) + 65));
							break;
						case 2:
							// 0-9
							randompw.append((rnd.nextInt(10)));
							break;
						}
					}

					String newpw = randompw.toString();
					System.out.println(newpw);
					Session session = Session.getInstance(props, new javax.mail.Authenticator() {
						protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
							return new javax.mail.PasswordAuthentication(user, password);
						}

					});

					try {
						MimeMessage msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(user, "PLOG"));
						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(findemail));

						// 메일 제목
						msg.setSubject("안녕하세요. 비밀번호 변경 메일입니다.");
						// 메일 내용
						msg.setText("새로운 비밀 번호는 : " + randompw);
						Transport.send(msg);
						System.out.println("이메일 전송");
						Long find_id = new Long(finduser.get().getId());
						System.out.println("ok" + " " + find_id);
						
						User change_user = finduser.get();
						
						change_user.setPassword(encoder.encode(newpw));
						System.out.println("ok");
						
						userRepository.save(change_user);
						
					} catch (Exception e) {
						e.printStackTrace();// TODO: handle exception
					}
				}
				else {
					System.out.println("id, emaiil 불일치 ");
				}
			}
		}
	}

	@GetMapping("/findID")
	public void idMailSend(@RequestParam String findemail2) {
		System.out.println(findemail2);
		String host = "smtp.naver.com";
		String user = "s48012@naver.com";
		String password = "test1234";

		Boolean chk = userRepository.existsByEmail(findemail2);
		if (chk) {
			Optional<User> finduser = userRepository.findByEmail(findemail2);
			if (finduser.isPresent()) {
				Properties props = new Properties();
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", 587);
				props.put("mail.smtp.auth", "true");
				String temp = finduser.get().getUsername();
				System.out.println(temp);
				
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(user, password);
					}
				});

				try {
					MimeMessage msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress(user, "PLOG"));
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(findemail2));

					msg.setSubject("안녕하세요. ID 확인 메일입니다.");
					msg.setText("당신의 ID : " + temp);
					Transport.send(msg);
					System.out.println("이메일 전송");
				} catch (Exception e) {
					e.printStackTrace();// TODO: handle exception
				}
			}
		}
	}
}
