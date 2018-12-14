package admin;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import tcpserver.TCPClient1;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;

public class BookRegister_admin2 extends JFrame implements ActionListener {

	private JTextField numberField;
	private JTextField nameField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel jlb = new JLabel(" ");
	JComboBox combo;
	JButton fileButton;
	JLabel picture;
	private JFileChooser jfc = new JFileChooser();
	JButton button;
	JPanel jp;

	public BookRegister_admin2() {
		jp = new JPanel();
		jp.setBounds(0, 10, 502, 506);
		jp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jp.setBackground(SystemColor.control);
		// 도서 일련번호 입력
		JLabel bookNumber = new JLabel("도서 일련번호");
		bookNumber.setBounds(12, 51, 84, 15);

		numberField = new JTextField();
		numberField.setBounds(108, 48, 381, 21);
		jp.add(numberField);
		numberField.setColumns(10);

		// 도서 이름 입력
		JLabel bookName = new JLabel("도서 이름");
		bookName.setBounds(12, 76, 84, 15);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(108, 73, 381, 21);
		jp.add(nameField);

		// 도서 분류
		JLabel bookClassification = new JLabel("도서 분류");
		bookClassification.setBounds(12, 101, 84, 15);
		String[] classification = { "총류", "철학", "종교", "사회학", "언어", "자연과학", "기술과학", "예술", "문학", "역사" };
		combo = new JComboBox(classification);
		combo.setBounds(108, 98, 381, 23);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(108, 123, 381, 21);
		jp.add(textField_3);

		// 도서 출판년도
		JLabel bookYear = new JLabel("도서 출판 년도");
		bookYear.setBounds(12, 151, 84, 15);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(108, 148, 381, 21);

		// 도서 사진 및 파일첨부
		JLabel bookPicture = new JLabel("도서 사진");
		bookPicture.setBounds(12, 176, 84, 15);
		jlb.setBounds(108, 202, 209, 51);
		jp.add(jlb);

		fileButton = new JButton("파일 첨부");
		fileButton.setBounds(108, 172, 97, 23);
		jp.add(fileButton);
		fileButton.addActionListener(this);
		jfc.setFileFilter(new FileNameExtensionFilter("jpg", "jpg")); // 필터
		jfc.setMultiSelectionEnabled(false);// 다중 선택 불가
		picture = new JLabel("사진");
		picture.setBounds(22, 202, 97, 153);

//		picture.setIcon(new ImageIcon("3.jpg"));
		jp.add(picture);

		// JFrame에 추가 및 설정
		jp.setLayout(null);
		jp.add(bookNumber);
		jp.add(bookName);
		jp.add(bookClassification);
		jp.add(bookYear);

		// 도서 저자
		JLabel bookAuthor = new JLabel("도서 저자");
		bookAuthor.setBounds(12, 126, 84, 15);
		jp.add(bookAuthor);
		jp.add(bookPicture);
		jp.add(combo);
		jp.add(textField_4);

		button = new JButton("도서 등록하기");
		button.setBounds(108, 368, 121, 23);
		button.addActionListener(this);
		jp.add(button);
		
		JLabel label = new JLabel("책 등록");
		label.setBounds(219, 10, 57, 15);
		jp.add(label);

	}
	static int c;
	public void actionPerformed(ActionEvent e) {
		c = 1 ;
		// TODO Auto-generated method stub
		if (e.getSource() == fileButton) {
			if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				ImageIcon icon = new ImageIcon(jfc.getSelectedFile().toString());
				Image image = icon.getImage(); // ImageIcon을 Image로 변환.
				image = image.getScaledInstance(97, 153, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(image); // Image로 ImageIcon 생성

				jlb.setText("열기 경로 : " + jfc.getSelectedFile().toString());
				picture.setIcon(icon);
			}
		}

		if (e.getSource() == button) {
			numberField.getText();
			nameField.getText();
			String kind = (String) combo.getSelectedItem();
			textField_3.getText();
			textField_4.getText();
			String path = jfc.getSelectedFile().toString();
			String name = (path.substring(path.lastIndexOf("\\") + 1)); // 사진 이름

//			String result =
			new TCPClient1().bookRegist("bookRegist\n" + numberField.getText() + "\n" + nameField.getText() + "\n"
					+ (String) combo.getSelectedItem() + "\n" + textField_3.getText() + "\n" + textField_4.getText()
					+ "\n" + name, path);
		}

		
	}

	
}
