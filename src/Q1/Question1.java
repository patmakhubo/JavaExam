package Q1;
import java.util.HashMap;
import java.util.Hashtable;
public class Question1 {
    public static void main(String args[]) { 
        //----------hashtable ------------------------- 
        Hashtable<Integer,String> ht=new Hashtable<>(); 
        ht.put(100,"Thabang"); 
        ht.put(101,"Makhubo"); 
        ht.put(102,"Pat"); 
        System.out.println("-------------Hash table--------------"); 
        ht.entrySet().forEach(m -> {
            System.out.println(m.getKey()+" "+m.getValue());
        }); 
  
        //----------------hashmap-------------------------------- 
        HashMap<Integer,String> hm=new HashMap<>(); 
        hm.put(100,"Thabang"); 
        hm.put(101,"Makhubo"); 
        hm.put(102,"Pat"); 
        System.out.println("-----------Hash map-----------"); 
        hm.entrySet().forEach(m -> {
            System.out.println(m.getKey()+" "+m.getValue());
        }); 
    }
}
