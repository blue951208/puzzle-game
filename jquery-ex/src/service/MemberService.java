package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDao;
import vo.Member;

public class MemberService {
	public String login(Member member) {
		System.out.println("MemberService 도착\n"+member);
		Member returnMember = null;
		Connection conn = null;
		try{
			DBService dbService = new DBService();
		conn = dbService.getConnection();
		conn.setAutoCommit(false);
		MemberDao memberDao = new MemberDao();
		//before
		returnMember = memberDao.login(conn, member);
		System.out.println("returnMember : "+returnMember);
		//after
		conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();
			}catch(Exception e1){
			e.printStackTrace();
			}
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return returnMember.getMemberId();
	}
}
