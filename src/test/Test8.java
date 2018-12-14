package test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Test8 extends JFrame implements ActionListener {

	public Test8() throws Exception {
		setSize(882, 707);
		setLayout(null);
//		JPanel jp = new JPanel();
//		jp.setLayout(null);
//		jp.setBounds(0, 0, 866, 609);
//		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("1.png"));
		lblNewLabel.setBounds(0, 0, 866, 609);

//		jp.add(lblNewLabel);

//		add(jp);
		add(lblNewLabel);
		setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws Exception {
		new Test8();

	}

}