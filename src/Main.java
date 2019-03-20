import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args)
    {
        ReadFile fileReadObj = new ReadFile("input.dat");
        int numberOfProcesses = fileReadObj.getNumberOfProcesses();
        int numberOfRounds = fileReadObj.getNumberOfRounds();
        int messageDropNum = fileReadObj.getmessageDropNum();
        int[] inputVal = fileReadObj.getInputVal();
        //System.out.println(numberOfProcesses);
        //System.out.println(numberOfRounds);
        //System.out.println(messageDropNum);
        //for (int i = 0; i < numberOfProcesses; i++)
        //System.out.println(inputVal[i]);
        Round r = new Round(numberOfProcesses);
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfProcesses);
        Process processArr[] = new Process[numberOfProcesses];
        for (int i = 0; i < numberOfProcesses; i++) {
            Process p = new Process(i, numberOfProcesses, numberOfRounds, inputVal[i]);
            processArr[i] = p;
        }
            Communication channel = new Communication(processArr,messageDropNum);
            for (int i = 0; i < numberOfProcesses; i++) {
                threadPool.submit(processArr[i]);
            }

        int round = -1;
        while(round < numberOfRounds){
          try {
              if (Round.threadCount.get() == 0) {
                  round++;
                  Thread.currentThread().sleep(1000);
                  r.nextRound(numberOfProcesses, round);
                  System.out.println("Started round : " + (round));
              }
          }catch(Exception e) {
              e.printStackTrace();
          }
        }
        r.setStopAllThreads(true);
        System.out.println("All rounds finishied . Closing Thread pool");
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            System.out.println("Thread pool closed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }

    }




