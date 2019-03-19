import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            Process p = new Process(i, numberOfProcesses, inputVal[i]);
            processArr[i] = p;
        }
            Communication channel = new Communication(processArr);
            for (int i = 0; i < numberOfProcesses; i++) {
                threadPool.submit(processArr[i]);
            }
        }



    }

