package dao;

import java.sql.*;

public class SelectBestTime {
	public int bestTimeRecord(Connection conn) throws Exception {
		int time=0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select min(timer) as timer from report";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				time = rs.getInt("timer");
			}
		}finally {
			stmt.close();
			rs.close();
		}
				
		return time;
	}
}
