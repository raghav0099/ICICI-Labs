package icici_labs;
import java.util.Scanner;

class TreeNode{
	TreeNode left;
	TreeNode right;
	  int data;
	  int count;
	  public TreeNode(int v)
	  {
	    data = v;
	    count = -1;
	    left = null;
	    right = null;
	  }
}

public class CountingLesserNumber {

	 static int index = 1;
	
	 public static TreeNode insert(TreeNode root, int v)
	 {
		    if (root == null)
		      return new TreeNode(v);
		    else if (v < root.data)
		      root.left = insert(root.left, v);
		    else
		      root.right = insert(root.right, v);
		    
		    return root;
	 }
	 
	 public static void assignIndex(TreeNode root)
	 {
		    if(root == null)
		      return;
		    
		    assignIndex(root.left);
		    root.count = index++;
		    assignIndex(root.right);
	 }
	 
	 public static TreeNode search(TreeNode root, int v)
	 {
		    if (root == null)
		      return null;
		    else if (root.data == v)
		      return root;
		    else if (root.data < v)
		      return search(root.right, v);
		    else
		      return search(root.left, v);
	}
		
	 public static TreeNode inOrderPre(TreeNode root)
	 {
		 while(root.right != null)
			 root = root.right;
		 
		 return root;
	 }
	 
	 public static TreeNode findPredecessor(TreeNode root, TreeNode prec, int key)
	    {
	        if (root == null) 
	            return prec;
	        if (root.data == key)
	        {
	            if (root.left != null) 
	                prec =  inOrderPre(root.left);
	        }
	        else if (key < root.data) 
	            return findPredecessor(root.left, prec, key);
	        else 
	        {
	            prec = root;
	            return findPredecessor(root.right, prec, key);
	        }
	        return prec;
	    }
	 
	public static void main(String[] args) 
	{
		  int arr[] = { 3, 6, 8, 9, 4, 5, 2 };
		  
		  TreeNode root = null;
		  root = insert(root, arr[0]);
		  for (int i = 1; i < arr.length; i++) 
		  {
		    insert(root, arr[i]);
		  }
		  
		  assignIndex(root);
		  
		  System.out.print("Search Query: ");
		  
		  Scanner sc = new Scanner(System.in);
		  int q = sc.nextInt();
		  
		  TreeNode ans = findPredecessor(root, null , q);
		  
		  if (ans == null || ans.count == -1)
			  System.out.println("There is no number smaller than " + q);
		  else
			  System.out.println("There are " + ans.count +  " numbers smaller than " + q);
		  
		  sc.close();
	}
}