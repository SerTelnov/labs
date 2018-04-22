import java.io.IOException;

/**
 * Created by @author Telnov Sergey on 24.10.2017.
 */

public class Gym {

    private boolean correct(long num) {
        long val = num;
        int sum = 0;
        while (val > 0) {
            sum += val % 10;
            val /= 10;
        }
        for (int i = 1; i != 10; i++) {
            long curr = 1;
            for (int j = 1; j != i; j++) {
                curr *= sum;
            }
            if (curr == num) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        int count = 0;
        long num = 10;
        while (count != 15) {
            if (correct(num)) {
                System.out.println("num: '" + (count + 1) + "' value: '" + num + "'");
                count++;
            }
            num++;
        }
    }

    public static void main(String[] args) throws IOException {
        new Gym().run();
    }
}
