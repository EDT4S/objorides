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

	interface Has<T>{
		Container<T> getContainer();
	}

    interface Wrapped<T> extends Container<T>,Has<T>{
    	default boolean add(T item){return getContainer().add(item);}
        default T remove(){return getContainer().remove();}
        default boolean isEmpty(){return getContainer().isEmpty();}
        default int size(){return getContainer().size();}
        default boolean contains(T item){return getContainer().contains(item);}
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
    final class Array<T> implements Container<T>{
    	protected final T[] l;
    	protected int length;

    	public Array(int size){
    		this.l = (T[])new Object[size];
    		this.length = 0;
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
				return l[--length];
			}else{
				return null;
			}
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
}
