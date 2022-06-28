package com.epam;



public class Task {
    /**
     * Accepts a linked list head, reverses all elements and returns a new head (the last element).
     * PLEASE NOTE that it should not create new nodes, only change the next references of the existing ones.
     * E.g. you have a like "head:5 -> 7 -> 1 -> 4" should this method will return "head:4 -> 1 -> 7 -> 5"
     *
    // * @param head the first element of the list
     * @param <T>  element type
     * @return new head
     */
    private static class Node<T>{
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static <T> Node<T> reverseLinkedList(Node<T> head) {

        throw new UnsupportedOperationException();
    }
}
