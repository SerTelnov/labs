package turing.machine.g;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("less.out"));
        bw.write("start: start\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "start _ -> start _ >\n" +
                "start 0 -> s 0 ^\n" +
                "start 1 -> s 1 ^\n" +
                "s _ -> rj _ ^\n" +
                "s 0 -> s 0 >\n" +
                "s 1 -> s 1 >\n" +
                "s < -> find < <\n" +
                "find 0 -> was_zero # >\n" +
                "find 1 -> was_one # >\n" +
                "find # -> find # <\n" +
                "find _ -> check _ >\n" +
                "was_zero < -> was_zero < >\n" +
                "was_zero # -> was_zero # >\n" +
                "was_zero 0 -> was_zero 0 >\n" +
                "was_zero 1 -> was_zero 1 >\n" +
                "was_zero 2 -> sub_0 2 <\n" +
                "was_zero 3 -> sub_0 3 <\n" +
                "was_zero _ -> sub_0 _ <\n" +
                "sub_0 0 -> back 2 <\n" +
                "sub_0 1 -> back 3 <\n" +
                "sub_0 < -> rj < ^\n" +
                "was_one < -> was_one < >\n" +
                "was_one # -> was_one # >\n" +
                "was_one 0 -> was_one 0 >\n" +
                "was_one 1 -> was_one 1 >\n" +
                "was_one 2 -> sub_1 2 <\n" +
                "was_one 3 -> sub_1 3 <\n" +
                "was_one _ -> sub_1 _ <\n" +
                "sub_1 0 -> carry 3 <\n" +
                "carry 1 -> back 0 <\n" +
                "carry 0 -> carry 1 <\n" +
                "carry < -> rj < ^\n" +
                "sub_1 1 -> back 2 <\n" +
                "sub_1 < -> rj < ^\n" +
                "back 0 -> back 0 <\n" +
                "back 1 -> back 1 <\n" +
                "back < -> find < <\n" +
                "check # -> check # >\n" +
                "check < -> check < >\n" +
                "check 0 -> check 0 >\n" +
                "check 1 -> ac 1 ^\n" +
                "check 2 -> check 2 >\n" +
                "check 3 -> ac 3 ^\n" +
                "check _ -> rj _ ^");
        bw.close();
    }
}
