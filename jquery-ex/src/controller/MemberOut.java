package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberOutService;

/**
 * Servlet implementation class MemberOut
 */
@WebServlet("/MemberOut")
public class MemberOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		System.out.println("MemberOut servlet 도착\n memberID::"+memberId);
		
		MemberOutService memberOutService = new MemberOutService();
		memberOutService.memberOut(memberId);
		request.getSession().invalidate();
	}

}
