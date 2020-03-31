package com.library.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.library.database.Book;
import com.library.database.Category;
import com.library.database.User;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
@WebListener
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	List<User> userList = new ArrayList<>();
    	userList.add(new User("ysk1", "5566812"));
    	userList.add(new User("ysk2", "5566812"));
    	sce.getServletContext().setAttribute("userList", userList);
    	Map<String, Category> categoryMap = new HashMap<>();
    	List<String> categoryList = new ArrayList<>();
    	categoryList.add("CA0001");
    	Category category1 = new Category("CA0001", "计算机");
    	categoryMap.put(category1.getId(), category1);
    	sce.getServletContext().setAttribute("categoryList", categoryList);
    	sce.getServletContext().setAttribute("categoryMap", categoryMap);
    	Book book = new Book("book0001", "Java基础", "CA0001", "29", "/Library/image/g1.jpg", "");
    	List<Book> bookList = new ArrayList<>();
    	bookList.add(book);
    	sce.getServletContext().setAttribute("bookList", bookList);
    }
	
}
