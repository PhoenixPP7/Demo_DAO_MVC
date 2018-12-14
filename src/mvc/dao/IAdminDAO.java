package mvc.dao;

import mvc.vo.Admin;

public interface IAdminDAO extends IDAO<String, Admin> {
	/**
	 * 	实现管理员登陆验证操作;
	 * @param vo
	 * @return 如果账号密码正确返回true,否则返回false;
	 * @throws Exception
	 */
	public boolean findLogin(Admin vo) throws Exception;
}
