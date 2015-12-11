//--
//Name: Dakota Smith
//Class: CST200
//Date: 11/26/15
//Time: 2300
//--

import java.util.*;

public class rosterType {
	private int index;
	private StudentType[] students;
	private StatPackage scores;

	public rosterType() {
		index = 0;
		students = new StudentType[20];
		scores  = new StatPackage();
	}

	public boolean isFull() {
		return index == 20;
	}

	public boolean isEmpty() {
		return index == 0;
	}

	public void addStudent(StudentType newStudent) {
		if(index == 20) {
			return;
		}
		students[index++] = newStudent;
	}

	public StudentType findStudent(String sid) {
		StudentType s;
		for(int i = 0; i < index; i++) {
			s = students[i];
			if(s.getSID() == sid) {
				return s;
			}
		}
		return new StudentType();
	}

	public String toString() {
		StudentType[] studentCopy = sortCopy(students, index, new Comparator<StudentType>() {
			public int compare(StudentType s1, StudentType s2) {
				return s1.getSID().compareTo(s2.getSID());
			}
		});
		StringBuffer sb = new StringBuffer();
		for(StudentType s : studentCopy) {
			sb.append(s.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public void printByGrade() {
		if(isEmpty()) {
			System.out.println("The class is empty");
			return;
		}
		StudentType[] studentCopy = sortCopy(students, index, new Comparator<StudentType>() {
			public int compare(StudentType s1, StudentType s2) {
				//sort in descending order
				return (int) (s2.getTotal()/ - s1.getTotal());
			}
		});
		for(StudentType s : studentCopy) {
			scores.insert(s.getTotal());
			System.out.printf("%s %2d%n", s, s.getTotal());
		}
		System.out.printf("Mean: %2d%n", scores.Mean());
		System.out.printf("Median: %2d%n", scores.Median());
	}

	//length parameter exists so you can range over the results
	//if passed a non-full array
	public <T> T[] sortCopy(T[] arr, int length, Comparator<T> comp) {
		T[] copy = Arrays.copyOf(arr, length);
		Arrays.sort(copy, comp);
		return copy;
	}
}
