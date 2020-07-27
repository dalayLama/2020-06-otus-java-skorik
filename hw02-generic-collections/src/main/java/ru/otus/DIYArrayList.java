package ru.otus;

import java.util.*;

public class DIYArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;

    private int size = 0;

    public DIYArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public DIYArrayList(Collection<? extends E> collection) {
        elements = Arrays.copyOf(collection.toArray(), collection.size());
        size = elements.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        add(e, size);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elements[index];

    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = get(index);
        elements[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void add(E e, int insertIndex) {
        if (insertIndex >= elements.length) {
            elements = grow(insertIndex);
        }
        elements[insertIndex] = e;
        size++;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > 0) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);
            return elements = Arrays.copyOf(elements, newCapacity);
        } else {
            return elements = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private class Itr implements Iterator<E> {

        /**
         * index current position
         */
        int cursor = -1;

        /**
         * index last returned element
         */
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return (cursor + 1) != size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            final int newIndex = cursor + 1;
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            E e = (E) elements[newIndex];
            cursor = newIndex;
            lastRet = newIndex;
            return e;
        }
        
    }

    private class ListItr extends Itr implements ListIterator<E> {

        @Override
        public boolean hasPrevious() {
            return (cursor - 1) >= 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E previous() {
            final int newIndex = cursor - 1;
            if (newIndex < 0) {
                throw new NoSuchElementException();
            }
            E e = (E) elements[newIndex];
            cursor = newIndex;
            lastRet = newIndex;
            return e;
        }

        @Override
        public int nextIndex() {
            return cursor + 1;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            elements[lastRet] = e;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

}
