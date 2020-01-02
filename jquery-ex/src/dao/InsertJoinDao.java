package dao;

import java.sql.*;

import vo.Member;

public class InsertJoinDao {
	public void insertMemeber(Connection conn,Member member) throws Exception {
		System.out.println("Join Dao 도착");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into member(member_id,member_pw,member_level) values(?,?,'Y')";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
		}finally {
			rs.close();
			stmt.close();
		}
	}
}
