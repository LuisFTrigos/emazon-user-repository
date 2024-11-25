package com.example.emazon_aux.infrastructure.jwt;

import com.example.emazon_aux.infrastructure.configuration.security.util.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final List<String> excludedPrefixes = Arrays.asList("/auth/**", "/swagger-ui/**", "/actuator/**");
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        //Get bearer token from Authorization header
        String bearerToken = request.getHeader("Authorization");

        //Validate that bearerToken is not null and starts with "Bearer"
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            //Remove the word "Bearer" from the bearerToken variable
            String token = bearerToken.replace("Bearer ", "");
            //Get authentication with the credentials in the token
            UsernamePasswordAuthenticationToken authenticationToken =
                    TokenUtils.getAuthenticationToken(token);
            //Set authentication in Spring SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String currentRoute = request.getServletPath();
        for (String prefix : excludedPrefixes) {
            if (pathMatcher.matchStart(prefix, currentRoute)) {
                return true;
            }
        }
        return false;
    }
}
