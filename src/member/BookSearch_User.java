package member;

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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class BookSearch_User implements ActionListener{
	private JPanel jp;
	private JButton searchButton;
	JComboBox combo;
	JTextField searchText;
	public ArrayList bookInfo;
	JScrollPane scrollPane;
	JPanel panel;
	public JTable infoTable;

	public BookSearch_User() throws Exception {
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
		chckbxNewCheckBox.setBounds(422, 5, 139, 23);
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
		jp.setBounds(12, 10, 600, 670);
		searchButton.addActionListener(this);

		panel = new JPanel();
		panel.setBounds(12, 37, 580, 620);
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
		scrollPane.setBounds(0, 0, 580, 620);
		panel.add(scrollPane);

		infoTable.getTableHeader().setReorderingAllowed(false); // 이동 불가
//		infoTable.getTableHeader().setResizingAllowed(false); //크기 조절 불가

	}

	public JPanel panel() {
		return jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/*
		 * int row = table.getSelectedRow(); int col = table.getSelectedColumn(); Object
		 * value = table.getValueAt(row, col);
		 */

		panel.removeAll();
		panel.setBounds(12, 37, 580, 620);
		if (e.getSource() == searchButton) {
			String select = combo.getSelectedItem().toString();
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


				scrollPane.setBounds(0, 0, 580, 670);
				panel.add(scrollPane);
				infoTable.setDragEnabled(false);
			} // if

			else if (select.equals("저자")) {

			}

		}

	}

	public static void main(String[] args) throws Exception {
		JFrame jf = new JFrame();

		BookSearch_User a = new BookSearch_User();
		jf.getContentPane().setLayout(null);
		jf.getContentPane().add(a.panel());
		jf.setVisible(true);
		jf.setSize(622, 607);

	}

}
