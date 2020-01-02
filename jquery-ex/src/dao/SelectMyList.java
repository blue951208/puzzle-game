package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Member;
import vo.Report;

public class SelectMyList {
	public List<Report> viewMyList(Connection conn,String memberId) throws Exception{
		System.out.println("mylist method 도착");
		List<Report> list = new ArrayList<Report>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select report_id,member_id,report_date,count,timer from report where member_id=?";
			try {
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Report report = new Report();
					report.setReportId(rs.getInt("report_Id"));
					report.setMember(new Member());
					report.getMember().setMemberId((rs.getString("member_id")));
					report.setReportDate(rs.getString("report_date"));
					report.setCount(rs.getInt("count"));
					report.setTimer(rs.getInt("timer"));
					
					list.add(report);
					System.out.println("MyList 값은 :"+list);
				}
				
			}finally {
				rs.close();
				stmt.close();
			}
		return list;
		
	}
}
