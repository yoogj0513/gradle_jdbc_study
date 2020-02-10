package gradle_jdbc_study.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = DepartmentDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	}

	@Test
	public void test01SelectDeptByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department resDept = dao.selectDeptByNo(new Department(2));
		Assert.assertNotNull(resDept);
		LogUtil.prnLog(resDept);
	}

	@Test
	public void test02SelectDeptByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Department> lists = dao.selectDeptByAll();
		Assert.assertNotNull(lists);
		
		for(Department d : lists) LogUtil.prnLog(d);
	}

	@Test
	public void test03InsertDept() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department newDept = new Department(5, "디자인", 5);
		int res = dao.insertDept(newDept);
		Assert.assertEquals(1, res);
		List<Department> lists = dao.selectDeptByAll();
		for(Department d : lists) LogUtil.prnLog(d);
	}

	@Test
	public void test04UpdateDept() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department upDept = new Department(5, "회계");
		int res = dao.updateDept(upDept);
		Assert.assertEquals(1, res);
		List<Department> lists = dao.selectDeptByAll();
		for(Department d : lists) LogUtil.prnLog(d);
	}

	@Test
	public void test05DeleteDept() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department dltDept = new Department(5);
		int res = dao.deleteDept(dltDept);
		Assert.assertEquals(1, res);
		List<Department> lists = dao.selectDeptByAll();
		for(Department d : lists) LogUtil.prnLog(d);
	}

}
