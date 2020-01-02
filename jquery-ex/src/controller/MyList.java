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

import service.MyListService;
import vo.Report;

/**
 * Servlet implementation class MyList
 */
@WebServlet("/MyList")
public class MyList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("mylist Servlet 도착");
		//memberId값 받기
		String memberId = request.getParameter("memberId");
		System.out.println("myList Servlet memberId::"+memberId);
		
		response.setContentType("application/json;charset=utf-8");
		
		MyListService myListService = new MyListService();
		List<Report> list = new ArrayList<Report>();
		list = myListService.selectMyList(memberId);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		response.getWriter().write(jsonStr);
	}

}
