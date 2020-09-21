import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.lang.Math.*;


public class Helping {
    private FastScanner in;
    private PrintWriter out;
    int p = (int) 1e9 + 9;

    int count, n, k;
    int[] y;
    void solve() throws IOException {
      int sum=0;
        for (int i = 10; i <10000 ; i++) {
            int a=i/1000;
            int b=(i/100)%10;
            int c=(i%100)/10;
            int d=i%10;

            if(a+b+c+d==10){
                sum++;
            out.println(i);
            }
        }
        out.print(sum);


    }

    void put1(int x) {

        if (x == k) {
            count++;
        } else {
            for (y[x] = 0; y[x] < n; ) {
                if (correct(x)) {
                    put1(x + 1);
                    y[x]++;
                    put1(x + 1);
                    y[x]++;
                }
            }
        }
    }
    void put2(int x) {

        if (x == k) {
            count++;
        } else {
            for (y[x] = 0; y[x] < n; y[x]++) {
                if (correct(x)) {
                    put2(x + 1);
                    putno(x+1);
                }
            }
        }
    }
    void putno(int x) {

        if (x == k) {
            count++;
        } else {
            for (y[x] = 0; y[x] < n; y[x]++) {
                if (correct(x)) {
                    put2(x + 1);
                    putno(x+1);
                }
            }
        }
    }

    boolean correct(int x) {
        boolean ok = true;
        for (int x1 = 0; x1 < x; x1++)
            if (y[x1] == y[x] || abs(x - x1) == abs(y[x] - y[x1]))
                ok = false;
        return ok;
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
        in = new FastScanner(System.in);
        out = new PrintWriter(System.out);
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Helping().run();

    }
}
