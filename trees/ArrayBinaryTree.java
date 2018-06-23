package trees;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
	
	private int size;
	private Node<E> root ;
	private Position<E>[] list = (Position<E>[])new Object[8];
	protected  class Node<E> implements Position<E>{
	
		private E e;
		private int pos;
		
		public Node(E e,int pos) {
		    this.e = e;
			this.pos = pos;
		}
		public void setElement(E e) {
			this.e = e;
		}
		public void setPosition(int pos) {
			this.pos = pos;
		}
		public E getElement() {
			return e;
		}
		public int getPosition() {
			return pos;
		}
		public int getParent(){
			if(pos%2==0) {
				return (pos-2)/2;
			}
			return (pos-1)/2;
		}
		public int getLeft() {
			return (2*pos)+1;
		}
		public int getRight() {
			return (2*pos)+2;
		}
		
	}
	
    protected Node<E> createNode(E e,int pos){
    	return new Node<E>(e,pos);
    }
    protected Node<E> validate(Position<E> p) {
    	if(!( (p instanceof Node)||p==null))
    		throw new IllegalArgumentException("Not a valid position");
    	Node<E> node = (Node<E>) p;
    	if(node.getParent()==-1) throw new IllegalArgumentException("This position is not in tree");
    	return node;
    }
    public void addRoot(E e) {
    	if(!isEmpty()) throw new IllegalStateException("Tree is not empty");
    	root = createNode(e,0);
    	size=1;
    }
    public void addLeft(E e, Position<E> p) {
    	Node<E> n = validate(p);
    	int pos = n.getPosition();
    	int newPos = pos*2+1;
    	if(newPos>list.length) {
    		Position<E>[] temp =list;
    		list = createList(temp,newPos*2+2);
    	}
    	list[newPos] = createNode(e,newPos);
    		
    	size++;
    }
    public void addRight(E e, Position<E> p) {
    	Node<E> n = validate(p);
    	int pos = n.getPosition();
    	int newPos = pos*2+1;
    	if(newPos>list.length) {
    		Position<E>[] temp =list;
    		list = createList(temp,newPos*2+2);
    	}
    	list[newPos] = createNode(e,newPos);
    	size++;	
    	
    }
    
    protected Position<E>[] createList(Position<E>[] list,int len) {
    	Position<E>[] ans = (Position<E>[]) new Object[len];
    	for(int i=0; i<list.length;i++) {
    		ans[i] = list[i];
    	}
    	return ans;
    }
    public Position<E> get(int i){
    	return list[i];
    }
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return get(validate(p).getLeft());
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return get(validate(p).getRight());
	}

	@Override
	public Position<E> root() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return get(validate(p).getParent());
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

}
