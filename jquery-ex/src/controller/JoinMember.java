package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.JoinService;
import vo.Member;

/**
 * Servlet implementation class JoinMember
 */
@WebServlet("/JoinMember")
public class JoinMember extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("join Servlet 도착");
		//joinId,joinPw받아오기
		String joinId = request.getParameter("joinId");
		String joinPw = request.getParameter("joinPw");
		
		System.out.println("joinID: "+joinId+"\n joinPW : "+joinPw);
		
		Member member = new Member();
		member.setMemberId(joinId);
		member.setMemberPw(joinPw);
		
		JoinService joinService = new JoinService();
		joinService.join(member);
	}

}
