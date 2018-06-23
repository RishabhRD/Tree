package trees;

public class SinglyLinkedList<E> implements Cloneable{
	  //This is a Node. That is required in singly linked list.
      private static class Node<E>{
    	  private E element;
    	  private Node<E> next;
    	  public Node(E e,Node n) {
    		  this.element = e;
    		  this.next = n;
    	  }
    	  public E getElement() {
    		  return element;
    	  }
    	  public Node<E> getNext(){
    		  return next;
    	  }
    	  public void setNext(Node<E> node) {
    		  next=node;
    	  }
      }
      //End of Node.
      
      
      private Node<E> head = null;
      private Node<E> tail = null;
      private int size=0;
      public SinglyLinkedList() {} //Creates an empty list.
      
      public int size() {
    	  return size;
      }
      
      public boolean isEmpty() {
    	  return size==0;
      }
      public E first() {
    	  if(size==0)
    		  return null;
    	  return head.getElement();
      }
      public E last() {
    	  if(size==0)
    		  return null;
    	  return tail.getElement();
      }
      public E get(int index) {
    	  if(index<0||index>=size)
    		  throw new IndexOutOfBoundsException();
    	  Node<E> cur = head;
    	  if(index==0)
    		  return head.getElement();
    	  for(int i=0; i<index;i++) {
    		  cur = cur.next;
    		  
    	  }
    	  return cur.getElement();
      }
      public E[] toArray() {
    	  E[] array = (E[]) new Object[size];
    	  for(int i=0;i<size;i++) {
    		  array[i] = get(i);
    	  }
    	  return array;
    	  
      }
      private Node<E> getNode(int index){
    	  if(index<0||index>=size)
    		  throw new IndexOutOfBoundsException();
    	  Node<E> cur = head;
    	  if(index==0)
    		  return head;
    	  for(int i=0; i<index;i++) {
    		  cur = cur.next;
    		  
    	  }
    	  return cur;
      }
      
      //update methods
      public void addFirst(E e) {
    	  head = new Node<E>(e, head);
    	  if(size==0)
    		  tail=head;
    	  size++;
      }
      public void addLast(E e) {
    	  Node<E> newest = new Node<E>(e,null);
    	  if(isEmpty())
    		  head=newest;
    	  else
    		  tail.setNext(newest);
    	  tail = newest;
    	  size++;
      }
      public E removeFirst() {
    	  if(isEmpty())
    		  return null;
    	  E answer = head.getElement();
    	  head = head.getNext();
    	  size--;
    	  if(size==0)
    		  tail=null;
    	  return answer;
      }
      public void add(E e, int index) {
    	  if(index==0) {
    		  addFirst(e);
    		  return;
    	  }
    	  if(index==size) {
    		  addLast(e);
    		  return;
    	  }
    	  if(index<0||index>size)
    		  throw new IndexOutOfBoundsException();
    	  Node<E> pre = getNode(index-1);
    	  Node<E> post= getNode(index);
    	  Node<E> newest = new Node<E>(e,post);
    	  pre.setNext(newest);
    	  size++;
    	  
      }
      public E removeLast() {
    	  if(isEmpty()) {
    		  return null;
    	  }
    	  E answer = last();
    	  if(size==1) {
    		  removeFirst();
    		  return answer;
    	  }
    	  
    	  Node<E> newTail = getSecondLast();
    	  tail = newTail;
    	  newTail.setNext(null);
    	  size--;
    	  return answer;
      }
      public E remove(int index) {
    	  if(index==0) {
    		  return removeFirst();
    		  
    	  }
    	  if(index==size-1)
    	      return removeLast();
    	  if(index<0||index>size-1)
    		  throw new IndexOutOfBoundsException();
    	  E answer = get(index);
    	  Node<E> pre = getNode(index-1);
    	  pre.setNext(getNode(index+1));
    	  size--;
    	  return answer;
      }
      private Node<E> getSecondLast() {
    	  return getNode(size-2);
      }
      public boolean equals(Object o) {
    	  if(o==null)return false;
	      if(getClass() != o.getClass())
    		  return false;
	      SinglyLinkedList other = (SinglyLinkedList) o;
	      if(size!=other.size()) {
	    	  return false;
	      }
	      Node a = head;
	      Node b = other.head;
	      while(a!=null) {
	    	  if(!a.getElement().equals(b.getElement())) return false;
	    	  a = a.getNext();
	    	  b = b.getNext();
	      }
	      return true;
	      
      }
      public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
    	  SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();
    	  if(size>0) {
    		  other.head = new Node<E>(head.getElement(),null);
    		  Node<E> walk = head.getNext();
    		  Node<E> otherTail = other.head;
    		  while(walk!=null) {
    			  Node<E> newest = new Node<>(walk.getElement( ), null);
    			  otherTail.setNext(newest);
    			// link previous node to this one
    			otherTail = newest;
    			walk = walk.getNext( );
    		  }
    	  }
    	  return other;
      }  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    		  
    	  
         
}
