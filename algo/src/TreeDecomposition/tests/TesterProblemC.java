package TreeDecomposition.tests;

import TreeDecomposition.C;

import java.io.IOException;

/**
 * Created by Telnov Sergey on 13.11.2017.
 */

public class TesterProblemC extends C {

    private static FastReader in;
    private static FastReader inLinkTest;
    private static FastReader out;
    @Override
    protected FastReader getScanner() {
        return in;
    }

    private void checkCurrVertex(final int index) {
        try {
            int correctVertex = inLinkTest.nextInt();
            if (correctVertex != index) {
                printPath(root);
                throw new RuntimeException("link test failed");
            }
        } catch (IOException e) {
            printPath(root);
            throw new RuntimeException("link test failed");
        }
    }

    public static void printPath(C.EulerTourTree.Node v) {
        if (v != null) {
            printPath(v.left);
            System.out.print(v.name + 1 + " ");
            printPath(v.right);
        }
    }

    private C.EulerTourTree.Node root = null;

    private void checkLink(C.EulerTourTree.Node vertex) {
        if (root == null) {
            root = vertex;
        }
        if (vertex == null) {
            return;
        }
        checkLink(vertex.left);
        checkCurrVertex(vertex.name + 1);
        checkLink(vertex.right);
    }

    private static int askCounter = 1;

    @Override
    protected void printResult(final int v) {
        final int value;
        try {
            value = out.nextInt();
        } catch (IOException e) {
            throw new RuntimeException("too many inputs");
        }
        if (v != value) {
            throw new RuntimeException("wrong answer");
        }
        askCounter++;
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i != 1; i++) {
            in = new FastReader("C:\\pro\\homework\\labs\\algo\\src\\TreeDecomposition\\tests\\testsC.in");
            inLinkTest = new FastReader("C:\\pro\\homework\\labs\\algo\\src\\TreeDecomposition\\tests\\linkTestsC.in");
            out = new FastReader("C:\\pro\\homework\\labs\\algo\\src\\TreeDecomposition\\tests\\testsC.out");
            int testCounter = 1;
            boolean testPassed = true;
            while (in.hasNext()) {
                askCounter = 1;
                try {
                    new TesterProblemC().run();
                } catch (RuntimeException e) {
                    System.out.println("test '" + testCounter + "' failed\non #" + askCounter + "\n" + e.getMessage());
                    testPassed = false;
                    break;
                }
                System.out.println(testCounter + " Ok");

                testCounter++;
            }
            if (!testPassed) {
                System.out.println("test failed");
                break;
            }
        }
    }
}
