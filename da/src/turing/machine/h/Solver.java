package turing.machine.h;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 06.06.2018.
 */
public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("convertto2.out"));
        bw.write("start: start\n" +
                "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "start _ -> start _ >\n" +
                "start 0 -> put_symbols 0 <\n" +
                "start 1 -> put_symbols 1 <\n" +
                "start 2 -> put_symbols 2 <\n" +
                "put_symbols _ -> put_zero # <\n" +
                "put_zero _ -> s 0 >\n" +
                "s # -> s # >\n" +
                "s 0 -> s 0 >\n" +
                "s 1 -> s 1 >\n" +
                "s 2 -> s 2 >\n" +
                "s _ -> sub _ <\n" +
                "sub 0 -> find_carry 0 <\n" +
                "sub 1 -> to_new 0 <\n" +
                "sub 2 -> to_new 1 <\n" +
                "find_carry # -> clean # >\n" +
                "find_carry 0 -> find_carry 0 <\n" +
                "find_carry 1 -> add_carry3 0 >\n" +
                "find_carry 2 -> add_carry3 1 >\n" +
                "add_carry3 0 -> add_carry3 2 >\n" +
                "add_carry3 _ -> to_new _ <\n" +
                "to_new 0 -> to_new 0 <\n" +
                "to_new 1 -> to_new 1 <\n" +
                "to_new 2 -> to_new 2 <\n" +
                "to_new # -> add # <\n" +
                "add _ -> s 1 >\n" +
                "add 0 -> s 1 >\n" +
                "add 1 -> carry2 0 <\n" +
                "carry2 _ -> s 1 >\n" +
                "carry2 0 -> s 1 >\n" +
                "carry2 1 -> carry2 0 <\n" +
                "clean 0 -> clean _ >\n" +
                "clean _ -> to_start _ <\n" +
                "to_start 0 -> to_start 0 <\n" +
                "to_start 1 -> to_start 1 <\n" +
                "to_start _ -> to_start _ <\n" +
                "to_start # -> to_res _ <\n" +
                "to_res 0 -> to_res 0 <\n" +
                "to_res 1 -> to_res 1 <\n" +
                "to_res _ -> ac _ >");
        bw.close();
    }
}
