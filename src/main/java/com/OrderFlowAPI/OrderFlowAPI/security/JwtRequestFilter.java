package com.OrderFlowAPI.OrderFlowAPI.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.OrderFlowAPI.OrderFlowAPI.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getTokenFromHeader(request);

            if (token != null) {
                validateToken(token, response);

                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);

                // If the email is valid and the user is not authenticated, the user is
                // authenticated
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Create a list of authorities (roles) for the user
                    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_" + role);
                    // Create an authentication object with the user's email and roles
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            email, null, authorities);
                    // Sets the authentication details in the request
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Sets the authentication context to the Spring security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has expired");
            return;
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An unexpected error occurred");
            e.printStackTrace();
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

    private void validateToken(String token, HttpServletResponse response) throws IOException {
        if (jwtUtil.isTokenExpired(token)) {
            throw new ExpiredJwtException(null, null, "Token has expired");
        }
    }

}
