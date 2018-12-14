package test;

import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UrlImage extends JFrame {

	public UrlImage() throws Exception {
		JLabel a = new JLabel();
		URL url = new URL("http://104.196.4.68/1.jpg");
		Image image = ImageIO.read(url);
		ImageIcon b = new ImageIcon(image);
		a.setIcon(b);
		setLayout(new FlowLayout());
		add(a);
		setVisible(true);
		setSize(50, 50);
	}

	public static void main(String[] args) throws Exception {
		new UrlImage();
	}

}
