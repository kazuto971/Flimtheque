package fr.eni.tp.filmotheque.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Définir le code HTTP 403 et le message
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Vous n'avez pas accès à cette page.");
        response.getWriter().flush();
    }
}
