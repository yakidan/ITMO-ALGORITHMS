import java.io.*;
import java.util.*;

public class Lab2 {

    FastScanner in;
    PrintWriter out;

    public class Pair {
        int num = 0;
        Pair pair_prev = null;
        Pair pair_next = null;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int arr[]=new int[n+10];
        int h=1;
        int t=0;

        for (int i = 0; i < n; i++) {
            String[] s = in.nextLine().split(" ");
            if(s[0].equals("+")) {
                t++;
                int a=Integer.parseInt(s[1]);
                arr[t%n]=a;
            }

            else {
                out.println(arr[h%n]);
                h++;
            }
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
                new FastScanner(new FileInputStream("queue.in\n"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("queue.in\n"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Lab2().run();
    }
}