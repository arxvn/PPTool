/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Configuration;

/**
 *
 * @author aruns
 */
public class SecurityConstants {
    
    public static final String SIGN_UP_URLS = "/api/users/**";
    
    public static final String SECRET = "SecretKeyABCJWT" ;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";
    
    public static final long EXPIRATION_TIME = 300_000;

}
