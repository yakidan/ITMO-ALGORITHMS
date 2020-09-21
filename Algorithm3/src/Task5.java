import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task5 {
    FastScanner in;
    PrintWriter out;

    String[] sort(String s[], int j) {
        int len = 'z' - 'A' + 1;
        String s_new[] = new String[s.length];
        int ar[] = new int[len];
        for (int i = 0; i < s.length; i++) {
            ar[s[i].charAt(j) - 'A']++;
        }
        for (int i = 1; i < len; i++) {
            ar[i] = ar[i] + ar[i - 1];
        }
        int q;
        for (int i = s.length - 1; i >= 0; i--) {
            ar[s[i].charAt(j) - 'A']--;
            s_new[ar[s[i].charAt(j) - 'A']]=s[i];
        }
        return s_new;

    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        String s[] = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }
        int j = m - 1;
        String[] ans = sort(s, j);
        k--;
        j--;
        while (k > 0) {
            ans = sort(ans, j);
            k--;
            j--;
        }


        for (int i = 0; i < ans.length; i++) {
            out.println(ans[i]);
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
                new FastScanner(new FileInputStream("radixsort.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("radixsort.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task5().run();
    }
}