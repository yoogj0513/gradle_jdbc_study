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

import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.content.TitlePanel;
import gradle_jdbc_study.ui.exception.InvalidCheckException;
import gradle_jdbc_study.ui.list.TitleTblPanel;
import gradle_jdbc_study.ui.service.TitleUiService;

@SuppressWarnings("serial")
public class TitleUiPanel extends JPanel implements ActionListener {
	private JButton btnAdd;
	private JButton btnCancel;
	private TitleUiService service;
//	private DlgEmployee dialog;
	
	private TitlePanel pTitle;
	private TitleTblPanel pTitleList;
	

	public TitleUiPanel() {
		service = new TitleUiService();
//		dialog = new DlgEmployee();
		initialize();
	}

	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));

		pTitle = new TitlePanel();
		pContent.add(pTitle, BorderLayout.CENTER);

		JPanel pBtn = new JPanel();
		add(pBtn);

		btnAdd = new JButton("추가");
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		pBtn.add(btnCancel);

		JPanel pList = new JPanel();
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));

		pTitleList = new TitleTblPanel();
		pList.add(pTitleList, BorderLayout.CENTER);
		pTitleList.loadData(service.showTitleList());
		pTitleList.setPopupMenu(createPopupMenu());

		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);
		
//		JMenuItem showEmployee = new JMenuItem("소속직원");
//		showEmployee.addActionListener(myPopMenuListener);
//		popMenu.add(showEmployee);
		
		return popMenu;
	}
	
	ActionListener myPopMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().contentEquals("수정")) {
				Title item = pTitleList.getSelectedItem();
				pTitle.setItem(item);
				btnAdd.setText("수정");
			}
			
			if(e.getActionCommand().contentEquals("삭제")) {
				Title item = pTitleList.getSelectedItem();
				service.removeTitleItem(item);
				pTitleList.removeRow();
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
			
//			if(e.getActionCommand().contentEquals("소속직원")) {
//				Title selectedTitle = pTitleList.getSelectedItem();
//				List<Employee> list = service.showEmployeeGroupByTitle(selectedTitle);
//				dialog.setEmpList(list);
//				dialog.setTitle(selectedTitle.getTitleName() + " 부서");
//				
//				dialog.setVisible(true);
//			}
			
		}
	};
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("수정")) {
				btnUpdateActionPerformed(e);
				return;
			}
			btnAddActionPerformed(e);
		}
	}

	private void btnUpdateActionPerformed(ActionEvent e) {
		Title item = pTitle.getItem();
		pTitleList.updateRow(item, pTitleList.getSelectedRowIdx());
		service.updateTitleItem(item);
		btnAdd.setText("추가");
		pTitle.clearTf();
		JOptionPane.showMessageDialog(null, "직책이 수정되었습니다.");
	}

	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Title item = pTitle.getItem();
			pTitleList.addItem(item);
			service.addTitleItem(item);
			pTitle.clearTf();
			JOptionPane.showMessageDialog(null, "직책이 추가되었습니다");
		} catch(InvalidCheckException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		} catch (Exception e1) {
			SQLException e2 = (SQLException) e1;
			if(e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "직책번호 중복");
				System.err.println(e2.getMessage());
				return;
			}
			e1.printStackTrace();
		}
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		pTitle.clearTf();
		btnAdd.setText("추가");
	}
}
