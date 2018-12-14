package mvc.service.impl;

import mvc.dbc.DatabaseConnection;
import mvc.factory.DAOFactory;
import mvc.service.IAdminService;
import mvc.vo.Admin;

public class AdminServiceImpl implements IAdminService {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean login(Admin vo) throws Exception {
		try {
			return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
