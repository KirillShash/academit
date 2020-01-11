package ru.academit.shashkov.mylist;

import java.util.NoSuchElementException;

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

    public T getFirstData() {
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

    public T remove(int index) {
        checkIndex(index);

        if (index == 0){
           return removeHead();
        }

        MyNode<T> prev = getNode(index-1);
        MyNode<T> current = prev.getNext();

    }

    public T removeHead() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст!");
        }

        T data = head.getData();
        MyNode<T> temp = head;
        head = head.getNext();

        temp.setData(null);
        temp.setNext(null);

        --size;

        return data;
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
