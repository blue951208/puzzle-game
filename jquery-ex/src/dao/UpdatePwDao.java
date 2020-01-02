package dao;

import java.sql.*;

import vo.Member;

public class UpdatePwDao {
	public void updatePw(Connection conn,Member member) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println(member);
		System.out.println("update dao 도착");
		String sql = "update member set member_pw =? where member_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberPw());
			stmt.setString(2,member.getMemberId());
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
}
