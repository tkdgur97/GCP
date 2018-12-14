package test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class TCPClient2 {

	private static String IMAGE_PATH = "2.jpg";// 파일 포맷 일치 필요

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 9400);
		OutputStream outputStream = socket.getOutputStream();
		BufferedImage image = ImageIO.read(new File(IMAGE_PATH));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", byteArrayOutputStream); // 파일 포맷 일치 필요
		byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
		outputStream.write(size);
		outputStream.write(byteArrayOutputStream.toByteArray());
		outputStream.flush();
		System.out.println("Flushed: " + System.currentTimeMillis());
		socket.close();
	}

}
