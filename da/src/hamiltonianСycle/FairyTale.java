package hamiltonianСycle;

import java.io.IOException;
import java.util.*;


/**
 * Created by @author Telnov Sergey on 14.10.2017.
 */

public class FairyTale {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        input.nextLine();
        List<Integer> path = new ArrayList<>(n);
        for (int i = 1; i != n + 1; i++) {
            path.add(i);
        }
        path.sort((Integer i, Integer j) -> {
            System.out.println(1 + " " + i + " " + j);
            System.out.flush();
            String answer = input.nextLine();
            return answer.equals("YES") ? -1 : 1;
        });
        System.out.print(0 + " ");
        for (int i = 0; i != n; i++) {
            System.out.print(path.get(i) + " ");
        }
        System.out.flush();
    }
}
