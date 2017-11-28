package TreeDecomposition.tests;

import TreeDecomposition.A;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by @author Telnov Sergey on 10.11.2017.
 */
public class TesterProblemA extends A {

    private static FastReader in;
    private static Scanner out;

    @Override
    protected FastReader getInputFile() throws IOException {
        return in;
    }

    @Override
    protected void getResult(int result) {
        if (out.nextInt() != result) {
            throw new RuntimeException("");
        }
    }

    private static int testCounter = 1;

    public static void main(String[] args) throws IOException {
        in = new FastReader("C:\\pro\\homework\\labs\\algo\\src\\TreeDecomposition\\tests\\testsA.in");
        out = new Scanner(new File("C:\\pro\\homework\\labs\\algo\\src\\TreeDecomposition\\tests\\testsA.out"));
        while (in.hasNext()) {
            try {
                new TesterProblemA().run();
                System.out.println("ok");
            } catch (RuntimeException e) {
                System.out.println("test '" + testCounter + "' failed\n" + e.getMessage());
                break;
            }
            testCounter++;
        }
    }
}
