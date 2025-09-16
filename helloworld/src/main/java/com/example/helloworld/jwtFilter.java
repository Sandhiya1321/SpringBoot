package com.example.helloworld;

import com.example.helloworld.utils.jwtutil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    private jwtutil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //we will send authorization token in header in postman
        String authHeader=request.getHeader("Authorization");
        if(authHeader!=null&&authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            if(jwtUtil.validateToken(token)){
                //if correct
                String email= jwtUtil.extractUtil(token);
                //creating authentication variable
                var auth=new UsernamePasswordAuthenticationToken(email,null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
    //onceperrequestfilter--> for request it call first

}
