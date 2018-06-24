package turing.machine;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Telnov Sergey on 04.06.2018.
 */

/*
start: s
accept: ac
reject: rj
blank: _
s _ -> ac _ ^
s 0 -> n _ >
n 0 -> s _ >
n _ -> rj _ >
 */

public class A {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = Files
                .newBufferedWriter(Paths.get("zero.out"));
        bw.write(("start: s \n" +
                "accept: ac \n" +
                "reject: rj \n" +
                "blank: _ \n" +
                "s _ -> ac _ ^ \n" +
                "s 0 -> n _ > \n" +
                "n 0 -> s _ > \n" +
                "n _ -> rj _ >"));
        bw.close();
    }
}
