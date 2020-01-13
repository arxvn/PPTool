/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Configuration;

import com.arxvn.PersonalProjectTool.Exceptions.ExceptionObjects.InvalidLoginResponse;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author aruns
 */
@Component
public class JwtAuthencticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {
        InvalidLoginResponse invalidLoginResponse = new InvalidLoginResponse();
        String jsonLoginResponse = new Gson().toJson(invalidLoginResponse);

        hsr1.setContentType("application/json");
        hsr1.setStatus(401);
        hsr1.getWriter().print(jsonLoginResponse);
    }

}
