import java.util.ArrayList;

public class LongestInfo {
    int length;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> offset = new ArrayList<>();
    int hashString;

    public LongestInfo(String fileOne, String fileTwo, int offsetOne, int offsetTwo, int len, String result){
        names.add(fileOne);
        names.add(fileTwo);
        offset.add(offsetOne);
        offset.add(offsetTwo);
        length = len;
        hashString = result.hashCode();
    }
    
    public LongestInfo() {
        this.length = 0;
        this.hashString = 0;
    }

    public LongestInfo update(String fileOne, String fileTwo, int offsetOne, int offsetTwo, int len, String result) {
        if (this.length > len) {
            return this;
        } else if (this.length < len) {
            return new LongestInfo(fileOne, fileTwo, offsetOne, offsetTwo, len, result);
        } else {
            if (this.hashString != result.hashCode()) {
                return this;
            } else {
                if (!names.contains(fileOne)){
                    names.add(fileOne);
                    offset.add(offsetOne);
                }
                if(!names.contains(fileTwo)){
                    names.add(fileTwo);
                    offset.add(offsetTwo);
                }
                return this;
            }
        }
    }
}

