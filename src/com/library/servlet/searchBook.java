package com.library.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.library.database.Book;
import com.library.database.Category;

/**
 * Servlet implementation class searchBook
 */
@WebServlet("/searchBook")
public class searchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryName = (String)request.getParameter("cat");
		List<String> categoryList = (List<String>)request.getServletContext().getAttribute("categoryList");
		Map<String, Category> categoryMap = (Map<String, Category>)request.getServletContext().getAttribute("categoryMap");
		String categoryId = "";
		for(String c: categoryList) {
			Category ca = categoryMap.get(c);
			if(ca.getCatName().equals(categoryName)) {
				categoryId = ca.getId();
				break;
			}
		}
		List<Book> bookList = (List<Book>)request.getServletContext().getAttribute("bookList");
		List<Book> result = new ArrayList<>();
		for(Book b: bookList) {
			if(b.getCategory().equals(categoryId)) {
				result.add(b);
			}
		}
		response.getWriter().println(JSON.toJSONString(result));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
