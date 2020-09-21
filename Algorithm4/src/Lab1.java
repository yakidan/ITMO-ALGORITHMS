import java.io.*;
import java.util.*;

public class Lab1 {

    FastScanner in;
    PrintWriter out;



    public void solve() throws IOException {
        int n = in.nextInt();
        int arr[]=new int[n+10];
        int h=-1;
        for (int i = 0; i < n; i++) {
            String[] s = in.nextLine().split(" ");

            if(s[0].equals("+")) {
                h++;
                int a=Integer.parseInt(s[1]);
                arr[h]=a;
            }
            else {
                out.println(arr[h]);
                h--;
//                out.println(s[0]);
//                out.print(head.num);
//                if (i != n - 1) {
//                    out.println();
//                }
//                head = head.pair_prev;
//                head.pair_next = null;
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
        in = new FastScanner(System.in);
      //  new FastScanner(new FileInputStream("queue.in"));
        out = new PrintWriter(System.out);
     //   new PrintWriter(new FileOutputStream("queue.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Lab1().run();
    }
}