import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Process implements Runnable {

    private BlockingQueue<Message> queue;
    private int index;
    private int decision = -1;
    private int key;
    private int numberOfprocesses;
    private int numberOfRounds;
    private int[] inputVal;
    private int[] level;
    private int min = 0;

    public boolean decisionDone = false;



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


        try{
           if(!this.queue.isEmpty()) {
               while (!this.queue.isEmpty()) {
                   Message m = this.queue.take();
                   if(this.key==-1)
                       this.key = m.key;
                   for (int i = 0; i < numberOfprocesses; i++) {
                       if (this.inputVal[i] == -1 & m.inputVal[i] != -1)
                           this.inputVal[i] = m.inputVal[i];
                       if (this.level[i] < m.level[i])
                           this.level[i] = m.level[i];
                   }

               }
               if (this.index == 0)
                   min = this.level[1];
               else
                   min = this.level[0];
                for(int i =0;i<numberOfprocesses;i++) {
                if(this.level[i]<min & i!=this.index)
                    min = this.level[i];
                }
                this.level[this.index] = min +1;

                }
           } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }

        public void processDecision()
        {
            System.out.println(this.index);
            for (int i=0;i<numberOfprocesses;i++)
            { if(this.inputVal[i]==0 || this.level[i]<this.key)
                    this.decision = 0;}
            if(this.decision == -1)
                this.decision = 1;
               this.decisionDone = true;
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
                    if(globalRoundNum == numberOfRounds-1)
                        this.processDecision();
                    else {
                        Message toSend = new Message(this.key, this.inputVal, this.index, this.level);
                        Thread.sleep(1000);
                        for (int i = 0; i < numberOfprocesses; i++)
                            Communication.sendMessage(toSend, i);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                Round.update(this.index, 0);

            }
            if (Round.getStopAllThreads()) {
                System.out.println("Shutting down process : " + this.index + " with decision : " + this.decision + " with level vector : " +
                        "" + Arrays.toString(this.level)+" with input vector " + Arrays.toString(this.inputVal)+" with key "+this.key);
                break;
            }
        }
    }

}