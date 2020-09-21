
import java.io.*;
import java.util.*;

public class Task3 {

    FastScanner in;
    PrintWriter out;
    Top root = null;

    class Top {
        Top left;

        public void setLeft(Top left) {
            this.left = left;
        }

        public void setRight(Top right) {
            this.right = right;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setParent(Top parent) {
            this.parent = parent;
        }

        Top parent;
        Top right;
        int count;

        Top(int count, Top left, Top right, Top parent) {
            this.count = count;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void insert(Top root, Top cur) {
        while (root != null) {
            if (cur.count > root.count) {
                if (root.right != null) {
                    root = root.right;
                } else {
                    cur.parent = root;
                    root.right = cur;
                    break;
                }
            } else if (cur.count < root.count) {
                if (root.left != null) {
                    root = root.left;
                } else {
                    cur.parent = root;
                    root.left = cur;
                    break;
                }
            }
        }
    }

    boolean isTree(Top root) {
        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean check(Top v, int min, int max) {
        if (v == null) {
            return true;
        }
        if (v.count <= min || v.count >= max) {
            return false;
        }
        return (check(v.left, min, v.count) && check(v.right, v.count, max));
    }

    boolean bol = false;

    void exist(Top x, int key) {
        if (x != null) {
            if (x.count == key) {
                bol = true;
            }
            exist(x.left, key);
            exist(x.right, key);
        }
    }

    Top searchNext(Top root, int key) {
        Top top = null;
        while (root != null) {
            if (root.count > key) {
                top = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return top;
    }

    Top searchPrev(Top root, int key) {
        Top top = null;
        while (root != null) {
            if (root.count < key) {
                top = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return top;
    }

    Top search(Top root, int key) {
        if (root == null || key == root.count)
            return root;
        if (key < root.count) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    Top searchMin(Top root) {
        if (root.left == null)
            return root;
        return searchMin(root.left);
    }

    Top searchMax(Top root) {
        if (root.right == null)
            return root;
        return searchMin(root.right);
    }

    void delete(Top root) {
        if (root.left == null && root.right == null) {
            if (root.parent.left == root) {
                root.parent.left = null;
            }
            if (root.parent.right == root) {
                root.parent.right = null;
            }
        } else if (root.left == null || root.right == null) {
            if (root.left == null) {
                if (root.parent.left == root) {
                    root.parent.left = root.right;
                } else {
                    root.parent.right = root.right;
                }
                root.right.parent = root.parent;
            } else {
                if (root.parent.left == root) {
                    root.parent.left = root.left;
                } else {
                    root.parent.right = root.left;
                }
                root.left.parent = root.parent;
            }
        } else {
            Top next = searchNext(root, root.count);
            root.count = next.count;
            if (next.parent.left == next) {
                next.parent.left = next.right;
                if (next.right != null)
                    next.right.parent = next.parent;
            } else {
                next.parent.right = next.left;
                if (next.left != null) {
                    next.right.parent = next.parent;
                }
            }
        }
    }

    void deleteRoot(Top root) {
        if (root.left == null && root.right == null) {
            this.root = null;
        } else if (root.left == null || root.right == null) {
            if (root.left == null) {
                this.root = root.right;
                root.right.parent = null;
            } else {
                this.root = root.left;
                root.left.parent = null;
            }
        } else {
           Top min=searchMin(root.right);
           min.left=root.left;
           root.left.parent=min.left;
           this.root=root.right;
        }
    }

    public void solve() throws IOException {
        String s = "";
        int k = 0;
        root = null;
        Top cur = null;
        while (in.hasNextLine()) {
            s = in.next();
            bol = false;
            if (s.equals("insert")) {
                k = in.nextInt();
                if (root == null) {
                    root = new Top(k, null, null, null);
                } else {
                    cur = new Top(k, null, null, null);
                    exist(root,k);
                    if(!bol){
                        insert(root, cur);
                    }
                }
            } else if (s.equals("exists")) {
                k = in.nextInt();
                if (root != null) {
                    exist(root, k);
                    out.println(bol);
                } else {
                    out.println(bol);
                }
            } else if (s.equals("next")) {
                k = in.nextInt();
                cur = searchNext(root, k);
                if (cur != null) {
                    out.println(cur.count);
                } else {
                    out.println("none");
                }
            } else if (s.equals("prev")) {
                k = in.nextInt();
                cur = searchPrev(root, k);
                if (cur != null) {
                    out.println(cur.count);
                } else {
                    out.println("none");
                }
            } else if (s.equals("delete")) {
                k = in.nextInt();
                cur = search(root, k);
                if (root != null && cur != null) {
                    if (root == cur) {
                        deleteRoot(root);
                    } else {
                        delete(cur);
                    }
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
                new FastScanner(new FileInputStream("bstsimple.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("bstsimple.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}