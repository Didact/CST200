//--
//Name: Dakota Smith
//Class: CST200
//Date: 12/11/15
//Time: 1200
//--

import java.util.Arrays;

public class Stack<T> {
	private int pos; //topmost element of the stack
	private T[] stack;

	public Stack(int size) {
		pos = -1;
		stack = (T[]) new Object[size];
	}

	public Stack(T[] arr) {
		stack = arr;
		pos = arr.length-1;
	}

	public void push(T e) {
		if(full()) {
			return;
		}
		stack[++pos] = e;
	}

	public T pop() {
		if(empty()) {
			return null;
		}
		return stack[pos--];
	}

	public T peek() {
		if(empty()) {
			return null;
		}
		return stack[pos];
	}

	public boolean full() {
		return pos == stack.length-1;
	}

	public boolean empty() {
		return pos == -1;
	}

	public void clear() {
		pos = -1;
	}

	public int size() {
		return stack.length;
	}

	public void resize(int size) {
		stack = Arrays.copyOf(stack, size);
	}

	public String toString() {
		if(empty()) {
			return "[]";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(stack[0]);
		for(int i = 1; i <= pos; i++) {
			sb.append(" ");
			sb.append(stack[i]);
		}
		sb.append("]");
		return sb.toString();
	}
}
