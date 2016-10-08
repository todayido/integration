package com.core;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Administrator on 2016/9/28.
 */
public class MyCredentialsMatcher {

    //data加密密方法
    public String encrypt(String data) {
        String md5 = new Md5Hash(data).toHex();
        return md5;
    }
}
