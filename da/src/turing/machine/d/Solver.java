package turing.machine.d;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 06.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("tandem.out"));
        bw.write("start: start\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "start _ -> start _ >\n" +
                "start 0 -> change_next 0 ^\n" +
                "start 1 -> change_next 1 ^\n" +
                "change_next _ -> rj _ ^\n" +
                "change_next 0 -> to_end a >\n" +
                "change_next 1 -> to_end b >\n" +
                "change_next a -> was_middle a ^\n" +
                "change_next b -> was_middle b ^\n" +
                "to_end 0 -> to_end 0 >\n" +
                "to_end 1 -> to_end 1 >\n" +
                "to_end a -> change_prev a <\n" +
                "to_end b -> change_prev b <\n" +
                "to_end _ -> change_prev _ <\n" +
                "change_prev 0 -> to_start a <\n" +
                "change_prev 1 -> to_start b <\n" +
                "change_prev a -> was_middle a ^\n" +
                "change_prev b -> was_middle b ^\n" +
                "to_start 0 -> to_start 0 <\n" +
                "to_start 1 -> to_start 1 <\n" +
                "to_start a -> change_next a >\n" +
                "to_start b -> change_next b >\n" +
                "was_middle _ -> rj _ ^\n" +
                "was_middle a -> was_zero # <\n" +
                "was_middle b -> was_one # <\n" +
                "was_zero # -> was_zero # <\n" +
                "was_zero a -> was_zero a <\n" +
                "was_zero b -> was_zero b <\n" +
                "was_zero _ -> zero _ >\n" +
                "zero a -> get_next _ >\n" +
                "zero b -> rj b ^\n" +
                "zero # -> rj # ^\n" +
                "was_one _ -> one _ >\n" +
                "was_one # -> was_one # <\n" +
                "was_one a -> was_one a <\n" +
                "was_one b -> was_one b <\n" +
                "one a -> rj a ^\n" +
                "one b -> get_next _ >\n" +
                "one # -> rj # ^\n" +
                "get_next a -> get_next a >\n" +
                "get_next b -> get_next b >\n" +
                "get_next # -> get # >\n" +
                "get # -> get # >\n" +
                "get a -> was_zero # <\n" +
                "get b -> was_one # <\n" +
                "get _ -> check _ <\n" +
                "check # -> check # <\n" +
                "check a -> rj a ^\n" +
                "check b -> rj b ^\n" +
                "check _ -> ac _ ^");
        bw.close();
    }
}
