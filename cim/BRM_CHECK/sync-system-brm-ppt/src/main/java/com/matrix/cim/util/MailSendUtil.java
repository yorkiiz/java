package com.matrix.cim.util;

import com.matrix.cim.config.MailUtilInfo;
import com.matrix.cim.config.MyAuthenticator;
import com.matrix.cim.entity.ams.EmailGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Component
public class MailSendUtil {

    @Autowired
    private  MailUtilInfo mailUtilInfo;

    public  void sendMail(String message,List<EmailGroup> receivers) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        //1、连接邮件服务器的参数配置
        Properties props = System.getProperties();

        props.put("mail.smtp.host", mailUtilInfo.getMailHost());
        props.put("mail.smtp.port", mailUtilInfo.getMailPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol", "smtp");

        System.out.println(props);

        //2、创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getInstance(props);
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        //3、创建邮件的实例对象
        Message msg = getMimeMessage(session,message,receivers);
        //4、根据session对象获取邮件传输对象Transport
        Transport transport = session.getTransport();
        //设置发件人的账户名和密码

        transport.connect(mailUtilInfo.getUsername(),mailUtilInfo.getPassword());
        //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(msg,msg.getAllRecipients());


        //如果只想发送给指定的人，可以如下写法
        //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});

        //5、关闭邮件连接
        transport.close();
    }

    public  MimeMessage getMimeMessage(Session session, String content, List<EmailGroup> receivers) throws Exception{
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        msg.setFrom(new InternetAddress(mailUtilInfo.getUsername(),"MES_ALARM","UTF-8"));
        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        if (receivers == null || receivers.size() == 0){
            msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress("aicheng.hua@everdisplay.com","aicheng.hua","UTF-8"));
//            msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress("zhipeng.wang@everdisplay.com","zhipeng.wang","UTF-8"));
        }else{
            for (EmailGroup receiver : receivers) {
                msg.addRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiver.getRecipient(),receiver.getRecipient_name(),"UTF-8"));
            }

        }

        //设置邮件主题
        msg.setSubject("数据同步检查报告","UTF-8");
        //设置邮件正文
        msg.setContent(content, "text/html;charset=UTF-8");
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());

        return msg;
    }
}
