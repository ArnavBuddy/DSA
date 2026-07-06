import java.util.*;
import java.lang.*;
import java.io.*;

class Student implements Comparable<Student>{
    String name;
    int marks;
    Student(String name, int marks){
        this.name = name;
        this.marks = marks;
    }
    @Override
    public String toString(){
        return "Student Name: " + this.name + " Student Marks: " + this.marks;
    }
    @Override
    public int compareTo(Student that){
        if (this.marks == that.marks) return this.name.compareTo(that.name);//if marks same then return smaller name lexicographically
        // return this.marks - that.marks; // asc order 
        return that.marks - this.marks; // dec order 
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Student){
            Student that = (Student) obj;
            boolean isNameSame = this.name.compareTo(that.name) == 0;
            if (this.marks == that.marks && isNameSame) return true;
        }
        return false;
    }
}

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		PriorityQueue<Student> pq = new PriorityQueue<>();
		pq.offer(new Student("typhid", 90));
		pq.offer(new Student("divya", 90));
		pq.offer(new Student("lastogi", 80));
		pq.offer(new Student("nirma", 70));
		System.out.println(pq.peek());
 		System.out.println(pq.contains(new Student("lastogi", 80)));
	}
}
