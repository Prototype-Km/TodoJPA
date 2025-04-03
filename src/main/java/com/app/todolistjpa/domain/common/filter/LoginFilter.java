package com.app.todolistjpa.domain.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/api/users/signup", "/api/users/login", "/api/users/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest
            , ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {

        //Filter  다운캐스팅
        HttpServletRequest httpRequest =(HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI= httpRequest.getRequestURI();

        log.info("로그인 필터 로직 실행");

        // WHITE_LIST가 아닌 경우 실행
        if(!isWhiteList(requestURI)){

            HttpSession session = httpRequest.getSession(false); //세션이 없으면 null

            if(session == null || session.getAttribute("loginUser") == null){
                throw new RuntimeException("로그인 해주세요.");
            }
            //로그인 성공
            log.info("로그인에 성공");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }
}
