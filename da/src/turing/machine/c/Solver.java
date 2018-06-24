package turing.machine.c;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("mirror.out"));
        bw.write("start: start\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "start _ -> start _ >\n" +
                "start 0 -> s 0 ^\n" +
                "start 1 -> s 1 ^\n" +
                "s 0 -> s 0 >\n" +
                "s 1 -> s 1 >\n" +
                "s _ -> append _ <\n" +
                "append 0 -> zero 2 >\n" +
                "append 1 -> one 3 >\n" +
                "append 2 -> append 2 <\n" +
                "append 3 -> append 3 <\n" +
                "append _ -> rebuild _ >\n" +
                "zero 0 -> zero 0 >\n" +
                "zero 1 -> zero 1 >\n" +
                "zero 2 -> zero 2 >\n" +
                "zero 3 -> zero 3 >\n" +
                "zero _ -> back 0 <\n" +
                "one 0 -> one 0 >\n" +
                "one 1 -> one 1 >\n" +
                "one 2 -> one 2 >\n" +
                "one 3 -> one 3 >\n" +
                "one _ -> back 1 <\n" +
                "back 0 -> back 0 <\n" +
                "back 1 -> back 1 <\n" +
                "back 2 -> append 2 <\n" +
                "back 3 -> append 3 <\n" +
                "back _ -> rebuild _ >\n" +
                "rebuild 2 -> rebuild 0 >\n" +
                "rebuild 3 -> rebuild 1 >\n" +
                "rebuild 0 -> finish 0 <\n" +
                "rebuild 1 -> finish 1 <\n" +
                "finish 0 -> finish 0 <\n" +
                "finish 1 -> finish 1 <\n" +
                "finish _ -> ac _ >");
        bw.close();
    }
}
