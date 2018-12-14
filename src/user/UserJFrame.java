package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import tcpserver.BookDTO;
import tcpserver.MemberDTO;
import tcpserver.TCPClient1;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;

import admin.BookSearch_admin;
import main.LibraryMain;
import main.Login;
import member.BookSearch_User;

public class UserJFrame extends JFrame implements ActionListener, MouseListener {
	private JTable table1, table2;
	JFileChooser fileOpen;
	FileNameExtensionFilter fileName;
	JButton btn1 = new JButton();
	JButton btn2, btn3, btnUpdate, btnDelete, btnLogout;
	JComboBox select;
	ImageIcon userIma;
	String[][] searchCon;
	int offset = btn1.getInsets().left;
	int count, searchCount;
	int comboxSelect = 0; // 콤보박스 선택 결과를 저장할 변수
	MemberDTO dto;
	BookSearch_User search;
	// User 로그인 프레임 설정

	public UserJFrame(String id) throws Exception {
		// 회원정보 가져오기
		dto = new TCPClient1().personalInfo(id);

		// 프레임 설정
		setTitle(dto.getName() + "회원님 도서 이용 정보");
		setSize(1350, 800);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 10, 1460, 741);
		panel.setLayout(null);
		getContentPane().add(panel);

		// 회원 대출 목록 테이블
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 224));
		panel_1.setBounds(12, 502, 633, 229);
		panel.add(panel_1);
		String[] columnNames = { "번호", "도서 이름", "대출일", "반납 예정일" };
		String[][] rowData = new String[count][4];
		table1 = new JTable(rowData, columnNames);
		table1.setColumnSelectionAllowed(true);
		table1.setCellSelectionEnabled(true);
		table1.setEnabled(false);
		table1.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(table1);
//		panel_1.add(scrollPane);

		JPanel panel_2 = new JPanel(null);
//		panel_2.setBackground(new Color(255, 255, 224));
		panel_2.setBounds(683, 33, 628, 698);
		search = new BookSearch_User();
		search.infoTable.addMouseListener(this);
		panel_2.add(search.panel());
		panel.add(panel_2);

		// 회원 사진 등록 버튼
		btn1.setBounds(12, 33, 200, 280);
		panel.add(btn1);
		btn1.setToolTipText("사진을 등록합니다.");

		// 로그인한 회원 아이디 출력
		JLabel idLab = new JLabel("ID");
		idLab.setBounds(271, 45, 39, 15);
		panel.add(idLab);
		idLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));

		JLabel idPrint = new JLabel(dto.getId());
		idPrint.setBounds(271, 70, 226, 15);
		panel.add(idPrint);
		idPrint.setFont(new Font("굴림", Font.PLAIN, 15));

		// 로그인한 주민번호를 통해 나이 및 성별을 계산
		String rrn = dto.getRrn();
		String userAge = age(rrn);
		String userGender = gender(rrn);

//		// 로그인한 계정에 사진 정보가 있을 경우 사진 출력
//		if (!(user.equals(null))) {
//			System.out.println(user[6]);
//			userIma = new ImageIcon(user[6]);
//			btn1.setIcon(imageSize(userIma, btn1.getWidth() - offset, btn1.getHeight() - offset));
//
//		} else {
//			btn1.setText("사진 등록");
//		}

		// 버튼(회원 정보 수정, 회원탈퇴, 로그아웃)
		btnLogout = new JButton("로그아웃");
		btnLogout.setBounds(12, 442, 200, 50);
		panel.add(btnLogout);

		btnDelete = new JButton("회원 탈퇴");
		btnDelete.setBounds(12, 383, 201, 50);
		panel.add(btnDelete);

		btnUpdate = new JButton("회원 정보 수정");
		btnUpdate.setBounds(12, 323, 200, 50);
		panel.add(btnUpdate);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(255, 255, 224));
		panel_3.setBounds(224, 33, 421, 459);
		panel.add(panel_3);

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(0, 0, 0, 0);
		panel_3.add(scrollPane_1);

		// 로그인한 회원 이름 출력
		JLabel nameLab = new JLabel("NAME");
		nameLab.setBounds(46, 80, 60, 15);
		panel_3.add(nameLab);
		nameLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));

		JLabel namePrint = new JLabel(dto.getName());
		namePrint.setBounds(46, 105, 226, 15);
		panel_3.add(namePrint);
		namePrint.setFont(new Font("굴림", Font.PLAIN, 15));

		// 로그인한 회원 나이 출력
		JLabel ageLab = new JLabel("AGE");
		ageLab.setBounds(46, 145, 77, 15);
		panel_3.add(ageLab);
		ageLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));

		JLabel agePrint = new JLabel(userAge);
		agePrint.setBounds(46, 170, 226, 15);
		panel_3.add(agePrint);
		agePrint.setFont(new Font("굴림", Font.PLAIN, 15));

		// 로그인한 회원 성별 출력
		JLabel genderLab = new JLabel("GENDER");
		genderLab.setBounds(46, 212, 77, 15);
		panel_3.add(genderLab);
		genderLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));

		JLabel lblDdd = new JLabel(userGender);
		lblDdd.setBounds(46, 237, 226, 15);
		panel_3.add(lblDdd);
		lblDdd.setFont(new Font("굴림", Font.PLAIN, 15));

		// 로그인한 회원 전화번호 출력
		JLabel telLab = new JLabel("TEL");
		telLab.setBounds(46, 275, 97, 15);
		panel_3.add(telLab);
		telLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));

		JLabel TelPrint = new JLabel(dto.getTel());
		TelPrint.setBounds(46, 300, 226, 15);
		panel_3.add(TelPrint);
		TelPrint.setFont(new Font("굴림", Font.PLAIN, 15));

		// 로그인한 회원 주소 출력
		JLabel addLab = new JLabel("ADDRESS");
		addLab.setBounds(46, 341, 97, 15);
		panel_3.add(addLab);
		addLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));

		JLabel addPrint = new JLabel(dto.getAddress());
		addPrint.setBounds(46, 366, 226, 15);
		panel_3.add(addPrint);
		addPrint.setFont(new Font("굴림", Font.PLAIN, 15));

		// 로그인한 회원 등급 출력
		JLabel gradeLab = new JLabel("GRADE");
		gradeLab.setBounds(46, 409, 77, 15);
		panel_3.add(gradeLab);
		gradeLab.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));

		JLabel gradePrint = new JLabel("일반 회원");
		gradePrint.setBounds(46, 434, 83, 15);
		panel_3.add(gradePrint);
		gradePrint.setFont(new Font("굴림", Font.PLAIN, 15));

		// 버튼 액션
		btn1.addActionListener(this);

		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnLogout.addActionListener(this);
		setVisible(true);
	}

	// 입력받은 주민번호를 계산하여 나이를 반환하는 메소드
	public String age(String userAge) {
		// 주민번호의 앞자리 2글자를 저장
		Calendar cal = Calendar.getInstance();
		int age = Integer.parseInt(userAge.substring(0, 2));

		// 년도 계산
		if (age > 18 && age < 100) {
			age += 1900;
		} else if (age <= 18) {
			age += 2000;
		}
		// 현재 년도를 저장
		int year = cal.get(Calendar.YEAR);

		// 나이 결과값을 String 타입의 result에 저장
		String result = String.valueOf(year - age + 1);
		return result;
	}

	// 입력받은 주민번호를 계산하여 성별을 반환하는 메소드
	public String gender(String userAge) {
		// 주민번호 뒷자리 저장
		String gender = userAge.substring(7, 8);
		String result = null;

		if (gender.equals("1") || gender.equals("3")) {
			result = "남성";
		} else if (gender.equals("2") || gender.equals("4")) {
			result = "여성";
		}

		return result;
	}

	// 회원 사진 크기 조절
	public Icon imageSize(ImageIcon icon, int width, int height) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Withdrawal withdrawal = null;
		if (e.getSource() == btn1) {
			// 사진 등록을 위한 JFileChooser 객체 생성
			fileOpen = new JFileChooser();
			// 필터링 확장자 지정
			fileName = new FileNameExtensionFilter("jpg", "jpg");
			// 확장자 추가
			fileOpen.addChoosableFileFilter(fileName);
			fileOpen.showOpenDialog(btn1);
			// 회원 사진 저장
			ImageIcon userIma = new ImageIcon(fileOpen.getSelectedFile().toString());
			String imageName = fileOpen.getSelectedFile().toString();

			System.out.println(imageName);
			int offset = btn1.getInsets().left;
			btn1.setIcon(imageSize(userIma, btn1.getWidth() - offset, btn1.getHeight() - offset));

			String result = new TCPClient1().userImage(dto.getId() + "\n" + imageName);
			System.out.println(result);

			// 회원 정보 수정
		} else if (e.getSource() == btnUpdate) {
			new UserUpdateJFrame(dto);
			// 회원 탈퇴
		} else if (e.getSource() == btnDelete) {
			withdrawal = new Withdrawal(dto);
			withdrawal.btnNewButton.addActionListener(this);
			// 로그 아웃
		} else if (e.getSource() == btnLogout) {
			new Login();
			dispose();

		} else if (e.getActionCommand().equals("회원탈퇴")) {
			dispose();
			new Login();
		}
	}

	public static void main(String[] args) throws Exception {
		new UserJFrame("aaa");
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
			int row = search.infoTable.getSelectedRow();
			String title = (String) search.infoTable.getValueAt(row, 1);
			for (int i = 0; i < search.bookInfo.size(); i++) {
				BookDTO dto = (BookDTO) search.bookInfo.get(i);
				if (dto.getTitle().equals(title)) {
					if (dto.getRent().equals("Y")) {
						String[] buttons = { "대출 예약", "취소" };
						int c = JOptionPane.showOptionDialog(null, title, "제목", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
						System.out.println(c);
						if (c == 1) {

						}

					}
				}
			}//for
		}//if

	}//mouseReleased
}//class
