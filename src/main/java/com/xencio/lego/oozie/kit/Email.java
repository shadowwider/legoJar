package com.xencio.lego.oozie.kit;

import com.xencio.lego.oozie.config.ConfigEmail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;


public class Email {

    private static ConfigEmail config = new ConfigEmail(); //xbb帮我注意下这里用静态方法会不会导致整个发送邮件main函数出问题

    /**
     * 参数顺序1 邮件模板名 2 tableName/path 分别表示自己查表和现成文件 3.如为path则表示实际地址，如为tableName和后面参数意义一样  4-：邮件模板占位符文字
     *
     * @param
     */
    public static void main(final String[] args) {
        System.out.println("1");
        config = ConfigEmail.load(args[0]);
        System.out.println("21");
        if (args.length > 1) {
            if ("tableName".equals(args[1])) {
                Connection con = Kit.getHiveConneciton(); //如果是表名则连接数据库自己生成文件
                System.out.println("TODO");
            } else if ("path".equals(args[1])) {
                String[] params = Arrays.copyOfRange(args, 3, 8);//最多5个参数
                System.out.println("3");
                buildAndSendRemindMail(args[2], params);
                System.out.println("4");
            }
        } else {
            String[] params = Arrays.copyOfRange(args, 1, 6); //最多5个参数
            buildAndSendRemindMail(params);
        }

    }


    /**
     * @param
     * @return
     */
    public static boolean buildAndSendRemindMail(String attachmentPath, String[] params) {

       
            // 创建Session实例对象
            Session mailSession = buildMailSession();
            // 创建Session实例对象
            MimeMessage msg = new MimeMessage(mailSession);
            // 发件人
            msg.setFrom(new InternetAddress(config.getSenderMailAddress()));
            // 收件人
            String receivers = config.getOwnerMailAddresses();
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receivers));
            // 抄送
            String adminAddress = config.getAdminMailAddresses();
            msg.setRecipient(Message.RecipientType.CC, new InternetAddress(adminAddress));

            // 邮件主题,并指定编码格式(未指定编码)
            String subject = config.getMailSubject();
            msg.setSubject(receivers);
            // 设置纯文本内容为邮件正文
            String template = config.getMailContentTemplate();
            String mailText = MessageFormat.format(template, Objects.toString(params[0],""), Objects.toString(params[1],""),Objects.toString(params[2],""), Objects.toString(params[3],""), Objects.toString(params[4],""));
            MimeBodyPart textContent = new MimeBodyPart();
            textContent.setText(mailText);


            // 设置内嵌图片邮件体
            MimeBodyPart attachment = new MimeBodyPart();

            attachmentPath = attachmentPath.substring(attachmentPath.lastIndexOf(File.separator) + 1);
            DataHandler dh2 = new DataHandler(new FileDataSource(attachmentPath));
            attachment.setDataHandler(dh2);
            attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

            MimeMultipart mailContent = new MimeMultipart();
            mailContent.addBodyPart(attachment);
            mailContent.addBodyPart(textContent);

            msg.setContent(mailContent);

            Transport transport = mailSession.getTransport();
            transport.connect(config.getMailAccountName(), config.getMailAccountPsw());

            transport.sendMessage(msg, msg.getAllRecipients());

       

        return true;
    }

    /**
     * @param
     * @return
     */
    public static boolean buildAndSendRemindMail(String[] params) {

        try {

            // 创建Session实例对象
            Session mailSession = buildMailSession();

            // 创建MimeMessage实例对象
            MimeMessage msg = new MimeMessage(mailSession);
            // 设置发件人
            msg.setFrom(new InternetAddress(config.getSenderMailAddress()));

            // 设置收件人
            String receivers = config.getOwnerMailAddresses();
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receivers));
            // 设置收件人 抄送
            String adminAddress = config.getAdminMailAddresses();
            msg.setRecipient(Message.RecipientType.CC, new InternetAddress(adminAddress));
            // 设置邮件主题
            String subject = config.getMailSubject();
            msg.setSubject(receivers);

            String template = config.getMailContentTemplate();
            String mailText = MessageFormat.format(template, params[0], params[1], params[2], params[3], params[4]);
            MimeBodyPart textContent = new MimeBodyPart();
            textContent.setText(mailText);

            MimeMultipart mailContent = new MimeMultipart();
            mailContent.addBodyPart(textContent);

            msg.setContent(mailContent);

            Transport transport = mailSession.getTransport();
            transport.connect(config.getMailAccountName(), config.getMailAccountPsw());

            transport.sendMessage(msg, msg.getAllRecipients());

        } catch (final Exception e) {
            System.out.println("code:45  "+e);
            throw new RuntimeException("{code:46,message:邮件发送出错}");
        }
        return true;
    }


    public static Session buildMailSession() {

        final Properties props = new Properties();
        // 邮件发送协议
        props.setProperty("mail.transport.protocol", config.getMailProtocol());
        // SMTP邮件服务器
        props.setProperty("mail.smtp.host", config.getMailHost());
        // SMTP邮件服务器默认端口
        props.setProperty("mail.smtp.port",  config.getMailHostPort());
        // 是否要求身份认证
        
        
        props.setProperty("mail.smtp.auth", config.getMailSmtpAuth());
        props.setProperty("mail.smtp.starttls.enable", config.getMailSmtpStarttls());
        // 创建Session实例对象
        final Session mailSession = Session.getDefaultInstance(props);
        // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
        mailSession.setDebug(true);
        return mailSession;
    }


    private static final String ATTACHMENT_LOCAL_PATH = "attachment/mapping-ext-product-mapping-{0}.csv";
}
