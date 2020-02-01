package ru.academit.shashkov.myhashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<T>[] table;
    private static final int DEFAULT_SIZE = 20;
    private int length;
    private int modCount;

    public MyHashTable() {
        //noinspection unchecked
        table = new ArrayList[DEFAULT_SIZE];
    }

    public MyHashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер таблицы должен быть больше нуля!");
        }
        //noinspection unchecked
        table = new ArrayList[size];
    }

    private int getIndexByHash(Object o) {
        if (o == null) {
            throw new NullPointerException("Попытка получить хэш-код от null!");
        }

        return Math.abs(o.hashCode() % table.length);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isIndexCorrect(int index) {
        return index < table.length && !isRowEmpty(index);
    }

    private boolean isRowEmpty(int index) {
        return table[index] == null || table[index].size() == 0;
    }

    private void ensureCapacity(int requiredCapacity) {
        while (table.length < requiredCapacity) {
            table = Arrays.copyOf(table, table.length * 2);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MyHashTable = {");

        for (int i = 0; i < table.length; ++i) {
            if (table[i] != null) {
                sb.append("Hash = ").append(i).append(table[i].toString()).append(", ");
            }
        }

        sb.append("}, length=").append(length);

        return sb.toString();
    }

    private class MyHashTableIterator implements Iterator<T> {
        int currentRowIndex = 0;
        int currentColumnIndex = -1;
        int expectedModCount = modCount;
        int rowsLeft = length;

        void checkForModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Коллекция была изменена в процессе итерирования!");
            }
        }

        int getIndexOfNextRow(int currentRowIndex) {
            for (int i = currentRowIndex + 1; ; ++i) {
                if (i >= table.length) {
                    throw new NoSuchElementException("Текущий элемент - последний в коллекции!");
                }

                if (!isRowEmpty(i)) {
                    --rowsLeft;
                    return i;
                }
            }
        }

        int getIndexOfPreviousRow(int currentRowIndex) {
            for (int i = currentRowIndex - 1; ; --i) {
                if (i == 0 || !isRowEmpty(i)) {
                    return i;
                }
            }
        }

        boolean isRowEnds(int currentColumnIndex) {
            return currentColumnIndex + 1 >= table[currentRowIndex].size();
        }

        @Override
        public boolean hasNext() {
            return rowsLeft > 0 || (!isRowEmpty(currentRowIndex) && !isRowEnds(currentColumnIndex));
        }

        @Override
        public T next() {
            checkForModification();

            if (isRowEmpty(currentRowIndex) || isRowEnds(currentColumnIndex)) {
                currentColumnIndex = -1;
                currentRowIndex = getIndexOfNextRow(currentRowIndex);
            }

            ++currentColumnIndex;

            return table[currentRowIndex].get(currentColumnIndex);
        }

        @Override
        public void remove() {
            checkForModification();
            if (currentColumnIndex < 0) {
                throw new IllegalStateException("Попытка обратиться к элементу с индексом меньше нуля!");
            }

            table[currentRowIndex].remove(currentColumnIndex);
            --currentColumnIndex;

            if (currentColumnIndex < 0) {
                if (isRowEmpty(currentRowIndex)) {
                    --length;
                }

                currentRowIndex = getIndexOfPreviousRow(currentRowIndex);

                if (table[currentRowIndex] != null) {
                    currentColumnIndex = table[currentRowIndex].size() - 1;
                }
            }
        }
    }

    @Override
    public int size() {
        return table.length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndexByHash(o);

        if (!isIndexCorrect(index)) {
            return false;
        }

        for (T e : table[index]) {
            if (Objects.equals(o, e)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(table, length);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray(Object[] a) {
        if (a.length >= length) {
            System.arraycopy(table, 0, a, 0, length);

            if (a.length > length) {
                a[length] = null;
            }

            return a;
        }

        return Arrays.copyOf(table, length, a.getClass());
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
        int index = getIndexByHash(t);

        ensureCapacity(index + 1);

        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }

        if (table[index].size() == 0) {
            ++length;
        }

        table[index].add(t);
        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) {
            return false;
        }

        int expectedModCount = modCount;

        for (Object o : c) {
            //noinspection unchecked
            if (add((T) o)) {
                ++modCount;
            }
        }

        return expectedModCount != modCount;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndexByHash(o);

        if (!isIndexCorrect(index)) {
            return false;
        }

        if (table[index].remove(o)) {
            ++modCount;

            if (table[index].size() == 0) {
                --length;
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        int expectedModCount = modCount;

        for (Object o : c) {
            int index = getIndexByHash(o);

            if (!isIndexCorrect(index)) {
                continue;
            }

            if (table[index].removeAll(c)) {
                if (table[index].size() == 0) {
                    --length;
                }
                ++modCount;
            }
        }

        return expectedModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }

        int expectedModCount = modCount;

        for (ArrayList<T> list : table) {
            if (list != null) {
                if (list.retainAll(c)) {
                    if (list.size() == 0) {
                        --length;
                    }
                    ++modCount;
                }
            }
        }

        return expectedModCount != modCount;
    }

    @Override
    public void clear() {
        for (ArrayList<T> list : table) {
            if (list != null && list.size() != 0) {
                list.clear();
                --length;
            }
        }

        ++modCount;
    }
}
