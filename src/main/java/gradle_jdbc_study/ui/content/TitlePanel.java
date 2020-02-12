package gradle_jdbc_study.ui.content;

import java.awt.GridLayout;

import javax.activity.InvalidActivityException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class TitlePanel extends AbsItemPanel<Title> {
	private JTextField tfName;
	private JTextField tfNo;

	public TitlePanel() {

		initialize();
	}
	
	private void initialize() {
		setBorder(new TitledBorder(null, "\uC9C1\uCC45\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setText("");
		add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("직책명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);
		
		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);
	}

	@Override
	public Title getItem() {
		validCheck();
		int titleNo = Integer.parseInt(tfNo.getText().trim());
		String titleName = tfName.getText().trim();
		return new Title(titleNo, titleName);
	}

	@Override
	public void setItem(Title item) {
		tfNo.setText(item.getTitleNo()+"");
		tfName.setText(item.getTitleName());
	}

	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
	}

	@Override
	public void validCheck() {
		if(tfNo.getText().contentEquals("") || tfName.getText().contentEquals("")) {
			throw new InvalidCheckException();
		}
	}

}
