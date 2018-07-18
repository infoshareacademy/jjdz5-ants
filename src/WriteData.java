import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileWriter;

public class WriteData {

    public void WriteJSONArray(JSONArray array, String FILEPATH) {
        try {
            FileWriter updateArray = new FileWriter(new File(FILEPATH));
            updateArray.write(array.toJSONString());
            updateArray.close();
        }
        catch (java.io.IOException e) {
            System.out.println();
            System.out.println("BŁĄD AKTUALIZACJI PLIKU: \"" + FILEPATH + "\"!");
            System.out.println();
        }
    }

}
