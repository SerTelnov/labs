package TreeDecomposition.tests;

import TreeDecomposition.F;

import java.io.IOException;

/**
 * Created by Telnov Sergey on 24.11.2017.
 */
public class TesterProblemF extends F {

    private static FastReader in;
    private static FastReader answersReader;
    @Override
    protected FastReader getScanner() {
        return in;
    }

    @Override
    protected void getResult(final long res) {
        final long answer;
        try {
            answer = answersReader.nextLong();
        } catch (IOException e) {
            throw new RuntimeException("too many output values!!!");
        }
        if (res != answer) {
              throw new RuntimeException("wrong answer!!!\nyour answer: '" + res + "', correct: '" + answer + "'");
        }
    }

    private void runTest() throws IOException {
        in = new FastReader("C:\\pro\\homework\\labs\\algo\\test.in");
        answersReader = new FastReader("C:\\pro\\homework\\labs\\algo\\test.out");
        new TesterProblemF().run();
    }

    public static void main(String[] args) throws IOException {
        new TesterProblemF().runTest();
        System.out.println("OK");
    }
}
