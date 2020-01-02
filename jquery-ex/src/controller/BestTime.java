package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.BestTimeService;

/**
 * Servlet implementation class BestTime
 */
@WebServlet("/BestTime")
public class BestTime extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Best Time Servlet 도착 ");
		
		response.setContentType("application/json;charset=utf-8");
		
		BestTimeService bestTime = new BestTimeService();
		int time = bestTime.timeRecord();
		Gson gson = new Gson();
		String gsonTime = gson.toJson(time);
		
		response.getWriter().write(gsonTime);
	}

}
