package gradle_jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.ui.content.EmployeePanel;
import gradle_jdbc_study.ui.exception.InvalidCheckException;
import gradle_jdbc_study.ui.list.EmployeeTblPanel;
import gradle_jdbc_study.ui.service.EmployeeUiService;
import gradle_jdbc_study.util.LogUtil;

@SuppressWarnings("serial")
public class EmployeeUiPanel extends JPanel implements ActionListener {
	
	private EmployeeUiService service;
	private EmployeePanel pEmployee;
	private EmployeeTblPanel pEmployeeList;
	
	private JButton btnAdd;
	private JButton btnCancel;
	
	public EmployeeUiPanel() {
		service = new EmployeeUiService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pEmployee = new EmployeePanel();
		pEmployee.setService(service);
		pContent.add(pEmployee, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		add(pBtn);
		
		btnAdd = new JButton("추가");
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		JPanel pList = new JPanel();
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		pEmployeeList = new EmployeeTblPanel();
		pEmployeeList.loadData(service.showEmployeeList());
		pEmployeeList.setPopupMenu(createPopupMenu());
		pList.add(pEmployeeList, BorderLayout.CENTER);
		
		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu =  new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
	
	ActionListener myPopMenuListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().contentEquals("수정")) {
				Employee upEmp = pEmployeeList.getSelectedItem();
				pEmployee.setItem(upEmp);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().contentEquals("삭제")) {
				Employee delEmp = pEmployeeList.getSelectedItem();
				service.removeEmployee(delEmp);
				pEmployeeList.removeRow();
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
		}
	};
	
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("수정")) {
//				JOptionPane.showMessageDialog(null, e.getActionCommand());
				btnUpdateActionPerformed(e);
			} else {				
				btnAddActionPerformed(e);			
			}
		}
	}
	
	protected void btnUpdateActionPerformed(ActionEvent e) {
		Employee upEmp = pEmployee.getItem();
		service.modifyEmployee(upEmp);
		
		pEmployeeList.updateRow(upEmp, pEmployeeList.getSelectedRowIdx());
		
		btnAdd.setText("추가");
		pEmployee.clearTf();
		JOptionPane.showMessageDialog(null, "부서가 수정되었습니다.");
	}
	
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Employee newEmp = pEmployee.getItem(); //공백이면 예외발생 -> InvalidCheckException
			LogUtil.prnLog(newEmp.toDebug());
			pEmployeeList.addItem(newEmp);
			service.addEmployee(newEmp);
			pEmployee.clearTf();
			JOptionPane.showMessageDialog(null, String.format("%s(%d) 추가되었습니다.", newEmp.getEmpName(), newEmp.getEmpNo()));
		} catch(InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			SQLException e2 = (SQLException) e1;
			if(e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "부서번호 중복");
				System.err.println(e2.getMessage());
				return;
			}
			e1.printStackTrace();
		}
		
	}
	
	protected void btnCancelActionPerformed(ActionEvent e) {
		if(btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
		pEmployee.clearTf();
	}
}
