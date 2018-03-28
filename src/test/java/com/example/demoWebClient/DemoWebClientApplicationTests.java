package com.example.demoWebClient;

import com.example.demoWebClient.account.dto.Role;
import com.example.demoWebClient.account.service.UserAccountValidationService;
import com.example.demoWebClient.config.dao.ConfigMapper;
import com.example.demoWebClient.config.dao.ConfigSqlProvider;
import com.example.demoWebClient.config.service.AnyUserDetailsService;
import com.example.demoWebClient.config.service.ConfigLoadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoWebClientApplicationTests {

	@Autowired
	private UserAccountValidationService userAccountValidationServiceImpl;

	@Autowired
	private ConfigLoadService configLoadServiceImpl;

	@Autowired
	private ConfigMapper configMapper;

	@Test
	public void send(){
		Role r = new Role();
		userAccountValidationServiceImpl.login(r);
	}

	@Test
	public void test(){
		ConfigSqlProvider configSqlProvider = new ConfigSqlProvider();
		HashMap map = new HashMap<String,String>();
		map.put("s_key","yes");
		map.put("s_value","no");
		System.out.println(configSqlProvider.updateConfigSql(map));
	}

	@Test
	public void test2(){
		HashMap map = new HashMap<String,String>();
		/*map.put("s_key","yes");
		map.put("s_value","no");*/
		System.out.println(configMapper.loadConfig(map));
	}


	@Test
	public void test3(){
		if(passwordEncoder().matches("123456","$2a$10$85oZcoG5lpaHITZaXVoiI.sL7MXpdCR9Qo7CG7gMHngcEG7Qb8oey"))
			System.out.println(passwordEncoder().encode("123456"));
	}

	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Test
	public void test4(){
		AnyUserDetailsService anyUserDetailsService = new AnyUserDetailsService();
		System.out.println(anyUserDetailsService.loadUserByUsername("abc"));
	}

}
