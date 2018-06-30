package xyz.nxlexiaoyao.sales.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;

public class PasswordUtil {
    private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");


    public static String getMD5Password(String password,String salt){
        return  DigestUtils.md5DigestAsHex((password+salt).getBytes(UTF8_CHARSET));
    }

    public static void main(String[] args) {
        String salt = RandomStrUtil.getRandomStr(8);
        String pwd = "123456";
        System.out.println(salt);
        System.out.println(getMD5Password(pwd,salt));
    }
}
