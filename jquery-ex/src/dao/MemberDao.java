package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;

public class MemberDao {
	public Member login(Connection conn,Member member) throws Exception{
		System.out.println("MemberDao 도착 \n"+member);
		
		 String sql = "select member_id from member where member_pw=? and member_id=?";// and member_level='Y'";
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		Member returnMember = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberPw());
			stmt.setString(2,member.getMemberId());
			 rs = stmt.executeQuery();
			 returnMember = new Member();
			if(rs.next()) {
				returnMember.setMemberId(rs.getString("member_id"));
			}
		}finally {
			try{
				rs.close();
				stmt.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		return returnMember;
	}
}
