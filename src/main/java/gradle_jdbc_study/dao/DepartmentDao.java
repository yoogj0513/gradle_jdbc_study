package gradle_jdbc_study.dao;

import java.util.List;

import gradle_jdbc_study.dto.Department;

public interface DepartmentDao {
	Department selectDeptByNo(Department dept);
	List<Department> selectDeptByAll();
	
	int insertDept(Department dept);
	int updateDept(Department dept);
	int deleteDept(Department dept);
}
