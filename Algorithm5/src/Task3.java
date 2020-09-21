import java.awt.List;
import java.io.*;
import java.util.*;

public class Task3 {

    FastScanner in;
    PrintWriter out;
    public ArrayList<Pair> arr[];
    final static int n = 3539;
    Pair prev ;
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
        if(arr==null){
            return -1;
        }
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
                if(arr.get(i).equals(prev)){
                    prev=arr.get(i).prev;
                }
                if (arr.get(i).prev != null) {
                    arr.get(i).prev.next = arr.get(i).next;
                }
                if (arr.get(i).next != null) {
                    arr.get(i).next.prev = arr.get(i).prev;
                }
                arr.remove(i);
                break;
            }
        }
    }

    class Pair {

        public String key;
        public String value;
        public Pair prev;
        public Pair next;

        Pair(String key, String value, Pair prev, Pair next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }


    public void solve() throws IOException {
        arr = new ArrayList[n];
        String operation;
        String key;
        String value;
        int hash;
        boolean have = false;
        prev = null;
        Pair next = null;
        Pair current = null;
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
                if (index == -1) {
                    arr[hash].add(new Pair(key, value, null, null));
                    current=arr[hash].get(arr[hash].size()-1);
                    if (prev == null) {
                        prev = current;
                    } else {
                        prev.next = current;
                        current.prev = prev;
                        prev = current;
                    }
                } else {
                    arr[hash].get(index).value = value;
                }
            } else if (operation.equals("get")) {
                key = in.next();
                hash = Hashcode(key);
                if (get_index(arr[hash], key) != -1) {
                    out.println(get_value(arr[hash], key));
                } else {
                    out.println("none");
                }
            } else if (operation.equals("delete")) {
                key = in.next();
                hash = Hashcode(key);
                if (get_index(arr[hash], key) != -1) {
                    delete(arr[hash], key);
                }
            } else if (operation.equals("prev")) {
                key = in.next();
                hash = Hashcode(key);
                int index = get_index(arr[hash], key);
                if (index != -1 && arr[hash].get(index).prev != null) {
                    out.println(arr[hash].get(index).prev.value);
                } else {
                    out.println("none");
                }
            } else if (operation.equals("next")) {
                key = in.next();
                hash = Hashcode(key);
                int index = get_index(arr[hash], key);
                if (index != -1 && arr[hash].get(index).next != null) {
                    out.println(arr[hash].get(index).next.value);
                } else {
                    out.println("none");
                }
            }
        }
    }

    int Hashcode(String s) {
        int k = Math.abs(s.hashCode());
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
                new FastScanner(new FileInputStream("linkedmap.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("linkedmap.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}