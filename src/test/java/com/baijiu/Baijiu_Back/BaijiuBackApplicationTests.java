package com.baijiu.Baijiu_Back;

import com.baijiu.Baijiu_Back.entity.User;
import com.baijiu.Baijiu_Back.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaijiuBackApplicationTests {
	@Autowired // 注入用户映射器Bean
	private UserMapper userMapper;

	@Test
	public void testLogin() {
		// 定义用户名和密码
		String name = "管理员";
		String password = "123456";
		// 调用用户映射器登录方法
		User user = userMapper.login(name, password);
		// 判断是否登录成功
		if (user != null) {
			System.out.println("欢迎，[" + name + "]登录成功~");
		} else {
			System.err.println("密码错误，[" + name + "]登录失败，请重试");
		}
	}


}
