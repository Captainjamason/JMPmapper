package maptool;
public class jmp {
    /// Variables/Datatypes
    public int[][] data = new int[8][8];
    public int[][] testData = {
        {1,0,0,0,0,0,0,0},
        {0,1,0,0,0,0,0,0},
        {0,0,1,0,0,0,0,0},
        {0,0,0,1,0,0,0,0},
        {0,0,0,0,1,0,0,0},
        {0,0,0,0,0,1,0,0},
        {0,0,0,0,0,0,1,0},
        {0,0,0,0,0,0,0,1},
    };

    /// Public functions.
    // Encode JMP string from jmp.data or int[][]
    public String encodeJmp(int[][] data, String mapName) {
        String out = "";

        out += "{$JMP*"+mapName+"\n";   //  Header.
        out += "%"+"\n";    //  String Seperator.
        for(int x = 0; x < 8; x++) {    // Inject our data.
            for(int y = 0; y < 8; y++) {
                out += data[x][y];
                if(y == 7) {
                    out += ";";
                } else {
                    out += ",";
                }
            }
            out += "\n";
        }
        out += "#\n}"; 

        return out;
    }

    // Decode String into int[][],
    public int[][] decodeJmp(String s) {
        int[][] out = new int[8][8];
        boolean isData = false;
        int idCount = 0;

        String[] byLine = s.split("\n");
        for(int i = 0; i < byLine.length; i++) {
            if(isData && idCount < 8) {
                if(byLine[i].contains(";")) {
                    byLine[i] = byLine[i].replace(";", "");
                }
                String[] d = byLine[i].split(",");
                for(int y = 0; y < d.length; y++) {
                    out[idCount][y] = Integer.parseInt(d[y]);
                }
                idCount++;
            }
            if(byLine[i].contains("%")) {
                isData = true;
            }
            if(byLine[i].contains("#")) {
                isData = false;
            }
        }

        return out;
    }
}
