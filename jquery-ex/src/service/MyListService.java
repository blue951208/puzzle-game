package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.SelectMyList;
import vo.Report;

public class MyListService {
	public List<Report> selectMyList(String memberId){
		List<Report> list = new ArrayList<Report>();
		Connection conn = null;
		
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			SelectMyList selectMyList = new SelectMyList();
			list = selectMyList.viewMyList(conn,memberId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		return list;
	}
}
