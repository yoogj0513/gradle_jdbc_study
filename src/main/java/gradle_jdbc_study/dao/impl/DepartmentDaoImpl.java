package gradle_jdbc_study.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradle_jdbc_study.dao.DepartmentDao;
import gradle_jdbc_study.ds.MysqlDataSource;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.util.LogUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();
	private DepartmentDaoImpl() {};
	
	public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Department selectDeptByNo(Department dept) {
		String sql = "select dept_no, dept_name, floor from department where dept_no = ?";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			LogUtil.prnLog(pstmt);
			pstmt.setInt(1, dept.getDeptNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getDept(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Department getDept(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("dept_no");
		String deptName = rs.getString("dept_name");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public List<Department> selectDeptByAll() {
		String sql = "select dept_no, dept_name, floor from department";
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			List<Department> list = new ArrayList<>();
			while(rs.next()) {
				list.add(getDept(rs));
			}
			LogUtil.prnLog(pstmt);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertDept(Department dept) {
		String sql = "insert into  department values(?, ?, ?)";
		int res = -1;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, dept.getDeptNo());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setInt(3, dept.getFloor());
			res = pstmt.executeUpdate();
			LogUtil.prnLog(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateDept(Department dept) {
		String sql = "update department set dept_name = ? where dept_no = ?";
		int res = -1;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, dept.getDeptName());
			pstmt.setInt(2, dept.getDeptNo());
			res = pstmt.executeUpdate();
			LogUtil.prnLog(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteDept(Department dept) {
		String sql = "delete from department where dept_no = ?";
		int res = -1;
		try(Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, dept.getDeptNo());
			res = pstmt.executeUpdate();
			LogUtil.prnLog(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
