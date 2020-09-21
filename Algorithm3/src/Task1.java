import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task1{
    FastScanner in;
    PrintWriter out;
    Scanner scanner = new Scanner(System.in);

    int binarysearchLeft(int[] arr, int x) {
        int m = -1;
        int l = 0;
        int r = arr.length - 1;
        while (r - l > 1) {
            m = (l + r) / 2;
            if (arr[m] < x) {
                l = m;
            } else {
                r = m;
            }

        }
        if (arr[r] != x)
            return -1;
        else
            return r + 1;
    }

    int binarysearchRight(int[] arr, int x) {
        int m = -1;
        int l = 0;
        int r = arr.length - 1;
        while (r - l > 1) {
            m = (l + r) / 2;
            if (arr[m] <= x) {
                l = m;
            } else {
                r = m;
            }
        }
        return r ;
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int m = in.nextInt();
        int j = 0;
        for (int i = 0; i < m; i++) {
            j = in.nextInt();
            if (binarysearchLeft(arr, j) == -1) {
                out.println("-1 -1");
                continue;
            }
            if (arr[0] == j) {
                if (arr[n - 1] == j) {
                    out.println(1 + " " + n);
                } else {
                    out.println(1 + " " + binarysearchRight(arr, j));
                }
                continue;
            }
            if (arr[n - 1] == j) {
                out.println(binarysearchLeft(arr, j) + " " + n);
                continue;
            }
            out.println(binarysearchLeft(arr, j) + " " + binarysearchRight(arr, j));
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
                new FastScanner(new FileInputStream("binsearch.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("binsearch.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();
    }
}