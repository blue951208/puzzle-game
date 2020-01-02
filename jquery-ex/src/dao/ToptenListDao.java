package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vo.Member;
import vo.Report;

public class ToptenListDao {
	public List<Report> topList(Connection conn,int top) throws Exception{
		System.out.println("top method 값은 :"+top);
		List<Report> list = new ArrayList<Report>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "";
		if(top==1) {
			sql = "select report_id,member_id,report_date,count,timer from report limit 10";
		}else if(top==2) {
			sql = "select report_id,member_id,report_date,count,timer from report where substr(report_date,1,7)=substr(now(),1,7) limit 10";
		}else if(top==3) {
			sql = "select report_id,member_id,report_date,count,timer from report where substr(report_date,1,10)=substr(now(),1,10) limit 10";
		}
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Report report = new Report();
				 report = new Report();
				 report.setCount(rs.getInt("count"));
				 report.setMember(new Member());
				 report.getMember().setMemberId(rs.getString("member_id"));
				System.out.println(report.getMember());
				 report.setReportId(rs.getInt("report_id"));
				 report.setReportDate(rs.getString("report_date"));
				 report.setTimer(rs.getInt("timer"));
				 list.add(report);
			}
		}finally {
			rs.close();
			stmt.close();
		}
		
		return list;
	}
}
