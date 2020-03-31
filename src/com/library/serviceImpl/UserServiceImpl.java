package com.library.serviceImpl;

import java.util.List;

import com.library.database.User;
import com.library.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public int login(List<User> listUser, User user) {
		int result = 0;
		for(User u: listUser) {
			if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())) {
				result = 1;
				return result;
			}
		}
		return result;
	}

}
