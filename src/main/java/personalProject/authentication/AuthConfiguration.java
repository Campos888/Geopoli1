package personalProject.authentication;


import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;

import static personalProject.model.Credentials.ADMIN_ROLE;

@Configuration
public class AuthConfiguration {

    private final DataSource dataSource;

    public AuthConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(HttpMethod.GET,
            			    "/", "/index",
            			    "/continents",
            			    "/continentCountries/**",  // ✅ QUESTO È FONDAMENTALE
            			    "/country/**",
            			    "/globe",
            			    "/login", "/register",
            			    "/css/**", "/images/**", "/favicon.ico", "/api/search", "/search"
            			).permitAll()



                .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority(ADMIN_ROLE)
                 .requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority(ADMIN_ROLE)
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/success", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .permitAll()
            )
            .exceptionHandling(ex -> ex.accessDeniedPage("/login"))
            .csrf(csrf -> csrf.disable()); // Enable CSRF if needed

        return http.build();
    }

    /**
     * Custom authentication using JDBC with custom SQL queries
     */
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Expose AuthenticationManager bean if needed for manual auth
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
