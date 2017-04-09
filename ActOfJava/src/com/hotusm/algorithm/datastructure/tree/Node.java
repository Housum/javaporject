package com.hotusm.algorithm.datastructure.tree;

public class Node {
	
	private String data;
	private Node liftChild;
	private Node rightChild;
	public Node(String data, Node liftChild, Node rightChild) {
		super();
		this.data = data;
		this.liftChild = liftChild;
		this.rightChild = rightChild;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Node getLiftChild() {
		return liftChild;
	}
	public void setLiftChild(Node liftChild) {
		this.liftChild = liftChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	
	public static void main(String[] args) {
		
		/**
		 * 		  a
		 *      b    c
		 *     d  e f g 
		 */
		Node d=new Node("d",null,null);
		Node e=new Node("e",null,null);
		Node f=new Node("f",null,null);
		Node g=new Node("g",null,null);
		Node b=new Node("b",d,e);
		Node c=new Node("c",f,g);
		Node a=new Node("a",b,c);
		//frontFind(a);
		//middleFind(a);
		endFind(a);
	}
	
	/**
	 * «∞–Ú
	 * @description <br/> 
	 * @param root
	 */
	public static void frontFind(Node root){
		
		if(root==null){
			return;
		}
		System.out.print(root.getData());
		if(root.getLiftChild()!=null){
			frontFind(root.getLiftChild());
		}if(root.getRightChild()!=null){
			frontFind(root.getRightChild());
		}
	}
	
	/**
	 * ÷––Ú
	 * @description <br/> 
	 * @param root
	 */
	public static void middleFind(Node root){
		if(root==null){
			return;
		}
		if(root.getLiftChild()!=null){
			middleFind(root.getLiftChild());
		}
		System.out.print(root.getData());
		if(root.getRightChild()!=null){
			middleFind(root.getRightChild());
		}
		
	}
	/**
	 * ∫Û–Ú
	 * @description <br/> 
	 * @param root
	 */
	public static void endFind(Node root){
		if(root==null){
			return;
		}
		if(root.getLiftChild()!=null){
			endFind(root.getLiftChild());
		}
		if(root.getRightChild()!=null){
			endFind(root.getRightChild());
		}
		System.out.print(root.getData());
	}
	
	
	

}
