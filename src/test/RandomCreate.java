package test;

import java.util.Random;

public class RandomCreate {

	public int random() {
		
		Random r = new Random();
		System.out.println(r.nextInt(1)+1);
		
		return 0;
	}
	
	public static void main(String[] args) {
		new RandomCreate().random();
	}

}
