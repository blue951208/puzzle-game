package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.ToptenListDao;
import vo.Report;

public class ToptenService {
	/*
	 * Connection 생성
	 * Dao에서 던진 오류잡기
	 * */
	public List<Report> toptenList(int top){
		System.out.println("topten Service 도착");
		System.out.println("top 값은:"+top);
		List<Report> list = new ArrayList<Report>();
		Connection conn = null;
			
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			ToptenListDao topTenListDao = new ToptenListDao();
			list = topTenListDao.topList(conn,top);
			System.out.println(list);
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
