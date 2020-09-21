import java.io.*;
import java.util.*;

public class Lab4 {

    FastScanner in;
    PrintWriter out;


    public void solve() throws IOException {


        int h = -1;
        boolean b = true;
        String s[] = in.nextLine().split(" ");
        String arr[] = new String[s.length + 10];
        long a = -1;
        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            if (!str.equals("+") && !str.equals("-") && !str.equals("*")) {
                h++;
                arr[h] = str;
            } else if (str.equals("+")) {
                a = Long.parseLong(arr[h]) + Long.parseLong(arr[h - 1]);
                h -= 1;
                arr[h] = String.valueOf(a);
            } else if (str.equals("-")) {
                a = Long.parseLong(arr[h - 1]) - Long.parseLong(arr[h]);
                h -= 1;
                arr[h] = String.valueOf(a);
            } else {
                a = Long.parseLong(arr[h]) * Long.parseLong(arr[h - 1]);
                h -= 1;
                arr[h] = String.valueOf(a);
            }
        }
        out.println(a);
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
                new FastScanner(new FileInputStream("postfix.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("postfix.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Lab4().run();
    }
}