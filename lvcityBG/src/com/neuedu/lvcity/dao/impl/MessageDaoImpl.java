package com.neuedu.lvcity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.MessageDao;
import com.neuedu.lvcity.model.Message;

public class MessageDaoImpl implements MessageDao {
	
	private Connection conn;
	
	public MessageDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int messageCount() {
				//声明变量，用于保存查询结果
				int result = 0;
				//声明预编译的声明对象变量，用于进行数据库操作的载体
				PreparedStatement pstam = null;
				//声明结果集对象变量，用于保存数据库查询结果
				ResultSet rs = null;
				try {
					//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
					pstam = conn.prepareStatement("select count(*) from message");
					//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
					rs = pstam.executeQuery();
					
					//如果查询结果不为空，将取出结果集统计的数据
					if(rs.next()){
						result = rs.getInt("count(*)");
									}	
				}catch (SQLException e) {
					//如果出现异常，输出异常信息
					System.out.println("在查询统计message的时候出错了.错误信息是 ：" + e.getMessage());			
				} finally {
					//调用数据库工具类，关闭结果集对象和声明对象
					DBUtils.closeStatement(rs, pstam);
				}
			    //返回查询到的用户列表
				return result;
	}

	@Override
	public List<Message> findAllMessage(Map<String, Object> map) {
		//声明变量，用于保存查询结果
		List<Message> list = new ArrayList<Message>();
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;
		//声明结果集对象变量，用于保存数据库查询结果
		ResultSet rs = null;
		int startPage = (int) map.get("startPage");
		int endPage = (int) map.get("endPage");
		
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			pstam = conn.prepareStatement("select * from message limit ?,?");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			pstam.setInt(1, startPage);
			pstam.setInt(2, endPage);
			rs = pstam.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中，所有对象放到集合中
			while(rs.next()){
			//创建用户对象，用于封装当前游标中的各个字段的值
			Message message = new Message();
			message.setMid(rs.getInt("mid"));
			message.setTel(rs.getString("tel"));
			message.setSex(rs.getString("sex"));
			message.setName(rs.getString("name"));
			message.setMessage(rs.getString("message"));
						
			//声明结果集对象变量，用于保存数据库查询结果
			list.add(message);
			}	
		}catch (SQLException e) {
			//如果出现异常，输出异常信息
			System.out.println("在查询全部message的时候出错了.错误信息是 ：" + e.getMessage());			
		} finally {
			//调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, pstam);
		}
	    //返回查询到的用户列表
		return list;
	}

	@Override
	public int deleteMessage(Message message) {
		//声明变量，用于保存查询结果
		int result = 0;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;				
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			pstam = conn.prepareStatement("DELETE FROM message WHERE mid =?");
			pstam.setInt(1, message.getMid());
			
			//调用预编译对象的executeQuery方法，
			result = pstam.executeUpdate();
			//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中，所有对象放到集合中
				
		}catch (SQLException e) {
			//如果出现异常，输出异常信息
			System.out.println("删除message出错.错误信息是 ：" + e.getMessage());			
		} finally {
			//调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(null, pstam);
		}
	    //返回查询到的用户列表
		return result;
	}

	

}
