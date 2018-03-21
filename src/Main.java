import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main{

	private static int fNum, sNum, tNum, result;

	private static String firstOperator, secondOperator;
	
	private final static String ADD = "+";
	private final static String SUB = "-";
	private final static String MUL = "*";
	private final static String DIV = "/";
	

	private final static String[] OPERATOR = { "+", "-", "*", "/" };

	private static int ac=1;
	public static void main(String[] args) {
		
		File file = new File("result.txt");
		if(file.exists()) {
			file.delete();
		}
		writeToFile("201571030112\n");
		int a=Integer.parseInt(args[0]);
		for (int i = 0; i < a; i++) 
		 {
			compute();
				
		 }
	}

	public static boolean compute() {

		while (true) {
			fNum = generateRandomNum(1, 99);

			sNum = generateRandomNum(1, 99);

			tNum = generateRandomNum(1, 99);

			firstOperator = OPERATOR[generateRandomNum(0, 3)];

			secondOperator = OPERATOR[generateRandomNum(0, 3)];

			if (firstOperator.equals(secondOperator)) {
				continue;
			}
			
			try {
				if (secondOperator.equals(MUL)
						|| secondOperator.equals(DIV)) {
					int preResult = ys(sNum, tNum, secondOperator);
					if (preResult < 0) {
						continue;
					}
					result = ys(fNum, preResult, firstOperator);
				} else {
					int preResult = ys(fNum, sNum, firstOperator);
					if (preResult < 0) {
						continue;
					}
					result = ys(preResult, tNum, secondOperator);
				}
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			

			if (result < 0) {
				continue;
			} else {

				StringBuffer buffer = new StringBuffer();

				buffer.append(fNum).append(firstOperator).append(sNum).append(secondOperator).append(tNum)
						.append("=").append(result).append("\n");
				
				System.out.println(buffer);

				if (writeToFile(buffer.toString())) {

					return true;

				} else {

					return false;

				}

			}
		}
	}

	public static int generateRandomNum(int min, int max) {

		Random random = new Random();

		return random.nextInt(max - min + 1) + min;

	}

	public static boolean writeToFile(String result) {
		
		try {
		     FileWriter fw = new FileWriter("result.txt", true);
    	     fw.write(result);
    	     fw.flush();
    	     fw.close();

			return true;

		} catch (IOException e) {

			e.printStackTrace();

			return false;

		}

	}

	public static Integer ys(int firstNum, int secNum, String operator) throws Exception{

		switch (operator) {

		case ADD: {

			return firstNum + secNum;

		}

		case SUB: {

			return firstNum - secNum;

		}

		case MUL: {

			return firstNum * secNum;

		}

		case DIV: {
			if (firstNum % secNum == 0) {
				return firstNum / secNum;
			}
			else {
				throw new Exception("");
			}

			

		}

		default: {

			return null;

		}

		}

	}

}