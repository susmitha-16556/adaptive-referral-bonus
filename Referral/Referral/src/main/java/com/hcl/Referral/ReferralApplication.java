package com.hcl.Referral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hcl.Referral.Service.ExcelService;
import com.hcl.Referral.model.UserModel;

@SpringBootApplication
public class ReferralApplication {

	@Autowired
	ExcelService excelService;

	public static void main(String[] args) {
		SpringApplication.run(ReferralApplication.class, args);
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				UserModel account = excelService.findByUsername(username);
				if (account != null) {
					return new User(account.getUserName(), "{noop}"+account.getPassword(), true, true, true, true,
							AuthorityUtils.createAuthorityList("USER"));

				} else {
					throw new UsernameNotFoundException("could not find the user '" + username + "'");
				}
			}

		};
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest()
					.fullyAuthenticated().and().formLogin().and().csrf().disable();
		}

	}
}
