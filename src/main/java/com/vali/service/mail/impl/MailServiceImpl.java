package com.vali.service.mail.impl;

import com.vali.service.mail.MailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fanshuai on 15/8/25.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
    @Override
    public void sendEmail(String email,String title, String content) {

    }
}
