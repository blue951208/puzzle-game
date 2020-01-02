package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.ToptenService;

/**
 * Servlet implementation class Topten
 */
@WebServlet("/Topten")
public class Topten extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("top10 Servlet 도착");
		int top = 0;
			if(request.getParameter("topList")!=null) {
				top = Integer.parseInt(request.getParameter("topList"));
			}
		System.out.println("top -->"+top);
		response.setContentType("application/json;charset=utf-8");
		//ToptenService 호출
		ToptenService toptenService = new ToptenService();
		//json타입으로
		List list = new ArrayList();
		list = toptenService.toptenList(top);
		//html로
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		response.getWriter().write(jsonStr);
	}

}
