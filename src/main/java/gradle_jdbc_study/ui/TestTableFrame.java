package gradle_jdbc_study.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import gradle_jdbc_study.dao.impl.TitleDaoImpl;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.list.TitleTblPanel;

public class TestTableFrame {
	
	private static TitleTblPanel titleP;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		titleP = new TitleTblPanel();
		frame.add(titleP);
		List<Title> list = TitleDaoImpl.getInstance().selectTitleByAll();
		titleP.loadData(list);
		frame.setVisible(true);
		
		titleP.setPopupMenu(createPopupMenu());
		frame.setVisible(true);
	}
	static ActionListener myPopMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().contentEquals("수정")) {
				Title updateTitle = new Title(1, "이사");
				titleP.updateRow(updateTitle, titleP.getSelectedRowIdx());
			}
			if(e.getActionCommand().contentEquals("삭제")) {
				Title selectTitle = titleP.getSelectedItem();
				JOptionPane.showMessageDialog(null, selectTitle);
				
				titleP.removeRow();
				
				Title insTitle = new Title(5, "인턴");
				titleP.addItem(insTitle);
			}
			if(e.getActionCommand().contentEquals("소속 사원")) {
				JOptionPane.showConfirmDialog(null, e.getActionCommand());
			}
		}
	};
	
	
	private static JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem showEmployee = new JMenuItem("소속 사원");
		showEmployee.addActionListener(myPopMenuListener);
		popMenu.add(showEmployee);
		
		return popMenu;
	}
}
