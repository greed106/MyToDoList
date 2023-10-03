package com.ymj.mytodolist.utils;

import com.ymj.mytodolist.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailVerificationUtils {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationCode(User user) {
        long expire = 5*60*1000L;   // 5 min
        Map<String, Object> claims = new HashMap<>();
        String emailAddress = user.getEmail();
        claims.put("email", emailAddress);
        claims.put("username", user.getUsername());
        String verificationCode = JwtUtils.generateJwt(claims, expire);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1062415694@qq.com");
        message.setTo(emailAddress);
        message.setSubject("[ToDoList] 请确认您的验证令牌");
        message.setText("您好!\n感谢您使用[ToDoList]提供的邮箱验证服务，您的验证令牌是 \n\n" + verificationCode+
                "\n\n验证令牌会在 "+expire/(60*1000)+" 分钟后过期，请尽快完成验证"+
                "\n如果您没有请求这个验证令牌，那么您可以放心的忽略这封邮件\n再次感谢！\n"+
                "\n北京邮电大学 杨明佳\n");
        mailSender.send(message);
    }
}
