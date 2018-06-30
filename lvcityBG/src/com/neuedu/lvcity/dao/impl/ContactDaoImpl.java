package com.neuedu.lvcity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.ContactDao;
import com.neuedu.lvcity.model.Contact;

public class ContactDaoImpl implements ContactDao {

	private Connection conn;
	
	public ContactDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int contactCount() {
				//声明变量，用于保存查询结果
				int result = 0;
				//声明预编译的声明对象变量，用于进行数据库操作的载体
				PreparedStatement pstam = null;
				//声明结果集对象变量，用于保存数据库查询结果
				ResultSet rs = null;
				try {
					//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
					pstam = conn.prepareStatement("select count(*) from contact");
					//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
					rs = pstam.executeQuery();
					
					//如果查询结果不为空，将取出结果集统计的数据
					if(rs.next()){
						result = rs.getInt("count(*)");
									}	
				}catch (SQLException e) {
					//如果出现异常，输出异常信息
					System.out.println("在查询统计contact的时候出错了.错误信息是 ：" + e.getMessage());			
				} finally {
					//调用数据库工具类，关闭结果集对象和声明对象
					DBUtils.closeStatement(rs, pstam);
				}
			    //返回查询到的用户列表
				return result;
	}

	@Override
	public List<Contact> findAllContact(Map<String, Object> map) {
		//声明变量，用于保存查询结果
		List<Contact> list = new ArrayList<Contact>();
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;
		//声明结果集对象变量，用于保存数据库查询结果
		ResultSet rs = null;
		int startPage = (int) map.get("startPage");
		int endPage = (int) map.get("endPage");
		
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			pstam = conn.prepareStatement("select * from contact limit ?,?");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			pstam.setInt(1, startPage);
			pstam.setInt(2, endPage);
			rs = pstam.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中，所有对象放到集合中
			while(rs.next()){
			//创建用户对象，用于封装当前游标中的各个字段的值
			Contact contact = new Contact();
			contact.setContactid(rs.getInt("contactid"));
			contact.setContactname(rs.getString("contactname"));
			contact.setTel(rs.getString("tel"));
			contact.setQq(rs.getString("qq"));
			contact.setWeb(rs.getString("web"));
			contact.setZipcode(rs.getString("zipcode"));
			contact.setFax(rs.getString("fax"));
			contact.setAddress(rs.getString("address"));
			
			//声明结果集对象变量，用于保存数据库查询结果
			list.add(contact);
			}	
		}catch (SQLException e) {
			//如果出现异常，输出异常信息
			System.out.println("在查询全部contact的时候出错了.错误信息是 ：" + e.getMessage());			
		} finally {
			//调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, pstam);
		}
	    //返回查询到的用户列表
		return list;
	}

	@Override
	public int addContact(int contactid, String contactname, String tel,
			String qq, String web, String zipcode, String fax, String address) {
		//声明变量，用于保存查询结果
				int result = 0;
				//声明预编译的声明对象变量，用于进行数据库操作的载体
				PreparedStatement pstam = null;				
				try {
					//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
					pstam = conn.prepareStatement("insert into contact(contactname,tel,qq,web,zipcode,fax,address) values(?)");
					pstam.setString(1, contactname+","+tel+","+qq+","+web+","+zipcode+","+fax+","+address);
					
					//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
					result = pstam.executeUpdate();
					//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中，所有对象放到集合中
						
				}catch (SQLException e) {
					//如果出现异常，输出异常信息
					System.out.println("增加contact出错.错误信息是 ：" + e.getMessage());			
				} finally {
					//调用数据库工具类，关闭结果集对象和声明对象
					DBUtils.closeStatement(null, pstam);
				}
			    //返回查询到的用户列表
				return result;
	}

	@Override
	public int updateContact(Contact contact) {
		//声明变量，用于保存查询结果
		int result = 0;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;				
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			pstam = conn.prepareStatement("UPDATE contact SET contactname='?',address='?',tel='?' WHERE contactid=?;");
			pstam.setString(1,contact.getContactname());
			pstam.setString(2,contact.getAddress());
			pstam.setString(3,contact.getTel());
			pstam.setInt(4,contact.getContactid());
			
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			result = pstam.executeUpdate();
			//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中，所有对象放到集合中
				
		}catch (SQLException e) {
			//如果出现异常，输出异常信息
			System.out.println("更新contact出错.错误信息是 ：" + e.getMessage());			
		}finally {
			//调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(null, pstam);
		}
	    //返回查询到的用户列表
		return result;
	}

	@Override
	public int deleteContact(Contact contact) {
		//声明变量，用于保存查询结果
		int result = 0;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;				
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			pstam = conn.prepareStatement("DELETE FROM contact WHERE contactid =?");
			pstam.setInt(1, contact.getContactid());
			
			//调用预编译对象的executeQuery方法，
			result = pstam.executeUpdate();
			//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中，所有对象放到集合中
				
		}catch (SQLException e) {
			//如果出现异常，输出异常信息
			System.out.println("删除contact出错.错误信息是 ：" + e.getMessage());			
		} finally {
			//调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(null, pstam);
		}
	    //返回查询到的用户列表
		return result;
	}

	@Override
	public Contact findOneContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

}
