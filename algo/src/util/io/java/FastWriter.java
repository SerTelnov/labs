package util.io.java;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FastWriter implements Closeable {

    private BufferedWriter bw;
    private StringBuilder sb = new StringBuilder();

    public FastWriter() {
        bw = new BufferedWriter(
                new OutputStreamWriter(System.out));
    }

    public FastWriter(String fileName) throws IOException {
        bw = Files.newBufferedWriter(Paths.get(fileName));
    }

    public void println(int i) {
        sb.append(i).append('\n');
    }

    public void println(long l) {
        sb.append(l).append('\n');
    }

    public void println(String s) {
        sb.append(s).append('\n');
    }

    public void println() {
        sb.append('\n');
    }

    public void println(double d) {
        sb.append(d).append('\n');
    }

    public void println(char[] chArr) {
        sb.append(chArr).append('\n');
    }

    public void print(char ch) {
        sb.append(ch);
    }

    public void print(String s) {
        sb.append(s).append(' ');
    }

    public void print(int i) {
        sb.append(i).append(' ');
    }

    public void print(double d) {
        sb.append(d).append(' ');
    }

    public void close() throws IOException {
        bw.write(sb.toString());
        bw.close();
    }
}
