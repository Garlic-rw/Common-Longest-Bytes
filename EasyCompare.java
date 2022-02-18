import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EasyCompare{
    byte[] binaryFile;
    String hexString;
    String fileName;

    public EasyCompare(String filePath, String fileName) throws IOException{
        Path path = Paths.get(filePath);
        this.binaryFile =  Files.readAllBytes(path);
        String hex = "";
  
        // Iterating through each byte in the array
        for (byte i : this.binaryFile) {
            hex += String.format("%02X", i);
        }
        this.hexString = hex;
        this.fileName = fileName;
    }

    public static LongestInfo longestCommonString(EasyCompare one, EasyCompare two) {
        String first = one.hexString;
        String second = two.hexString;
        int[][] common = new int[first.length()/2][second.length()/2];
        String rt = "";
        int longest = 0;
        int firstOffset = 0;
        int secondOffset = 0;
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
                        firstOffset = i - longest + 2;
                        secondOffset = j - longest + 2;
                    } 
                }
                else{
                    common[i/2][j/2] = 0;
                }
            }
        }
        return new LongestInfo(one.fileName, two.fileName, firstOffset, secondOffset, longest, rt.hashCode());
    }

    public static LongestInfo longestCommonStringFast(EasyCompare one, EasyCompare two) {
        String first = one.hexString;
        String second = two.hexString;
        int[][] common = new int[2][second.length()/2];
        String rt = "";
        int longest = 0;
        int firstOffset = 0;
        int secondOffset = 0;
        for (int j = 0; j < second.length(); j += 2) {
            if (first.substring(0, 2).equals(second.substring(j, j+2))){
                common[0][j/2] = 2;
                if (common[0][j/2] > longest) {
                    longest = common[0][j/2];
                    rt = first.substring(0-longest+2, 0+2); 
                    firstOffset = 0 - longest + 2;
                    secondOffset = j - longest + 2;
                } 
            }
            else{
                common[0][j/2] = 0;
            }
        }
        for (int i = 2; i< first.length(); i += 2) {
            for (int j = 0; j < second.length(); j += 2){
                if (first.substring(i, i + 2).equals(second.substring(j, j+2))){
                    if (i == 0 || j == 0) {
                        common[1][j/2] = 2;
                    } else {
                        common[1][j/2] = common[0][j/2 - 1] + 2;
                    }
                    if (common[1][j/2] > longest) {
                        longest = common[1][j/2];
                        rt = first.substring(i-longest+2, i+2); 
                        firstOffset = i - longest + 2;
                        secondOffset = j - longest + 2;
                    } 
                }
                else{
                    common[1][j/2] = 0;
                }
            }
            common[0] = common[1];
            common[1] = new int[second.length()/2];
        }
        return new LongestInfo(one.fileName, two.fileName, firstOffset, secondOffset, longest, rt.hashCode());
    }

    public static LongestInfo longestCommonFull(ArrayList<EasyCompare> input) {
        LongestInfo rt = new LongestInfo();
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++){
                LongestInfo compare = longestCommonStringFast(input.get(i), input.get(j));
                rt = rt.update(compare.names.get(0), compare.names.get(1), compare.offset.get(0), compare.offset.get(1), compare.length, compare.hashString);
            }
        }
        return rt;
    }

public static void main(String[] args) throws IOException {
    EasyCompare test1 = new EasyCompare("samples/sample.1", "sample.1");
    EasyCompare test2 = new EasyCompare("samples/sample.2", "sample.2");
    EasyCompare test3 = new EasyCompare("samples/sample.3", "sample.3");
    EasyCompare test4 = new EasyCompare("samples/sample.4", "sample.4");
    EasyCompare test5 = new EasyCompare("samples/sample.5", "sample.5");
    EasyCompare test6 = new EasyCompare("samples/sample.6", "sample.6");
    EasyCompare test7 = new EasyCompare("samples/sample.7", "sample.7");
    EasyCompare test8 = new EasyCompare("samples/sample.8", "sample.8");
    EasyCompare test9 = new EasyCompare("samples/sample.9", "sample.9");
    EasyCompare test10 = new EasyCompare("samples/sample.10", "sample.10");
    EasyCompare[] test = new EasyCompare[10];
    test[0] = test1;
    test[1] = test2;
    test[2] = test3;
    test[3] = test4;
    test[4] = test5;
    test[5] = test6;
    test[6] = test7;
    test[7] = test8;
    test[8] = test9;
    test[9] = test10;
    
    //System.out.println(longestCommonStringFast(test8, test9).length);
    
    ArrayList<EasyCompare> allSamples = new ArrayList<>();
    File folder = Paths.get(System.getProperty("user.dir") + "/samples").toFile();
    
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
        if (file.isFile()) {
            allSamples.add(new EasyCompare(System.getProperty("user.dir")+ "/samples/"+file.getName(), file.getName()));
        } 
    }
    LongestInfo result = longestCommonFull(allSamples);
    System.out.println(result.length);
    System.out.println(result.names);
    System.out.println(result.offset);
}

}
