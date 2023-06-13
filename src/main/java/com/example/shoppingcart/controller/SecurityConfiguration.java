package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.UserInfo;
import com.example.shoppingcart.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserRepo userRepo;
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails user1 = User.withUsername("admin")
//                .roles("ADMIN")
//                .password(passwordEncoder().encode("pass1"))
//                .build();
//        UserDetails user2 = User.withUsername("user2")
//                .roles("USER")
//                .password(passwordEncoder().encode("pass2"))
//                .build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(username -> {
                    List<UserInfo> userInfo = userRepo.findAllByUserName(username);
                    if (userInfo != null) {
                        for(UserInfo userInfo1: userInfo) {
                            return org.springframework.security.core.userdetails.User
                                    .withUsername(userInfo1.getUserName())
                                    .password(passwordEncoder().encode(userInfo1.getPassword()))
                                    .roles("ADMIN")
                                    .build();
                        }
                    }
                    else {
                        throw new UsernameNotFoundException("User not found");
                    }
                            return null;
                        }
                );
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .requestMatchers("/","/home").permitAll()
                .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                .requestMatchers("/productsonline").hasRole("ADMIN")
                .requestMatchers("/showcartitems").hasRole("ADMIN")
                .requestMatchers("/saveproduct/{id}").hasRole("ADMIN")
                .requestMatchers("/updatecart/{id}").hasRole("ADMIN")
                .requestMatchers("/addcartitem/{id}").hasRole("ADMIN")
                .and().formLogin();
        return http.build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                ).build();
    }



//    @Bean
//    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource){
//        UserDetails user1 = User.withUsername("admin")
//                .roles("ADMIN")
//                .password(passwordEncoder().encode("pass1"))
//                .build();
//        UserDetails user2 = User.withUsername("user2")
//                .roles("USER")
//                .password(passwordEncoder().encode("pass2"))
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
//        jdbcUserDetailsManager.createUser(user1);
//        jdbcUserDetailsManager.createUser(user2);
//        return jdbcUserDetailsManager;
//    }

//    public DataSource dataSource(){
//        return new EmbeddedDatabaseBuilder().
//                setType(EmbeddedDatabaseType.H2)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }

}
