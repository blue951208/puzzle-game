package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AddReportService;
import vo.Member;
import vo.Report;

/**
 * Servlet implementation class Addreport
 */
@WebServlet("/Addreport")
public class Addreport extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addReport Servlet 도착");
		
		//memberId,timer,count 값 받아오기
		int timer = Integer.parseInt(request.getParameter("timer"));
		int count = Integer.parseInt(request.getParameter("count"));
		String memberId = request.getParameter("memberId");
		int bestCombo = Integer.parseInt(request.getParameter("bestCombo"));
		System.out.println("timer : "+timer+"\n count : "+count);
		System.out.println("memberID : "+memberId+"\n bestCOMBO :"+bestCombo);
		
		//report에 set
		Report report = new Report();
		report.setTimer(timer);
		report.setCount(count);
		report.setMember(new Member());
		report.getMember().setMemberId(memberId);;
		report.setBestCombo(bestCombo);
		
		System.out.println(report);
		
		AddReportService addReportService = new AddReportService();//service 호출
		addReportService.addReport(report);
	}

}
