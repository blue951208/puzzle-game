package service;

import java.sql.*;

import dao.SelectBestTime;

public class BestTimeService {
	public int timeRecord() {
		Connection conn = null;
		int time = 0;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			SelectBestTime selectBestTime =new SelectBestTime();
			time = selectBestTime.bestTimeRecord(conn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		return time;
	}
	
}
