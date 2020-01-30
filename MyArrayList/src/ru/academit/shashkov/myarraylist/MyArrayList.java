package ru.academit.shashkov.myarraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private static final int DEFAULT_CAPACITY = 10;
    private int length;
    private int modCount;

    public MyArrayList(int capacity) {
        if (capacity == 0) {
            throw new IllegalArgumentException("Вместимость не может быть равна 0!");
        }

        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Отсутствует индекс!");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Отсутствует индекс!");
        }
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity > items.length) {
            items = Arrays.copyOf(items, requiredCapacity);
        }
    }

    public void trimToSize() {
        if (length < items.length) {
            items = Arrays.copyOf(items, length);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MyArrayList{items = [");

        for (int i = 0; i < length; i++) {
            sb.append(items[i]);

            if (i == length - 1) {
                break;
            }
            sb.append(", ");
        }

        sb.append("], length= ").append(length).append("}");

        return sb.toString();
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (length <= a.length) {
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(items, 0, a, 0, length);

            if (length < a.length) {
                a[length] = null;
            }

            return a;
        }

        //noinspection unchecked
        return (T1[]) Arrays.copyOf(items, length, a.getClass());
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, length * 2);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T oldValue = items[index];

        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }

        --length;
        ++modCount;

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; ++i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index >= 0) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return true;
        }

        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean add(T t) {
        if (length > items.length) {
            increaseCapacity();
        }

        items[length] = t;
        ++length;
        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexForAdd(index);

        int addLength = c.size();

        ensureCapacity(length + addLength);

        if (index != length) {
            System.arraycopy(items, index, items, index + addLength, length - index);

            modCount += addLength;
            length += addLength;
            int i = index;

            for (T o : c) {
                items[i] = o;
                ++i;
            }
            return true;
        }

        for (T o : c) {
            add(o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (Object o : c) {
            if (contains(o)) {
                remove(o);
                --length;
                ++modCount;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            this.clear();
            return true;
        }

        int expectedModCount = modCount;

        for (int i = 0; i < length; ++i) {
            if (c.contains(items[i])) {
                continue;
            }
            remove(i);
            --i;
        }

        return expectedModCount != modCount;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; ++i) {
            items[i] = null;
        }

        modCount += length;
        length = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);

        T oldValue = items[index];
        items[index] = element;

        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);

        if (index == length) {
            add(element);
            return;
        }

        if (length >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);

        items[index] = element;

        ++length;
        ++modCount;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; --i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    private class MyIterator implements Iterator<T> {
        int currentIndex = -1;
        int expectedModCount = modCount;

        void checkForModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Коллекция была изменена в процессе итерирования!");
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public T next() {
            checkForModification();

            if (!hasNext()) {
                throw new NoSuchElementException("Текущий элемент - последний в коллекции!");
            }

            ++currentIndex;

            return items[currentIndex];
        }

        @Override
        public void remove() {
            if (currentIndex < 0) {
                throw new IllegalStateException("Попытка обратиться к элементу с индексом меньше нуля!");
            }

            checkForModification();
            MyArrayList.this.remove(currentIndex);

            expectedModCount = modCount;
            --currentIndex;
        }
    }

    private class MyListIterator extends MyIterator implements ListIterator<T> {
        MyListIterator(int index) {
            super();

            if (index != 0) {
                currentIndex = index - 1;
            }
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public T previous() {
            checkForModification();

            if (!hasPrevious()) {
                throw new NoSuchElementException("Текущий элемент - первый в коллекции!");
            }

            --currentIndex;

            return items[currentIndex];
        }

        @Override
        public int nextIndex() {
            return currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void set(T t) {
            if (currentIndex < 0) {
                throw new IllegalStateException("Попытка обратиться к элементу с индексом меньше нуля!");
            }

            checkForModification();

            MyArrayList.this.set(currentIndex, t);
        }

        @Override
        public void add(T t) {
            checkForModification();

            ++currentIndex;

            MyArrayList.this.add(currentIndex, t);

            expectedModCount = modCount;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        checkIndexForAdd(index);
        return new MyListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        //noinspection ConstantConditions
        return null;
    }
}
