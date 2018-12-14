package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBTest {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. 드라이버 셋팅 성공..");

		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. db연결 성공");

		String sql = "select * from member where id=2";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println(rs);
		String id = null;
		while (rs.next()) {
			id = rs.getString("id");
			System.out.println(rs.getString("pw"));
			System.out.println(rs.getString("tel"));
		}
		System.out.println(id);
	}

}
