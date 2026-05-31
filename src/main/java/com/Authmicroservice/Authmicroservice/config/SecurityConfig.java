package com.Authmicroservice.Authmicroservice.config;


import com.Authmicroservice.Authmicroservice.models.User;
import com.Authmicroservice.Authmicroservice.services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // public all apis otherwise it throw 401 Unauthorized error for api testing .
    // Spring Security automatically secures all APIs by default.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        http.csrf(c ->c.disable())
                .authorizeHttpRequests(
                        auth->
          //   login and refister both only public
                        auth.requestMatchers(
                                "/user/register-user",
                                        "/user/generate-token"
//
                                ).permitAll()

                //   other all apis route should be authenticated or are private unauthorizec access
                                .anyRequest().authenticated()
                               );
//                .userDetailsService(userDetailsService())   // this create stackOverflow issue
//                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws  Exception{
//      return configuration.getAuthenticationManager();
//    }
//
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new MyUserDetailsService();
//    }

}













