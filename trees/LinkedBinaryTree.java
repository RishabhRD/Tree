package trees;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{
	
	protected Node<E> root = null;
	private int size =0;
	public LinkedBinaryTree() {}
	
	protected static class Node<E> implements Position<E>{
        private E element;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;
        
        public Node(E e, Node<E> parent, Node<E> left,Node<E> right) {
        	this.element = e;
        	this.left = left;
        	this.right = right;
        	this.parent = parent;
        }
		@Override
		public E getElement() throws IllegalStateException {
			// TODO Auto-generated method stub
			return element;
		}
		public Node<E> getParent(){
			return parent;
		}
		public Node<E> getLeft(){
			return left;
		}
		public Node<E> getRight(){
			return right;
		}
		public void setElement(E e) {
			this.element = e;
		}
		public void setLeft(Node<E> left) {
			this.left = left;
		}
		public void setRight(Node<E> right) {
			this.right = right;
		}
		public void setParent(Node<E> parent) {
			this.parent = parent;
		}
	
		
	}
    
	protected Node<E> createNode(E e, Node<E> parent,Node<E> left,Node<E> right ){
		return new Node<E>(e,parent,left,right);
	}
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node)) {
			throw new IllegalArgumentException("Not valid Position type");
		}
		Node<E> node = (Node<E>)p;
		if(node.getParent()==node)
			throw new IllegalArgumentException("This node is not in the tree");//We set node's parent to the same node when we remove it. so its our condition to be defective node.
		return node;
				
	}
	public Position<E> addRoot(E e) throws IllegalStateException {
		if(!isEmpty())
			throw new IllegalStateException("Tree is not empty.");
		root = createNode(e,null,null,null);
		size =1;
		return root;
	}
	public Position<E> addLeft(Position<E> p,E e) throws IllegalStateException{
		Node<E> parent = validate(p);
		if(parent.getLeft()!=null)
			throw new IllegalStateException("p already has a left child");
		Node<E> child = createNode(e,parent,null,null);
		parent.setLeft(child);
		size++;
		return child;
	}
	public Position<E> addRight(Position<E> p,E e) throws IllegalStateException{
		Node<E> parent = validate(p);
		if(parent.getRight()!=null)
			throw new IllegalStateException("p already has a right child");
		Node<E> child = createNode(e,parent,null,null);
		parent.setRight(child);
		size++;
		return child;
	}
	public void attach(Position<E> p, LinkedBinaryTree<E> t1,LinkedBinaryTree<E> t2) {
		Node<E> node = validate(p);
		if(isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
		size+=t1.size+t2.size;
		if(!t1.isEmpty()) {
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root = null;
			t1.size =0;
		}
		if(!t2.isEmpty()) {
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size =0;
		}
	}
	public E remove(Position<E> p)throws IllegalArgumentException{
		Node<E> node = validate(p);
		if(numChildren(p)==2)
			throw new IllegalArgumentException("p has 2 children.");
		Node<E> child ;
		if(node.getLeft()!=null)
			child = node.getLeft();
		else
			child = node.getRight();
		if(child!=null)
			child.setParent(node.getParent());
		if(node==root)
			root = child;
		else {
			Node<E> parent = node.getParent();
			if(node==parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size--;
		E temp = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);
		return temp;
	}
	public  E set(Position<E> p, E e) {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(temp);
		return temp;
	}
	
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node  = validate(p);
		return node.getRight();
	}

	@Override
	public Position<E> root() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return node.getParent();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		List<Position<E>> temp = (List<Position<E>>)positions();
		LinkedList<E> list = new LinkedList<>();
		for(Position<E> p : temp) {
			list.add(p.getElement());
		}
		return list.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		List<Position<E>> list = new LinkedList<>();
		add(root,list);
		return list;
		
	}
	private void add(Position<E> p,List<Position<E>> l) {
		 l.add(p);
		 Iterable<Position<E>> nList = children(p);
		 for(Position<E> pos : nList) {
			 add(pos,l);
		 }
	}
	

}
