import java.io.*;
import java.util.*;

public class Task2 {

    FastScanner in;
    PrintWriter out;
    static String[] country;
    static String[] name;
    double ar[];

    boolean binarySearch() {
        for (int i = 2; i < ar.length; i++) {
            ar[i] = (ar[i - 1] + 1) * 2 - ar[i - 2];
            if (ar[i] < 0) {
                return false;
            }
        }
        return true;
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        double a = in.nextDouble();
        ar = new double[n];
        ar[0] = a;
        double min = 0;
        double max = ar[0];
        while (true) {
            ar[1]=(min+max)/2;
            if (ar[1] == max || ar[1] == min) {
                break;
            }
            if(binarySearch()){
                max=ar[1];
            }else{
                min=ar[1];
            }
        }
        out.printf("%.2f",ar[n-1]);
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
                new FastScanner(new FileInputStream("garland.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("garland.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}