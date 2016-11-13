/**
 * Created by t.simonov on 11.11.16.
 */
public class FixedCapacityStackOfStrings {
    private String[] s;
    private int N = 0;
    public FixedCapacityStackOfStrings(int capacity) {

    }
    public boolean isEmpty (){
        return N == 0;
}
    public void push(String item) {
        s[N++] = item;
    }
    public String pop() {
        return s[N--];
    }

}

