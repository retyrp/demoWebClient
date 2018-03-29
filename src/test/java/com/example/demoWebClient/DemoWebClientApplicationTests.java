package com.example.demoWebClient;

import com.example.demoWebClient.account.dto.Role;
import com.example.demoWebClient.account.service.UserAccountValidationService;
import com.example.demoWebClient.config.dao.ConfigMapper;
import com.example.demoWebClient.config.dao.ConfigSqlProvider;
import com.example.demoWebClient.config.service.AnyUserDetailsService;
import com.example.demoWebClient.config.service.ConfigLoadService;
import com.example.demoWebClient.foundation.service.RSAFactory;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

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

	@Test
	public void tests5() throws Exception {
		RSAFactory RSACoder = new RSAFactory();
		//初始化密钥
		//生成密钥对
		Map<String, Object> keyMap = RSACoder.initKey();
		//公钥
		byte[] publicKey = RSACoder.getPublicKey(keyMap);

		//私钥
		byte[] privateKey = RSACoder.getPrivateKey(keyMap);
		System.out.println("公钥："+Base64.encodeBase64String(publicKey));
		System.out.println("私钥："+Base64.encodeBase64String(privateKey));

		System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");
		String str = "RSA密码交换算法";
		System.out.println("/n===========甲方向乙方发送加密数据==============");
		System.out.println("原文:" + str);
		//甲方进行数据的加密
		byte[] code1 = RSACoder.encryptByPrivateKey(str.getBytes(), privateKey);
		System.out.println("加密后的数据：" + Base64.encodeBase64String(code1));
		System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");
		//乙方进行数据的解密
		byte[] decode1 = RSACoder.decryptByPublicKey(code1, publicKey);
		System.out.println("乙方解密后的数据：" + new String(decode1) + "/n/n");

		System.out.println("===========反向进行操作，乙方向甲方发送数据==============/n/n");

		str = "乙方向甲方发送数据RSA算法";

		System.out.println("原文:" + str);

		//乙方使用公钥对数据进行加密
		byte[] code2 = RSACoder.encryptByPublicKey(str.getBytes(), publicKey);
		System.out.println("===========乙方使用公钥对数据进行加密==============");
		System.out.println("加密后的数据：" + Base64.encodeBase64String(code2));

		System.out.println("=============乙方将数据传送给甲方======================");
		System.out.println("===========甲方使用私钥对数据进行解密==============");

		//甲方使用私钥对数据进行解密
		byte[] decode2 = RSACoder.decryptByPrivateKey(code2, privateKey);

		System.out.println("甲方解密后的数据：" + new String(decode2));
	}

}
