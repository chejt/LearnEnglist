package org.english.dao.api;


import java.util.List;

import org.english.form.Admin;
import org.english.form.Student;

public interface AdminDao {
	boolean addAdmin(Admin admin);
	public Admin getAdminById(int id);
	Admin getAdminByEmail(String email, String password);
	public int getAdminRoleId(int id);
	public List<Admin> getAllAdminPage(int page,int pageSize);
	Long getAllAdminCount();
	boolean updateRoleByAdminId(int adminId, int roleId);
}
