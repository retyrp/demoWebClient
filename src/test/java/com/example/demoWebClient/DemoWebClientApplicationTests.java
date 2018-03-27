package com.example.demoWebClient;

import com.example.demoWebClient.account.dto.Role;
import com.example.demoWebClient.account.service.UserAccountValidationService;
import com.example.demoWebClient.config.dao.ConfigMapper;
import com.example.demoWebClient.config.dao.ConfigSqlProvider;
import com.example.demoWebClient.config.service.ConfigLoadService;
import com.sun.javafx.collections.MappingChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

}
