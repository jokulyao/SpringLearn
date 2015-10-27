package com.bjsxt.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;

public class UserService {

	private UserDAO userDAO;

	public void init() {
		System.out.println("init");
	}

	public void add(User user) {
		userDAO.save(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Resource
	//注意JDK1.5没有@Resource注解，@Resource从JDK1.6开始出现的
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void destroy() {
		System.out.println("destroy");
	}
}
