package ru.academit.shashkov.mylist;

class MyNode<T> {
    private T data;
    private MyNode<T> next;

    MyNode(T data, MyNode<T> next) {
        this.data = data;
        this.next = next;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    MyNode<T> getNext() {
        return next;
    }

    void setNext(MyNode<T> next) {
        this.next = next;
    }
}
