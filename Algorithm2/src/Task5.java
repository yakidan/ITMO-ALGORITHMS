import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task5 {
    FastScanner in;
    PrintWriter out;


    void sort(int a[], int l, int r, int k) {
        int cur = a[(l + r) / 2];
        int i = l;
        int j = r;
        while (i <= j) {
            while (a[i] < cur)
                i++;
            while (a[j] > cur)
                j--;
            if (i <= j) {
                int x = a[i];
                a[i] = a[j];
                a[j] = x;
                i++;
                j--;
            }
        }
        if (l < j) {
            if (k <= j) {
                sort(a, l, j, k);
            }
        }
        if (i < r) {
            if (k >= i) {
                sort(a, i, r, k);
            }
        }
    }


    private void solve() throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] ar = new int[n];
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        ar[0] = in.nextInt();
        ar[1] = in.nextInt();
        for (int i = 2; i < n; i++) {
            ar[i] = a * ar[i - 2] + b * ar[i - 1] + c;
            //out.println(i + " элемент = " + "= " + ar[i]);
        }
        k--;
        sort(ar, 0, n - 1, k);
        out.println(ar[k]);
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
                new FastScanner(new FileInputStream("kth.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("kth.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task5().run();
    }
}