package dao;


import java.sql.*;

import vo.Report;

public class InsertAddReportDao {
	/*
	 * Connection ,report객체를 매게변수로 하는 메소드 생성
	 * db접속후 ,매게변수 report값을 insert
	 * */
	public void insertReport(Connection conn,Report report) throws Exception {
		System.out.println("insertReport Dao 도착");
		PreparedStatement stmt = null;
		
		String sql = "insert into report(member_id,report_date,count,timer,best_combo) values(?,now(),?,?,?)";
	
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,report.getMember().getMemberId());
			stmt.setInt(2,report.getCount());
			stmt.setInt(3, report.getTimer());		
			stmt.setInt(4, report.getBestCombo());
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
}
