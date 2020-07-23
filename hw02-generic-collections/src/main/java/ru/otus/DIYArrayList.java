package ru.otus;

import java.util.*;

public class DIYArrayList<E> implements List<E> {

    private Object[] elements;

    private int size = 0;

    public DIYArrayList() {
        elements = new Object[0];
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
        return Arrays.copyOf(elements, size());
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
        int insertIndex = elements.length;
        elements = grow();
        elements[insertIndex] = e;
        size++;
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
        Objects.checkIndex(index, size());
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size());
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

    private Object[] grow() {
        return grow(elements.length + 1);
    }

    private Object[] grow(int newCapacity) {
        if (newCapacity <= elements.length) {
            throw new IllegalArgumentException();
        }
        return elements = Arrays.copyOf(elements, newCapacity);
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

    private class Itr implements Iterator<E> {

        /**
         * index current position
         */
        private int cursor = -1;

        /**
         * index last returned element
         */
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return (getCursor() + 1) != size();
        }

        @Override
        public E next() {
            final int newIndex = getCursor() + 1;
            if (getCursor() >= size()) {
                throw new NoSuchElementException();
            }
            E e = get(newIndex);
            setCursor(newIndex);
            setLastRet(newIndex);
            return e;
        }

        final protected int getCursor() {
            return cursor;
        }

        final protected void setCursor(int cursor) {
            this.cursor = cursor;
        }

        final protected int getLastRet() {
            return lastRet;
        }

        final protected void setLastRet(int lastRet) {
            this.lastRet = lastRet;
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {

        @Override
        public boolean hasPrevious() {
            return (getCursor() - 1) >= 0;
        }

        @Override
        public E previous() {
            final int newIndex = getCursor() - 1;
            if (newIndex < 0) {
                throw new NoSuchElementException();
            }
            E e = get(newIndex);
            setCursor(newIndex);
            setLastRet(newIndex);
            return e;
        }

        @Override
        public int nextIndex() {
            return getCursor() + 1;
        }

        @Override
        public int previousIndex() {
            return getCursor() - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (getLastRet() < 0) {
                throw new IllegalStateException();
            }
            DIYArrayList.this.set(getLastRet(), e);
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

}
