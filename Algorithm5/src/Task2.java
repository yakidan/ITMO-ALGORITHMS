import java.awt.List;
import java.io.*;
import java.util.*;

public class Task2 {

    FastScanner in;
    PrintWriter out;
    public ArrayList<Pair> arr[];
    final static int n = 13539;

    boolean exist(ArrayList<Pair> arr, String k) {
        boolean have = false;
        if (arr == null) {
            return have;
        }
        for (Pair a : arr) {
            if (a.key.equals(k)) {
                have = true;
                break;
            }
        }
        return have;
    }

    String get_value(ArrayList<Pair> arr, String k) {
        String value = "";
        for (Pair a : arr) {
            if (a.key.equals(k)) {
                value = a.value;
                break;
            }
        }
        return value;
    }

    int get_index(ArrayList<Pair> arr, String k) {
        int index = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).key.equals(k)) {
                index = i;
                break;
            }
        }
        return index;
    }

    void delete(ArrayList<Pair> arr, String k) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).key.equals(k)) {
                arr.remove(i);
            }
        }
    }

    class Pair {
        String key;
        String value;

        Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public void solve() throws IOException {
        arr = new ArrayList[n];
        String operation;
        String key;
        String value;
        int k;
        int hash;
        boolean have = false;
        while (in.hasNextLine()) {
            operation = in.next();
            if (operation.equals("put")) {
                key = in.next();
                value = in.next();
                hash = Hashcode(key);
                if (arr[hash] == null) {
                    arr[hash] = new ArrayList<>();
                }
                int index = get_index(arr[hash], key);
                if (index ==-1) {
                    arr[hash].add(new Pair(key, value));
                } else {
                    arr[hash].set(index, new Pair(key, value));
                }

            } else if (operation.equals("get")) {
                key = in.next();
                hash = Hashcode(key);
                if (exist(arr[hash], key)) {
                    out.println(get_value(arr[hash], key));
                } else {
                    out.println("none");
                }

            } else if (operation.equals("delete")) {
                key = in.next();
                hash = Hashcode(key);
                if (exist(arr[hash], key)) {
                    delete(arr[hash], key);
                }
            }

        }


    }

    int Hashcode(String s) {
        int k = 0;
        k = Math.abs(s.hashCode());
        return k % n;
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
                new FastScanner(new FileInputStream("map.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("map.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}