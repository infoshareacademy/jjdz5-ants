////////////////////////////////////////////
//  IT'S ONLY A TEST FILE!
//  CREATED TO CHECK THAT EVERYTHING WORKS
//  PROPERLY WITH "FileOperations" CLASS
////////////////////////////////////////////

public class ConfigTester {

    public static void main(String[] args) {

        Configuration file = new Configuration();

        System.out.println(file.getPlacesDB());
        System.out.println(file.getDefaultTR());
        System.out.println(file.getUserTR());
        System.out.println(file.getTripOTD());
        System.out.println();
        System.out.println(file.getTestFile());
        System.out.println();

        file.allLineReader(file.getTestFile());

    }
}