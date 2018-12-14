package admin;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import tcpserver.TCPClient1;
import test.BarChart;
import test.Test11;
import test.Test4;

import javax.swing.JComboBox;

//	관리자 화면

public class AdminJFrame extends JFrame implements ActionListener {
	private JTextField idField;
	private JTextField numField;
	private JTextField idField1;
	private JTextField numField1;

	public AdminJFrame() throws Exception {
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("도서 관리 프로그램");
		setSize(1263, 1029);
		getContentPane().setLayout(null);

		// 탭 추가
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 39, 1223, 941);
		getContentPane().add(tabbedPane);

		// 홈 탭 설정
				JPanel home = new JPanel();
				home.setLayout(null);
				home.setBounds(10, 10, 380, 530);
				tabbedPane.add("홈", home);
		
		// 회원 탭 설정
		JPanel member = new JPanel();
		member.setLayout(null);
		member.setBounds(10, 10, 380, 530);

		// 책 관리 탭 설정
		JPanel bookManage = new JPanel();

		bookManage.setLayout(null);
		bookManage.setBounds(10, 10, 380, 530);
		tabbedPane.add("책 관리", bookManage);

		// 관리 탭 설정

		JPanel panel_1 = new JPanel();

		panel_1.setLayout(null);
		panel_1.setBounds(12, 10, 522, 522);
		BookRegister_admin2 a = new BookRegister_admin2();
		panel_1.add(a.jp);

		bookManage.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(546, 10, 645, 522);
		panel_2.add(new BookSearch_admin().panel());
		bookManage.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(94, 542, 546, 308);
		panel_3.setLayout(null);
		bookManage.add(panel_3);

		// tabbedPane에 대출 탭 설정
		tabbedPane.add("회원관리", member);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		JPanel memberPanel = new MemberManagement_admin().jp();
		panel_4.add(memberPanel);
		panel_4.setBounds(32, 31, 1146, 518);
		member.add(panel_4);

		

		// 홈 탭 설정

		JLabel lblNewLabel = new JLabel("도서 현황");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(32, 10, 349, 52);
		home.add(lblNewLabel);

		JLabel label_1 = new JLabel("도서관 회원 비율");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(32, 466, 349, 52);
		home.add(label_1);

		JPanel panel = new JPanel();
		panel.add(new BookChart_admin("").chart());
		panel.setBounds(32, 50, 501, 406);
		home.add(panel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(32, 514, 447, 258);
		panel_5.add(new Test4().jpanel());
		home.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(510, 514, 431, 258);
		panel_6.add(new MemberGender_admin2().panel());
		home.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(564, 50, 599, 406);
		Test11 t = new Test11();
		panel_7.add(t.jp());
		home.add(panel_7);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(12, 5, 1207, 31);
		getContentPane().add(lblNewLabel_1);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	public static void main(String[] args) throws Exception {
		new AdminJFrame();

	}
}
