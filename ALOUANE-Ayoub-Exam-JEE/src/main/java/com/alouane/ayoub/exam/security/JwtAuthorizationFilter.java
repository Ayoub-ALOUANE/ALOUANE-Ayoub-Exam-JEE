package com.alouane.ayoub.exam.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        
        if (request.getServletPath().equals("/login") || request.getServletPath().contains("/swagger-ui") || request.getServletPath().contains("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationToken = request.getHeader(JwtUtils.AUTH_HEADER);
        if (authorizationToken != null && authorizationToken.startsWith(JwtUtils.PREFIX)) {
            try {
                String jwt = authorizationToken.substring(JwtUtils.PREFIX.length());
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(JwtUtils.SECRET.getBytes()))
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();
                
                String username = claims.getSubject();
                List<String> roles = (List<String>) claims.get("roles");
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));
                
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                response.setHeader("error-message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
