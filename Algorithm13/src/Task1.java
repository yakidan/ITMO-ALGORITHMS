import java.io.*;
import java.util.*;

public class Task1 {
    FastScanner in;
    PrintWriter out;

    ArrayList<Integer> search(String p, String t) {
        ArrayList<Integer> list = new ArrayList<>();
        t = t + "   ";
        for (int i = 0; i < t.length()-p.length(); i++) {
            if (t.substring(i,i+ p.length()).equals(p)) {
                list.add(i);
            }
        }
        return list;
    }

    private void solve() throws IOException {
        String p = in.next();
        String t = in.next();
        ArrayList<Integer> ans = search(p, t);
        out.println(ans.size());
        for (Integer a :
                ans) {
            out.print(a+1 + " ");
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
                new FastScanner(new FileInputStream("search1.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("search1.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();
    }
}