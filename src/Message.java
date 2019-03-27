import java.util.ArrayList;
import java.util.List;

public class Message<T> {

    public List<T> inputVal;
    public List<T> level;
    public T key;

    public Message(T key, ArrayList<T> inputVal, ArrayList<T> level) {

        this.inputVal = inputVal;
        this.key = key;
        this.level = level;
    }

}
