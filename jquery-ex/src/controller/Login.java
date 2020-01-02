package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.MemberService;
import vo.Member;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("로그인 Servlet 도착");
		//memberId,memberPw 값 받기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
	
		System.out.println("ID : "+memberId+"\nPw : "+memberPw);
		//member객체에 set
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		//member 객체를 service로
		MemberService memberService = new MemberService();
		String returnMemberId = memberService.login(member);
		 HttpSession session = request.getSession();
		session.setAttribute("sessionInfo",returnMemberId); 
				}

}
