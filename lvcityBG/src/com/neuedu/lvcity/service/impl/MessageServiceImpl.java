package com.neuedu.lvcity.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.MessageDao;
import com.neuedu.lvcity.dao.impl.MessageDaoImpl;
import com.neuedu.lvcity.model.Message;
import com.neuedu.lvcity.service.MessageService;

public class MessageServiceImpl implements MessageService {
	
	private static final MessageService instance = new MessageServiceImpl();
	public static MessageService getInstance() {
		return instance;
	}

	@Override
	public int messageCount() {
				//声明数据库连接对象，用于保存数据库连接对象
				Connection conn = null;
				//声明变量，用于保存数据库查询结果
				int result = 0;
				try{
					//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
					conn = DBUtils.getConnection();
					//创建userDao的实现类对象
					MessageDao messageDao = new MessageDaoImpl(conn);
					
					//调用dao中的banarCount方法，进行数据库查询操作，结果赋值给查询结果变量
					result = messageDao.messageCount();	
				
				} catch (Exception e) {
					System.out.println("查询统计所有message错误"+e.getMessage());
				} finally {
					//调用数据库工具类的closeConnection方法，关闭连接
					DBUtils.closeConnection(conn);
				}
				//返回数据库查询结果
				return result;
	}

	@Override
	public List<Message> findAllMessage(Map<String, Object> map) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		//声明变量，用于保存数据库查询结果
		List<Message> message = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//创建userDao的实现类对象
			MessageDao messageDao = new MessageDaoImpl(conn);
			//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
			message = messageDao.findAllMessage(map);			
		
		} catch (Exception e) {
			System.out.println("查询所有message错误"+e.getMessage());
		} finally {
			//调用数据库工具类的closeConnection方法，关闭连接
			DBUtils.closeConnection(conn);
		}
		//返回数据库查询结果
		return message;
	}

	@Override
	public int deleteMessage(Message message) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		//声明变量，用于保存数据库查询结果
		int result = 0;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//创建userDao的实现类对象
			MessageDao MessageDao = new MessageDaoImpl(conn);
			//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
			result = MessageDao.deleteMessage(message);
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("删除message错误"+e.getMessage());
		} finally {
			//调用数据库工具类的closeConnection方法，关闭连接
			DBUtils.closeConnection(conn);
		}
		//返回数据库查询结果
		return result;
	}

	@Override
	public Message findOneMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

}
