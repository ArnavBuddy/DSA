public class QueueArray{
    int[] arr;
    int front, rear;
    QueueArray (int size){
        arr = new int[size];
        front = rear = -1;
    }
    public void insert(int data){
        if (front == -1){
            front = 0;
        }
        arr[front] = data;
    }
    public void display(){
        for (int i = front; i <= rear; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}