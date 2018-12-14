package user;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import main.LibraryMain;
import tcpserver.MemberDTO;
import tcpserver.TCPClient1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Withdrawal extends  JFrame implements ActionListener {
	
	JPasswordField PasswordField, PasswordCheckField;
	JButton btnNewButton;
	String userId, userPw;
	int withdrawalCheck;
	
	public Withdrawal(MemberDTO dto) {
		
		this.userId = dto.getId();
		this.userPw = dto.getPw();
		
		//비밀번호
		JLabel PasswordLabel = new JLabel("비밀번호");
		PasswordLabel.setBounds(12, 22, 57, 15);

		PasswordField = new JPasswordField(10);
		PasswordField.setBounds(110,19,136,21);
		
		//비밀번호 확인
		JLabel PasswordCheckLabel = new JLabel("비밀번호 확인");
		PasswordCheckLabel.setBounds(12, 50, 99, 15);
				
		PasswordCheckField = new JPasswordField(10);
		PasswordCheckField.setBounds(110,47,136,21);
		
		//회원탈퇴 버튼
		btnNewButton = new JButton("회원탈퇴");
		btnNewButton.setBounds(79, 78, 97, 23);

		//JFrame에 추가 및 설정
		add(PasswordLabel);
		add(PasswordField);
		add(PasswordCheckLabel);
		add(PasswordCheckField);
		add(btnNewButton);
		
		//버튼 이벤트(회원 탈퇴)
		btnNewButton.addActionListener(this);
		setTitle("회원 탈퇴");
		setLayout(null);
		setResizable(false);
		setBounds(300, 700, 281, 138);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton) {
			String pw = PasswordField.getText();
			String pwCheck = PasswordCheckField.getText();
			
			if(pw.equals(userPw) && pwCheck.equals(userPw)) {
				try {
					String result = new TCPClient1().memberDelete(userId);
					if(result.equals("성공")) {
						dispose();
						JOptionPane.showMessageDialog(null, "회원탈퇴 성공");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

}
