package com.library.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckImg
 */
@WebServlet("/CheckImg")
public class CheckImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int width = 150;
		int height = 42;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(getRandColor(200, 255));
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width - 1, height - 1);
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setFont(new Font("宋体", Font.BOLD, 24));
		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		Random random = new Random();
		StringBuffer code = new StringBuffer();
		int position = 15;
		for(int i = 0; i < 4; i++) {
			graphics2d.setColor(getRandColor(20, 150));
			int idx = random.nextInt(words.length());
			char singleCode = words.charAt(idx);
			code.append(singleCode);
			double theta = (random.nextInt(90) - 45) * Math.PI / 180;
			graphics2d.rotate(theta, position, 20);
			graphics2d.drawString(((Character)singleCode).toString(), position, 25);
			graphics2d.rotate(-theta, position, 20);
			position += 35;
		}
		request.getSession().setAttribute("imgCode", code.toString().toLowerCase());
		graphics.setColor(getRandColor(0, 1));
		for(int i = 0; i < 30; i++) {
			int x1 = random.nextInt(width);
			int x2 = x1 + random.nextInt(20);
			int y1 = random.nextInt(height);
			int y2 = y1 + random.nextInt(20);
			graphics.drawLine(x1, y1, x2, y2);
		}
		graphics.dispose();
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if(fc > 255) {
			fc = 255;
		}
		if(bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
}
