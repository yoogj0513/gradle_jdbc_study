package gradle_jdbc_study.ui.service;

import java.util.List;

import gradle_jdbc_study.dao.DepartmentDao;
import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.dao.TitleDao;
import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dao.impl.EmployeeDaoImpl;
import gradle_jdbc_study.dao.impl.TitleDaoImpl;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;

public class EmployeeUiService {
	private EmployeeDao empDao;
	private DepartmentDao deptDao;
	private TitleDao titDao;

	public EmployeeUiService() {
		empDao = EmployeeDaoImpl.getInstance();
		deptDao = DepartmentDaoImpl.getInstance();
		titDao = TitleDaoImpl.getInstance();
	}

	public List<Department> showDeptList() {
		return deptDao.selectDeptByAll();
	}

	public List<Employee> showManagerList(Department dept) {
		return empDao.selectEmployeeGroupByDno(dept);
	}

	public List<Title> showTitleList() {
		return titDao.selectTitleByAll();
	}
	
}
