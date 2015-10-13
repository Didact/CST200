//--
//Name: Dakota Smith
//CST: 200
//Time: 9:45
//Date: 10/8/2015
//Assignment: Lab4 (Quiz)
//--

import java.io.*;

public class lab4 {
	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		double totalavg = 0.0;
		int num = -1;
		char[] master = null;
		String name;
		StringBuffer id;
		int score;
		String line;
		String[] tokens = null;

		line = stdin.readLine();
		if(line == null) {
			return;
		}

		tokens = line.split("(\\s)+");
		master = new char[tokens.length-1];
		num = Integer.parseInt(tokens[0]);

		for(int i = 1; i < tokens.length; i++) {
			master[i-1] = tokens[i].charAt(0);
		}

		System.out.printf("Results for quiz %d:%n%n", num);

		int count = 0;
		while((line = stdin.readLine()) != null && !line.equals("ZZZZ")) {
			count++;
			score = 0;
			tokens = line.split("(\\s)+");

			id = new StringBuffer(tokens[2]);
			id.insert(3, '-');
			id.insert(6, '-');

			name = tokens[1] + " " + tokens[0].substring(0, tokens[0].length()-1);

			for(int i = 3; i < tokens.length; i++) {
				if(Character.toLowerCase(master[i-3]) == Character.toLowerCase(tokens[i].charAt(0))) {
					score++;
				}
			}

			totalavg += score;
			System.out.printf("%s %s %d%n", id.toString(), name, score);
		}

		if(count == 0) {
			System.out.printf("Quiz %d: Empty class data%n", num);
			return;
		}

		System.out.println("");
		System.out.printf("The average score is %.01f%n", totalavg / count);
		
	}
}
