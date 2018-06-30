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
				//�������������ڱ����ѯ���
				int result = 0;
				//����Ԥ���������������������ڽ������ݿ����������
				PreparedStatement pstam = null;
				//���������������������ڱ������ݿ��ѯ���
				ResultSet rs = null;
				try {
					//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
					pstam = conn.prepareStatement("select count(*) from contact");
					//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
					rs = pstam.executeQuery();
					
					//�����ѯ�����Ϊ�գ���ȡ�������ͳ�Ƶ�����
					if(rs.next()){
						result = rs.getInt("count(*)");
									}	
				}catch (SQLException e) {
					//��������쳣������쳣��Ϣ
					System.out.println("�ڲ�ѯͳ��contact��ʱ�������.������Ϣ�� ��" + e.getMessage());			
				} finally {
					//�������ݿ⹤���࣬�رս�����������������
					DBUtils.closeStatement(rs, pstam);
				}
			    //���ز�ѯ�����û��б�
				return result;
	}

	@Override
	public List<Contact> findAllContact(Map<String, Object> map) {
		//�������������ڱ����ѯ���
		List<Contact> list = new ArrayList<Contact>();
		//����Ԥ���������������������ڽ������ݿ����������
		PreparedStatement pstam = null;
		//���������������������ڱ������ݿ��ѯ���
		ResultSet rs = null;
		int startPage = (int) map.get("startPage");
		int endPage = (int) map.get("endPage");
		
		try {
			//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
			pstam = conn.prepareStatement("select * from contact limit ?,?");
			//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
			pstam.setInt(1, startPage);
			pstam.setInt(2, endPage);
			rs = pstam.executeQuery();
			//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
			while(rs.next()){
			//�����û��������ڷ�װ��ǰ�α��еĸ����ֶε�ֵ
			Contact contact = new Contact();
			contact.setContactid(rs.getInt("contactid"));
			contact.setContactname(rs.getString("contactname"));
			contact.setTel(rs.getString("tel"));
			contact.setQq(rs.getString("qq"));
			contact.setWeb(rs.getString("web"));
			contact.setZipcode(rs.getString("zipcode"));
			contact.setFax(rs.getString("fax"));
			contact.setAddress(rs.getString("address"));
			
			//���������������������ڱ������ݿ��ѯ���
			list.add(contact);
			}	
		}catch (SQLException e) {
			//��������쳣������쳣��Ϣ
			System.out.println("�ڲ�ѯȫ��contact��ʱ�������.������Ϣ�� ��" + e.getMessage());			
		} finally {
			//�������ݿ⹤���࣬�رս�����������������
			DBUtils.closeStatement(rs, pstam);
		}
	    //���ز�ѯ�����û��б�
		return list;
	}

	@Override
	public int addContact(int contactid, String contactname, String tel,
			String qq, String web, String zipcode, String fax, String address) {
		//�������������ڱ����ѯ���
				int result = 0;
				//����Ԥ���������������������ڽ������ݿ����������
				PreparedStatement pstam = null;				
				try {
					//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
					pstam = conn.prepareStatement("insert into contact(contactname,tel,qq,web,zipcode,fax,address) values(?)");
					pstam.setString(1, contactname+","+tel+","+qq+","+web+","+zipcode+","+fax+","+address);
					
					//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
					result = pstam.executeUpdate();
					//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
						
				}catch (SQLException e) {
					//��������쳣������쳣��Ϣ
					System.out.println("����contact����.������Ϣ�� ��" + e.getMessage());			
				} finally {
					//�������ݿ⹤���࣬�رս�����������������
					DBUtils.closeStatement(null, pstam);
				}
			    //���ز�ѯ�����û��б�
				return result;
	}

	@Override
	public int updateContact(Contact contact) {
		//�������������ڱ����ѯ���
		int result = 0;
		//����Ԥ���������������������ڽ������ݿ����������
		PreparedStatement pstam = null;				
		try {
			//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
			pstam = conn.prepareStatement("UPDATE contact SET contactname='?',address='?',tel='?' WHERE contactid=?;");
			pstam.setString(1,contact.getContactname());
			pstam.setString(2,contact.getAddress());
			pstam.setString(3,contact.getTel());
			pstam.setInt(4,contact.getContactid());
			
			//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
			result = pstam.executeUpdate();
			//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
				
		}catch (SQLException e) {
			//��������쳣������쳣��Ϣ
			System.out.println("����contact����.������Ϣ�� ��" + e.getMessage());			
		}finally {
			//�������ݿ⹤���࣬�رս�����������������
			DBUtils.closeStatement(null, pstam);
		}
	    //���ز�ѯ�����û��б�
		return result;
	}

	@Override
	public int deleteContact(Contact contact) {
		//�������������ڱ����ѯ���
		int result = 0;
		//����Ԥ���������������������ڽ������ݿ����������
		PreparedStatement pstam = null;				
		try {
			//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
			pstam = conn.prepareStatement("DELETE FROM contact WHERE contactid =?");
			pstam.setInt(1, contact.getContactid());
			
			//����Ԥ��������executeQuery������
			result = pstam.executeUpdate();
			//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
				
		}catch (SQLException e) {
			//��������쳣������쳣��Ϣ
			System.out.println("ɾ��contact����.������Ϣ�� ��" + e.getMessage());			
		} finally {
			//�������ݿ⹤���࣬�رս�����������������
			DBUtils.closeStatement(null, pstam);
		}
	    //���ز�ѯ�����û��б�
		return result;
	}

	@Override
	public Contact findOneContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

}
