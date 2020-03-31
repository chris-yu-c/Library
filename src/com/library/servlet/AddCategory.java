package com.library.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.database.Category;

/**
 * Servlet implementation class AddCategory
 */
@WebServlet("/AddCategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");
		if(! Pattern.matches("^CA[0-9]{4}$", categoryId)) {
			request.setAttribute("msg", "种类ID格式不正确！");
			request.getRequestDispatcher("/addCategory.jsp").forward(request, response);
			return;
		}
		@SuppressWarnings("unchecked")
		List<String> categoryList = (List<String>)request.getServletContext().getAttribute("categoryList");
		@SuppressWarnings("unchecked")
		Map<String, Category> categoryMap = (Map<String, Category>)request.getServletContext().getAttribute("categoryMap");
		if(categoryMap.get(categoryId) != null) {
			request.setAttribute("msg", "种类ID已存在！");
			request.getRequestDispatcher("/addCategory.jsp").forward(request, response);
			return;
		}
		categoryList.add(categoryId);
		categoryMap.put(categoryId, new Category(categoryId, categoryName));
		Collections.sort(categoryList);
		request.getServletContext().setAttribute("categoryList", categoryList);
		request.getServletContext().setAttribute("categoryMap", categoryMap);
		response.sendRedirect(request.getServletContext().getContextPath() + "/categoryList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
