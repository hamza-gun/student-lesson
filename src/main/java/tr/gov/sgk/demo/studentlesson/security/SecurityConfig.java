package tr.gov.sgk.demo.studentlesson.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;


import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails hamza = User.builder()
                .username("hamza")
                .password("{noop}hamza123")
                .roles("STUDENT")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}hamza123")
                .roles("STUDENT", "TEACHER")
                .build();

        return new InMemoryUserDetailsManager(hamza, mary);
    }

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//        return new JdbcUserDetailsManager(dataSource);
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests(configurer ->
//                configurer
//                        .requestMatchers(new AntPathRequestMatcher("/")).hasRole("EMPLOYEE")
//                        .requestMatchers(new AntPathRequestMatcher("/leaders/**")).hasRole("MANAGER")
//                        .requestMatchers(new AntPathRequestMatcher("/systems/**")).hasRole("ADMIN")
//                        .anyRequest().authenticated()
//        )
//                .formLogin(form ->
//                        form
//                                .loginPage("/showMyLoginPage")
//                                .loginProcessingUrl("/authenticateTheUser")
//                                .permitAll()
//                )
//                .logout(logout -> logout.permitAll()
//                )
//                .exceptionHandling(configurer ->
//                        configurer.accessDeniedPage("/access-denied")
//                );
//
//        return http.build();
//    }

}
