package util;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Something that can store and release finitely many items in some kind of order.
 * @param <T> The type of the items.
 */
public interface Container<T>{
    /**
     * Store an item. Input.
     * @param item The item to be stored
     * @return Whether the item was successfully inserted into the container.
     */
    boolean add(T item);

    /**
     * Release an item. Output.
     * Returns null when it is not possible to remove anything from the collection at the moment (for example when it is empty).
     * @return The released item.
     */
    T remove();

    default boolean isEmpty(){
    	return size() == 0;
    }
    
    /**
     * The current number of elements in the container
     * @return
     */
    int size();
    
    boolean contains(T item);

    public static interface Wrapped<T> extends Container<T>{
    	Container<T> getContainer();
    	default boolean add(T item){return getContainer().add(item);}
        default T remove(){return getContainer().remove();}
        default boolean isEmpty(){return getContainer().isEmpty();}
        default int size(){return getContainer().size();}
        default boolean contains(T item){return getContainer().contains(item);}
    }

    public static class Empty<T> implements Container<T>{
    	public boolean add(T item){return false;}
        public T remove(){return null;}
        public int size(){return 0;}
        public boolean contains(T item){return false;}
    }

    public static class Single<T> implements Container<T>{
    	public T item = null;
    	public boolean add(T item){
    		if(this.item == null){
    			this.item = item;
    			return true;
    		}else{
    			return false;
    		}
    	}
        public T remove(){
        	T item = this.item;
        	this.item = null;
        	return item;
        }
        public int size(){return this.item == null? 0 : 1;}
        public boolean contains(T item){return this.item == item;}
    }

    /**
     * Wrapper around `java.util.Stack` implementing `Container`.
     * @param <T>
     */
    public static class Stack<T> implements Container<T>{
        public final java.util.Stack<T> l;
        public Stack(java.util.Stack<T> l){this.l = l;}

        public boolean add(T item){l.push(item); return true;}
        public T remove(){
        	try{
        		return l.pop();
        	}catch(EmptyStackException e){
        		return null;
        	}}
        public int size(){return l.size();}
        public boolean contains(T item){return l.search(item) >= 0;}
    }

    /**
     * Wrapper around a `java.util.Queue` implementing `Container`.
     * @param <T>
     */
    class Queue<T,L extends java.util.Queue<T>> implements Container<T>{
        public final L l;
        public Queue(L l){this.l = l;}

        public boolean add(T item){
        	try{
        		return l.add(item);
        	}catch(IllegalStateException e){
        		return false;
        	}
        }
        public T remove(){
        	try{
        		return l.remove();
        	}catch(NoSuchElementException e){
        		return null;
        	}
        }
        public int size(){return l.size();}
        public boolean contains(T item){return l.contains(item);}
    }

    /**
     * Wrapper around a primitive array (a list of fixed length) implementing `Container`.
     * @param <T>
     */
    class Array<T> implements Container<T>{
    	protected final Object[] l;
    	protected int length;

    	public Array(int size){
    		this.l = new Object[size];
    		this.length = 0;
    	}
    	public Array(T[] l,int length){
    		this.l = l;
    		this.length = length;
    	}
    	
		@Override
		public boolean add(T item){
			if(length < l.length){
				l[length++] = item;
				return true;
			}else{
				return false;
			}
		}

		@Override
		public T remove(){
			if(length > 0){
				return getUnchecked(--length);
			}else{
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
	    protected T getUnchecked(int index){
	        return (T)l[index];
	    }

		@Override
		public int size(){
			return length;
		}
    	
		@Override
		public boolean contains(T item){
			for(int i=0; i<length; i++){
				if(l[i] == item) return true;
			}
			return false;
		}
    }

	class Bounded<T,C extends Container<T>> implements Container<T>{
		protected final C inner;
		protected final int maxSize;
		
		public Bounded(C inner,int maxSize){
			this.inner = inner;
			this.maxSize = maxSize;
		}

		@Override
		public boolean add(T item) {
			if(this.size() < this.maxSize){
				return this.inner.add(item);
			}
			return false;
		}

		@Override
		public T remove() {
			return this.inner.remove();
		}

		@Override
		public int size() {
			return this.inner.size();
		}

		@Override
		public boolean contains(T item) {
			return this.inner.contains(item);
		}
	}
}
