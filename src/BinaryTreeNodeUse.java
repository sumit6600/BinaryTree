import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTreeNodeUse {

    /**
     * @param s
     * @return
     * Take Input of binary tree recursively
     */
    public static BinaryTreeNode<Integer> takeInputTree(Scanner s){
        System.out.println("Enter the root data :");
        int rootData  = s.nextInt();

        if(rootData ==-1 || rootData ==0){
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        root.left = takeInputTree(s);
        root.right = takeInputTree(s);
        return root;
    }

/* Input and Output of the recursive input and output
Enter the root data :
1
Enter the root data :
2
Enter the root data :
3
Enter the root data :
-1
Enter the root data :
-1
Enter the root data :
-1
Enter the root data :
-1
1: L: 2 ,
2: L: 3 ,
3:
 */

    /**
     * @param root
     * Print Binary Tree Recursively
     */
    public static void printBinaryTree(BinaryTreeNode<Integer> root){
        if(root == null){
            return;
        }
        String s = root.data + ": ";
        if(root.left!=null){
            s+= "L: " + root.left.data + " ,";
        }
        if(root.right!=null){
            s+= "" +
                    "R: " + root.right.data + "";
        }
        System.out.println(s);
        printBinaryTree(root.left);
        printBinaryTree(root.right);
    }

    /**
     * @param s
     * @return
     * Take Input of binary tree in level wise
     */

    public static BinaryTreeNode<Integer> takeInputLevelWiseBTree(Scanner s){
        System.out.println("Enter the root data");
        int rootData = s.nextInt();
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        Queue<BinaryTreeNode<Integer>> pendingNodes = new LinkedList<>();
        pendingNodes.add(root);

        while (!pendingNodes.isEmpty()){
            BinaryTreeNode<Integer> frontNode = pendingNodes.poll();
            System.out.println("Enter the left child of :"  + frontNode.data);
            int leftChild = s.nextInt();
            if(leftChild!=-1){
                BinaryTreeNode<Integer> leftNode = new BinaryTreeNode<Integer>(leftChild);
                pendingNodes.add(leftNode);
                frontNode.left = leftNode;
            }

            System.out.println("Enter the right child of :"  + frontNode.data);
            int rightChild = s.nextInt();
            if(rightChild!=-1){
                BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<Integer>(rightChild);
                pendingNodes.add(rightNode);
                frontNode.right = rightNode;
            }
        }
        return root;
    }

    /**
     * @param root
     * Print binary tree in level wise
     */
    public static void printLevelWiseBTree(BinaryTreeNode<Integer> root){
        if (root==null){
            return;
        }
        Queue<BinaryTreeNode<Integer>> pendingNodes = new LinkedList<>();
        pendingNodes.add(root);
        while (!pendingNodes.isEmpty()){
            BinaryTreeNode<Integer> front = pendingNodes.poll();
            String s = front.data + ": ";
            if(front.left!=null){
                s+="L:" + front.left.data +",";
                pendingNodes.add(front.left);
            }
            if(front.right!=null){
                s+= "R:" + front.right.data ;
                pendingNodes.add(front.right);
            }
            System.out.println(s);
        }
    }

    /**
     * @param root
     * Count nodes in binary tree
     */
    public static int nodeCount(BinaryTreeNode<Integer> root){
        if(root==null){
         return 0;
        }
        int count =1;
        if(root.left!=null){
            count+=nodeCount(root.left);
        }
        if(root.right!=null){
            count+=nodeCount(root.right);
        }
        return count;
    }

    /**
     * @param root
     * For a given Binary Tree of type integer and a number X, find whether a node exists in the tree with data X or not.
     */

    public static boolean findNode(BinaryTreeNode<Integer>  root , int x){
        if(root==null){
            return false;
        }
        if(root.data==x){
            return true;
        }
        boolean left = findNode(root.left , x);
        if(left){
            return left;
        }
        boolean right = findNode(root.right , x);
        return right;
    }


    /**
     * @param root
     * For a given Binary Tree of integers, find and return the height of the tree. Height is defined as the total number of nodes
     * along the longest path from the root to any of the leaf node.
     */

    public static int heightofTree(BinaryTreeNode<Integer> root){
        if(root==null){
            return 0;
        }
        int height = 1;
        int left =0;
        int right = 0;
        left+=heightofTree(root.left);
        right+=heightofTree(root.right);

        if(left>right){
            return left+height;
        }
        else {
            return right+height;
        }
    }

    /**
     * @param root
     * Mirror a binary tree
     * For a given Binary Tree of type integer, update it w corresponding mirror image.
     */

    public static BinaryTreeNode<Integer> mirrorBinaryTree(BinaryTreeNode<Integer> root){
        if(root==null){
            return null;
        }

        BinaryTreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirrorBinaryTree(root.
                left);
        mirrorBinaryTree(root.right);
        return root;
    }

    /**
     *
     * @param root
     * Diameter of binary tree (Longest path in binary tree) the time complexity of this depends upon the height of tree
     */

    public static int daimeterOfBinaryTree(BinaryTreeNode<Integer> root){
        if(root==null){
            return 0;
        }
        int option1 = useforDaimeterHeight(root.left) + useforDaimeterHeight(root.right);
        int option2 = useforDaimeterHeight(root.left);
        int option3 = useforDaimeterHeight(root.right);

        return Math.max(option1, Math.max(option2,option3));
    }

   public static int useforDaimeterHeight(BinaryTreeNode<Integer> root){
        if(root==null){
            return 0;
        }
        int lh = useforDaimeterHeight(root.left);
        int rh = useforDaimeterHeight(root.right);
        return 1+ Math.max(lh,rh);
   }

    /**
     * @param root
     * @return
     * Diameter of binary tree with order of n complexity
     */
   public static pair<Integer,Integer> heightDiameter(BinaryTreeNode<Integer> root){
        if(root==null){
            pair<Integer,Integer> output = new pair<>();
            output.left = 0;
            output.right =0;
            return output;
        }
       pair<Integer , Integer> lo = heightDiameter(root.left);
       pair<Integer , Integer> ro = heightDiameter(root.right);

       int height = 1 + Math.max(lo.left, ro.left);
       int option1 = lo.left + ro.left;
       int option2 = lo.right;
       int option3 = ro.right;

       int diameter = Math.max(option1,Math.max(option2,option3));

       pair<Integer,Integer> outputreturn = new pair<>();
       outputreturn.left = height;
       outputreturn.right = diameter;

       return outputreturn;
   }

    /**
     * @param root
     * Inorder print of binary tree
     */
   public static void inorderBinaryTree(BinaryTreeNode<Integer> root){
       if(root ==null){
           return;
       }
       inorderBinaryTree(root.left);
       System.out.print(root.data + " ");
       inorderBinaryTree(root.right);
   }

    /**
     * @param root
     * Preorder print of binary tree
     */
   public static void preOrderBinaryTree(BinaryTreeNode<Integer> root){
       if(root ==null){
           return;
       }
       System.out.print(root.data + " ");
       preOrderBinaryTree(root.left);
       preOrderBinaryTree(root.right);
   }

    /**
     * @param root
     * Postorder print of binary tree
     */
   public static void postOrderBinaryTree(BinaryTreeNode<Integer> root){
       if(root ==null){
           return;
       }
       postOrderBinaryTree(root.left);
       postOrderBinaryTree(root.right);
       System.out.print(root.data + " ");

   }

   public static BinaryTreeNode<Integer> buildTree(int[] inorder , int[] preorder ){
       return buildTreeHelper(inorder,preorder , 0 , inorder.length-1 ,0 , preorder.length -1);
   }
   public static BinaryTreeNode<Integer> buildTreeHelper(int[] inorder ,int[] preorder , int inostart , int inoend , int prestart , int preend){
       if(inostart > inoend){
           return null;
       }

       int rootdata = preorder[prestart];
       BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootdata);

       int rootindex = -1;
       for(int i =inostart;i<=inoend;i++){
           if(inorder[i]==rootdata){
               rootindex =i;
               break;
           }
       }
       if(rootindex==-1){
           return null;
       }

       int leftInostart = inostart;
       int leftInoend = rootindex -1;
       int rightInostart = rootindex +1;
       int rightInoend = inoend;

       int leftPrestart = prestart +1;
       int leftPreend =  leftInoend-leftInostart  + leftPrestart;
       int rightPrestart = leftPreend+1;
       int rightPreend = preend;

       root.left = buildTreeHelper(inorder,preorder,leftInostart,leftInoend,leftPrestart,leftPreend);
       root.right = buildTreeHelper(inorder,preorder,rightInostart,rightInoend,rightPrestart,rightPreend);

       return root;
   }

    /**
     * @param inorder , postordet
     * Construct Tree from Postorder and Inorder for a given postorder and inorder traversal of a Binary Tree of type integer stored in an array/list,
     * create the binary tree using the given two arrays/lists. construct the tree and return the root.
     */

    public static BinaryTreeNode<Integer> buildTreeUsePostInOrder(int[] inorder , int[] postorder){
        return buildTreeUsePostInOrderHelper(inorder,postorder, 0 ,inorder.length-1 ,0 ,postorder.length-1);
    }

    public static BinaryTreeNode<Integer> buildTreeUsePostInOrderHelper(int[] inorder, int[] postorder, int startInor, int endInor, int startPostor, int endPostor) {

        if(startPostor>endPostor){
            return null;
        }
        int rootData = postorder[endPostor];
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);

        int rootindex = -1;
        for(int i = startInor ; i <=endInor;i++){
            if(rootData==inorder[i]){
                rootindex = i;
                break;
            }
        }

        if(rootindex==-1){
            return null;
        }

        int leftinostart = startInor;
        int leftinoend = rootindex-1;
        int rightinostart = rootindex+1 ;
        int rightinoend = endInor;

        int leftpostostart = startInor;
        int leftpostoend = leftinoend- leftinostart + leftpostostart ;
        int rightpostostart = leftpostoend+1;
        int rightpostoend = endPostor;
       root.left =  buildTreeUsePostInOrderHelper(inorder,postorder ,leftinostart,leftinoend,leftpostostart,leftpostoend);
       root.right = buildTreeUsePostInOrderHelper(inorder,postorder,rightinostart,rightinoend,rightpostostart,rightinoend);

       return root;
    }

    /**
     * @param root , x
     * For a given Binary Tree of type integer and a number X, find whether a node exists in the tree with data X or not.
     */
    public static boolean nodeExists(BinaryTreeNode<Integer> root , int x){
        if(root==null){
            return false;
        }
        if(root.data==x){
            return true;
        }
        boolean left = nodeExists(root.left , x);
        if(left){
            return true;
        }
        boolean right = nodeExists(root.right , x);
        if (right){
            return true;
        }
        return false;
    }

    /**
     * @param root
     *  Nodes without sibling
     *  For a given Binary Tree of type integer, print all the nodes without any siblings.
     */
    static public void NodeWithoutSibling(BinaryTreeNode<Integer> root){
        if(root==null){
            return;
        }
        if(root.left!=null && root.right!=null){
            NodeWithoutSibling(root.left);
            NodeWithoutSibling(root.right);
        }
        else if(root.left!=null){
            System.out.print(root.left.data + " ");
            NodeWithoutSibling(root.left);
        }
        else if(root.right!=null){
            System.out.print(root.right.data);
            NodeWithoutSibling(root.right);
        }
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
//        BinaryTreeNode<Integer> rootdata = takeInputTree(s);
//        printBinaryTree(rootdata);
          System.out.println("Level wise Input and print");
          BinaryTreeNode<Integer> rootdatalevelwise = takeInputLevelWiseBTree(s);
          printLevelWiseBTree(rootdatalevelwise);

//        int numOfCount = nodeCount(rootdatalevelwise);
//        System.out.println("Number of nodes: " + numOfCount);
//
//        boolean presentOrnot = findNode(rootdatalevelwise , 9);
//        System.out.println("Number exists: " +presentOrnot);
//
//        int heightoftree = heightofTree(rootdatalevelwise);
//        System.out.println("Height of tree : " + heightoftree);
//
//        System.out.println("Mirror tree");
//        printLevelWiseBTree(mirrorBinaryTree(rootdatalevelwise));
//
//        System.out.println("Diameter of binary tree.");
//        daimeterOfBinaryTree(rootdatalevelwise);
//
//        System.out.println("Diameter of binary tree." +  heightDiameter(rootdatalevelwise).right);
//        System.out.println("Height of binary tree." +  heightDiameter(rootdatalevelwise).left);
//
//        System.out.println("In order Binary Tree : ");
//        inorderBinaryTree(rootdatalevelwise);
//
//        System.out.println("Pre Order of Binary Tree : ");
//        preOrderBinaryTree(rootdatalevelwise);
//
//        System.out.println("Post Order of Binary Tree :");
//        postOrderBinaryTree(rootdatalevelwise);

        System.out.println("Build Tree with preorder and in order");
        int preorder[] = {1,2,4,5,3,6,7};
        int inorder[] = {4,2,5,1,6,3,7};
        int postorder[] = {4,5,2,6,7,3,1};
        BinaryTreeNode<Integer> rootnode =   buildTree(inorder,preorder);
        printLevelWiseBTree(rootnode);
        System.out.println("Build Tree with postorder and in order");
        BinaryTreeNode<Integer> rootnodenew =   buildTree(inorder,postorder);
        printLevelWiseBTree(rootnodenew);

        System.out.println("Element exits in the binary tree : " + nodeExists(rootdatalevelwise , 6));
        System.out.print("Node without sibling : ");
        NodeWithoutSibling(rootdatalevelwise);


        /****************************************************/
        System.out.println("Binary Tree use");
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(2);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(3);
        root.left =left;
        root.right =right;
    }
}
