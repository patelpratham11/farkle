import java.util.ArrayList;
public class hello {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        for(int i = 0; i < list.size(); i++){
            String letOne = list.get(i);
            for (int j =0; j<list.size(); j++){
                String two = list.get(j);
                System.out.println(letOne + " , "+ two);
            }
            list.remove(letOne);
        }
    }
}
