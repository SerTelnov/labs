package algorithmsOnLines;

/**
 * Created by Telnov Sergey on 05.12.2017.
 */
public class N {


    private class StringComparator {
        public StringComparator(String s) {
            length = s.length();
            pow = new long[length];
            pow[0] = 1;

            hash = new long[length];
            hash[0] = s.charAt(0) - 'a' + 1;

            for (int i = 1; i != length; i++) {
                pow[i] = pow[i - 1] * P;
                hash[i] = (s.charAt(i) - 'a' + 1) * pow[i] + hash[i - 1];
            }
        }

        private long getHash(int start, int end) {
            if (start == 0) {
                return hash[end];
            } else {
                return hash[end] - hash[start - 1];
            }
        }

        public boolean isSameString(final int startA, final int endA, final int startB, final int endB) {
            return endA - startA == endB - startB &&
                    getHash(startA, endA) * pow[startB] == getHash(startB, endB) * pow[startA];
        }

        final int P = 31;
        final int length;
        long[] pow;
        long[] hash;
    }
}
