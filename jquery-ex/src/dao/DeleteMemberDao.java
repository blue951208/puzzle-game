package dao;

import java.sql.*;

public class DeleteMemberDao {
	public void deleteMember(Connection conn,String memberId) throws Exception {
		PreparedStatement stmt = null;
		System.out.println("delete Dao 도착\n");
		String sql = "delete from member where member_id=?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
}
