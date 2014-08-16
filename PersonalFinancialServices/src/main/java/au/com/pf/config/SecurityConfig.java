package au.com.pf.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) {
         // enable in memory based authentication with a user named
         // "user" and "admin"
         try {
			auth.inMemoryAuthentication()
			     .withUser("user").password("password").roles("USER").and()
			     .withUser("admin").password("password").roles("USER", "ADMIN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/**").hasRole("USER")
                .anyRequest().anonymous()
                .and()
                .httpBasic();
    }
}
