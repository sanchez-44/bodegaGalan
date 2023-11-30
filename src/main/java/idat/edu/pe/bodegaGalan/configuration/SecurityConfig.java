package idat.edu.pe.bodegaGalan.configuration;

import idat.edu.pe.bodegaGalan.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter {

    private final UsuarioDetailsService usuarioDetailsService;

    // Inyecta el UsuarioDetailsService en el constructor
    @Autowired
    public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private class HttpSecurityConfigurer extends AbstractHttpConfigurer<HttpSecurityConfigurer, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .antMatchers(
                                            "/auth/login",
                                            "/auth/registrar",
                                            "/auth/guardarusuario",
                                            "/resources/**",
                                            "/static/**",
                                            "/styles/**",
                                            "/scripts/**"
                                    ).permitAll()
                                    .anyRequest().authenticated()
                    )
                    .formLogin(formLogin ->
                            formLogin
                                    .loginPage("/auth/login")
                                    .defaultSuccessUrl("/auth/login-success")
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                    )
                    .logout(logout ->
                            logout
                                    .logoutSuccessUrl("/auth/login")
                                    .invalidateHttpSession(true)
                    )
                    .authenticationProvider(authenticationProvider());
        }
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}