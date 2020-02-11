package gradle_jdbc_study.ui.service;

import java.util.List;

import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.dao.TitleDao;
import gradle_jdbc_study.dao.impl.EmployeeDaoImpl;
import gradle_jdbc_study.dao.impl.TitleDaoImpl;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;

public class TitleUiService {
	private TitleDao titDao = TitleDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	public List<Title> showTitleList(){
		return titDao.selectTitleByAll();
	}
	
	public void addTitleItem(Title item) {
		titDao.insertTitle(item);
	}
	
	public void updateTitleItem(Title item) {
		titDao.updateTitle(item);
	}
	
	public void removeTitleItem(Title item) {
		titDao.deleteTitle(item);
	}
	
	public List<Employee> showEmployeeGroupByTitle(Title title){
		return empDao.selectEmployeeGroupByTitle(title);
	}
}
