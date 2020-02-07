package gradle_jdbc_study.dao.impl;

import java.util.List;

import gradle_jdbc_study.dao.DepartmentDao;
import gradle_jdbc_study.dto.Department;

public class DepartmentDaoImpl implements DepartmentDao {
	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();
	private DepartmentDaoImpl() {};
	
	public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Department selectDeptByNo(Department dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> selectDeptByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertDept(Department dept) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDept(Department dept) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDept(Department dept) {
		// TODO Auto-generated method stub
		return 0;
	}

}
