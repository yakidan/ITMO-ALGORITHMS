import java.awt.List;
        import java.io.*;
        import java.util.*;

public class Task1 {

    FastScanner in;
    PrintWriter out;
    public ArrayList<Integer> arr[];

    boolean exist(ArrayList<Integer> arr, int k) {
        boolean have = false;
        if (arr == null) {
            return have;
        }
        for (Integer a : arr) {
            if (a == k) {
                have = true;
                break;
            }
        }
        return have;
    }

    void delete(ArrayList<Integer> arr, int k) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == k) {
                arr.remove(i);
            }
        }
    }

    public void solve() throws IOException {
        int n = 3539;
        arr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }
        String operation;
        int k;
        boolean have = false;
        while (in.hasNextLine()) {
            operation = in.next();
            k = in.nextInt();
            if (operation.equals("insert")) {
                if (arr[Math.abs(k % n)] == null) {
                    arr[Math.abs(k % n)] = new ArrayList<>();
                }
                if (!exist(arr[Math.abs(k % n)], k)) {
                    arr[Math.abs(k % n)].add(k);
                }
            } else if (operation.equals("exists")) {
                if (exist(arr[Math.abs(k % n)], k)) {
                    out.println("true");
                } else {
                    out.println("false");
                }

            } else if (operation.equals("delete")) {
                if (exist(arr[Math.abs(k % n)], k)) {
                    delete(arr[Math.abs(k % n)], k);
                }
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
                new FastScanner(new FileInputStream("set.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("set.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();
    }
}