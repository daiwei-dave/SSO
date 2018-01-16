package com.sheefee.simple.sso.server.storage;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daiwei on 2018/1/16.
 */
public class TokenStorage {
    private Map<String, HttpSession> map = new HashMap<String, HttpSession>();
    private static TokenStorage tokenStorage = new TokenStorage();


    public void set(String token, HttpSession session) {
        map.put(token, session);
    }

    public HttpSession get(String token) {
        if (map.containsKey(token)) {
//			return map.remove(token);
            return  map.get(token);
        }
        return null;
    }

    public static TokenStorage getInstance() {
        return tokenStorage;
    }


}
