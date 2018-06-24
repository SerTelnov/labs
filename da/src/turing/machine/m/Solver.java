package turing.machine.m;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 10.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("sorting.out"));
        bw.write("2\n" +
                "S _ _ -> S _ > _ >\n" +
                "S 0 _ -> start 0 ^ _ ^\n" +
                "S 1 _ -> start 1 ^ _ ^\n" +
                "start 0 _ -> copy1 0 > 0 >\n" +
                "start 1 _ -> copy1 1 > 1 >\n" +
                "copy1 0 _ -> copy1 0 > 0 >\n" +
                "copy1 1 _ -> copy1 1 > 1 >\n" +
                "copy1 | _ -> check_next | > | ^\n" +
                "copy1 _ _ -> clean_rebuild_num | ^ | ^\n" +
                "check_next 0 | -> copy2 0 ^ | >\n" +
                "check_next 1 | -> copy2 1 ^ | >\n" +
                "check_next _ | -> clean_num _ < | <\n" +
                "check_next 2 | -> clean_rebuild_num 2 < | ^\n" +
                "check_next 3 | -> clean_rebuild_num 3 < | ^\n" +
                "copy2 0 _ -> copy2 0 > 0 >\n" +
                "copy2 1 _ -> copy2 1 > 1 >\n" +
                "copy2 2 _ -> clean_rebuild_num 2 < _ <\n" +
                "copy2 3 _ -> clean_rebuild_num 3 < _ <\n" +
                "copy2 | _ -> cmp | ^ _ <\n" +
                "copy2 _ _ -> cmp | ^ _ <\n" +
                "cmp | 0 -> cmp | ^ 0 <\n" +
                "cmp | 1 -> cmp | ^ 1 <\n" +
                "cmp | | -> get_sub | ^ | <\n" +
                "get_sub | 0 -> zero | ^ 2 >\n" +
                "get_sub | 1 -> one | ^ 3 >\n" +
                "get_sub | 2 -> get_sub | ^ 2 <\n" +
                "get_sub | 3 -> get_sub | ^ 3 <\n" +
                "get_sub | _ -> less | ^ _ ^\n" +
                "zero | 2 -> zero | ^ 2 >\n" +
                "zero | 3 -> zero | ^ 3 >\n" +
                "zero | | -> sub0 | ^ | >\n" +
                "sub0 | 0 -> sub0 | ^ 0 >\n" +
                "sub0 | 1 -> sub0 | ^ 1 >\n" +
                "sub0 | 2 -> sb0 | ^ 2 <\n" +
                "sub0 | 3 -> sb0 | ^ 3 <\n" +
                "sub0 | _ -> sb0 | ^ _ <\n" +
                "sb0 | 0 -> cmp | ^ 2 <\n" +
                "sb0 | 1 -> cmp | ^ 3 <\n" +
                "sb0 | | -> more | ^ | ^\n" +
                "one | 2 -> one | ^ 2 >\n" +
                "one | 3 -> one | ^ 3 >\n" +
                "one | | -> sub1 | ^ | >\n" +
                "sub1 | 0 -> sub1 | ^ 0 >\n" +
                "sub1 | 1 -> sub1 | ^ 1 >\n" +
                "sub1 | 2 -> sb1 | ^ 2 <\n" +
                "sub1 | 3 -> sb1 | ^ 3 <\n" +
                "sub1 | _ -> sb1 | ^ _ <\n" +
                "sb1 | 0 -> find_carry | ^ 3 <\n" +
                "find_carry | 0 -> find_carry | ^ 0 <\n" +
                "find_carry | 1 -> add_carry | ^ 0 >\n" +
                "find_carry | | -> more | ^ | ^\n" +
                "add_carry | 0 -> add_carry | ^ 1 >\n" +
                "add_carry | 3 -> cmp | ^ 3 <\n" +
                "sb1 | 1 -> cmp | ^ 2 <\n" +
                "sb1 | | -> more | ^ | ^\n" +
                "less | _ -> clean_copies | ^ _ >\n" +
                "clean_copies | 0 -> clean_copies | ^ _ >\n" +
                "clean_copies | 1 -> clean_copies | ^ _ >\n" +
                "clean_copies | 2 -> clean_copies | ^ _ >\n" +
                "clean_copies | 3 -> clean_copies | ^ _ >\n" +
                "clean_copies | | -> clean_copies | ^ | >\n" +
                "clean_copies | _ -> back_copies | ^ _ <\n" +
                "back_copies | _ -> back_copies | ^ _ <\n" +
                "back_copies | | -> to_prev_num | < _ >\n" +
                "to_prev_num 0 _ -> to_prev_num 0 < _ ^\n" +
                "to_prev_num 1 _ -> to_prev_num 1 < _ ^\n" +
                "to_prev_num | _ -> copy1 | > _ ^\n" +
                "more | | -> to_end | ^ | >\n" +
                "to_end | 2 -> to_end | ^ 2 >\n" +
                "to_end | 3 -> to_end | ^ 3 >\n" +
                "to_end | _ -> copy_values | < _ <\n" +
                "copy_values 0 0 -> copy_values # < 0 <\n" +
                "copy_values 0 1 -> copy_values # < 0 <\n" +
                "copy_values 0 2 -> copy_values # < 0 <\n" +
                "copy_values 0 3 -> copy_values # < 0 <\n" +
                "copy_values 1 0 -> copy_values # < 1 <\n" +
                "copy_values 1 1 -> copy_values # < 1 <\n" +
                "copy_values 1 2 -> copy_values # < 1 <\n" +
                "copy_values 1 3 -> copy_values # < 1 <\n" +
                "copy_values | | -> copy_values # < | <\n" +
                "copy_values _ _ -> to2 _ > _ >\n" +
                "copy_values | _ -> to2 | > _ >\n" +
                "to2 # 0 -> to2 # ^ 0 >\n" +
                "to2 # 1 -> to2 # ^ 1 >\n" +
                "to2 # | -> more_copy2 # ^ | >\n" +
                "more_copy2 # 0 -> more_copy2 0 > | >\n" +
                "more_copy2 # 1 -> more_copy2 1 > | >\n" +
                "more_copy2 # _ -> to1 # ^ _ <\n" +
                "to1 # | -> to1 # ^ _ <\n" +
                "to1 # 0 -> to1 # ^ 0 <\n" +
                "to1 # 1 -> to1 # ^ 1 <\n" +
                "to1 # _ -> more_copy1 | > _ >\n" +
                "more_copy1 # 0 -> more_copy1 0 > _ >\n" +
                "more_copy1 # 1 -> more_copy1 1 > _ >\n" +
                "more_copy1 | _ -> to_prev_num | < _ >\n" +
                "more_copy1 _ _ -> to_prev_num _ < _ <\n" +
                "clean_num | 0 -> clean_num _ ^ 0 ^\n" +
                "clean_num _ 0 -> clean_num _ ^ _ <\n" +
                "clean_num | 1 -> clean_num _ ^ 1 ^\n" +
                "clean_num _ 1 -> clean_num _ ^ _ <\n" +
                "clean_num _ _ -> back _ ^ _ >\n" +
                "back _ _ -> back _ ^ _ >\n" +
                "back _ | -> back_exp _ ^ _ ^\n" +
                "back_exp _ _ -> rebuild_end _ < _ <\n" +
                "to_start 0 _ -> to_start 0 < _ <\n" +
                "to_start 1 _ -> to_start 1 < _ <\n" +
                "to_start | _ -> to_start | < _ <\n" +
                "to_start _ _ -> start _ > _ >\n" +
                "rebuild_end 0 _ -> rebuild_end 2 < _ ^\n" +
                "rebuild_end 1 _ -> rebuild_end 3 < _ ^\n" +
                "rebuild_end _ _ -> rebuild_num _ > _ ^\n" +
                "rebuild_end | _ -> to_start | < _ ^\n" +
                "clean_rebuild_num | | -> cl_reb_num | ^ | <\n" +
                "cl_reb_num | 0 -> cl_reb_num | ^ _ <\n" +
                "cl_reb_num | 1 -> cl_reb_num | ^ _ <\n" +
                "cl_reb_num | | -> cl_reb_num | ^ _ <\n" +
                "cl_reb_num | _ -> back_cl_reb_num | ^ _ >\n" +
                "back_cl_reb_num | _ -> back_cl_reb_num | ^ _ >\n" +
                "back_cl_reb_num | | -> rebuild_end | < _ ^\n" +
                "rebuild_num 2 _ -> rebuild_num 0 > _ ^\n" +
                "rebuild_num 3 _ -> rebuild_num 1 > _ ^\n" +
                "rebuild_num | _ -> rebuild_num | > _ ^\n" +
                "rebuild_num _ _ -> check_br _ < _ <\n" +
                "check_br 0 _ -> to_ac 0 < _ ^\n" +
                "check_br 1 _ -> to_ac 1 < _ ^\n" +
                "check_br | _ -> to_ac _ < _ ^\n" +
                "to_ac 0 _ -> to_ac 0 < _ ^\n" +
                "to_ac 1 _ -> to_ac 1 < _ ^\n" +
                "to_ac | _ -> to_ac | < _ ^\n" +
                "to_ac _ _ -> AC _ > _ ^");
        bw.close();
    }
}
