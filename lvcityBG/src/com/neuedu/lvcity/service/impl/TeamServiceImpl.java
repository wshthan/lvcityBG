package com.neuedu.lvcity.service.impl;

import java.sql.Connection;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.TeamDao;
import com.neuedu.lvcity.dao.impl.TeamDaoImpl;
import com.neuedu.lvcity.model.Team;
import com.neuedu.lvcity.service.TeamService;

public class TeamServiceImpl implements TeamService {
	
	private static final TeamService instance = new TeamServiceImpl();
	public static TeamService getInstance() {
		return instance;
	}
	
	@Override
	public int updateTeam(Team team) {
				//声明数据库连接对象，用于保存数据库连接对象
				Connection conn = null;
				//声明变量，用于保存数据库查询结果
				int result = 0;
				try{
					//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
					conn = DBUtils.getConnection();
					DBUtils.beginTransaction(conn);
					//创建userDao的实现类对象
					TeamDao TeamDao = new TeamDaoImpl(conn);
					//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
					result = TeamDao.updateTeam(team);			
				    DBUtils.commit(conn);
				} catch (Exception e) {
					System.out.println("更新team错误"+e.getMessage());
				} finally {
					//调用数据库工具类的closeConnection方法，关闭连接
					DBUtils.closeConnection(conn);
				}
				//返回数据库查询结果
				return result;
	}

	

}
