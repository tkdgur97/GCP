package admin;

import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tcpserver.BookDTO;
import tcpserver.TCPClient1;
import user.UserJFrame;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class BookSearch_admin implements ActionListener, MouseListener {
	JPanel jp;
	JButton searchButton;
	JComboBox combo;
	JTextField searchText;
	ArrayList bookInfo;
	JScrollPane scrollPane;
	JPanel panel;
	JTable infoTable;

	public BookSearch_admin() throws Exception {
		// 패널만들기
		jp = new JPanel();
		jp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jp.setLayout(null);

		// 검색 메뉴 만들기 combo
		String[] searchMenu = { "책 제목", "저자" };
		combo = new JComboBox(searchMenu);
		combo.setBounds(12, 6, 82, 21);
		jp.add(combo);

		// 입력 받는 부분 만들기 jtext
		searchText = new JTextField(15);
		searchText.setBounds(106, 6, 225, 21);
		jp.add(searchText);

		// 검색 버튼 만들기
		searchButton = new JButton("검색");
		searchButton.setBounds(343, 5, 71, 23);
		jp.add(searchButton);

		// 대출 가능한 책만 보이게 하는 체크박스 만들기
		JCheckBox chckbxNewCheckBox = new JCheckBox("대출 가능한 책만");
		chckbxNewCheckBox.setBounds(422, 5, 125, 23);
		jp.add(chckbxNewCheckBox);

		// DB에서 책정보 가져오기
		bookInfo = new TCPClient1().getBookInfo();

		// 표 만들기 jtable
		String[] column = { "일련번호", "제목", "저자명", "발행처", "발행년도", "청구기호" };
		String[][] row = new String[bookInfo.size()][column.length];

		// row값에 데이터 입력
		for (int i = 0; i < row.length; i++) {
			BookDTO dto = (BookDTO) bookInfo.get(i);
			row[i][0] = dto.getNumber();
			row[i][1] = dto.getTitle();
			row[i][2] = dto.getAuthor();
			row[i][3] = dto.getPublisher();
			row[i][4] = dto.getYear();
			row[i][5] = dto.getBill();
		}
		jp.setBounds(12, 10, 582, 506);
		searchButton.addActionListener(this);

		panel = new JPanel();
		panel.setBounds(12, 37, 558, 456);
		jp.add(panel);
		panel.setLayout(null);

		// 테이블에 데이터 입력
		DefaultTableModel dtm = new DefaultTableModel(row, column) { // 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int a, int column) {
				return false;
			}
		};

		infoTable = new JTable(dtm);
		// 스크롤 pane에 테이블 추가
		scrollPane = new JScrollPane(infoTable);
		scrollPane.setBounds(0, 0, 558, 456);
		panel.add(scrollPane);
		infoTable.addMouseListener(this);
		infoTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
//		infoTable.getTableHeader().setResizingAllowed(false); //크기 조절 불가

	}

	public JPanel panel() {
		return jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == searchButton) {
			String select = combo.getSelectedItem().toString();
			panel.removeAll();
			panel.setBounds(12, 37, 525, 456);
			if (select.equals("책 제목")) {
				ArrayList list = new ArrayList();
				for (int i = 0; i < bookInfo.size(); i++) {
					BookDTO dto = (BookDTO) bookInfo.get(i);
					if (dto.getTitle().indexOf(searchText.getText()) != -1) {

						list.add(dto);
					}

				} // for

				String[] column = { "일련번호", "제목", "저자명", "발행처", "발행년도", "청구기호" };

				// list에 있는 dto를 스트링 배열에 저장
				String[][] row2 = new String[list.size()][column.length];
				for (int i = 0; i < list.size(); i++) {
					BookDTO dto = (BookDTO) list.get(i);
					row2[i][0] = dto.getNumber();
					row2[i][1] = dto.getTitle();
					row2[i][2] = dto.getAuthor();
					row2[i][3] = dto.getPublisher();
					row2[i][4] = dto.getYear();
					row2[i][5] = dto.getBill();
				}
				DefaultTableModel dtm = new DefaultTableModel(row2, column) { // 셀 수정 못하게 하는 부분
					public boolean isCellEditable(int a, int column) {
						return false;
					}
				};
				infoTable = new JTable(dtm);
				scrollPane = new JScrollPane(infoTable);
				infoTable.addMouseListener(this);

				scrollPane.setBounds(0, 0, 525, 456);
				panel.add(scrollPane);
				infoTable.setDragEnabled(false);
			} // if

			else if (select.equals("저자")) {
				ArrayList list = new ArrayList();
				for (int i = 0; i < bookInfo.size(); i++) {
					BookDTO dto = (BookDTO) bookInfo.get(i);
					if (dto.getAuthor().indexOf(searchText.getText()) != -1) {

						list.add(dto);
					}

				} // for

				String[] column = { "일련번호", "제목", "저자명", "발행처", "발행년도", "청구기호" };

				// list에 있는 dto를 스트링 배열에 저장
				String[][] row2 = new String[list.size()][column.length];
				for (int i = 0; i < list.size(); i++) {
					BookDTO dto = (BookDTO) list.get(i);
					row2[i][0] = dto.getNumber();
					row2[i][1] = dto.getTitle();
					row2[i][2] = dto.getAuthor();
					row2[i][3] = dto.getPublisher();
					row2[i][4] = dto.getYear();
					row2[i][5] = dto.getBill();
				}
				DefaultTableModel dtm = new DefaultTableModel(row2, column) { // 셀 수정 못하게 하는 부분
					public boolean isCellEditable(int a, int column) {
						return false;
					}
				};
				infoTable = new JTable(dtm);
				scrollPane = new JScrollPane(infoTable);
				infoTable.addMouseListener(this);

				scrollPane.setBounds(0, 0, 525, 456);
				panel.add(scrollPane);
				infoTable.setDragEnabled(false);
			}

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int a = infoTable.getSelectedRow();

			for (int i = 0; i < bookInfo.size(); i++) {
				BookDTO dto = (BookDTO) bookInfo.get(i);
				if (dto.getTitle().equals(infoTable.getValueAt(a, 1))) {
					if (dto.getRent().equals("Y")) {
						String[] buttons = { "책 정보 수정", "대출 처리", "취소" };
						int c = JOptionPane.showOptionDialog(null, "내용", "제목", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
						System.out.println(c);

					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		JFrame jf = new JFrame();

		BookSearch_admin a = new BookSearch_admin();
		jf.getContentPane().setLayout(null);
		jf.getContentPane().add(a.panel());
		jf.setVisible(true);
		jf.setSize(630, 608);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

}
