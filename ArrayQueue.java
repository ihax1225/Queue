
/**
 * Objecthis ArrayQueue object . . .
 * 
 * @author  
 * @version 
 */
public class ArrayQueue implements QueueInterface
{
    protected final int DEFCAP = 100; // default capacity
    protected Object[] queue;              // array that holds queue elements
    protected int numElements = 0;    // number of elements in the queue
    protected int front = 0;          // index of front of queue
    protected int rear;               // index of rear of queue

    public ArrayQueue(){
        queue = (Object[]) new Object[DEFCAP];
        rear =  DEFCAP - 1;
    }
    // returns the logical size of the queue
    public int size(){
        return numElements;
    }

    // tests if this queue is empty
    public boolean empty(){
        return numElements <= 0;
    }

    public boolean isFull(){
        return numElements == queue.length;
    }

    // adds an item onto the rear of this queue
    public Object add(Object item){
        if(isFull())
            checkSize();
        rear = increment(rear);
        queue[rear] = item;
        numElements++;
        return queue[rear];
    }   

    public int increment(int x){
        if(++x == queue.length)
            x = 0;
        return x;
    }

    // looks at the object at the front of this queue
    // without removing it from the queue
    public Object peek() throws QueueUnderflowException{
        if(empty())
            throw new QueueUnderflowException();
        else
            return queue[front];
    }

    // removes the object at the front of this queue 
    // and returns that object as the value of this function
    public Object remove() throws QueueUnderflowException{
        if(empty())
            throw new QueueUnderflowException();
        numElements--;
        Object removed = queue[front];
        front = increment(front);
        return removed;

    }

    // removes all of the elements from this stack
    public void clear(){
        while(!empty())
            remove();
    }

    private void checkSize()
    {
        if(isFull())
        {
            Object[] arr = new Object[size() * 2];
            for(int i = 0; i < queue.length; i++)
                arr[i] = queue[i];
            queue = arr;
        }
        if(size() < queue.length /4){
            Object[] log2 = new Object[queue.length / 2];
            for(int r = 0; r < log2.length; r++)
                log2[r] = queue[r];
            queue = log2;
        }
    }
}
