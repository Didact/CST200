//--
//Name: Dakota Smith
//Class: CST200
//Date: 12/11/15
//Time: 1200
//--

import java.io.*;

public class Boxcars {

	public static void main(String[] args) {
		String str = "";
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		while(str != null) {

			Stack<String> inputStack;
			Stack<String> stack;
			String input = "";
			String output = "";
			String[] inputArr;
			String desiredOutput;
			String[] desiredOutputArr;
			String[] outputArr;
			String seq = "The sequence of moves are:";

			try {
				input = str = stdin.readLine();
				output = stdin.readLine();
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			if(input == null || output == null) {
				continue;
			}

			inputArr = input.split("\\s+");
			desiredOutputArr = output.split("\\s+");
			if(inputArr.length != desiredOutputArr.length) {
				return;
			}
			if(inputArr.length > 10) {
				System.out.println("A maximum of only 10 items are allowed to be input for each sequence - Please re-enter");
				continue;
			}

			inputStack = new Stack<String>(reverse(inputArr));
			stack = new Stack<String>(inputArr.length);
			outputArr = new String[inputArr.length];
			int i = 0;

			boolean success = false;
			while(true) {
				if(arrEquals(outputArr, desiredOutputArr)) {
					success = true;
					break;
				}
				if(desiredOutputArr[i].equals(stack.peek())) {
					outputArr[i++] = stack.pop();
					seq += " pop";
					continue;
				}
				if(inputStack.empty() || stack.full()) {
					success = false;
					break;
				}
				stack.push(inputStack.pop());
				seq += " push";
			}
			System.out.printf("Attempting to map the input %s to the output %s%n", input, output);
			System.out.println(seq);
			System.out.printf("The input %s be mapped to the output%n", (success) ? "can" : "cannot");
		}
	}

	public static <T> boolean arrEquals(T[] as, T[] bs) {
		if(as.length != bs.length) {
			return false;
		}
		for(int i = 0; i < as.length; i++) {
			if(as[i] == null) {
				if(bs[i] == null ) {
					continue;
				}
				return false;
			}
			if(as[i].hashCode() != bs[i].hashCode()) {
				return false;
			}
		}
		return true;
	}

	public static <T> T[] reverse(T[] arr) {
		T[] res = (T[]) new Object[arr.length];
		for(int i = 0; i < arr.length; i++) {
			res[i] = arr[arr.length - i - 1];
		}
		return res;
	}
}
