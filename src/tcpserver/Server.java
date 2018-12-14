package tcpserver;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class Server {

	Socket socket;
	BufferedReader input;

	public Server() throws Exception {
		ServerSocket serverSocket = new ServerSocket(9400);
		while (true) {
			socket = serverSocket.accept();
			checkRequest();
			socket.close();
		}
	}

	// 어떤 요청인지 판별하기
	public void checkRequest() throws Exception {
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String info = input.readLine();

		// 책 등록하기
		if (info.equals("bookRegist")) {
			bookRegist();

			// 전체 회원정보 가져오기
		} else if (info.equals("allMemberInfo")) {
			allMemberInfo();
			// 회원 개인정보 가져오기
		} else if (info.equals("personalInfo")) {
			personalInfo();
			// 책정보 가져오기
		} else if (info.equals("bookInfo")) {
			bookInfo();
			// 책종류 가져오기
		} else if (info.equals("bookKind2")) {
			bookKind2();
			// 회원가입하기
		} else if (info.equals("join")) {
			memberJoin();
			// 회원가입 ID중복 확인
		} else if (info.equals("idCheck")) {
			idCheck();
			// 로그인 확인
		} else if (info.equals("loginCheck")) {
			loginCheck();
			// 개인정보 수정
		} else if (info.equals("userInfoChange")) {
			userInfoChange();
			// 개인정보 비밀번호 변경
		} else if (info.equals("pwChange")) {
			pwChange();
			// 회원 탈퇴
		} else if (info.equals("memberDelete")) {
			memberDelete();
			//아이디 찾기
		} else if (info.equals("idFind")) {
			idFind();
			//비밀번호 찾기
		} else if (info.equals("pwFind")) {
			pwFind();
			//관리자 유저 정보 변경
		} else if (info.equals("userInfoChange_admin")) {
			userInfoChange_admin();
		}

	}

	//관리자 유저 정보 변경
	public void userInfoChange_admin() throws Exception {
		String id = input.readLine();
		String pw = input.readLine();
		String name = input.readLine();
		String tel = input.readLine();
		String address = input.readLine();
		String result = new MemberDAO().userInfoChange_admin(id,pw,name,tel,address);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
		
	}

	//비밀번호 찾기
	public void pwFind() throws Exception {
		String id = input.readLine();
		String name = input.readLine();
		String result = new MemberDAO().pwFind(id, name);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
		
	}

	//아이디 찾기
	public void idFind() throws Exception {
		String name = input.readLine();
		String tel = input.readLine();
		String result = new MemberDAO().idFind(name, tel);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	//회원 탈퇴
	public void memberDelete() throws Exception {
		String id = input.readLine();
		String result = new MemberDAO().memberDelete(id);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
		
	}

	//개인정보 비밀번호 변경하기
	public void pwChange() throws Exception {
		String id = input.readLine();
		String pw = input.readLine();
		String result = new MemberDAO().pwChange(id,pw);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	//개인정보 수정하기
	public void userInfoChange() throws Exception {
		String id = input.readLine();
		String name = input.readLine();
		String tel = input.readLine();
		String address = input.readLine();
		String result = new MemberDAO().userInfoChange(id,name,tel,address);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	//개인정보 가져오기
	public void personalInfo() throws Exception {
		String id = input.readLine();
		MemberDTO dto = new MemberDAO().personalInfo(id);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(dto);
		oos.flush();
		oos.close();
	}

	// 회원가입 id 중복 확인하기
	public void idCheck() throws Exception {
		String id = input.readLine();
		String result = new MemberDAO().idCheck(id);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();
	}

	// 회원가입
	public void memberJoin() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		MemberDTO dto = (MemberDTO) ois.readObject();
		String result = new MemberDAO().join(dto);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		ois.close();
		out.close();
	}

	// 로그인 확인
	public void loginCheck() throws Exception {
		String id = input.readLine();
		String pw = input.readLine();
		String result = new MemberDAO().loginCheck(id, pw);

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(result);
		out.close();

	}

	// 책 종류 가져오기
	public void bookKind2() throws Exception {
		// TODO Auto-generated method stub
		TreeMap<String, Integer> bookKind = new BookDAO().bookKind2();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(bookKind);
		oos.flush();
		oos.close();
	}

	// 책 정보 가져오기
	public void bookInfo() throws Exception {
		ArrayList<BookDTO> d = new BookDAO().bookInfo();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(d);
		oos.flush();
		oos.close();

	}

	// 회원정보 가져오기
	public void allMemberInfo() throws Exception {
		ArrayList<MemberDTO> d = new MemberDAO().allMemberInfo();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(d);
		oos.flush();
		oos.close();
	}

	// 책 등록하기
	public void bookRegist() throws Exception {

		InputStream inputStream = socket.getInputStream();
		byte[] sizeAr = new byte[4];
		inputStream.read(sizeAr);
		int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
		byte[] imageAr = new byte[size];
		inputStream.read(imageAr);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
		String IMAGE_PATH = "3.jpg"; // 파일 포맷 일치 필요
		ImageIO.write(image, "jpg", new File(IMAGE_PATH)); // 파일 포맷 일치 필요
		new BookDAO().bookRegist(input);
	}

	public static void main(String[] args) throws Exception {
		new Server();
	}
}
