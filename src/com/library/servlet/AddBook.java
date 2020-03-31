package com.library.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.library.database.Book;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paraMap = new HashMap<>();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		try {
			List<FileItem> paraList = servletFileUpload.parseRequest(request);
			for(FileItem p: paraList) {
				if(p.isFormField()) {
					String pName = p.getFieldName();
					String pValue = p.getString("UTF-8");
					if((! pName.equals("remarks")) && (pValue == null || pValue.equals(""))) {
						request.setAttribute("msg", "所有内容不能为空！");
						request.getRequestDispatcher("/addBook.jsp").forward(request, response);
						return;
					}else if(pName.equals("bookPrice") && (! Pattern.matches("^[1-9][0-9]*(\\.[0-9]+)?$", pValue))) {
						request.setAttribute("msg", "价格必须是数字不能为空！");
						request.getRequestDispatcher("/addBook.jsp").forward(request, response);
						return;
					}
					paraMap.put(pName, pValue);
				}else {
					String fileName = p.getName();
					if(fileName == null || fileName.equals("")) {
						request.setAttribute("msg", "所有内容不能为空！");
						request.getRequestDispatcher("/addBook.jsp").forward(request, response);
						return;
					}
					String UUIDfileName = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
					InputStream is = p.getInputStream();
					String path = getServletContext().getRealPath("/image");
					OutputStream os = new FileOutputStream(path + "/" + UUIDfileName);
					paraMap.put("path", request.getContextPath() + "/image/" + UUIDfileName);
					int len = 0;
					byte[] b = new byte[1024];
					while((len = is.read(b)) != -1) {
						os.write(b, 0, len);
					}
					is.close();
					os.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Book newBook = new Book(paraMap.get("bookId"), paraMap.get("bookName"), paraMap.get("categoryId"), paraMap.get("bookPrice"), paraMap.get("path"), paraMap.get("remarks"));
		List<Book> bookList = (List<Book>)request.getServletContext().getAttribute("bookList");
		bookList.add(newBook);
		Collections.sort(bookList);
		request.getServletContext().setAttribute("bookList", bookList);
		response.sendRedirect(request.getContextPath()+"/bookList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
