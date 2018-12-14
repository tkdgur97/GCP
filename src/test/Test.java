package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Test extends JFrame implements ActionListener {

	private JFileChooser jfc = new JFileChooser();
	private JButton jbt_open = new JButton("열기");
	private JButton jbt_save = new JButton("저장");
	private JLabel jlb = new JLabel(" ");

	public Test() {

		getContentPane().setLayout(new FlowLayout());
		add(jbt_open);
		add(jbt_save);
		add(jlb);
		
		jbt_open.addActionListener(this);
		jbt_save.addActionListener(this);
		jfc.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
		
		// 파일 필터
		jfc.setMultiSelectionEnabled(false);// 다중 선택 불가
		this.setSize(400, 200);
		this.setVisible(true);

	}

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jbt_open) {
			if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				jlb.setText("열기 경로 : " + jfc.getSelectedFile().toString());
			}
		} else if (e.getSource() == jbt_save) {
			if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
				jlb.setText("저장 경로 : " + jfc.getSelectedFile().toString() + "." + jfc.getFileFilter().getDescription());
			}
		}
	}

	public static void main(String[] args) {
		Test tf = new Test();
	}

}
