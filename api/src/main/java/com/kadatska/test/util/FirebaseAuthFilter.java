package com.kadatska.test.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.kadatska.test.model.User;
import com.kadatska.test.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FirebaseAuthFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String idToken = request.getHeader("Authorization");

        if (idToken == null || idToken.isEmpty()) {
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);
            filterChain.doFilter(request, response);
            return;
        } else {
            try {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseToken decodedToken = auth.verifyIdToken(idToken.substring(7));
                String uid = decodedToken.getUid();
                String phoneNumber = auth.getUser(uid).getPhoneNumber();

                if (userService.getUserById(uid) == null) {
                    userService.save(createUser(uid, phoneNumber));
                } 
                UserDetails user = this.userService.loadUserByUsername(phoneNumber);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (FirebaseAuthException e) {
                System.out.println("ERROR");
                e.printStackTrace();
            }
            filterChain.doFilter(request, response);
        }
    }

    private User createUser(String uid, String phoneNumber) throws FirebaseAuthException {
        System.out.println("Create user with uid: " + uid);
        User user = new User();
        user.setUid(uid);
        user.setPhoneNumber(phoneNumber);
        return user;
    }

}
