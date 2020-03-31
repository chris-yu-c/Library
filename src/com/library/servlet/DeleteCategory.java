package com.library.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.database.Category;

/**
 * Servlet implementation class DeleteCategory
 */
@WebServlet("/DeleteCategory")
public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String categoryId = request.getParameter("categoryId");
		List<String> categoryList = (List<String>)request.getServletContext().getAttribute("categoryList");
		categoryList.remove(categoryId);
		Map<String, Category> categoryMap = (Map<String, Category>)request.getServletContext().getAttribute("categoryMap");
		categoryMap.remove(categoryId);
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
