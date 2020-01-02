package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PwUpdateService;
import vo.Member;

/**
 * Servlet implementation class PwUpdate
 */
@WebServlet("/PwUpdate")
public class PwUpdate extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("pw update Servlet");
		//memberId,oldPw,newPw 값 받아오기
		String newPw = request.getParameter("newPw");
		String memberId = request.getParameter("memberId");
		System.out.println("pw update ID::"+memberId);
		
		Member member = new Member();
		member.setMemberPw(newPw);
		member.setMemberId(memberId);
		
		//response.setContentType("application/json;charset=utf-8");
		
		PwUpdateService pwUpdateService = new PwUpdateService();
		pwUpdateService.pwUpdate(member);
	}

}
