package trees;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E> {

	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException{
		return numChildren(p)>0;
	}
	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException{
	    return numChildren(p)==0;
	}
	@Override
	public boolean isEmpty() {
		return size()==0;
	}
	@Override
	public boolean isRoot(Position<E> p) throws IllegalArgumentException{
		return p==root();
	}
	public int depth(Position<E> p) throws IllegalArgumentException{
		if(isRoot(p))
			return 0;
		else
		    return 1+depth(parent(p));
	}
	@SuppressWarnings("unused")
	private int heightBad() {//O(n^2) bad algorithm so private as users can not use it
		int h =0;
		for(Position<E> p : positions()) {
			if(isExternal(p)) {
				h = Math.max(h, depth(p));
			}
		}
		return h;
	}
	public int height(Position<E> p) {
		int h=0;
		for(Position<E> c :children(p) ) {
			h = Math.max(h	,1+height(c));
		}
		return h;
	}
	/*Preorder Traversal*/
	private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
		snapshot.add(p);
		for(Position<E> c: children(p)) {
			preorderSubtree(c,snapshot);
		}
	}
	public Iterable<Position<E>> preorder(){
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty())
			preorderSubtree(root(),snapshot);
		return snapshot;
	}
	/*End of Preorder Traversal*/
	/*Postorder Traversal*/
	private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
		
		for(Position<E> c: children(p)) {
			preorderSubtree(c,snapshot);
		}
		snapshot.add(p);
	}
	public Iterable<Position<E>> postorder(){
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty())
			preorderSubtree(root(),snapshot);
		return snapshot;
	}
	/*End of Postorder Traversal*/
	/*Breadth first Traversal*/
	public Iterable<Position<E>> breadthFirst(){
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()) {
			Queue<Position<E>> queue = new LinkedQueue<>();
			queue.enqueue(root());
			while(!queue.isEmpty()) {
				Position<E> pos = queue.dequeue();
				snapshot.add(pos);
				for(Position<E> p : children(pos)) {
					queue.enqueue(p);
				}
			}
		}
		return snapshot;
	}

}
