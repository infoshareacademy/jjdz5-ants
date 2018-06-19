
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class test {
    public static void main(String[] args)
            throws java.io.IOException{
        JSONObject obj = new JSONObject();

        obj.put("test1", "value1");

        JSONObject obj2 = new JSONObject();






        obj2.put("id", 0);
        obj2.put("name", "testname");
        obj2.put("test2", obj);

        String message = obj.toString();

        System.out.println(message);
        String message2 = obj2.toString();
        System.out.println(message2);

        File file = new File("pliczek.txt");
        if(!file.exists())
            file.createNewFile();

        PrintWriter zapis = new PrintWriter("pliczek.txt");
        zapis.println(message);
        zapis.println(message2);

        FileWriter cos = new FileWriter("pliczek.txt");
        zapis.close();
    }
}
