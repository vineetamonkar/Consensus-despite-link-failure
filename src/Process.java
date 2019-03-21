import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Process implements Runnable {

    private BlockingQueue<Message> queue;
    private int index;
    private int decision;
    private int key;
    private int numberOfprocesses;
    private int numberOfRounds;
    private int[] inputVal;
    private int[] level;




    public Process(int index, int numberOfprocesses, int numberOfRounds, int inputVal) {
        this.index = index;
        this.numberOfprocesses = numberOfprocesses;
        this.inputVal = new int[numberOfprocesses];
        Arrays.fill(this.inputVal, -1);
        this.inputVal[index] = inputVal;
        this.level = new int[numberOfprocesses];
        this.key = -1;
        this.numberOfRounds = numberOfRounds;
        this.queue =  new ArrayBlockingQueue<>(numberOfprocesses);
    }

    public void putMessage(Message m){
        queue.add(m);
    }

    public void processQueue() {

    }

    @Override
    public void run() {
        Thread.currentThread().setName("" + index);
        while (true) {

            if (Round.round.get(this.index) == 100) {
                int globalRoundNum = Round.getGlobalRoundNumber();
                try {
                    if (globalRoundNum == 0 & index == 0) {
                        Random rand = new Random();
                        key = rand.nextInt(numberOfRounds) + 1;
                    } else if (globalRoundNum != 0)
                        this.processQueue();
                    Message toSend = new Message(this.key,this.inputVal, this.index, this.level );
                    Thread.sleep(1000);
                    for (int i = 0; i < numberOfprocesses; i++)
                        Communication.sendMessage(toSend, i);


                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                Round.update(this.index, 0);

            }
            if (Round.getStopAllThreads()) {
                System.out.println("Shutting down process : " + this.index + " with decision : " + this.decision + " with level vector : " +
                        "" + Arrays.toString(this.level));
                break;
            }
        }
    }

}