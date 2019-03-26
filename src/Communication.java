import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Communication {

private static Process[] processArr;
private static int messageDropNum;
private static int numberOfProcesses;
private static AtomicIntegerArray messageArr;
private static AtomicInteger messageNum = new AtomicInteger(0);


public Communication(Process[] processArr, int messageDropNum, int numberOfProcesses)
{
    this.processArr = processArr;
    this.messageDropNum = messageDropNum;
    this.numberOfProcesses = numberOfProcesses;
    this.messageArr = new AtomicIntegerArray(numberOfProcesses);

}

public  void resetMessage(){
    for (int i = 0 ; i < messageArr.length(); i++)
        messageArr.set(i, 1);
    }



    public static synchronized void sendMessage(Message m,int receiver_id, int senderIndex){
    int globalRoundNum = Round.getGlobalRoundNumber();
    messageNum.set(messageArr.get(senderIndex) + (((numberOfProcesses-1))*senderIndex) + (globalRoundNum*(numberOfProcesses-1)*(numberOfProcesses)));
    messageArr.getAndIncrement(senderIndex);
    System.out.println("generating Message number "+messageNum);
    if(messageNum.get()%messageDropNum != 0) {
        Process receiver = processArr[receiver_id];
        send(m, receiver);
    }
    else
        System.out.println("");
    }


    private static void send(Message m , Process p){
        p.putMessage(m);
    }


}
