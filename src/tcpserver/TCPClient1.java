package tcpserver;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class TCPClient1 {
	Socket s;

	public TCPClient1() {
		try {

			s = new Socket("localhost", 9400);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 회원가입 id 중복방지 확인
	public String idCheck(String id) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("idCheck\n" + id);

			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "false";
	}

	// 회원가입 정보 전송
	public String join(MemberDTO dto) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("join");
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(dto);
			oos.flush();

			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// 로그인 확인
	public String loginCheck(String info) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("loginCheck\n" + info);

			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 회원 개인정보 가져오기
	public MemberDTO personalInfo(String id) throws Exception {
		PrintWriter out;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
			out.println("personalInfo\n" + id);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			MemberDTO dto = (MemberDTO) ois.readObject();
			return dto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 아이디 찾기
	public String idFind(String info) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("idFind\n"+info);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// 비밀번호 찾기
	public String pwFind(String info) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("pwFind\n"+info);
			System.out.println("send succes");
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String result = input.readLine();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 책 등록하기
	public void bookRegist(String string, String path) {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(string);
			OutputStream outputStream = s.getOutputStream();
			System.out.println(path);
			BufferedImage image = ImageIO.read(new File(path));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", byteArrayOutputStream);
			byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
			outputStream.write(size);
			outputStream.write(byteArrayOutputStream.toByteArray());
			outputStream.flush();

//			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
//			String result = input.readLine();
//			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		return "0";
	}

	// 전체회원 정보 얻어오기
	public ArrayList<MemberDTO> allMemberInfo() throws Exception {

		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("allMemberInfo");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<MemberDTO> a = (ArrayList<MemberDTO>) ois.readObject();
		return a;

	}

	// 도서 정보 얻어오기
	public ArrayList getBookInfo() throws Exception {

		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("bookInfo");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ArrayList<BookDTO> a = (ArrayList<BookDTO>) ois.readObject();

		return a;
	}

	// 책 종류 갖고오기
	public TreeMap<String, Integer> bookKind2() throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("bookKind2");
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

		TreeMap<String, Integer> a = (TreeMap<String, Integer>) ois.readObject();

		return a;

	}

	// 유저 정보 변경
	public String userInfoChange(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("userInfoChange\n" + info);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;
	}

	// 유저 이미지 올리기
	public String userImage(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> bookSearch(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	// 개인정보 수정 비밀번호 변경
	public String pwChange(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("pwChange\n" + info);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;
		
	}

	
	//회원탈퇴
	public String memberDelete(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("memberDelete\n" + info);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;
		

	}

	//유저 정보 변경
	public String userInfoChange_admin(String info) throws Exception {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("userInfoChange_admin\n" + info);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String result = input.readLine();
		return result;
	}

}
