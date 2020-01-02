package service;

import java.sql.Connection;

import dao.InsertAddReportDao;
import vo.Report;

public class AddReportService {
	/*report 객체를 받아와서,
	 * Connection 생성,
	 * Dao로 호출, Connection,report값 전달,
	 * Dao에서 thorow한 exception catch
	 * */ 
	public void addReport(Report report){
		Connection conn = null;
		DBService dbService = new DBService();
		try {
			conn = dbService.getConnection();
			InsertAddReportDao insertAddReportDao = new InsertAddReportDao();
			insertAddReportDao.insertReport(conn, report);
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
