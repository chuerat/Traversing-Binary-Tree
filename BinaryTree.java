import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    //前序遍历递归的方式
    public void preOrder(BinaryTreeNode root) {
        if (null != root) {
            System.out.print(root.getData() + "\t");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    /**
     * 前序遍历非递归的方式
     *
     * 1.申请一个新的栈，记为stack。然后将头节点root压入stack中。
     * 2.从stack中弹出栈顶节点，记为cur，然后打印cur节点的值，再将节点cur的右孩子（不为空的话）先压入stack中，最后将cur的左孩子（不为空的话）压入stack中。
     * 3.不断重复步骤2，直到stack为空，全部过程结束。
     *
     * @param root 根节点
     */
    public void preOrderNonRecursive(BinaryTreeNode root) {
        if (root != null) {
            Stack<BinaryTreeNode> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                System.out.print(root.getData() + "\t");
                if (root.getRight() != null) {
                    stack.push(root.getRight());
                }
                if (root.getLeft() != null) {
                    stack.push(root.getLeft());
                }
            }
        }
        System.out.println();
    }

    //中序遍历采用递归的方式
    public void inOrder(BinaryTreeNode root) {
        if (null != root) {
            inOrder(root.getLeft());
            System.out.print(root.getData() + "\t");
            inOrder(root.getRight());
        }
    }

    /**中序遍历采用非递归的方式
     *
     * 1.申请一个新的栈，记为stack。初始时，令变量cur=root。
     * 2.先把cur节点压入栈中，对以cur节点为根的整颗子树来说，依次把左边界压入栈中，即不停地令cur=cur.getLeft(),然后重复步骤2。
     * 3.不断重复步骤2，直到发现cur为空，此时从stack中弹出一个节点，记为node。打印node的值，并且让cur=node.getRight()，然后继续重复步骤2。
     * 4.当stack为空且cur为空时，整个过程停止。
     *
     * @param root 根节点
     */
    public void inOrderNonRecursive(BinaryTreeNode root) {
        if (root != null) {
            Stack<BinaryTreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.getLeft();
                } else {
                    root = stack.pop();
                    System.out.print(root.getData() + "\t");
                    root = root.getRight();
                }
            }
        }
        System.out.println();
    }

    //后序遍历采用递归的方式
    public void postOrder(BinaryTreeNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getData() + "\t");
        }
    }

    /**后序遍历采用非递归的方式
     *
     * 用两个栈实现
     * 1.申请两个栈，分别记为s1，s2，然后将头节点root压入s1中。
     * 2.从s1中弹出的节点记为cur，然后依次将cur的左孩子和右孩子压入s1中。
     * 3.在整个过程中，每一个从s1中弹出的节点都放进s2中。
     * 4.不断重复步骤2和步骤3，直到s1为空，过程停止。
     * 5.从s2中依次弹出节点并打印，打印的顺序就是后序遍历的顺序。
     *
     * @param root 根节点
     */
    public void postOrderNonRecursive(BinaryTreeNode root) {
        if (root != null) {
            Stack<BinaryTreeNode> s1 = new Stack<>();
            Stack<BinaryTreeNode> s2 = new Stack<>();
            s1.push(root);
            while (!s1.isEmpty()) {
                root = s1.pop();
                s2.push(root);
                if (root.getLeft() != null) {
                    s1.push(root.getLeft());
                }
                if (root.getRight() != null) {
                    s1.push(root.getRight());
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().getData() + "\t");
            }
        }
        System.out.println();
    }

    //层序遍历
    public void levelOrder(BinaryTreeNode root) {
        BinaryTreeNode temp;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.getData() + "\t");
            if (temp.getLeft() != null) {
                queue.offer(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.offer(temp.getRight());
            }
        }
    }

    //按层打印
    public void printByLevel(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        BinaryTreeNode last = root;
        BinaryTreeNode nextLast = null;
        int level = 1;

        System.out.print("Level" + (level++) + " : ");
        while (!queue.isEmpty()) {
            root = queue.poll();
            System.out.print(root.getData() + "\t");
            if (null != root.getLeft()) {
                queue.offer(root.getLeft());
                nextLast = root.getLeft();
            }
            if (null != root.getRight()) {
                queue.offer(root.getRight());
                nextLast = root.getRight();
            }
            if (root == last && !queue.isEmpty()) {
                System.out.print("Level" + (level++) + " : ");
                last = nextLast;
            }
        }
        System.out.println();
    }

    //ZigZag打印
    public void printByZigZag(BinaryTreeNode head) {
        if (head == null) {
            return;
        }

        Deque<BinaryTreeNode> dq = new LinkedList<>();
        int level = 1;
        boolean lr = true;
        BinaryTreeNode last = head;
        BinaryTreeNode nextLast = null;

        dq.offerFirst(head);
        printLevelAndOrientation(level++, lr);
        while (!dq.isEmpty()) {
            if (lr) {
                head = dq.pollFirst();
                if (head.getLeft() != null) {
                    nextLast = nextLast == null ? head.getLeft() : nextLast;
                    dq.offerLast(head.getLeft());
                }
                if (head.getRight() != null) {
                    nextLast = nextLast == null ? head.getRight() : nextLast;
                    dq.offerLast(head.getRight());
                }
            } else {
                head = dq.pollLast();
                if (head.getRight() != null) {
                    nextLast = nextLast == null ? head.getRight() : nextLast;
                    dq.offerFirst(head.getRight());
                }
                if (head.getLeft() != null) {
                    nextLast = nextLast == null ? head.getLeft() : nextLast;
                    dq.offerFirst(head.getLeft());
                }
            }
            System.out.print(head.getData() + " ");
            if (head == last && !dq.isEmpty()) {
                lr = !lr;
                last = nextLast;
                nextLast = null;
                System.out.println();
                printLevelAndOrientation(level++, lr);
            }
        }
        System.out.println();
    }

    public void printLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right: " : "right to left: ");
    }

    public static void main(String[] args) {
        BinaryTreeNode node10 = new BinaryTreeNode(10, null, null);
        BinaryTreeNode node8 = new BinaryTreeNode(8, null, null);
        BinaryTreeNode node9 = new BinaryTreeNode(9, null, node10);
        BinaryTreeNode node4 = new BinaryTreeNode(4, null, null);
        BinaryTreeNode node5 = new BinaryTreeNode(5, node8, node9);
        BinaryTreeNode node6 = new BinaryTreeNode(6, null, null);
        BinaryTreeNode node7 = new BinaryTreeNode(7, null, null);
        BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);
        BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);
        BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);

        BinaryTree tree = new BinaryTree();
        //采用递归的方式进行遍历
        System.out.println("-----前序遍历------");
        tree.preOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.preOrderNonRecursive(node1);
        System.out.println();


        //采用递归的方式进行遍历
        System.out.println("-----中序遍历------");
        tree.inOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.inOrderNonRecursive(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----后序遍历------");
        tree.postOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.postOrderNonRecursive(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----层序遍历------");
        tree.levelOrder(node1);
        System.out.println();
        //采用按层打印的方式进行遍历
        tree.printByLevel(node1);
        System.out.println();

        System.out.println("-----ZigZag遍历------");
        tree.printByZigZag(node1);
        System.out.println();
    }
}
