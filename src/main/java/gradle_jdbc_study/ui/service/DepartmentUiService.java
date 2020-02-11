package gradle_jdbc_study.ui.service;

import java.util.List;

import gradle_jdbc_study.dao.DepartmentDao;
import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dao.impl.EmployeeDaoImpl;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;

public class DepartmentUiService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	public List<Department> showDepartmentList(){
		return deptDao.selectDeptByAll();
	}
	
	public void insertDept(Department dept) {
		deptDao.insertDept(dept);
	}
	
	public void updateDept(Department dept) {
		deptDao.updateDept(dept);
	}
	
	public void deleteDept(Department dept) {
		deptDao.deleteDept(dept);
	}
	
	public List<Employee> showEmployeeGroupByDno(Department dept){
		return empDao.selectEmployeeGroupByDno(dept);
	}
}
