import java.io.*;
import java.util.*;

public class Lab3 {

    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int h = -1;
        boolean b = true;
        while (in.hasNextLine()) {
            String s = in.next();
            h = -1;
            b = true;
            char arr[] = new char[s.length() + 1];
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (h == -1) {
                    h++;
                    arr[h] = c;
                } else {
                    h++;
                    arr[h] = c;
                    if ((arr[h - 1] == '(' && arr[h] == ']') || (arr[h - 1] == '[' && arr[h] == ')')) {
                        b = false;
                        break;
                    }
                    if ((arr[h - 1] == '(' && arr[h] == ')') || (arr[h - 1] == '[' && arr[h] == ']')) {
                        h -= 2;
                    }
                }
            }
            if (h != -1) {
                b = false;
            }
            if (b) {
                out.println("YES");
            } else {
                out.println("NO");
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
                new FastScanner(new FileInputStream("brackets.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("brackets.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Lab3().run();
    }
}