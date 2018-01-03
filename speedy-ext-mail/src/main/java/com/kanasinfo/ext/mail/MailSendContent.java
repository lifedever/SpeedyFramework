package com.kanasinfo.ext.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Created by gefangshuai on 2017/5/23.
 */
@Component
public class MailSendContent {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration freemarkerConfig;

    /**
     * @param from     发件人
     * @param to       收件人
     * @param subject  主题
     * @param template 模板
     * @param model    数据
     * @param cc       抄送
     * @param bcc      密送
     * @throws MessagingException
     * @throws IOException
     * @throws TemplateException
     */
    public void sendMail(String from, String personal, String[] to, String subject, String template, Object model, String[] cc, String[] bcc) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from, personal);
        helper.setTo(to);
        if (cc != null && cc.length > 0)
            helper.setCc(cc);
        if (bcc != null && bcc.length > 0)
            helper.setBcc(bcc);
        helper.setSubject(subject);
        Template t = freemarkerConfig.getTemplate(template);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }
}
