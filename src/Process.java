import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

class Process implements Runnable {

    private BlockingQueue<Message> queue;
    private int index;
    private int key;
    private int numberOfprocesses;
    private int[] inputVal;
    private int[] level;

    public Process(int index, int numberOfprocesses ,int inputVal) {
        this.index = index;
        this.numberOfprocesses = numberOfprocesses;
        this.inputVal = new int[numberOfprocesses];
        Arrays.fill(this.inputVal,-1);
        this.inputVal[index] = inputVal;
        this.level = new int[numberOfprocesses];

    }

    @Override
    public void run() {

    }
}
