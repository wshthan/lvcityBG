package com.neuedu.lvcity.dao;

import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.model.Contact;


public interface ContactDao {
	public int contactCount();
	public List<Contact> findAllContact(Map<String, Object> map);
	public int addContact(int contactid, String contactname, String tel, String qq,
			String web, String zipcode, String fax, String address);
	public int updateContact(Contact contact);
	public int deleteContact(Contact contact);
	public Contact findOneContact(Contact contact);
}
