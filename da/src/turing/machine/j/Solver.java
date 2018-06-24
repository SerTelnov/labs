package turing.machine.j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 07.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("postfixlogic.out"));
        bw.write("1\n" +
                "S _ -> S _ >\n" +
                "S 0 -> evaluate 0 >\n" +
                "S 1 -> evaluate 1 >\n" +
                "evaluate 0 -> evaluate 0 >\n" +
                "evaluate 1 -> evaluate 1 >\n" +
                "evaluate o -> or # <\n" +
                "evaluate a -> and # <\n" +
                "evaluate # -> evaluate # >\n" +
                "or # -> or # <\n" +
                "or 0 -> eval_or0 # <\n" +
                "or 1 -> eval_or1 # <\n" +
                "or _ -> RJ _ ^\n" +
                "eval_or0 # -> eval_or0 # <\n" +
                "eval_or0 0 -> evaluate 0 >\n" +
                "eval_or0 1 -> evaluate 1 >\n" +
                "eval_or0 _ -> RJ _ ^\n" +
                "eval_or1 # -> eval_or1 # <\n" +
                "eval_or1 0 -> evaluate 1 >\n" +
                "eval_or1 1 -> evaluate 1 >\n" +
                "eval_or1 _ -> RJ _ ^\n" +
                "and # -> and # <\n" +
                "and 0 -> eval_and0 # <\n" +
                "and 1 -> eval_and1 # <\n" +
                "and _ -> RJ _ ^\n" +
                "eval_and0 # -> eval_and0 # <\n" +
                "eval_and0 0 -> evaluate 0 >\n" +
                "eval_and0 1 -> evaluate 0 >\n" +
                "eval_and0 _ -> RJ _ ^\n" +
                "eval_and1 # -> eval_and1 # <\n" +
                "eval_and1 0 -> evaluate 0 >\n" +
                "eval_and1 1 -> evaluate 1 >\n" +
                "eval_and1 _ -> RJ _ ^\n" +
                "evaluate _ -> clean _ <\n" +
                "clean # -> clean _ <\n" +
                "clean 0 -> AC 0 ^\n" +
                "clean 1 -> AC 1 ^");
        bw.close();
    }
}
