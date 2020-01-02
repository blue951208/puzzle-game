package service;

import java.sql.*;

import dao.UpdatePwDao;
import vo.Member;

public class PwUpdateService {
	public void pwUpdate(Member member) {
		System.out.println("pw Service\n"+"member::"+member);
		Connection conn = null;
		UpdatePwDao updatePwDao = new UpdatePwDao();
		DBService dBService = new DBService();
		try {
			conn = dBService.getConnection();
			updatePwDao.updatePw(conn, member);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
