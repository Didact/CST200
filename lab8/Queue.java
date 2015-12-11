import java.util.Arrays;

public class Queue<T> {

	Node<T> head;
	Node<T> tail;
	
	private class Node<T> {
		T data;
		Node<T> first;
		Node<T> last;
	}

	public void push(T t) {
		n = new Node<T>(t);
		if(empty()) {
			head = tail = n;
			return;
		}
		head.first = n;
		n.first = head;
		n.last = tail;
		tail.last = n;
		first = n;
	}

	public T pop() {
		if(empty()) {
			return null;
		}
		data = last.data;

	}

	public T peek() {
		if(empty()) {
			return null;
		}
		return tail.data;
	}

	public boolean empty() {
		return head == null;
	}
}
