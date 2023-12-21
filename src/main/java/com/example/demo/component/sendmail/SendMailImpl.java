package com.example.demo.component.sendmail;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendMailImpl implements SendMail {
	
	private final SendGrid sendGrid;

	@Override
	public void sendMail(String to, String subject, String content) {
		
		Email fromMail = new Email("forestlinkapp@gmail.com");
		Email toMail = new Email(to);
		Content contents = new Content("text/plain", content);
		Mail mail = new Mail(fromMail, subject, toMail, contents);
		
		Request request = new Request();	
		
		try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
