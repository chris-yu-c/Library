package com.library.service;

import java.util.List;

import com.library.database.User;

public interface UserService {
	public int login(List<User>  listUser,User user);
}
