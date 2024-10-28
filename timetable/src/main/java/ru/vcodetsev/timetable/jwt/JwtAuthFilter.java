package ru.vcodetsev.timetable.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.vcodetsev.timetable.ApiProperties;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final ApiProperties apiProperties;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring("Bearer ".length());
        TokenIntrospectionResult result = introspectToken(jwt).block();
        if (Objects.requireNonNull(result).isActive()) {
            Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(result.getRole());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(result.getUsername(), null, authorities));
        }

        filterChain.doFilter(request, response);
    }

    Mono<TokenIntrospectionResult> introspectToken(String accessToken) {
        return WebClient
                .create(apiProperties.getAccountServiceUrl() + "/api/Authentication/Validate?accessToken=" + accessToken)
                .get()
                .retrieve()
                .onStatus(Predicate.isEqual(HttpStatus.FORBIDDEN),
                        clientResponse ->  clientResponse.bodyToMono(Exception.class).map(Exception::new))
                .bodyToMono(TokenIntrospectionResult.class);
    }
}
