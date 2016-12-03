/**
 * Created by t.simonov on 02.12.16.
 */
public class Sandbox {

    public int getRow (int value) {
        int rowLength = 3;
//        int i = (int) Math.ceil((double) (value/rowLength));
        int i = (value + rowLength - 1) / rowLength;
        return i - 1;
    }
    private int getJ (int value) {
        int rowLength = 3;
        int j = value - ((getRow(value)) * rowLength);
        return j - 1;
    }

    public static void main(String[] args) {
        Sandbox box = new Sandbox();
        int x = 8;
        System.out.println(box.getRow(x));
        System.out.println((box.getJ(x)));
    }
}
