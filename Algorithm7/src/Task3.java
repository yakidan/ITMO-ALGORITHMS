import java.awt.List;
import java.io.*;
import java.util.*;

public class Task3 {

    FastScanner in;
    PrintWriter out;
    public ArrayList<Integer> arr[];
    Top root = null;
    Top[] out_top;

    class Top {
        Top left;
        Top right;
        int count;
        Top root;

        public void setIndex(int index) {
            this.index = index;
        }

        int index;
        int index_left = 0;
        int index_right = 0;

        public void setHeight(int height) {
            this.height = height;
        }

        int height;

        public void setLeft(Top left) {
            this.left = left;
        }

        public void setRight(Top right) {
            this.right = right;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setRoot(Top root) {
            this.root = root;
        }


        Top(int count, Top left, Top right, Top root, int index) {
            this.count = count;
            this.left = left;
            this.right = right;
            this.root = root;
            this.index = index;
            height = 1;
        }
    }

    int height(Top p) {
        int height_left = p.left != null ? p.left.height : 0;
        int height_right = p.right != null ? p.right.height : 0;
        return (height_right - height_left);
    }


    void setHeight(Top p) {
        if (p.right != null && p.left != null) {
            p.setHeight(Math.max(p.left.height, p.right.height) + 1);
        } else if (p.right != null && p.left == null) {
            p.setHeight(p.right.height + 1);
        } else if (p.right == null && p.left != null) {
            p.setHeight(p.left.height + 1);
        } else {
            p.setHeight(1);
        }
    }

    void rotate_small_left(Top a) {
        Top b = a.right;
        a.right = b.left;
        b.left = a;
        setHeight(a);
        setHeight(b);
    }

    void rotate_small_right(Top a) {
        Top b = a.left;
        a.left = b.right;
        b.right = a;
        setHeight(a);
        setHeight(b);
    }

    void rotate_left_big(Top p) {
        Top right = p.right.left;
        rotate_small_right(p.right);
        p.right = right;
        rotate_small_left(p);
    }

    void rotate_right_big(Top p) {
        Top left = p.left.right;
        rotate_small_left(p.left);
        p.left = left;
        rotate_small_right(p);
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int[] count = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        if (n == 0) {
            int k = in.nextInt();
            out.println(1);
            out.println(k + " " + 0 + " " + 0);
            return;
        }
        for (int i = 0; i < n; i++) {
            count[i] = in.nextInt();
            left[i] = in.nextInt();
            right[i] = in.nextInt();
        }

        Top top[] = new Top[n];
        out_top = new Top[n + 1];
        for (int i = 0; i < n; i++) {
            top[i] = new Top(0, null, null, null, i + 1);
        }
        for (int i = n - 1; i >= 0; i--) {
            if (left[i] == 0 && right[i] == 0) {
                top[i].setCount(count[i]);
            } else if (left[i] != 0 && right[i] == 0) {
                top[i].setCount(count[i]);
                top[i].setLeft(top[left[i] - 1]);
                top[left[i] - 1].setRoot(top[i]);
                top[i].setHeight(top[i].left.height + 1);
            } else if (left[i] == 0 && right[i] != 0) {
                top[i].setCount(count[i]);
                top[i].setRight(top[right[i] - 1]);
                top[right[i] - 1].setRoot(top[i]);
                top[i].setHeight(top[i].right.height + 1);
            } else {
                top[i].setCount(count[i]);
                top[i].setLeft(top[left[i] - 1]);
                top[i].setRight(top[right[i] - 1]);
                top[left[i] - 1].setRoot(top[i]);
                top[right[i] - 1].setRoot(top[i]);
                top[i].setHeight(Math.max(top[i].right.height, top[i].left.height) + 1);
            }
            top[i].index_left = left[i];
            top[i].index_right = right[i];
        }

        int k = in.nextInt();
        Top ins = new Top(k, null, null, null, 0);
        root = top[0];
        Top parent = insert(root, ins);
        Top parent_copy = parent;

        if ((parent.left != null && parent.right == null) || (parent.left == null && parent.right != null)) {
            parent.setHeight(parent.height + 1);
        }
        if (parent.root != null) {
            parent = parent.root;
            while (parent != null) {
                if (parent.left != null && parent.right != null) {
                    parent.setHeight(Math.max(parent.right.height, parent.left.height) + 1);
                } else if (parent.left != null && parent.right == null) {
                    parent.setHeight(parent.left.height + 1);
                } else if (parent.left == null && parent.right != null) {
                    parent.setHeight(parent.right.height + 1);
                } else {
                    parent.setHeight(1);
                }
                parent = parent.root;
            }
        }
        parent = parent_copy;
        while (parent != null) {
            parent_copy = parent.root;
            check_balance(parent);
            parent = parent_copy;
        }

        out.println(n + 1);
        width(root);
    }

    void check_balance(Top p) {
        Top a = null;
        Top b = null;
        Top c = null;
        Top parent = p;
        if (p == null) {
            return;
        } else {
            a = p;
        }
        root = a;
        if (p.right != null) {
            b = a.right;
        }
        if (b != null && b.left != null) {
            c = b.left;
        }
        if (b != null && ((height(a) >=2 && height(b) >= 1) || (height(a) >= 2 && height(b) == 0))) {
            root = b;
            rotate_small_left(a);
            if (parent.root != null) {
                root.setRoot(parent.root);
                if (parent.root.right != null && parent.root.right.equals(parent)) {
                    parent.root.setRight(root);
                } else if (parent.root.left != null && parent.root.left.equals(parent)) {
                    parent.root.setLeft(root);
                }
                setHeight(parent.root);
            }
        } else if (b != null && c != null && (height(a) >= 2 && height(b) <= -1)) {
            root = c;
            rotate_left_big(a);
            if (parent.root != null) {
                root.setRoot(parent.root);
                if (parent.root.right != null && parent.root.right.equals(parent)) {
                    parent.root.setRight(root);
                } else if (parent.root.left != null && parent.root.left.equals(parent)) {
                    parent.root.setLeft(root);
                }
                setHeight(parent.root);
            }
        }


        if (p.left != null) {
            b = p.left;
        } else {
            b = null;
        }
        if (b != null && b.right != null) {
            c = b.right;
        }
        if (b != null && ((height(a) <= -2 && height(b) <= -1) || (height(a) <= -2 && height(b) == 0))) {
            root = b;
            rotate_small_right(a);
            if (parent.root != null) {
                root.setRoot(parent.root);
                if (parent.root.right != null && parent.root.right.equals(parent)) {
                    parent.root.setRight(root);
                } else if (parent.root.left != null && parent.root.left.equals(parent)) {
                    parent.root.setLeft(root);
                }
                setHeight(parent.root);
            }
        } else if (b != null && c != null && (height(a) <= -2 && height(b) >= 1)) {
            root = c;
            rotate_right_big(a);
            if (parent.root != null) {
                root.setRoot(parent.root);
                if (parent.root.right != null && parent.root.right.equals(parent)) {
                    parent.root.setRight(root);
                } else if (parent.root.left != null && parent.root.left.equals(parent)) {
                    parent.root.setLeft(root);
                }
                setHeight(parent.root);
            }
        }
        setHeight(a);

    }

    Top insert(Top root, Top cur) {
        Top parent = null;
        while (root != null) {
            if (cur.count > root.count) {
                if (root.right != null) {
                    root = root.right;
                } else {
                    cur.root = root;
                    root.right = cur;
                    parent = root;
                    break;
                }
            } else if (cur.count < root.count) {
                if (root.left != null) {
                    root = root.left;
                } else {
                    cur.root = root;
                    root.left = cur;
                    parent = root;
                    break;
                }
            }
        }
        return parent;
    }

    void width(Top top) {
        Queue<Top> queue = new LinkedList<>();
        queue.add(top);
        int i = 1;
        while (!queue.isEmpty()) {
            Top cur = queue.poll();
            cur.setIndex(i);
            i++;

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        queue.clear();
        queue.add(top);
        while (!queue.isEmpty()) {
            Top cur = queue.poll();
            output(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }


    void output(Top root) {
        int index_left = 0;
        int index_right = 0;
        if (root.left != null && root.right != null) {
            index_left = root.left.index;
            index_right = root.right.index;
            out.println(root.count + " " + index_left + " " + index_right);
        } else if (root.left != null && root.right == null) {
            index_left = root.left.index;
            index_right = 0;
            out.println(root.count + " " + index_left + " " + index_right);
        } else if (root.left == null && root.right != null) {
            index_left = 0;
            index_right = root.right.index;
            out.println(root.count + " " + index_left + " " + index_right);

        } else {
            index_left = 0;
            index_right = 0;
            out.println(root.count + " " + index_left + " " + index_right);
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
                new FastScanner(new FileInputStream("addition.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("addition.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}