package ch03;

import java.io.*;

public class InputTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int c;
		try {
			InputStream in = new LowerCaseInputStream(
					new BufferedInputStream(
							new FileInputStream("data/test.txt")));
			while ((c = in.read()) >= 0) {
				System.out.print((char)c);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
