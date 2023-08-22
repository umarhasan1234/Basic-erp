package com.nrt.Email;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	private Logger log = LoggerFactory.getLogger(EmailSender.class);

	@Value("${username}")
	private String username;

	public void sendEmail(String emailTo, String subject, String filePath, Map<String, String> sourceMap)
			throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		Context context = new Context();
		for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
			context.setVariable(entry.getKey(), entry.getValue());
		}

		String emailContent = templateEngine.process(filePath, context);
		helper.setTo(emailTo);
		helper.setSubject(subject);
		helper.setText(emailContent, true);
		ClassPathResource imageResource = new ClassPathResource("templates/html/email/nrt.png");
		helper.addInline("nrtLogo", imageResource);
		javaMailSender.send(message);
		log.info("Email send  called successfully");
	}

	public void sendEmail(String emailTo, String subject, String filePath) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		Context context = new Context();
		String emailContent = templateEngine.process(filePath, context);
		helper.setTo(emailTo);
		helper.setSubject(subject);
		helper.setText(emailContent, true);
		ClassPathResource imageResource = new ClassPathResource("templates/html/email/nrt.png");
		helper.addInline("nrtLogo", imageResource);
		javaMailSender.send(message);
		log.info("Email send called successfully");
	}

}