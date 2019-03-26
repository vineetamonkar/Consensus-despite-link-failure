public class Message {

    public int[] inputVal;
    public int[] level;
    public int key;

    public Message(){}

    public Message(int key, int[] inputVal, int[] level) {

        this.inputVal = inputVal;
        this.key = key;
        this.level = level;
    }

}
