package turing.machine.I;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 06.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("multiplication.out"));
        bw.write("start: start\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "start _ -> start _ >\n" +
                "start 0 -> write_symbol 0 <\n" +
                "start 1 -> write_symbol 1 <\n" +
                "write_symbol _ -> find # >\n" +
                "find # -> find # >\n" +
                "find 0 -> find 0 >\n" +
                "find 1 -> find 1 >\n" +
                "find 2 -> find 2 >\n" +
                "find 3 -> find 3 >\n" +
                "find * -> find_mul * >\n" +
                "find_mul 0 -> find_mul 0 >\n" +
                "find_mul 1 -> find_mul 1 >\n" +
                "find_mul _ -> mul _ <\n" +
                "mul 0 -> zero _ <\n" +
                "mul 1 -> one _ <\n" +
                "mul * -> clean _ <\n" +
                "zero 0 -> zero 0 <\n" +
                "zero 1 -> zero 1 <\n" +
                "zero * -> mul_fzero * <\n" +
                "mul_fzero 0 -> write_f0 2 <\n" +
                "mul_fzero 1 -> write_f0 3 <\n" +
                "write_f0 0 -> write_f0 0 <\n" +
                "write_f0 1 -> write_f0 1 <\n" +
                "write_f0 # -> f0 # <\n" +
                "f0 8 -> f0 8 <\n" +
                "f0 9 -> f0 9 <\n" +
                "f0 0 -> cont_mul0 8 >\n" +
                "f0 1 -> cont_mul0 9 >\n" +
                "f0 _ -> cont_mul0 8 >\n" +
                "cont_mul0 8 -> cont_mul0 8 >\n" +
                "cont_mul0 9 -> cont_mul0 9 >\n" +
                "cont_mul0 2 -> cont_mul0 2 >\n" +
                "cont_mul0 3 -> cont_mul0 3 >\n" +
                "cont_mul0 # -> find_val0 # >\n" +
                "find_val0 0 -> find_val0 0 >\n" +
                "find_val0 1 -> find_val0 1 >\n" +
                "find_val0 2 -> mul_zero 2 <\n" +
                "find_val0 3 -> mul_zero 3 <\n" +
                "mul_zero 0 -> write0 2 <\n" +
                "mul_zero 1 -> write0 3 <\n" +
                "mul_zero # -> rebuild_cur_res # <\n" +
                "write0 0 -> write0 0 <\n" +
                "write0 1 -> write0 1 <\n" +
                "write0 # -> wr0 # <\n" +
                "wr0 _ -> cont_mul0 2 >\n" +
                "wr0 0 -> cont_mul0 2 >\n" +
                "wr0 1 -> cont_mul0 3 >\n" +
                "wr0 2 -> wr0 2 <\n" +
                "wr0 3 -> wr0 3 <\n" +
                "wr0 8 -> wr0 8 <\n" +
                "wr0 9 -> wr0 9 <\n" +
                "one 0 -> one 0 <\n" +
                "one 1 -> one 1 <\n" +
                "one * -> mul_fone * <\n" +
                "mul_fone 0 -> write_f10 2 <\n" +
                "mul_fone 1 -> write_f1 3 <\n" +
                "write_f10 0 -> write_f10 0 <\n" +
                "write_f10 1 -> write_f10 1 <\n" +
                "write_f10 # -> f10 # <\n" +
                "f10 8 -> f10 8 <\n" +
                "f10 9 -> f10 9 <\n" +
                "f10 _ -> cont_mul1 8 >\n" +
                "f10 0 -> cont_mul1 8 >\n" +
                "f10 1 -> cont_mul1 9 >\n" +
                "write_f1 0 -> write_f1 0 <\n" +
                "write_f1 1 -> write_f1 1 <\n" +
                "write_f1 # -> f1 # <\n" +
                "f1 8 -> f1 8 <\n" +
                "f1 9 -> f1 9 <\n" +
                "f1 _ -> cont_mul1 9 >\n" +
                "f1 0 -> cont_mul1 9 >\n" +
                "f1 1 -> carry 8 <\n" +
                "carry 1 -> carry 0 <\n" +
                "carry 0 -> cont_mul1 1 >\n" +
                "carry _ -> cont_mul1 1 >\n" +
                "cont_mul1 0 -> cont_mul1 0 >\n" +
                "cont_mul1 1 -> cont_mul1 1 >\n" +
                "cont_mul1 2 -> cont_mul1 2 >\n" +
                "cont_mul1 3 -> cont_mul1 3 >\n" +
                "cont_mul1 8 -> cont_mul1 8 >\n" +
                "cont_mul1 9 -> cont_mul1 9 >\n" +
                "cont_mul1 # -> find_val1 # >\n" +
                "find_val1 0 -> find_val1 0 >\n" +
                "find_val1 1 -> find_val1 1 >\n" +
                "find_val1 2 -> mul_one 2 <\n" +
                "find_val1 3 -> mul_one 3 <\n" +
                "mul_one 0 -> write10 2 <\n" +
                "mul_one 1 -> write1 3 <\n" +
                "mul_one # -> rebuild_cur_res # <\n" +
                "write10 0 -> write10 0 <\n" +
                "write10 1 -> write10 1 <\n" +
                "write10 # -> wr10 # <\n" +
                "wr10 _ -> cont_mul1 2 >\n" +
                "wr10 0 -> cont_mul1 2 >\n" +
                "wr10 1 -> cont_mul1 3 >\n" +
                "wr10 2 -> wr10 2 <\n" +
                "wr10 3 -> wr10 3 <\n" +
                "wr10 8 -> wr10 8 <\n" +
                "wr10 9 -> wr10 9 <\n" +
                "write1 0 -> write1 0 <\n" +
                "write1 1 -> write1 1 <\n" +
                "write1 # -> wr1 # <\n" +
                "wr1 _ -> cont_mul1 3 >\n" +
                "wr1 0 -> cont_mul1 3 >\n" +
                "wr1 1 -> carry 2 <\n" +
                "wr1 2 -> wr1 2 <\n" +
                "wr1 3 -> wr1 3 <\n" +
                "wr1 8 -> wr1 8 <\n" +
                "wr1 9 -> wr1 9 <\n" +
                "rebuild_val 2 -> rebuild_val 0 >\n" +
                "rebuild_val 3 -> rebuild_val 1 >\n" +
                "rebuild_val * -> find_mul * >\n" +
                "clean 0 -> clean _ <\n" +
                "clean 1 -> clean _ <\n" +
                "clean # -> build_res _ <\n" +
                "build_res 8 -> build_res 0 <\n" +
                "build_res 9 -> build_res 1 <\n" +
                "build_res 3 -> build_res 1 <\n" +
                "build_res 2 -> build_res 0 <\n" +
                "build_res 1 -> build_res 1 <\n" +
                "build_res 0 -> build_res 0 <\n" +
                "build_res _ -> check _ >\n" +
                "check 0 -> check _ >\n" +
                "check _ -> ac 0 ^\n" +
                "check 1 -> ac 1 ^\n" +
                "rebuild_cur_res 9 -> rebuild_cur_res 9 <\n" +
                "rebuild_cur_res 8 -> rebuild_cur_res 8 <\n" +
                "rebuild_cur_res 3 -> rebuild_cur_res 1 <\n" +
                "rebuild_cur_res 2 -> rebuild_cur_res 0 <\n" +
                "rebuild_cur_res 1 -> back 1 >\n" +
                "rebuild_cur_res _ -> back _ >\n" +
                "back 0 -> back 0 >\n" +
                "back 1 -> back 1 >\n" +
                "back 8 -> back 8 >\n" +
                "back 9 -> back 9 >\n" +
                "back # -> rebuild_val # >");
        bw.close();
    }
}
