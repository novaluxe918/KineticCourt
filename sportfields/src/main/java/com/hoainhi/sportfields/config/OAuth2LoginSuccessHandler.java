package com.hoainhi.sportfields.config;

import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.repository.AccountRepository;
import com.hoainhi.sportfields.enums.Role;
import com.hoainhi.sportfields.enums.UserStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler
        implements AuthenticationSuccessHandler {

    private final AccountRepository accountRepository;

    public OAuth2LoginSuccessHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        // Lấy email từ Google
        String email = oauth2User.getAttribute("email");

        // Tìm User trong database
        Optional<User> optionalUser =
                accountRepository.findByEmail(email);

        // Không tìm thấy tài khoản
        if (optionalUser.isEmpty()) {
            response.sendRedirect("/login?error=google");
            return;
        }

        User user = optionalUser.get();

        // Tài khoản bị khóa
        if (user.getStatus() == UserStatus.LOCKED) {
            response.sendRedirect("/login?error=locked");
            return;
        }

        // Lưu User vào session
        request.getSession().setAttribute("loginUser", user);

        // Redirect theo Role
        if (user.getRole() == Role.Admin) {
            response.sendRedirect("/dashboard");
            return;
        }

        if (user.getRole() == Role.Owner) {
            response.sendRedirect("/facility/facilities_owner");
            return;
        }

        response.sendRedirect("/home");
    }
}