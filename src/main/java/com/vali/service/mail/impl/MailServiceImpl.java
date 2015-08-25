package com.vali.service.mail.impl;

import com.vali.service.mail.MailService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

/**
 * Created by fanshuai on 15/8/25.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
    @Resource(name = "mailSender")
    private SimpleMailSender mailSender;
    @Override
    public void sendEmail(String email,String title, String content) {
        try {
            mailSender.send(email,title,content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
