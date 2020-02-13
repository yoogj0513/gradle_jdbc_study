package gradle_jdbc_study.dto;

import java.util.Date;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager;
	private int salary;
	private Department dept;
	private String passwd;
	private Date hireDate;
	private byte[] pic;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empNo) {
		this.empNo = empNo;
	}
	
	public Employee(int empNo, String passwd) {
		this.empNo = empNo;
		this.passwd = passwd;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept,
			Date hireDate) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
		this.hireDate = hireDate;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept,
			String passwd, Date hireDate) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
		this.passwd = passwd;
		this.hireDate = hireDate;
	}
	
	
	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept,
			Date hireDate, byte[] pic) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
		this.hireDate = hireDate;
		this.pic = pic;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept,
			String passwd, Date hireDate, byte[] pic) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
		this.passwd = passwd;
		this.hireDate = hireDate;
		this.pic = pic;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empNo != other.empNo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%d) - %s", empName, empNo, title.getTitleName());
		
//		return String.format(
//				"[%s %s %s %s %s %s %s %s %s]",
//				empNo, 
//				empName, 
//				title.getTitleNo(), 
//				manager.getEmpNo(), 
//				salary, 
//				dept.getDeptNo(), 
//				passwd==null?"****":passwd, 
//				String.format("%1$tF %1$tT", hireDate), //tF, tT :F(년-월-일) T(00:00:00) 날짜 포멧 / 1$는 하나의 매개변수로 여러 곳에 적용
//				pic != null? pic.length:null);
	}
	
	public String toDebug() {
		return String.format(
						"[%s %s %s %s %s %s %s %s %s]",
						empNo, 
						empName, 
						title.getTitleNo(), 
						manager.getEmpNo(), 
						salary, 
						dept.getDeptNo(), 
						passwd==null?"****":passwd, 
						String.format("%1$tF %1$tT", hireDate), //tF, tT :F(년-월-일) T(00:00:00) 날짜 포멧 / 1$는 하나의 매개변수로 여러 곳에 적용
						pic != null? pic.length:null);
	}
}
