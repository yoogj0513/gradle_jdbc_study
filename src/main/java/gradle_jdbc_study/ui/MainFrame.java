package gradle_jdbc_study.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.content.EmployeePanel;
import gradle_jdbc_study.ui.service.EmployeeUiService;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private LoginFrame loginFrame;
	private JButton btnLogOut;
	private JLabel lblLoginName;
	private JPanel pTop;
//	private JLabel lblNewLabel;
//	private JLabel lblNewLabel_1;
//	private JButton btnNewButton;
	private JPanel panel;
	private JButton btnTitle;
	private JButton btnDepartment;
	private JButton btnEmployee;

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		pTop = new JPanel();
		contentPane.add(pTop);

		lblLoginName = new JLabel("New label");
		lblLoginName.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblLoginName.setHorizontalAlignment(SwingConstants.CENTER);
		pTop.add(lblLoginName);

		btnLogOut = new JButton("로그아웃");
		btnLogOut.addActionListener(this);
		pTop.add(btnLogOut);

		loginNameRefresh();

		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 3, 5, 5));

		btnTitle = new JButton("직책");
		btnTitle.addActionListener(this);
		panel.add(btnTitle);

		btnDepartment = new JButton("부서 정보");
		btnDepartment.addActionListener(this);
		panel.add(btnDepartment);

		btnEmployee = new JButton("사원 정보");
		btnEmployee.addActionListener(this);
		panel.add(btnEmployee);
	}

	// 로그인 시 로그인 회원 이름 다시 받기
	public void loginNameRefresh() {
		lblLoginName.setText(LoginFrame.loginEmp.getEmpName());
	}

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmployee) {
			btnEmployeeActionPerformed(e);
		}
		if (e.getSource() == btnTitle) {
			btnTitleActionPerformed(e);
		}
		if (e.getSource() == btnDepartment) {
			btnDepartmentActionPerformed(e);
		}
		if (e.getSource() == btnLogOut) {
			btnLogOutActionPerformed(e);
		}
	}

	protected void btnLogOutActionPerformed(ActionEvent e) {
		LoginFrame.loginEmp = null;
		dispose();
		loginFrame.setVisible(true);
		loginFrame.clearTf();
	}

	protected void btnDepartmentActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		DepartmentUiPanel tp = new DepartmentUiPanel();
		frame.getContentPane().add(tp);
		frame.setVisible(true);
	}

	protected void btnTitleActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		TitleUiPanel tp = new TitleUiPanel();
		frame.getContentPane().add(tp);
		frame.setVisible(true);
	}
	protected void btnEmployeeActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		EmployeeUiService service = new EmployeeUiService();
		List<Department> list = service.showDeptList();
		EmployeePanel tp = new EmployeePanel();
		tp.setCmbDeptList(list);
		tp.getCmbDept().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					JOptionPane.showMessageDialog(null, e.getItem());
					tp.setCmbManagerList(service.showManagerList((Department)e.getItem()));
				}
			}
		});
		List<Title> titList = service.showTitleList();
		tp.setCmbTitleList(titList);
//		tp.setService(service);
		frame.add(tp);
		frame.setVisible(true);
	}
}
