import java.util.Arrays;

public class Communication {

private static Process[] processArr;
private static int messageDropNum;
private static int numberOfProcesses;
private static int[] messageArr;


public Communication(Process[] processArr, int messageDropNum, int numberOfProcesses)
{
    this.processArr = processArr;
    this.messageDropNum = messageDropNum;
    this.numberOfProcesses = numberOfProcesses;
    this.messageArr = new int[numberOfProcesses];

}

public  void resetMessage(){
    Arrays.fill(messageArr, 1);
    }



    public static void sendMessage(Message m,int receiver_id, int senderIndex){
    int globalRoundNum = Round.getGlobalRoundNumber();
    int messageNum = messageArr[senderIndex] + ((globalRoundNum + (numberOfProcesses-1))*senderIndex) + (globalRoundNum*numberOfProcesses*(numberOfProcesses-1));
    messageArr[senderIndex] = messageArr[senderIndex]+1;
    if(messageNum%messageDropNum!=0) {
        Process receiver = processArr[receiver_id];
        send(m, receiver);
    }
    else
        System.out.println("Message dropped number "+messageNum);
    }


    private static void send(Message m , Process p){
        p.putMessage(m);
    }


}
