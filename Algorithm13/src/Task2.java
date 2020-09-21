import java.io.*;
import java.util.*;

public class Task2 {
    FastScanner in;
    PrintWriter out;
    long k = 651649;
    int x = 11;
    long step[];

    ArrayList<Integer> search(String p, String t) {
        int pref[];
        String s=p+"#"+t;
        pref = pref_func(s);

        ArrayList<Integer> ans = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < t.length() ; i++) {
            if (pref[p.length() + i + 1] == p.length())
                ans.add(i-p.length()+1);
        }
        return ans;
    }

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
        String p = in.next();
        String t = in.next();

        ArrayList<Integer> ans = search(p, t);
        out.println(ans.size());
        for (Integer a :
                ans) {
            out.print(a + 1 + " ");
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
                new FastScanner(new FileInputStream("search2.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("search2.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}