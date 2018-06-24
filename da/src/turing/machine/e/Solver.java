package turing.machine.e;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

/**
 * Created by Telnov Sergey on 04.06.2018.
 */

public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("balanced.out"));
        bw.write("start: start\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "start _ -> ac _ ^\n" +
                "start ( -> s ( ^\n" +
                "start ) -> rj ) ^\n" +
                "s ( -> s ( >\n" +
                "s ) -> find_open # <\n" +
                "s _ -> check _ <\n" +
                "s # -> s # >\n" +
                "find_open ( -> s # >\n" +
                "find_open _ -> rj _ ^\n" +
                "find_open # -> find_open # <\n" +
                "check ( -> rj ( ^\n" +
                "check _ -> ac _ ^\n" +
                "check # -> check # <");

        bw.close();
    }
}
