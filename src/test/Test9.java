package test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test9 extends JPanel {
	JPanel a = this;
	public Test9() {
		this.setLayout(null);
		this.setBounds(0, 0, 300, 300);
		this.setOpaque(false);
		a.setBackground(new Color(255, 0, 0,0));
	}

	public JPanel panel() {
		return this;
	}

	public void paint(Graphics g) {
		a.setBackground(new Color(255, 0, 0,0));
		System.out.println("시작");
		g.setColor(Color.RED);
		Random r = new Random();
		int random = r.nextInt(2);
		if (random == 0) {
			a.setBackground(new Color(255, 0, 0,0));
			int xArray[] = { 100, 100, 300, 300, 100, 100, 300, 300 };
			int yArray[] = { 150, 200, 200, 250, 250, 300, 300, 350 };
			for (int i = 0; i < xArray.length - 1; i++) {
				g.drawLine(xArray[i], yArray[i], xArray[i + 1], yArray[i + 1]);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else if (random == 1) {
			a.setBackground(new Color(255, 0, 0,0));
			int aArray[] = { 300, 300, 100, 100, 300, 300, 100, 100 };
			int bArray[] = { 150, 200, 200, 250, 250, 300, 300, 350 };
			for (int i = 0; i < aArray.length - 1; i++) {
				g.drawLine(aArray[i], bArray[i], aArray[i + 1], bArray[i + 1]);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Test9 a = new Test9();
		jf.setSize(700, 700);
		jf.add(a);
		jf.setVisible(true);
	}
}
