import java.awt.List;
import java.io.*;
import java.util.*;

public class Task4 {

    FastScanner in;
    PrintWriter out;
    public ArrayList<Pair> arr[][];
    final static int n = 641;

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

    int get_index(ArrayList<Pair> arr, String v) {
        int index = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).value.equals(v)) {
                index = i;
                break;
            }
        }
        return index;
    }

    boolean having_same(ArrayList<Pair> arr, String k, String val) {
        boolean can = false;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).key.equals(k) && arr.get(i).value.equals(val)) {
                can = true;
                break;
            }
        }
        return can;
    }

    void delete(ArrayList<Pair> arr, String k, String val) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).key.equals(k) && arr.get(i).value.equals(val)) {
                arr.remove(i);
                break;
            }
        }
    }

    void deleteall(ArrayList<Pair> arr, String k) {
        int size = arr.size();
        int i = 0;
        while (i < size) {
            if (arr.get(i).key.equals(k)) {
                arr.remove(i);
                size--;
            } else {
                i++;
            }
        }
    }

    int size(ArrayList<Pair> arr, String key) {
        int size = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).key.equals(key)) {
                size++;
            }
        }
        return size;
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
        arr = new ArrayList[n][n];
        String operation;
        String key;
        String value;
        int hash_key;
        int hash_value;
        int size = 0;
        while (in.hasNextLine()) {
            operation = in.next();
            if (operation.equals("put")) {
                key = in.next();
                value = in.next();
                hash_key = Hashcode(key);
                hash_value = Hashcode(value);
                if (arr[hash_key][hash_value] == null) {
                    arr[hash_key][hash_value] = new ArrayList<>();
                }
                if (!having_same(arr[hash_key][hash_value], key, value)) {
                    arr[hash_key][hash_value].add(new Pair(key, value));
                }
            } else if (operation.equals("get")) {
                key = in.next();
                hash_key = Hashcode(key);
                ArrayList<String> ans = new ArrayList<>();
                size = 0;
                for (int i = 0; i < n; i++) {
                    if (arr[hash_key][i] != null) {
                        for (int j = 0; j < arr[hash_key][i].size(); j++) {
                            if (arr[hash_key][i].get(j).key.equals(key)) {
                                size++;
                                ans.add(arr[hash_key][i].get(j).value);
                            }
                        }
                    }
                }
                if (size > 0) {
                    out.print(size + " ");
                    for (String p :
                            ans) {
                        out.print(p + " ");
                    }
                } else {
                    out.print(0);
                }
                out.println();
            } else if (operation.equals("delete")) {
                key = in.next();
                value = in.next();
                hash_key = Hashcode(key);
                hash_value = Hashcode(value);
                if (arr[hash_key][hash_value] != null) {
                    delete(arr[hash_key][hash_value], key, value);
                }
            } else if (operation.equals("deleteall")) {
                key = in.next();
                hash_key = Hashcode(key);
                for (int i = 0; i < n; i++) {
                    if (arr[hash_key][i] != null) {
                        for (int j = 0; j < arr[hash_key][i].size(); ) {
                            if (arr[hash_key][i].get(j).key.equals(key)) {
                                arr[hash_key][i].remove(j);
                            } else {
                                j++;
                            }
                        }
                    }
                }
            }

        }


    }

    int Hashcode(String s) {
        int k = Math.abs(s.hashCode());
        return k % n;
    }

    class Listing {
        Pair head = null;
        Pair tail = null;

        class Pair {
            int value;
            Pair next;
            Pair prev;

            Pair(int value, Pair prev, Pair next) {
                this.value = value;
                this.prev = prev;
                this.next = next;
            }
        }

        void insert(int a) {
            if (head != null) {
                Pair cur = new Pair(a,tail.prev!=null?tail.prev:null,null);
                tail.next=cur;
                cur.prev=tail;
                tail=cur;
            }else{
                head= new Pair(a,null,null);
                tail=head;
            }
        }
        void delete(int a){
            Pair cur=head;
            while(cur!=null){
                if(cur.value==a){
                    if(cur.prev!=null&&cur.next!=null){
                        cur.prev.next=cur.next;
                        cur.next.prev=cur.prev;
                    }else if(cur.prev!=null&& cur.next==null){
                        cur.prev.next=null;
                        tail=cur.prev;
                    }else if(cur.prev==null&& cur.next!=null){
                        cur.next.prev=null;
                        head=cur.next;
                    }
                }else{
                    cur=cur.next;
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
                new FastScanner(new FileInputStream("multimap.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("multimap.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task4().run();
    }
}