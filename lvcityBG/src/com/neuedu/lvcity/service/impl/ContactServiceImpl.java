package com.neuedu.lvcity.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.ContactDao;
import com.neuedu.lvcity.dao.impl.ContactDaoImpl;
import com.neuedu.lvcity.model.Contact;
import com.neuedu.lvcity.service.ContactService;

public class ContactServiceImpl implements ContactService {
	
	private static final ContactService instance = new ContactServiceImpl();
	public static ContactService getInstance() {
		return instance;
	}
	
	@Override
	public int contactCount() {
				//声明数据库连接对象，用于保存数据库连接对象
				Connection conn = null;
				//声明变量，用于保存数据库查询结果
				int result = 0;
				try{
					//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
					conn = DBUtils.getConnection();
					//创建userDao的实现类对象
					ContactDao contactDao = new ContactDaoImpl(conn);
					
					//调用dao中的banarCount方法，进行数据库查询操作，结果赋值给查询结果变量
					result = contactDao.contactCount();	
				
				} catch (Exception e) {
					System.out.println("查询统计所有contact错误"+e.getMessage());
				} finally {
					//调用数据库工具类的closeConnection方法，关闭连接
					DBUtils.closeConnection(conn);
				}
				//返回数据库查询结果
				return result;
	}

	@Override
	public List<Contact> findAllContact(Map<String, Object> map) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		//声明变量，用于保存数据库查询结果
		List<Contact> contact = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//创建userDao的实现类对象
			ContactDao contactDao = new ContactDaoImpl(conn);
			//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
			contact = contactDao.findAllContact(map);		
		
		} catch (Exception e) {
			System.out.println("查询所有contact错误"+e.getMessage());
		} finally {
			//调用数据库工具类的closeConnection方法，关闭连接
			DBUtils.closeConnection(conn);
		}
		//返回数据库查询结果
		return contact;
	}

	@Override
	public int addContact(int contactid, String contactname, String tel,
			String qq, String web, String zipcode, String fax, String address) {
				//声明数据库连接对象，用于保存数据库连接对象
				Connection conn = null;
				//声明变量，用于保存数据库查询结果
				int result = 0;
				try{
					//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
					conn = DBUtils.getConnection();
					DBUtils.beginTransaction(conn);
					//创建userDao的实现类对象
					ContactDao ContactDao = new ContactDaoImpl(conn);
					//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
					result = ContactDao.addContact(contactid,contactname,tel,qq,web,zipcode,fax,address);			
				    DBUtils.commit(conn);
				} catch (Exception e) {
					System.out.println("增加contact错误"+e.getMessage());
				} finally {
					//调用数据库工具类的closeConnection方法，关闭连接
					DBUtils.closeConnection(conn);
				}
				//返回数据库查询结果
				return result;
	}

	@Override
	public int updateContact(Contact contact) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		//声明变量，用于保存数据库查询结果
		int result = 0;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//创建userDao的实现类对象
			ContactDao ContactDao = new ContactDaoImpl(conn);
			//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
			result = ContactDao.updateContact(contact);			
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("更新contact错误"+e.getMessage());
		} finally {
			//调用数据库工具类的closeConnection方法，关闭连接
			DBUtils.closeConnection(conn);
		}
		//返回数据库查询结果
		return result;
	}

	@Override
	public int deleteContact(Contact contact) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		//声明变量，用于保存数据库查询结果
		int result = 0;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//创建userDao的实现类对象
			ContactDao ContactDao = new ContactDaoImpl(conn);
			//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
			result = ContactDao.deleteContact(contact);
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("删除contact错误"+e.getMessage());
		} finally {
			//调用数据库工具类的closeConnection方法，关闭连接
			DBUtils.closeConnection(conn);
		}
		//返回数据库查询结果
		return result;
	}

	@Override
	public Contact findOneContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

}
