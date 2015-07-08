package net.kos.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletExample extends HttpServlet {

	private List<String> citations = new ArrayList();
	Map<String, String> pageColors = new HashMap<String,String>();
	private Cookie coo;

	@Override
	public void init() throws ServletException {

		citations.add("homo homeni lupus es");
		citations.add("ci vis pasem parabelum");
		citations.add("cesary cesarevo");
		citations = Collections.unmodifiableList(citations);
		Random rand = new Random();
		String cookieName = Integer.toString(rand.nextInt(1000));
		coo = new Cookie(cookieName, "200");
		
		pageColors.put(citations.get(0), "yellow");
		pageColors.put(citations.get(1), "red");
		pageColors.put(citations.get(2), "blue");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Random rand = new Random();
		int chosen = rand.nextInt(3);
		resp.getWriter().println();

		if (req.getCookies() != null) {
			for (Cookie cook : req.getCookies()) {
				System.out.print(cook.getName() + "--");

				if (!cook.getName().equals(coo.getName())) {
					resp.addCookie(coo);
				}
			}
			
		} else {
			resp.addCookie(coo);
		}
		resp.getWriter().println();
		resp.setContentType("text/html");
		String currentCitation = citations.get(chosen);
		resp.getWriter().println("<body bgcolor=" + pageColors.get(currentCitation) + ">"+
				"<div>"+ "hello<br>" + new Date().toString() + "<br>" + currentCitation +  "<br>"
				+ "all is ok " + coo.getName() +  "</div></body>");
		resp.getWriter().println("<meta http-equiv='refresh' content='3'>");
	}

}
