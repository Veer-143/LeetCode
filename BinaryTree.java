package com.test.viresh.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class BinaryTree {
	public void constructTree(int [] arr) {
		int size=arr.length;
	    Node root=helper(arr,0,size);
	    System.out.println("Printing tree inorder");
	    inorder(root);
		System.out.println(); 
		System.out.println("Printing tree preOrder");
		preOrder(root); 
		System.out.println();
		System.out.println("Printing tree vertically"); 
		util(root);
	    System.out.println();
	    System.out.println("Printing tree level order");
	    levelOrder(root);
	}

	private Node helper(int[] arr, int low, int high) {
		if(low>=high) {
			return null;
		}
		int mid=low+(high-low)/2;
		Node root=new Node(arr[mid]);
		root.left=helper(arr, low, mid);
		root.right=helper(arr, mid+1, high);
		return root;
	}
	public void inorder(Node root){
		List<Integer> nodes=inorderUtil(root, new ArrayList<Integer>());
		for(int x: nodes) {
			System.out.print(x+" ");
		}
		
	}
	public List<Integer> inorderUtil(Node root,List<Integer> list){
		if(root!=null) {
			inorderUtil(root.left, list);
			list.add(root.data);
			inorderUtil(root.right, list);
		}
		return list;
	}
	public void preOrder(Node root){
		List<Integer> nodes=preOrderUtil(root, new ArrayList<Integer>());
		for(int x: nodes) {
			System.out.print(x+" ");
		}
		
	}
	public List<Integer> preOrderUtil(Node root,List<Integer> list){
		if(root!=null) {
			list.add(root.data);
			inorderUtil(root.left, list);
			inorderUtil(root.right, list);
		}
		return list;
	}
	public void util(Node root) {
		List<List<Integer>> list=verticalTraversal(root);
		System.out.println(list);
	}
	public List<List<Integer>> verticalTraversal(Node root){
		TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : ys.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }
    private void dfs(Node root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(x)) {
            map.put(x, new TreeMap<>());
        }
        if (!map.get(x).containsKey(y)) {
            map.get(x).put(y, new PriorityQueue<>());
        }
        map.get(x).get(y).offer(root.data);
        dfs(root.left, x - 1, y + 1, map);
        dfs(root.right, x + 1, y + 1, map);
	}
    public void levelOrder(Node root) {
    	Queue<Node> node=new LinkedList<Node>();
    	node.add(root);
    	while(!node.isEmpty()){
    		int size=node.size();
    		for(int i=0;i<size;i++) {
    			Node temp=node.poll();
    			System.out.print(temp.data+" ");
    			if (temp.left!=null) {
					node.add(temp.left);
				}
    			if (temp.right!=null) {
					node.add(temp.right);
				}
    		}
    		System.out.println();
    	}
    }
}
