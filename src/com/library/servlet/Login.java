package com.library.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.database.User;
import com.library.service.UserService;
import com.library.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String codeTest = request.getParameter("verifyCode").toLowerCase();
		String imgCode = (String)request.getSession().getAttribute("imgCode");
		if(! codeTest.equals(imgCode)) {
			request.setAttribute("msg", "验证码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		User userTest = new User(userName, password);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>)request.getServletContext().getAttribute("userList");
		UserService us = new UserServiceImpl();
		int result = us.login(listUser, userTest);
		if(result == 1) {
			request.getSession().setAttribute("login", userTest);
			response.sendRedirect(request.getContextPath() + "/categoryList.jsp");
		}else {
			request.setAttribute("msg", "用户名或密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
