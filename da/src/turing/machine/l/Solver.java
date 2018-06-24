package turing.machine.l;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 15.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("factorial.out"));

        bw.write("start: start\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "start _ -> start _ >\n" +
                "start 0 -> ac 1 ^\n" +
                "start 1 -> add_mul 1 <\n" +
                "add_mul _ -> copy_num * >\n" +
                "copy_num * -> copy_num * >\n" +
                "copy_num 0 -> copy_num 0 >\n" +
                "copy_num 1 -> copy_num 1 >\n" +
                "copy_num 2 -> put 2 <\n" +
                "copy_num 3 -> put 3 <\n" +
                "copy_num _ -> put _ <\n" +
                "put 0 -> put0 2 <\n" +
                "put 1 -> put1 3 <\n" +
                "put * -> rebuild# * >\n" +
                "put0 0 -> put0 0 <\n" +
                "put0 1 -> put0 1 <\n" +
                "put0 2 -> put0 2 <\n" +
                "put0 3 -> put0 3 <\n" +
                "put0 * -> put0 * <\n" +
                "put0 _ -> copy_num 0 >\n" +
                "put1 0 -> put1 0 <\n" +
                "put1 1 -> put1 1 <\n" +
                "put1 2 -> put1 2 <\n" +
                "put1 3 -> put1 3 <\n" +
                "put1 * -> put1 * <\n" +
                "put1 _ -> copy_num 1 >\n" +
                "rebuild# 2 -> rebuild# 0 >\n" +
                "rebuild# 3 -> rebuild# 1 >\n" +
                "rebuild# _ -> dec# _ <\n" +
                "dec# 1 -> zero 2 <\n" +
                "dec# 0 -> find_carry# 0 <\n" +
                "find_carry# 0 -> find_carry# 0 <\n" +
                "find_carry# 1 -> add_carry# 0 >\n" +
                "add_carry# 0 -> add_carry# 1 >\n" +
                "add_carry# _ -> get _ <\n" +
                "find_carry# * -> clean * >\n" +
                "rebuild_num 2 -> rebuild_num 0 <\n" +
                "rebuild_num 3 -> rebuild_num 1 <\n" +
                "rebuild_num * -> find * >\n" +
                "find * -> find * >\n" +
                "find # -> find # >\n" +
                "find 0 -> find 0 >\n" +
                "find 1 -> find 1 >\n" +
                "find 8 -> find 8 >\n" +
                "find 9 -> find 9 >\n" +
                "find 2 -> get 2 <\n" +
                "find 3 -> get 3 <\n" +
                "find _ -> get _ <\n" +
                "get 0 -> zero 2 <\n" +
                "get 1 -> one 3 <\n" +
                "get * -> rebuild_val * >\n" +
                "zero 0 -> zero 0 <\n" +
                "zero 1 -> zero 1 <\n" +
                "zero * -> mul0 * <\n" +
                "mul0 _ -> shift # <\n" +
                "mul0 0 -> mul0 0 <\n" +
                "mul0 1 -> mul0 1 <\n" +
                "mul0 8 -> mul0 0 <\n" +
                "mul0 9 -> mul0 1 <\n" +
                "mul0 # -> shift # <\n" +
                "shift 0 -> find 8 >\n" +
                "shift 1 -> find 9 >\n" +
                "shift 8 -> shift 8 <\n" +
                "shift 9 -> shift 9 <\n" +
                "shift _ -> find 8 >\n" +
                "one 0 -> one 0 <\n" +
                "one 1 -> one 1 <\n" +
                "one * -> mul1 * <\n" +
                "mul1 0 -> add0 2 <\n" +
                "mul1 1 -> add1 3 <\n" +
                "mul1 8 -> add0 2 <\n" +
                "mul1 9 -> add1 3 <\n" +
                "mul1 # -> to_start # ^\n" +
                "add0 0 -> add0 0 <\n" +
                "add0 1 -> add0 1 <\n" +
                "add0 8 -> add0 0 <\n" +
                "add0 9 -> add0 1 <\n" +
                "add0 _ -> plus0 # <\n" +
                "add0 # -> plus0 # <\n" +
                "plus0 0 -> mul_num 2 >\n" +
                "plus0 1 -> mul_num 3 >\n" +
                "plus0 2 -> plus0 2 <\n" +
                "plus0 3 -> plus0 3 <\n" +
                "plus0 8 -> plus0 8 <\n" +
                "plus0 9 -> plus0 9 <\n" +
                "plus0 _ -> mul_num 2 >\n" +
                "add1 0 -> add1 0 <\n" +
                "add1 1 -> add1 1 <\n" +
                "add1 8 -> add1 0 <\n" +
                "add1 9 -> add1 1 <\n" +
                "add1 _ -> plus1 # <\n" +
                "add1 # -> plus1 # <\n" +
                "plus1 0 -> mul_num 3 >\n" +
                "plus1 1 -> carry 2 <\n" +
                "plus1 2 -> plus1 2 <\n" +
                "plus1 3 -> plus1 3 <\n" +
                "plus1 8 -> plus1 8 <\n" +
                "plus1 9 -> plus1 9 <\n" +
                "plus1 _ -> mul_num 3 >\n" +
                "carry 1 -> carry 0 <\n" +
                "carry 0 -> mul_num 1 >\n" +
                "carry _ -> mul_num 1 >\n" +
                "mul_num 0 -> mul_num 0 >\n" +
                "mul_num 1 -> mul_num 1 >\n" +
                "mul_num 2 -> mul_num 2 >\n" +
                "mul_num 3 -> mul_num 3 >\n" +
                "mul_num 8 -> mul_num 8 >\n" +
                "mul_num 9 -> mul_num 9 >\n" +
                "mul_num # -> get_mul # >\n" +
                "get_mul 0 -> get_mul 0 >\n" +
                "get_mul 1 -> get_mul 1 >\n" +
                "get_mul 2 -> mul1 2 <\n" +
                "get_mul 3 -> mul1 3 <\n" +
                "to_start 0 -> to_start 0 <\n" +
                "to_start 1 -> to_start 1 <\n" +
                "to_start 2 -> to_start 2 <\n" +
                "to_start 3 -> to_start 3 <\n" +
                "to_start 8 -> add_shift 8 <\n" +
                "to_start 9 -> add_shift 9 <\n" +
                "to_start # -> add_shift # <\n" +
                "add_shift 8 -> add_shift 8 <\n" +
                "add_shift 9 -> add_shift 9 <\n" +
                "add_shift 2 -> to_start 8 <\n" +
                "add_shift 3 -> to_start 9 <\n" +
                "add_shift 0 -> to_start 8 <\n" +
                "add_shift 1 -> to_start 9 <\n" +
                "to_start _ -> rebuild_mul _ >\n" +
                "rebuild_mul 0 -> rebuild_mul 0 >\n" +
                "rebuild_mul 1 -> rebuild_mul 1 >\n" +
                "rebuild_mul 2 -> rebuild_mul 0 >\n" +
                "rebuild_mul 3 -> rebuild_mul 1 >\n" +
                "rebuild_mul 8 -> rebuild_mul 8 >\n" +
                "rebuild_mul 9 -> rebuild_mul 9 >\n" +
                "rebuild_mul # -> rebuild_mul # >\n" +
                "rebuild_mul * -> find * >\n" +
                "rebuild_val 2 -> rebuild_val 0 >\n" +
                "rebuild_val 3 -> rebuild_val 1 >\n" +
                "rebuild_val _ -> dec _ <\n" +
                "dec 1 -> to_start_first 0 <\n" +
                "dec 0 -> find_carry 0 <\n" +
                "find_carry 0 -> find_carry 0 <\n" +
                "find_carry 1 -> add_carry 0 >\n" +
                "add_carry 0 -> add_carry 1 >\n" +
                "add_carry _ -> to_start_first _ <\n" +
                "find_carry * -> clean * >\n" +
                "clean 0 -> clean _ >\n" +
                "clean _ -> delete _ <\n" +
                "to_start_first 0 -> to_start_first 0 <\n" +
                "to_start_first 1 -> to_start_first 1 <\n" +
                "to_start_first * -> change * >\n" +
                "change 0 -> change * >\n" +
                "change 1 -> swap 1 <\n" +
                "change * -> swap * >\n" +
                "change _ -> delete* _ <\n" +
                "swap * -> swap * >\n" +
                "swap _ -> clean* _ <\n" +
                "swap 0 -> swap-put0 * <\n" +
                "swap 1 -> swap-put1 * <\n" +
                "swap-put0 * -> swap-put0 * <\n" +
                "swap-put0 0 -> swap-put0 0 <\n" +
                "swap-put0 1 -> swap-put0 1 <\n" +
                "swap-put0 # -> swap-put-zero # >\n" +
                "swap-put0 2 -> swap-put-zero 2 >\n" +
                "swap-put0 3 -> swap-put-zero 3 >\n" +
                "swap-put-zero 0 -> swap_get 2 >\n" +
                "swap-put-zero 1 -> swap_get 2 >\n" +
                "swap-put1 * -> swap-put1 * <\n" +
                "swap-put1 0 -> swap-put1 0 <\n" +
                "swap-put1 1 -> swap-put1 1 <\n" +
                "swap-put1 # -> swap-put-one # >\n" +
                "swap-put1 2 -> swap-put-one 2 >\n" +
                "swap-put1 3 -> swap-put-one 3 >\n" +
                "swap-put-one 0 -> swap_get 3 >\n" +
                "swap-put-one 1 -> swap_get 3 >\n" +
                "swap_get 0 -> swap_get 0 >\n" +
                "swap_get 1 -> swap_get 1 >\n" +
                "swap_get * -> swap * >\n" +
                "delete* * -> delete* _ <\n" +
                "delete* 0 -> delete* _ <\n" +
                "delete* 1 -> delete* _ <\n" +
                "delete* # -> delete# _ <\n" +
                "delete _ -> delete _ <\n" +
                "delete 0 -> delete _ <\n" +
                "delete 1 -> delete _ <\n" +
                "delete * -> delete# _ <\n" +
                "delete# 0 -> delete# 0 <\n" +
                "delete# 1 -> delete# 1 <\n" +
                "delete# 8 -> delete# 0 <\n" +
                "delete# 9 -> delete# 1 <\n" +
                "delete# # -> ac _ >\n" +
                "delete# _ -> ac _ >\n" +
                "clean* * -> clean* _ <\n" +
                "clean* 2 -> clean* 0 <\n" +
                "clean* 3 -> clean* 1 <\n" +
                "clean* 0 -> clean* _ <\n" +
                "clean* 1 -> clean* _ <\n" +
                "clean* # -> find * >");

        bw.close();
    }
}
