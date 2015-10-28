package com.bjsxt.service;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

import org.junit.Test;

import sun.misc.ProxyGenerator;

import com.bjsxt.aop.LogInterceptor;
import com.bjsxt.dao.UserDAO;
import com.bjsxt.dao.impl.UserDAOImpl;
import com.bjsxt.model.User;
import com.bjsxt.spring.BeanFactory;
import com.bjsxt.spring.ClassPathXmlApplicationContext;


public class UserServiceTest {

	@Test
	public void testAdd() throws Exception {
		BeanFactory applicationContext = new ClassPathXmlApplicationContext();
		
		UserService service = (UserService)applicationContext.getBean("userService");
		
		User u = new User();
		u.setUsername("zhangsan");
		u.setPassword("zhangsan");
		service.add(u);
	}
	
	@Test
	public void testProxy() {
		UserDAO userDAO = new UserDAOImpl();
		LogInterceptor li = new LogInterceptor();
		li.setTarget(userDAO);
		UserDAO userDAOProxy = (UserDAO)Proxy.newProxyInstance(userDAO.getClass().getClassLoader(), userDAO.getClass().getInterfaces(), li);
		System.out.println(userDAOProxy.getClass());
//		userDAOProxy.getClass().
		userDAOProxy.delete();
		userDAOProxy.save(new User());
		
		
		//使用ProxyGenerator生成userDAOProxy接口的代理对象，并输出到class文件以便查看生成的代理类对象
		byte[] proxyClass = ProxyGenerator.generateProxyClass("$Proxy11", userDAOProxy.getClass().getInterfaces());
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream("D:/$Proxy11.class");
			outputStream.write(proxyClass);
			outputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*class $Proxy4 implements UserDAO 
	 * {
	 * 	save(User u) {
	 * 	Method m = UserDAO.getclass.getmethod 
	 * li.invoke(this, m, u)
	 * }
	 * }
	 */

}
