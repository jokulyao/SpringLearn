package com.bjsxt.service;

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

	@Autowired
	// �Զ�װ�䣬���Ȱ�����ƥ�䣬���ƥ��ɹ���װ�룻�������ƥ�䵽������򱨴�
	// ��Ҫ��@Qualifierָ���ض�������
	public void setUserDAO(@Qualifier("userDAO") UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void destroy() {
		System.out.println("destroy");
	}
}