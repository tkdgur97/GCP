package user;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import tcpserver.TCPClient1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChangePassword extends JFrame implements ActionListener {

	String userId;
	String userPw;
	JButton btnNewButton;
	JPasswordField CurrentField, ChangeField;

	public ChangePassword(String userId, String userPw) {

		this.userId = userId;
		this.userPw = userPw;

		// 현재 비밀번호
		JLabel CurrentLabel = new JLabel("현재 비밀번호");
		CurrentLabel.setBounds(8, 8, 93, 15);
		CurrentField = new JPasswordField(10);
		CurrentField.setBounds(113, 5, 116, 21);

		// 변경 비밀번호
		JLabel ChangeLabel = new JLabel("변경할 비밀번호");
		ChangeLabel.setBounds(8, 34, 104, 18);
		ChangeField = new JPasswordField(10);
		ChangeField.setBounds(113, 31, 116, 21);

		// 변경하기 버튼
		btnNewButton = new JButton("변경하기");
		btnNewButton.setBounds(8, 62, 221, 21);

		// 버튼 액션
		btnNewButton.addActionListener(this);

		// JFrame 설정 및 추가
		add(CurrentLabel);
		add(CurrentField);
		add(ChangeLabel);
		add(ChangeField);
		add(btnNewButton);
		setLayout(null);
		setTitle("비밀번호 변경");
		setResizable(false);
		setBounds(300, 700, 247, 126);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnNewButton) {
			if (CurrentField.getText().equals(userPw)) {
				if (!ChangeField.getText().equals("")) {
					try {
						String result = new TCPClient1().pwChange(userId + "\n" + ChangeField.getText());
						if (result.equals("성공")) {
							JOptionPane.showMessageDialog(null, "비밀번호 변경 성공");
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호 변경 실패");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// 비밀번호 변경이 성공하면 프로그램 종료
				}
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "비밀번호 불일치", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
