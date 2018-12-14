package test;

import java.awt.Dimension;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Platform;

import javafx.embed.swing.JFXPanel;

import javafx.scene.Group;

import javafx.scene.Scene;

import javafx.scene.web.WebEngine;

import javafx.scene.web.WebView;

public class Test11 {

	public JPanel jp;
	public void initFX() {
		 jp = new JPanel();
		 jp.setLayout(null);
		 jp.setBounds(0,0,500,500);
//		JFrame frame = new JFrame("FX");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
		JFXPanel fxPanel = new JFXPanel();
		jp.add(fxPanel);
//		frame.setVisible(true);
		fxPanel.setBounds(0,0,500,500);
		fxPanel.setLocation(new Point(0, 27));
//		frame.getContentPane().setPreferredSize(new Dimension(300, 327));
//		frame.pack();
//		frame.setResizable(false);

		Platform.runLater(new Runnable() {

			public void run() {

				initAndLoadWebView(fxPanel);

			}

		});

	}

	public JPanel jp() {
		return jp;
	}
	
	public void initAndLoadWebView(JFXPanel fxPanel) {
		Group group = new Group();
		Scene scene = new Scene(group);
		fxPanel.setScene(scene);
		WebView webView = new WebView();
		group.getChildren().add(webView);
		webView.setMinSize(500, 500);
		webView.setMaxSize(500, 500);
		WebEngine webEngine = webView.getEngine();
		webEngine.load("http://map.naver.com/");
	}

	public Test11() {
		initFX();
	}
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Test11 t = new Test11();
		jf.setLayout(null);
		jf.add(t.jp);
		jf.setVisible(true);
	}

}
