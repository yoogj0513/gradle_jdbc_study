package gradle_jdbc_study.dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_jdbc_study.dao.impl.TitleDaoImpl;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = TitleDaoImpl.getInstance();
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
	public void test01SelectTitleByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title resTitle = dao.selectTitleByNo(new Title(2));
		Assert.assertNotNull(resTitle);
		LogUtil.prnLog(resTitle);
	}

	@Test
	public void test02SelectTitleByAll() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Title> lists = dao.selectTitleByAll();
		Assert.assertNotNull(lists);
		
		for(Title t : lists) LogUtil.prnLog(t);
		
	}

	@Test
	public void test03InsertTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title newTit = new Title(6, "인턴");
		int res = dao.insertTitle(newTit);	
		Assert.assertEquals(1, res);
		List<Title> lists = dao.selectTitleByAll();
		for(Title t : lists) LogUtil.prnLog(t);
	}

	@Test
	public void test04UpdateTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title updateTit = new Title(6, "계약직");
		int res = dao.updateTitle(updateTit);
		Assert.assertEquals(1, res);
		List<Title> lists = dao.selectTitleByAll();
		for(Title t : lists) LogUtil.prnLog(t);
	}

	@Test
	public void test05DeleteTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title deleTit = new Title(6);
		int res = dao.deleteTitle(deleTit);
		Assert.assertEquals(1, res);
		List<Title> lists = dao.selectTitleByAll();
		for(Title t : lists) LogUtil.prnLog(t);
	}

}
