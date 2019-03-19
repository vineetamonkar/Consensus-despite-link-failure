

public class Main {

    public static void main(String[] args)
    {
        ReadFile fileReadObj = new ReadFile("input.dat");
        int numberOfProcesses = fileReadObj.getNumberOfProcesses();
        int numberOfRounds = fileReadObj.getNumberOfRounds();
        int messageDropNum = fileReadObj.getmessageDropNum();
        int[] inputVal = fileReadObj.getInputVal();
        System.out.println(numberOfProcesses);
        System.out.println(numberOfRounds);
        System.out.println(messageDropNum);
        for (int i = 0; i < numberOfProcesses; i++)
        System.out.println(inputVal[i]);


    }
}
