import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
//Queue using collection framework
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(10);
//        queue.add(20);
//        queue.add(30);
//        queue.add(40);
//        queue.add(50);
//        System.out.println(queue);
        QueueArray qa = new QueueArray(7);
        qa.insert(10);
        qa.insert(20);
        qa.insert(30);
        qa.display();
    }
}