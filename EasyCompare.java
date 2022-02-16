import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EasyCompare{
    byte[] binaryFile;
    String hexString;
    String fileName;

    public EasyCompare(String filePath) throws IOException{
        Path path = Paths.get(filePath);
        this.binaryFile =  Files.readAllBytes(path);
        String hex = "";
  
        // Iterating through each byte in the array
        for (byte i : this.binaryFile) {
            hex += String.format("%02X", i);
        }
        this.hexString = hex;
    }
    

    public static String longestCommonString(String first, String second) {
        int[][] common = new int[first.length()/2][second.length()/2];
        String rt = "";
        int longest = 0;
        for (int i = 0; i< first.length(); i += 2) {
            for (int j = 0; j < second.length(); j += 2){
                if (first.substring(i, i + 2).equals(second.substring(j, j+2))){
                    if (i == 0 || j == 0) {
                        common[i/2][j/2] = 2;
                    } else {
                        common[i/2][j/2] = common[i/2 -1][j/2 - 1] + 2;
                    }
                    if (common[i/2][j/2] > longest) {
                        longest = common[i/2][j/2];
                        rt = first.substring(i-longest+2, i+2);   
                    } 
                }
                else{
                    common[i/2][j/2] = 0;
                }
            }
        }
        return rt;
    }

public static void main(String[] args) throws IOException {
    EasyCompare test1 = new EasyCompare("samples/sample.1");
    EasyCompare test2 = new EasyCompare("samples/sample.2");
    EasyCompare test3 = new EasyCompare("samples/sample.3");
    EasyCompare test4 = new EasyCompare("samples/sample.4");
    EasyCompare test5 = new EasyCompare("samples/sample.5");
    EasyCompare test6 = new EasyCompare("samples/sample.6");
    EasyCompare test7 = new EasyCompare("samples/sample.7");
    EasyCompare test8 = new EasyCompare("samples/sample.8");
    EasyCompare test9 = new EasyCompare("samples/sample.9");
    EasyCompare test10 = new EasyCompare("samples/sample.10");
    String testResult = longestCommonString(test1.hexString, test2.hexString);
    System.out.println(" with length: "+ testResult.length());
    System.out.println(testResult);

}
}