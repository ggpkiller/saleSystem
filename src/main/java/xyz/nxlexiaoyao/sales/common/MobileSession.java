package xyz.nxlexiaoyao.sales.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import xyz.nxlexiaoyao.sales.bean.Token;

public class MobileSession {
    //3天  3 * 24 * 60 * 60 * 1000 = 259200000
    private final static long OUT_STAMP = 259200000L;


    private final static Map<String,Token> tokens = new HashMap<String,Token>();

    public static void saveToken(Token t){
        tokens.put(t.getToken(),t);
    }

    public static void flushTimestamp(String token){
        tokens.get(token).setTimestamp(System.currentTimeMillis());
    }

    public static void removeToken(String token){
        tokens.remove(token);
    }
    
    public static Token getToken(String token) {
    	return tokens.get(token);
    }

    public static void check(){
        long nowStamp = System.currentTimeMillis();
        Iterator<Map.Entry<String,Token>> ite = tokens.entrySet().iterator();
        while(ite.hasNext()){
            Map.Entry<String,Token> entry = ite.next();
            String userId = entry.getKey();
            Token token = entry.getValue();
            long timestamp = token.getTimestamp();
            if(nowStamp - timestamp > OUT_STAMP){
                ite.remove();
            }
        }
        ite = null;
    }
}
