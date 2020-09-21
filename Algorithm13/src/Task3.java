import java.io.*;
import java.util.*;

public class Task3 {
    FastScanner in;
    PrintWriter out;

    int[] pref_func(String s) {
        int n = s.length();
        int pi[] = new int[n];
        for (int i = 1; i < n; ++i) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi[j - 1];
            if (s.charAt(i) == s.charAt(j)) ++j;
            pi[i] = j;
        }
        return pi;
    }

    private void solve() throws IOException {
        String t = in.next();

        int pref[] = new int[t.length()];
        pref = pref_func(t);
        for (int i = 0; i < pref.length; i++) {
            out.print(pref[i] + " ");
        }
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
                new FastScanner(new FileInputStream("prefix.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("prefix.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}