package turing.machine.b;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("aplusb.out"));
        bw.write("start: s\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s _ -> s _ >\n" +
                "s 0 -> s 0 >\n" +
                "s 1 -> s 1 >\n" +
                "s 2 -> s 2 >\n" +
                "s 3 -> s 3 >\n" +
                "s + -> was_plus + >\n" +
                "was_plus 0 -> was_plus 0 >\n" +
                "was_plus 1 -> was_plus 1 >\n" +
                "was_plus 2 -> was_plus 2 >\n" +
                "was_plus 3 -> was_plus 3 >\n" +
                "was_plus _ -> add _ <\n" +
                "add 0 -> was_zero _ <\n" +
                "add 1 -> was_one _ <\n" +
                "add + -> rebuild_num _ <\n" +
                "rebuild_num 2 -> rebuild_num 0 <\n" +
                "rebuild_num 3 -> rebuild_num 1 <\n" +
                "rebuild_num 0 -> rebuild_num 0 <\n" +
                "rebuild_num 1 -> rebuild_num 1 <\n" +
                "rebuild_num _ -> ac _ >\n" +
                "was_zero 0 -> was_zero 0 <\n" +
                "was_zero 1 -> was_zero 1 <\n" +
                "was_zero + -> add_zero + <\n" +
                "was_one 0 -> was_one 0 <\n" +
                "was_one 1 -> was_one 1 <\n" +
                "was_one + -> add_one + <\n" +
                "add_one 0 -> s 3 >\n" +
                "add_one 1 -> add_carry 2 <\n" +
                "add_one 2 -> add_one 2 <\n" +
                "add_one 3 -> add_one 3 <\n" +
                "add_one _ -> s 3 >\n" +
                "add_carry 1 -> add_carry 0 <\n" +
                "add_carry 0 -> s 1 >\n" +
                "add_carry _ -> s 1 >\n" +
                "add_zero 0 -> s 2 >\n" +
                "add_zero 1 -> s 3 >\n" +
                "add_zero 2 -> add_zero 2 <\n" +
                "add_zero 3 -> add_zero 3 <\n" +
                "add_zero _ -> s 2 >");
        bw.close();
    }
}
