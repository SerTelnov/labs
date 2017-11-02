import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by @author Telnov Sergey on 16.10.2017.
 */
public class TestFactory {
    public static void main(String[] args) throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter("tests.out"));
        output.println(100);
        output.println();
        output.println(0);
        output.println(10);
        Random value = new Random();
        for (int i = 3; i != 100; i++) {
            for (int j = 0; j != i - 1; j++) {
                output.print(value.nextBoolean() ? 1 : 0);
            }
            output.println(1);
        }
        output.close();
    }
}
