import java.io.*;
import java.util.*;

public class Task {
    FastScanner in;
    PrintWriter out;

    private void solve() throws IOException {
        int n = in.nextInt();
        int ar[] = new int[n];
        ArrayList<Integer> b = new ArrayList();
        HashSet<Integer> hash = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ar[i] = in.nextInt();
            hash.add(ar[i]);
        }
        for (int i = n - 1; i >= 0; i--) {
            if (hash.contains(ar[i])) {
                hash.remove(ar[i]);
            } else {
                b.add(ar[i]);
            }
        }
        Collections.reverse(b);
        out.println(b.size());
        for (int i = 0; i < b.size(); i++) {
            out.print(b.get(i) + " ");
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
                new FastScanner(new FileInputStream("input.txt"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("output.txt"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task().run();
    }
}