package ru.academit.shashkov.mylist;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyList<T> {
    private int size;
    private MyNode<T> head;

    private MyNode<T> getHead() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст!");
        }

        return head;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Такого индекса нет в списке.");
        }
    }

    public T getHeadData() {
        return head.getData();
    }

    public int getSize() {
        return size;
    }

    private MyNode<T> getNode(int index) {
        checkIndex(index);

        MyNode<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }

    public T getData(int index) {
        return getNode(index).getData();
    }

    public T setData(T data, int index) {
        checkIndex(index);

        MyNode<T> node = getNode(index);
        T oldData = node.getData();

        node.setData(data);

        return oldData;
    }

    public T removeHead() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст!");
        }

        T removedData = head.getData();
        MyNode<T> temp = head;
        head = head.getNext();

        temp.setData(null);
        temp.setNext(null);

        --size;

        return removedData;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeHead();
        }

        MyNode<T> prev = getNode(index - 1);
        MyNode<T> current = prev.getNext();
        T removedData = getData(index);

        prev.setNext(current.getNext());

        current.setNext(null);
        current.setData(null);
        --size;

        return removedData;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            removeHead();

            return true;
        }

        for (MyNode<T> current = head.getNext(), prev = head; current != null; prev = current, current = current.getNext()) {
            if (Objects.equals(current.getData(), data)) {
                prev.setNext(current.getNext());
                current.setNext(null);
                current.setData(null);
                --size;

                return true;
            }
        }
        return false;
    }

    public void addHead(T data) {
        head = new MyNode<>(data, head);
        ++size;
    }

    public void add(T data, int index) {
        checkIndex(index);

        if (index == 0) {
            addHead(data);
        }

        MyNode<T> prev = getNode(index - 1);
        MyNode<T> current = prev.getNext();

        prev.setNext(new MyNode<>(data, current));

        ++size;
    }

    public void invert() {
        if (head == null) {
            return;
        }

        for (MyNode<T> prev = null, current = head, next = current.getNext(); ; prev = current, current = next, next = next.getNext()) {
            current.setNext(prev);

            if (next == null) {
                head = current;
                break;
            }
        }
    }

    public MyList<T> copy() {
        MyList<T> newList = new MyList<>();

        if (head == null) {
            return newList;
        }

        newList.size = this.size;
        newList.addHead(this.getHeadData());

        for (MyNode<T> prev = newList.head, current = head.getNext(); ; current = current.getNext()) {
            prev.setNext(new MyNode<>(current.getData(), null));
            prev = prev.getNext();

            if (current.getNext() == null) {
                prev.setNext(null);
                break;
            }
        }

        return newList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (MyNode<T> current = this.getHead(); current != null; current = current.getNext()) {
            sb.append(current.getData());
            sb.append(" ");
        }

        return sb.toString();
    }
}
