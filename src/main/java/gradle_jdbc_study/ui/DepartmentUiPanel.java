package gradle_jdbc_study.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.ui.content.DepartmentPanel;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gradle_jdbc_study.ui.list.DepartmentTblPanel;
import gradle_jdbc_study.ui.service.DepartmentUiService;

@SuppressWarnings("serial")
public class DepartmentUiPanel extends JPanel implements ActionListener {
	
	private DepartmentUiService service;
	private JButton btnAdd;
	private JButton btnCancel;
	private DepartmentPanel pDepartment;
	private DepartmentTblPanel pDeptList;
	private DlgEmployee dialog;
	private int updateIdx;
	
	public DepartmentUiPanel() {
		service = new DepartmentUiService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pDepartment = new DepartmentPanel();
		pContent.add(pDepartment, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		add(pBtn);
		
		btnAdd = new JButton("추가");
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		JPanel pList = new JPanel();
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		pDeptList = new DepartmentTblPanel();
		pList.add(pDeptList, BorderLayout.CENTER);
		pDeptList.loadData(service.showDepartmentList());
		pDeptList.setPopupMenu(createPopupMenu());
		
		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	ActionListener myPopMenuListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().contentEquals("수정")) {
				pDepartment.setItem(pDeptList.getSelectedItem());
				updateIdx = pDeptList.getSelectedRowIdx();
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().contentEquals("삭제")) {
				JOptionPane.showMessageDialog(null, e.getActionCommand());
				service.deleteDept(pDeptList.getSelectedItem());
				pDeptList.removeRow();
			}
			if(e.getActionCommand().contentEquals("소속사원")) {
				Department selectedDept = pDeptList.getSelectedItem();
				List<Employee> list = service.showEmployeeGroupByDno(selectedDept);
				dialog.setEmpList(list);
				dialog.setTitle(selectedDept.getDeptName() + " 부서");
				
				dialog.setVisible(true);
//				JOptionPane.showMessageDialog(null, e.getActionCommand());
			}
		}
	};
	
	
	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu =  new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem showEmployee = new JMenuItem("소속사원");
		showEmployee.addActionListener(myPopMenuListener);
		popMenu.add(showEmployee);
		
		return popMenu;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().equals("수정")) {
				JOptionPane.showMessageDialog(null, e.getActionCommand());
				updateActionPerformed(e);
				return;
			}
			btnAddActionPerformed(e);			
		}
	}
	
	protected void updateActionPerformed(ActionEvent e) {
		Department item = pDepartment.getItem();
		pDeptList.updateRow(item, updateIdx);
		service.updateDept(item);
		btnAdd.setText("추가");
		pDepartment.clearTf();
	}
	
	protected void btnAddActionPerformed(ActionEvent e) {
		Department item = pDepartment.getItem();
		pDeptList.addItem(item);
		service.insertDept(item);
	}
	
	protected void btnCancelActionPerformed(ActionEvent e) {
		pDepartment.clearTf();
		btnAdd.setText("추가");
	}
}
