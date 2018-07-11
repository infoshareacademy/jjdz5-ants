import java.io.*;
import java.util.Properties;

public class Configuration {

    private Properties cfg = new Properties();

    public String getPlacesDB(){
        loadConfig();
        return cfg.getProperty("placesDB");
    }

    public String getDefaultTR(){
        loadConfig();
        return cfg.getProperty("defaultTR");
    }

    public String getUserTR(){
        loadConfig();
        return cfg.getProperty("userTR");
    }

    public String getTripOTD(){
        loadConfig();
        return cfg.getProperty("tripOTD");
    }

    public String getTestFile(){
        loadConfig();
        return cfg.getProperty("testFile");
    }

    private void loadConfig() {

        try {
            cfg.load(new FileInputStream("resources/cfg.properties"));
        } catch (FileNotFoundException e) {
            System.out.println("NIE ZNALEZIONO PLIKU KONFIGURACYJNEGO!");
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU!");
        }
    }

    public void allLineReader(String FILEPATH){

        String line;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILEPATH))) {
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}