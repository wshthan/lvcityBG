package com.neuedu.lvcity.service.impl;

import java.sql.Connection;

import org.apache.tomcat.jni.User;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UsersDao;
import com.neuedu.lvcity.dao.impl.UsersDaoImpl;
import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.UsersService;

import sun.security.jca.GetInstance.Instance;

public class UsersServiceImpl implements UsersService {
	/**
	 * ���췽��˽�л�
	 */
	 private UsersServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	 
	 /**
	  * ����һ��Ψһ��ʵ��
	  */
	 private static UsersService Instance = new UsersServiceImpl();
	 
	 /**
	  * �ṩһ��������ʵĹ����ӿ�
	  * @return
	  */
	 public static  UsersService getInstance() {
		return Instance;
	}

	@Override
	public Users login(String username, String passwrod) {
		// TODO Auto-generated method stub
		//���ؽ��
		Users users = null;
		//��������
		Connection conn= null;
		try{
				//��ȡ���ݿ�����
				conn = DBUtils.getConnection();
				//�õ�dao��ʵ����Ķ���
				UsersDao usersDao = new UsersDaoImpl(conn);
				//����dao��ķ���
				users = usersDao.login(username, passwrod);
		}catch(Exception exception ){
			System.out.println("��¼��ѯ�û��ǳ����쳣��"+exception.getMessage());
		}finally {
			DBUtils.closeConnection(conn);
		}		
		return users;		
	}

}
