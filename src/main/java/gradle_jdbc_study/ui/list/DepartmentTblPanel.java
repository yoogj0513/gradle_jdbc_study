package gradle_jdbc_study.ui.list;

import javax.swing.SwingConstants;

import gradle_jdbc_study.dto.Department;

@SuppressWarnings("serial")
public class DepartmentTblPanel extends AbstractTblPanel<Department> {

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(50, 150, 50);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2);
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"부서번호", "부서명", "위치"};
	}

	@Override
	protected Object[] toArray(Department item) {
		return new Object[] {
				item.getDeptNo(), 
				item.getDeptName(), 
				item.getFloor()};
	}

	@Override
	public void updateRow(Department item, int updateIdx) {
		model.setValueAt(item.getDeptNo(), updateIdx, 0);//학생번호
		model.setValueAt(item.getDeptName(), updateIdx, 1);
		model.setValueAt(item.getFloor(), updateIdx, 2);
	}

}
