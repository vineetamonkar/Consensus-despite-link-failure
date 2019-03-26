import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;


public class ReadFile {

    private ArrayList<String> inputValues;
    private int numberOfProcesses;
    private int[] inputVal;
    private int numberOfRounds;
    private int messageDropNum;


    public int getNumberOfProcesses() {
        return numberOfProcesses;
    }

    public int[] getInputVal() {
        return inputVal;
    }


    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public int getmessageDropNum() {
        return messageDropNum;
    }



    public ReadFile(String arg) {
        inputValues = new ArrayList<>();
        readFile(arg);
    }

    private void readFile(String arg) {
        File file = new File(arg);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            inputValues.add(str);
        }
        init(inputValues);

    }

    public void init(ArrayList<String> values) {
        ArrayList<String> inputFile = values;
        Iterator itr = inputFile.iterator();
        numberOfProcesses = new Integer(Integer.parseInt((String) itr.next()));
        String[] IDs = ((String) itr.next()).split(", *");
        inputVal = Stream.of(IDs).mapToInt(Integer::parseInt).toArray();
        numberOfRounds = new Integer(Integer.parseInt((String) itr.next()));
        messageDropNum = new Integer(Integer.parseInt((String) itr.next()));
    }
}
