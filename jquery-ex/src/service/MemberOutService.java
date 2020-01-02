package service;

import java.sql.*;

import dao.DeleteMemberDao;

public class MemberOutService {
	public void memberOut(String memberId) {
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			
			DeleteMemberDao deletMemberDao = new DeleteMemberDao();
			deletMemberDao.deleteMember(conn, memberId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
