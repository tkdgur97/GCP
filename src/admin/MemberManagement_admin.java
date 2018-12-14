package admin;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import tcpserver.BookDTO;
import tcpserver.MemberDTO;
import tcpserver.TCPClient1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class MemberManagement_admin extends JFrame implements ActionListener, MouseListener {

	JComboBox combo;
	JTextField searchText;
	JButton searchButton;
	JTable infoTable;
	ArrayList memberInfo;
	JPanel scrollBasic;
	JScrollPane js;
	JPanel jp;

	public JPanel jp() {

		return jp;
	}

	public MemberManagement_admin() throws Exception {
		jp = new JPanel();
		jp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		// 검색 메뉴 만들기 combo
		String[] searchMenu = { "아이디", "이름" };
		jp.setLayout(null);
		combo = new JComboBox(searchMenu);
		combo.setBounds(397, 7, 76, 21);
		jp.add(combo);

		// 입력 받는 부분 만들기 jtext
		searchText = new JTextField(15);
		searchText.setBounds(478, 7, 171, 21);
		jp.add(searchText);

		// 검색 버튼 만들기
		searchButton = new JButton("검색");
		searchButton.setBounds(654, 6, 76, 23);
		jp.add(searchButton);
		searchButton.addActionListener(this);
		memberInfo = new TCPClient1().allMemberInfo();
		// 표 만들기
		String[] column = { "아이디", "이름", "전화번호", "주소", "현재 대출 권수 ", "대출 횟 수 ", "연체 횟 수", "주민 번호" };
		Object row[][] = new Object[memberInfo.size()][column.length];
		for (int i = 0; i < row.length; i++) {
			MemberDTO dto = (MemberDTO) memberInfo.get(i);
			row[i][0] = dto.getId();
			row[i][1] = dto.getName();
			row[i][2] = dto.getTel();
			row[i][3] = dto.getAddress();
			row[i][4] = dto.getBookrentcurrent();
			row[i][5] = dto.getBookrentcumlative();
			row[i][6] = dto.getBooklate();
			row[i][7] = dto.getRrn();

		}

		DefaultTableModel dtm = new DefaultTableModel(row, column) { // 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int a, int column) {
				return false;
			}
		};
		infoTable = new JTable(dtm);
		// 스크롤 pane에 테이블 추가
		js = new JScrollPane(infoTable);
		infoTable.addMouseListener(this);
		infoTable.getTableHeader().setReorderingAllowed(false); // 이동 불가

		// JFrame에 추가 및 설정
		scrollBasic = new JPanel();
		scrollBasic.setBounds(12, 39, 1149, 439);
		scrollBasic.setLayout(null);
		scrollBasic.add(js);
		jp.setBounds(0, 0, 1173, 505);
		jp.add(scrollBasic);

		js.setBounds(0, 0, 1122, 439);
	}

	//
	public void refresh() throws Exception {
		memberInfo = new TCPClient1().allMemberInfo();
		scrollBasic.removeAll();
		String select = combo.getSelectedItem().toString();
		if (select.equals("아이디")) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < memberInfo.size(); i++) {
				MemberDTO dto = (MemberDTO) memberInfo.get(i);
				if (dto.getId().indexOf(searchText.getText()) != -1) {

					list.add(dto);
				}

			} // for

			String[] column = { "아이디", "이름", "전화번호", "주소", "현재 대출 권수 ", "대출 횟 수 ", "연체 횟 수", "주민 번호" };

			// list에 있는 dto를 스트링 배열에 저장
			String[][] row2 = new String[list.size()][column.length];
			for (int i = 0; i < list.size(); i++) {
				MemberDTO dto = (MemberDTO) list.get(i);
				row2[i][0] = dto.getId();
				row2[i][1] = dto.getName();
				row2[i][2] = dto.getTel();
				row2[i][3] = dto.getAddress();
				row2[i][4] = dto.getBookrentcurrent();
				row2[i][5] = dto.getBookrentcumlative();
				row2[i][6] = dto.getBooklate();
				row2[i][7] = dto.getRrn();

			}
			DefaultTableModel dtm = new DefaultTableModel(row2, column) { // 셀 수정 못하게 하는 부분
				public boolean isCellEditable(int a, int column) {
					return false;
				}
			};
			infoTable = new JTable(dtm);
			infoTable.addMouseListener(this);
			js = new JScrollPane(infoTable);

			scrollBasic.setLayout(null);
			scrollBasic.add(js);
			js.setBounds(0, 0, 1122, 439);

			infoTable.setDragEnabled(false);
		} // if

		else if (select.equals("이름")) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < memberInfo.size(); i++) {
				MemberDTO dto = (MemberDTO) memberInfo.get(i);
				if (dto.getName().indexOf(searchText.getText()) != -1) {

					list.add(dto);
				}

			} // for

			String[] column = { "아이디", "이름", "전화번호", "주소", "현재 대출 권수 ", "대출 횟 수 ", "연체 횟 수", "주민 번호" };

			// list에 있는 dto를 스트링 배열에 저장
			String[][] row2 = new String[list.size()][column.length];
			for (int i = 0; i < list.size(); i++) {
				MemberDTO dto = (MemberDTO) list.get(i);
				row2[i][0] = dto.getId();
				row2[i][1] = dto.getName();
				row2[i][2] = dto.getTel();
				row2[i][3] = dto.getAddress();
				row2[i][4] = dto.getBookrentcurrent();
				row2[i][5] = dto.getBookrentcumlative();
				row2[i][6] = dto.getBooklate();
				row2[i][7] = dto.getRrn();

			}
			DefaultTableModel dtm = new DefaultTableModel(row2, column) { // 셀 수정 못하게 하는 부분
				public boolean isCellEditable(int a, int column) {
					return false;
				}
			};
			infoTable = new JTable(dtm);
			infoTable.addMouseListener(this);
			js = new JScrollPane(infoTable);

			scrollBasic.setLayout(null);
			scrollBasic.add(js);
			js.setBounds(0, 0, 1122, 439);

			infoTable.setDragEnabled(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {

			try {
				refresh();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getActionCommand().equals("check")) {
			try {

				refresh();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {

			int a = infoTable.getSelectedRow();
			for (int i = 0; i < memberInfo.size(); i++) {
				MemberDTO dto = (MemberDTO) memberInfo.get(i);

				if (dto.getId().equals(infoTable.getValueAt(a, 0))) {

					String[] buttons = { "회원 정보 수정", "탈퇴 처리", "취소" };
					int c = JOptionPane.showOptionDialog(null, "아이디 : " + infoTable.getValueAt(a, 0), "회원 정보 수정",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");

					// 회원 정보 수정
					if (c == 0) {
						String id = infoTable.getValueAt(a, 0).toString();
						try {
							dto = new TCPClient1().personalInfo(id);
							MemberUpdate_admin update = new MemberUpdate_admin(dto);
							update.btnCheck.addActionListener(this);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// 탈퇴 처리
					} else if (c == 1) {
						
						
						
						// 취소
					} else if (c == 2) {

					}
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		JFrame jf = new JFrame();
		MemberManagement_admin a = new MemberManagement_admin();

		jf.setTitle("회원 정보(관리자)");
		jf.getContentPane().add(a.jp());
		jf.setResizable(true);
		jf.setVisible(true);
		jf.setBounds(300, 200, 1200, 700);

	}

}
