public class Message {

    public int[] inputVal;
    public int[] level;
    public int index;
    public int key;

    public Message(){}

    public Message(int key, int[] inputVal, int index, int[] level) {

        this.inputVal = inputVal;
        this.key = key;
        this.index = index;
        this.level = level;
    }

}
