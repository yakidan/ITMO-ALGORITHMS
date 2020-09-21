import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task3 {
    FastScanner in;
    PrintWriter out;

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int first[] = new int[m];
        int end[] = new int[m];
        for (int i = 0; i < m; i++) {
            first[i] = in.nextInt();
            end[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if ((first[i] == first[j] && end[i] == end[j]) || (first[i] == end[j] && first[j] == end[i])) {
                    out.println("YES");
                    return;
                }
            }
        }
        out.println("NO");

    }

    class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        boolean hasNext() throws IOException {
            return br.ready() || (st != null && st.hasMoreTokens());
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }

        boolean hasNextLine() throws IOException {
            return br.ready();
        }

    }

    private void run() throws IOException {
        in = //new FastScanner(System.in);
                new FastScanner(new FileInputStream("input.txt"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("output.txt"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}