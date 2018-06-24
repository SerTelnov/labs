package turing.machine.f;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("reverse.out"));
        bw.write("start: start\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "start _ -> start _ >\n" +
                "start 0 -> s 0 >\n" +
                "start 1 -> s 1 >\n" +
                "s 0 -> s 0 >\n" +
                "s 1 -> s 1 >\n" +
                "s _ -> find_num _ <\n" +
                "find_num 2 -> find_num 2 <\n" +
                "find_num 3 -> find_num 3 <\n" +
                "find_num 4 -> find_num 4 <\n" +
                "find_num 5 -> find_num 5 <\n" +
                "find_num 0 -> append_zero 2 >\n" +
                "find_num 1 -> append_one 3 >\n" +
                "find_num _ -> rebuild _ >\n" +
                "rebuild 2 -> rebuild _ >\n" +
                "rebuild 3 -> rebuild _ >\n" +
                "rebuild 4 -> rebuild 0 >\n" +
                "rebuild 5 -> rebuild 1 >\n" +
                "rebuild _ -> to_start _ <\n" +
                "to_start 0 -> to_start 0 <\n" +
                "to_start 1 -> to_start 1 <\n" +
                "to_start _ -> ac _ >\n" +
                "append_zero _ -> find_num 4 <\n" +
                "append_zero 0 -> append_zero 0 >\n" +
                "append_zero 1 -> append_zero 1 >\n" +
                "append_zero 2 -> append_zero 2 >\n" +
                "append_zero 3 -> append_zero 3 >\n" +
                "append_zero 4 -> append_zero 4 >\n" +
                "append_zero 5 -> append_zero 5 >\n" +
                "append_one _ -> find_num 5 <\n" +
                "append_one 0 -> append_one 0 >\n" +
                "append_one 1 -> append_one 1 >\n" +
                "append_one 2 -> append_one 2 >\n" +
                "append_one 3 -> append_one 3 >\n" +
                "append_one 4 -> append_one 4 >\n" +
                "append_one 5 -> append_one 5 >\n");
        bw.close();
    }
}
