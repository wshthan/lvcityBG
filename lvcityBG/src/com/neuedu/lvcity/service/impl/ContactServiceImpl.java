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
				//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
				Connection conn = null;
				//�������������ڱ������ݿ��ѯ���
				int result = 0;
				try{
					//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
					conn = DBUtils.getConnection();
					//����userDao��ʵ�������
					ContactDao contactDao = new ContactDaoImpl(conn);
					
					//����dao�е�banarCount�������������ݿ��ѯ�����������ֵ����ѯ�������
					result = contactDao.contactCount();	
				
				} catch (Exception e) {
					System.out.println("��ѯͳ������contact����"+e.getMessage());
				} finally {
					//�������ݿ⹤�����closeConnection�������ر�����
					DBUtils.closeConnection(conn);
				}
				//�������ݿ��ѯ���
				return result;
	}

	@Override
	public List<Contact> findAllContact(Map<String, Object> map) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		List<Contact> contact = null;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			//����userDao��ʵ�������
			ContactDao contactDao = new ContactDaoImpl(conn);
			//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
			contact = contactDao.findAllContact(map);		
		
		} catch (Exception e) {
			System.out.println("��ѯ����contact����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return contact;
	}

	@Override
	public int addContact(int contactid, String contactname, String tel,
			String qq, String web, String zipcode, String fax, String address) {
				//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
				Connection conn = null;
				//�������������ڱ������ݿ��ѯ���
				int result = 0;
				try{
					//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
					conn = DBUtils.getConnection();
					DBUtils.beginTransaction(conn);
					//����userDao��ʵ�������
					ContactDao ContactDao = new ContactDaoImpl(conn);
					//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
					result = ContactDao.addContact(contactid,contactname,tel,qq,web,zipcode,fax,address);			
				    DBUtils.commit(conn);
				} catch (Exception e) {
					System.out.println("����contact����"+e.getMessage());
				} finally {
					//�������ݿ⹤�����closeConnection�������ر�����
					DBUtils.closeConnection(conn);
				}
				//�������ݿ��ѯ���
				return result;
	}

	@Override
	public int updateContact(Contact contact) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		int result = 0;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//����userDao��ʵ�������
			ContactDao ContactDao = new ContactDaoImpl(conn);
			//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
			result = ContactDao.updateContact(contact);			
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("����contact����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return result;
	}

	@Override
	public int deleteContact(Contact contact) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		int result = 0;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//����userDao��ʵ�������
			ContactDao ContactDao = new ContactDaoImpl(conn);
			//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
			result = ContactDao.deleteContact(contact);
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("ɾ��contact����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return result;
	}

	@Override
	public Contact findOneContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

}
