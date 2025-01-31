package maptool;

import java.io.File;

public class JMPfiles {
    static void saveFile(File f, jmp jmp) {

    }
    static jmp loadFile(File f) {
        jmp d = new jmp();

        return d;
    }
    static jmp newFile(File f) {
        jmp d = new jmp();
        saveFile(f, d);
        return loadFile(f);
    }
}
