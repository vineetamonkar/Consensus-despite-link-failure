public class Message {

    private int[] inputVal;
    private int[] level;
    public int index;
    private int key;

    public Message(){}

    public Message(int key, int[] inputVal, int index, int[] level) {

        this.inputVal = inputVal;
        this.key = key;
        this.index = index;
        this.level = level;
    }

}
