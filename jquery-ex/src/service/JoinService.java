package service;


import java.sql.Connection;

import dao.InsertJoinDao;
import vo.Member;

public class JoinService {
	public void join(Member member) {
		System.out.println("service 도착");
		Connection conn = null;
		DBService dbService = new DBService();
		InsertJoinDao insertMember = new InsertJoinDao();
		try {
			conn = dbService.getConnection();
			insertMember.insertMemeber(conn, member);
		}catch(Exception e){
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
