package turing.machine.k;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 08.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("infixlogic.out"));
        bw.write("3\n" +
                "S _ _ _ -> S _ > _ > _ >\n" +
                "S 0 _ _ -> eval # > 0 > _ ^\n" +
                "S 1 _ _ -> eval # > 1 > _ ^\n" +
                "S ( _ _ -> eval # > _ ^ ( >\n" +
                "eval 0 _ _ -> eval # > 0 > _ ^\n" +
                "eval 1 _ _ -> eval # > 1 > _ ^\n" +
                "eval _ _ _ -> count _ < _ ^ _ <\n" +
                "eval o _ _ -> put_or # ^ _ ^ _ <\n" +
                "eval a _ _ -> put_and # ^ _ ^ _ <\n" +
                "eval ( _ _ -> set_bracket # ^ _ ^ _ ^\n" +
                "eval ) _ _ -> count_br # ^ _ ^ _ <\n" +
                "put_or # _ o -> or # ^ _ < o >\n" +
                "put_or # _ a -> and # ^ _ < o >\n" +
                "put_or # _ ( -> set_or # ^ _ ^ ( >\n" +
                "put_or # _ _ -> set_or # ^ _ ^ _ ^\n" +
                "set_or # _ _ -> eval # > _ ^ o >\n" +
                "put_and # _ a -> and # ^ _ < a >\n" +
                "put_and # _ o -> set_and # ^ _ ^ o >\n" +
                "put_and # _ ( -> set_and # ^ _ ^ ( >\n" +
                "put_and # _ _ -> set_and # ^ _ ^ _ ^\n" +
                "set_and # _ _ -> eval # > _ ^ a >\n" +
                "set_bracket # _ _ -> eval # > _ ^ ( >\n" +
                "count_br # _ o -> br_or # ^ _ < _ ^\n" +
                "count_br # _ a -> br_and # ^ _ < _ ^\n" +
                "count_br # _ ( -> eval # > _ ^ _ ^\n" +
                "count_br # _ _ -> eval # > _ ^ _ ^\n" +
                "br_or # 0 _ -> br_or0 # ^ _ < _ ^\n" +
                "br_or # 1 _ -> br_or1 # ^ _ < _ ^\n" +
                "br_or0 # 0 _ -> count_br # ^ 0 > _ <\n" +
                "br_or0 # 1 _ -> count_br # ^ 1 > _ <\n" +
                "br_or1 # 0 _ -> count_br # ^ 1 > _ <\n" +
                "br_or1 # 1 _ -> count_br # ^ 1 > _ <\n" +
                "br_and # 0 _ -> br_and0 # ^ _ < _ ^\n" +
                "br_and # 1 _ -> br_and1 # ^ _ < _ ^\n" +
                "br_and0 # 0 _ -> count_br # ^ 0 > _ <\n" +
                "br_and0 # 1 _ -> count_br # ^ 0 > _ <\n" +
                "br_and1 # 0 _ -> count_br # ^ 0 > _ <\n" +
                "br_and1 # 1 _ -> count_br # ^ 1 > _ <\n" +
                "or # 0 _ -> or0 # ^ _ < _ ^\n" +
                "or # 1 _ -> or1 # ^ _ < _ ^\n" +
                "or0 # 0 _ -> eval # > 0 > _ ^\n" +
                "or0 # 1 _ -> eval # > 1 > _ ^\n" +
                "or1 # 0 _ -> eval # > 1 > _ ^\n" +
                "or1 # 1 _ -> eval # > 1 > _ ^\n" +
                "and # 0 _ -> and0 # ^ _ < _ ^\n" +
                "and # 1 _ -> and1 # ^ _ < _ ^\n" +
                "and0 # 0 _ -> eval # > 0 > _ ^\n" +
                "and0 # 1 _ -> eval # > 0 > _ ^\n" +
                "and1 # 0 _ -> eval # > 0 > _ ^\n" +
                "and1 # 1 _ -> eval # > 1 > _ ^\n" +
                "count # _ o -> or _ < _ < _ ^\n" +
                "count # _ a -> and _ < _ < _ ^\n" +
                "count # _ _ -> put_res _ ^ _ < _ ^\n" +
                "put_res _ 0 _ -> AC 0 ^ _ ^ _ ^\n" +
                "put_res _ 1 _ -> AC 1 ^ _ ^ _ ^");
        bw.close();
    }
}
