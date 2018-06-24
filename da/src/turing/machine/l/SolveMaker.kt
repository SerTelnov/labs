package turing.machine.l

import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * Created by Telnov Sergey on 15.06.2018.
 */

fun main(args: Array<String>) {
    var value = BigInteger("1")

    val joiner = StringJoiner("\n")

    for (i in 1..30) {
        value = value.multiply(i.toBigInteger())

        joiner.add("to_end$i 0 -> to_end$i 0 >")
        joiner.add("to_end$i 1 -> to_end$i 1 >")
        joiner.add("to_end$i _ -> dec$i _ <")
        joiner.add("dec$i 1 -> check$i 0 <")
        joiner.add("check$i 1 -> to_end${i + 1} 1 >")
        joiner.add("check$i 0 -> check$i 0 <")
        joiner.add("check$i _ -> clean$i _ >")
        joiner.add("dec$i 0 -> find_carry$i 0 <")
        joiner.add("find_carry$i 0 -> find_carry$i 0 <")
        joiner.add("find_carry$i 1 -> add_carry$i 0 >")
        joiner.add("find_carry$i _ -> clean$i _ >")
        joiner.add("add_carry$i 0 -> add_carry$i 1 >")
        joiner.add("add_carry$i _ -> dec${i + 1} _ <")
        joiner.add("clean$i 0 -> clean$i _ >")
        joiner.add("clean$i _ -> ac ${value.toString(2)} ^")
    }

    joiner.add("dec31 0 -> rj 0 ^")
    joiner.add("dec31 1 -> rj 1 ^")
    joiner.add("to_end31 0 -> rj 0 ^")
    joiner.add("to_end31 1 -> rj 1 ^")


    val bw = Files.newBufferedWriter(Paths.get("temp-factorial.out"))
    bw.write("start: start\n" +
            "accept: ac\n" +
            "reject: rj\n" +
            "blank: _\n" +
            "start _ -> start _ >\n" +
            "start 0 -> ac 1 ^\n" +
            "start 1 -> to_end1 1 ^\n")

    bw.write(joiner.toString())
    bw.close()

    val bw2 = Files.newBufferedWriter(Paths.get("temp-fact.txt"))
    val s = "start: s\n" +
            "accept: ac\n" +
            "reject: rj\n" +
            "blank: _\n" +

            "s 1 -> print# 1 < \n" +
            "s 0 -> print# 0 <\n" +


            "print# _ -> print* # >\n" +

            "print* 1 -> print* 1 > \n" +
            "print* 0 -> print* 0 >\n" +
            "print* _ -> to_start_for_copy * <\n" +

            "to_start_for_copy 1 -> to_start_for_copy 1 <\n" +
            "to_start_for_copy 0 -> to_start_for_copy 0 <\n" +
            "to_start_for_copy # -> copy_first # >\n" +
            "to_start_for_copy * -> copy_second * >\n" +

            "copy_first 1 -> paste1 3 >\n" +
            "copy_first 0 -> paste0 2 >\n" +
            "copy_first 2 -> copy_first 2 >\n" +
            "copy_first 3 -> copy_first 3 >\n" +
            "copy_first * -> change_first * <\n" +

            "copy_second 1 -> paste_second1 3 >\n" +
            "copy_second 0 -> paste_second0 2 >\n" +
            "copy_second 2 -> copy_second 2 >\n" +
            "copy_second 3 -> copy_second 3 >\n" +
            "copy_second - -> change_second - <\n" +

            "paste1 1 -> paste1 1 >\n" +
            "paste1 0 -> paste1 0 >\n" +
            "paste1 * -> paste1 * >\n" +
            "paste1 _ -> back_to_copy 1 <\n" +

            "paste0 1 -> paste0 1 >\n" +
            "paste0 0 -> paste0 0 >\n" +
            "paste0 * -> paste0 * >\n" +
            "paste0 _ -> back_to_copy 0 <\n" +

            "paste_second1 1 -> paste_second1 1 >\n" +
            "paste_second1 0 -> paste_second1 0 >\n" +
            "paste_second1 - -> paste_second1 - >\n" +
            "paste_second1 _ -> back_to_copy_second 1 <\n" +

            "paste_second0 1 -> paste_second0 1 >\n" +
            "paste_second0 0 -> paste_second0 0 >\n" +
            "paste_second0 - -> paste_second0 - >\n" +
            "paste_second0 _ -> back_to_copy_second 0 <\n" +

            "back_to_copy 1 -> back_to_copy 1 <\n" +
            "back_to_copy 0 -> back_to_copy 0 <\n" +
            "back_to_copy 2 -> back_to_copy 2 <\n" +
            "back_to_copy 3 -> back_to_copy 3 <\n" +
            "back_to_copy * -> back_to_copy * <\n" +
            "back_to_copy # -> copy_first # >\n" +

            "back_to_copy_second 1 -> back_to_copy_second 1 <\n" +
            "back_to_copy_second 0 -> back_to_copy_second 0 <\n" +
            "back_to_copy_second 2 -> back_to_copy_second 2 <\n" +
            "back_to_copy_second 3 -> back_to_copy_second 3 <\n" +
            "back_to_copy_second - -> back_to_copy_second - <\n" +
            "back_to_copy_second * -> copy_second * >\n" +

            "change_first 2 -> change_first 0 <\n" +
            "change_first 3 -> change_first 1 <\n" +
            "change_first # -> to_second # >\n" +

            "change_second 2 -> change_second 0 <\n" +
            "change_second 3 -> change_second 1 <\n" +
            "change_second * -> to_second_second * >\n" +

            "to_second 1 -> to_second 1 >\n" +
            "to_second 0 -> to_second 0 >\n" +
            "to_second * -> to_second * >\n" +
            "to_second _ -> to_start_for_copy - <\n" +

            "to_second_second 1 -> to_second_second 1 >\n" +
            "to_second_second 0 -> to_second_second 0 >\n" +
            "to_second_second - -> to_second_second - >\n" +
            "to_second_second _ -> sub_right _ <\n" +

            "sub_right 1 -> to_middle 0 <\n" +
            "sub_right 0 -> sub_right 1 <\n" +
            "sub_right - -> ac _ ^\n" +

            "to_middle 1 -> to_middle 1 <\n" +
            "to_middle 0 -> to_middle 0 <\n" +
            "to_middle - -> clear_middle - <\n" +

            "clear_middle 1 -> clear_middle _ <\n" +
            "clear_middle 0 -> clear_middle _ <\n" +
            "clear_middle 2 -> clear_middle _ <\n" +
            "clear_middle 3 -> clear_middle _ <\n" +
            "clear_middle * -> to_third_start * >\n" +

            "to_third_start _ -> to_third_start _ >\n" +
            "to_third_start - -> to_third_end - >\n" +

            "to_third_end 1 -> to_third_end 1 >\n" +
            "to_third_end 0 -> to_third_end 0 >\n" +
            "to_third_end _ -> check_third_zero _ <\n" +

            "check_third_zero 0 -> check_third_zero 0 <\n" +
            "check_third_zero 1 -> back_to_third_end 1 >\n" +

            "back_to_third_end 1 -> back_to_third_end 1 >\n" +
            "back_to_third_end 0 -> back_to_third_end 0 >\n" +
            "back_to_third_end _ -> copy_third_to_second _ <\n" +

            "check_third_zero - -> to_end_for_ans - >\n" +

            "to_end_for_ans 0 -> to_end_for_ans 0 >\n" +
            "to_end_for_ans _ -> clear_tape _ <\n" +

            "clear_tape 0 -> clear_tape _ <\n" +
            "clear_tape 1 -> clear_tape _ <\n" +
            "clear_tape - -> clear_tape _ <\n" +
            "clear_tape _ -> clear_tape _ <\n" +
            "clear_tape * -> to_ans _ <\n" +

            "to_ans 1 -> to_ans 1 <\n" +
            "to_ans 0 -> to_ans 0 <\n" +
            "to_ans # -> ac _ >\n" +

            "copy_third_to_second 1 -> paste_to_second1 3 <\n" +
            "copy_third_to_second 0 -> paste_to_second0 2 <\n" +
            "copy_third_to_second - -> change_third - >\n" +

            "paste_to_second1 1 -> paste_to_second1 1 <\n" +
            "paste_to_second1 0 -> paste_to_second1 0 <\n" +
            "paste_to_second1 - -> paste_to_second1_1 - <\n" +

            "paste_to_second1_1 1 -> paste_to_second1_1 1 <\n" +
            "paste_to_second1_1 0 -> paste_to_second1_1 0 <\n" +
            "paste_to_second1_1 * -> back_to_count * >\n" +
            "paste_to_second1_1 _ -> back_to_count 1 >\n" +

            "paste_to_second0 1 -> paste_to_second0 1 <\n" +
            "paste_to_second0 0 -> paste_to_second0 0 <\n" +
            "paste_to_second0 - -> paste_to_second0_0 - <\n" +

            "paste_to_second0_0 1 -> paste_to_second0_0 1 <\n" +
            "paste_to_second0_0 0 -> paste_to_second0_0 0 <\n" +
            "paste_to_second0_0 * -> back_to_count * >\n" +
            "paste_to_second0_0 _ -> back_to_count 0 >\n" +

            "back_to_count 1 -> back_to_count 1 >\n" +
            "back_to_count 0 -> back_to_count 0 >\n" +
            "back_to_count - -> continue_copy - >\n" +

            "continue_copy 1 -> continue_copy 1 >\n" +
            "continue_copy 0 -> continue_copy 0 >\n" +
            "continue_copy 3 -> copy_third_to_second 3 <\n" +
            "continue_copy 2 -> copy_third_to_second 2 <\n" +

            "change_third 3 -> change_third 1 > \n" +
            "change_third 2 -> change_third 0 >\n" +
            "change_third _ -> to_second_for_count _ <\n" +

            "to_second_for_count 1 -> to_second_for_count 1 <\n" +
            "to_second_for_count 0 -> to_second_for_count 0 <\n" +
            "to_second_for_count - -> sub1 - <\n" +


            "check0 1 -> check0 1 >\n" +
            "check0 0 -> check0 0 >\n" +
            "check0 * -> to_end_first * >\n" +

            /* "check00 1 -> to_end_first 1 >\n" +
                "check00 0 -> ans_0 _ <\n" +

                "ans_0 1 -> ans_0 _ <\n" +
                "ans_0 0 -> ans_0 _ <\n" +
                "ans_0 * -> ans_0 _ <\n" +
                "ans_0 # -> ac 0 ^\n" +*/


            "to_end_first 1 -> to_end_first 1 >\n" +
            "to_end_first 0 -> to_end_first 0 >\n" +
            "to_end_first * -> to_end_first * >\n" +
            "to_end_first - -> sub1 - <\n" +

            /* "check_all_zero 0 -> check_all_zero 0 <\n" +
                "check_all_zero * -> start_copy_first * <\n" +
                "check_all_zero 1 -> to_sub1 1 >\n" +

                "to_sub1 0 -> to_sub1 0 >\n" +
                "to_sub1 1 -> to_sub1 1 >\n" +
                "to_sub1 - -> sub 1 - <\n" +*/



            "sub1 1 -> to_multiply_skip 0 <\n" +
            "sub1 0 -> sub1 1 <\n" +
            "sub1 * -> skip_first * <\n" +

            "skip_first 1 -> skip_first ! <\n" +
            "skip_first 0 -> skip_first ! <\n" +
            "skip_first # -> copy_first_lol ! <\n" +

            "copy_first_lol 1 -> copy1 ! >\n" +
            "copy_first_lol 0 -> copy0 ! >\n" +
            "copy_first_lol _ -> delete! _ >\n" +

            "copy1 ! -> copy1 ! >\n" +
            "copy1 1 -> print1_after_copy 1 <\n" +
            "copy1 0 -> print1_after_copy 0 <\n" +
            "copy1 * -> print1_after_copy * <\n" +

            "print1_after_copy ! -> to_next_copy 1 <\n" +
            "print0_after_copy ! -> to_next_copy 0 <\n" +

            "copy0 ! -> copy0 ! >\n" +
            "copy0 1 -> print0_after_copy 1 <\n" +
            "copy0 0 -> print0_after_copy 0 <\n" +
            "copy0 * -> print0_after_copy * <\n" +

            "to_next_copy ! -> to_next_copy ! <\n" +
            "to_next_copy 1 -> copy1 ! >\n" +
            "to_next_copy 0 -> copy0 ! >\n" +
            "to_next_copy _ -> delete! _ >\n" +

            "delete! ! -> delete! _ >\n" +
            "delete! 1 -> print## 1 <\n" +
            "delete! 0 -> print## 0 <\n" +

            "print## _ -> to_right_num # >\n" +

            "to_right_num 1 -> to_right_num 1 >\n" +
            "to_right_num 0 -> to_right_num 0 >\n" +
            "to_right_num * -> to_right_num * >\n" +
            "to_right_num - -> to_right_num - >\n" +
            "to_right_num _ -> sub_right _ < \n" +


            "to_multiply_skip 1 -> to_multiply_skip 1 <\n" +
            "to_multiply_skip 0 -> to_multiply_skip 0 <\n" +
            "to_multiply_skip * -> to_multiply * <\n" +

            "to_multiply 1 -> plus1_skip 3 <\n" +
            "to_multiply 0 -> plus0_skip 2 <\n" +
            "to_multiply 3 -> to_multiply 3 <\n" +
            "to_multiply 2 -> to_multiply 2 <\n" +
            "to_multiply # -> to_start # <\n" +

            "plus1_skip 1 -> plus1_skip 1 <\n" +
            "plus1_skip 0 -> plus1_skip 0 <\n" +
            "plus1_skip # -> plus1 # <\n" +
            "plus0_skip 1 -> plus0_skip 1 <\n" +
            "plus0_skip 0 -> plus0_skip 0 <\n" +
            "plus0_skip # -> plus0 # <\n" +

            "plus1 3 -> plus1 3 <\n" +
            "plus1 2 -> plus1 2 <\n" +
            "plus1 0 -> back 3 >\n" +
            "plus1 1 -> plus11 2 <\n" +
            "plus1 _ -> check_prev _ >\n" +

            "plus11 0 -> back 1 >\n" +
            "plus11 1 -> plus11 0 <\n" +
            "plus11 _ -> back 1 >\n" +

            "check_prev # -> print3 # <\n" +
            "check_prev 2 -> print1 2 <\n" +
            "check_prev 1 -> print1 1 <\n" +
            "check_prev 3 -> print1 3 <\n" +
            "check_prev 0 -> print1 0 <\n" +

            "print3 _ -> back 3 >\n" +
            "print1 _ -> back 3 >\n" +

            "plus0 3 -> plus0 3 <\n" +
            "plus0 2 -> plus0 2 <\n" +
            "plus0 1 -> back 3 >\n" +
            "plus0 0 -> back 2 >\n" +
            "plus0 _ -> back 2 >\n" +

            "back 3 -> back 3 >\n" +
            "back 2 -> back 2 >\n" +
            "back 1 -> back 1 >\n" +
            "back 0 -> back 0 >\n" +
            "back # -> back_second # >\n" +

            "back_second 1 -> back_second 1 >\n" +
            "back_second 0 -> back_second 0 >\n" +
            "back_second 2 -> to_multiply 2 <\n" +
            "back_second 3 -> to_multiply 3 <\n" +

            "to_start 2 -> to_start 2 <\n" +
            "to_start 1 -> to_start 1 <\n" +
            "to_start 0 -> to_start 0 <\n" +
            "to_start 3 -> to_start 3 <\n" +
            "to_start _ -> clear_2_num _ >\n" +

            "clear_2_num 1 -> clear_2_num 1 >\n" +
            "clear_2_num 0 -> clear_2_num 0 >\n" +
            "clear_2_num 2 -> clear_2_num 0 >\n" +
            "clear_2_num 3 -> clear_2_num 1 >\n" +
            "clear_2_num # -> clear_2_num # >\n" +
            "clear_2_num * -> to_end_first * >\n" +
            //пока не использую


            ""
    bw2.write(s)
    bw2.close()
}