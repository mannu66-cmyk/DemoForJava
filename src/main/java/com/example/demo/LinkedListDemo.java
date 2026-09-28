package com.example.demo;

public class LinkedListDemo {

    // Node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;
    Node tail;

    // =========================================================
    // 1. INSERT / CREATE
    // =========================================================

    // Add node at the end
    public void add(int data) {

        Node newNode = new Node(data);

        // If list is empty
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        // Add after tail
        tail.next = newNode;
        // Move tail
        tail = newNode;
    }
    public void addAtIndex(int index, int data) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node newNode = new Node(data);

        // Insert at beginning
        if (index == 0) {
            newNode.next = head;
            head = newNode;

            if (tail == null) {
                tail = newNode;
            }

            return;
        }

        Node current = head;

        // Move to node just before desired index
        for (int i = 0; i < index - 1; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException("Invalid index");
            }
            current = current.next;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        // Insert new node
        newNode.next = current.next;
        current.next = newNode;

        // If inserted at the end, update tail
        if (newNode.next == null) {
            tail = newNode;
        }
    }
    // =========================================================
    // 2. PRINT LINKED LIST
    // =========================================================

    public void printList() {

        Node current = head;

        while (current != null) {

            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    // =========================================================
    // 3. SEARCH / CHECK ELEMENT
    // =========================================================

    public boolean contains(int value) {

        Node current = head;

        while (current != null) {

            if (current.data == value) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    // =========================================================
    // 4. UPDATE ELEMENT
    // =========================================================

    public boolean update(int oldValue, int newValue) {

        Node current = head;

        while (current != null) {

            if (current.data == oldValue) {

                current.data = newValue;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    // =========================================================
    // 5. DELETE ELEMENT
    // =========================================================

    public boolean delete(int value) {

        // Empty list
        if (head == null) {
            return false;
        }

        // Delete head
        if (head.data == value) {

            head = head.next;

            // If list became empty
            if (head == null) {
                tail = null;
            }

            return true;
        }

        Node current = head;

        while (current.next != null) {

            // Check next node
            if (current.next.data == value) {

                // If deleting tail
                if (current.next == tail) {
                    tail = current;
                }

                // Skip the node
                current.next = current.next.next;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    // =========================================================
    // 6. FIND MIDDLE ELEMENT
    // =========================================================

    public int findMiddle() {

        if (head == null) {
            throw new RuntimeException("LinkedList is empty");
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;

            fast = fast.next.next;
        }

        return slow.data;
    }

    // =========================================================
    // 7. CHECK CYCLIC LINKED LIST
    // =========================================================

    public boolean hasCycle() {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;

            fast = fast.next.next;

            // Both pointers meet
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    // =========================================================
    // 8. REVERSE LINKED LIST
    // =========================================================

    public void reverse() {

        Node previous = null;
        Node current = head;

        while (current != null) {

            // Save next node
            Node next = current.next;

            // Reverse the pointer
            current.next = previous;

            // Move previous
            previous = current;

            // Move current
            current = next;
        }

        // Old head becomes tail
        tail = head;

        // Previous becomes new head
        head = previous;
    }

    // =========================================================
    // 9. MERGE TWO SORTED LINKED LISTS
    // =========================================================

    public static Node merge(Node list1, Node list2) {

        // Dummy node to simplify logic
        Node dummy = new Node(0);

        Node current = dummy;

        while (list1 != null && list2 != null) {

            if (list1.data <= list2.data) {

                current.next = list1;

                list1 = list1.next;

            } else {

                current.next = list2;

                list2 = list2.next;
            }

            current = current.next;
        }

        // Remaining nodes
        if (list1 != null) {
            current.next = list1;
        }

        if (list2 != null) {
            current.next = list2;
        }

        return dummy.next;
    }

    // =========================================================
    // 10. PRINT FROM A GIVEN HEAD
    // =========================================================

    public static void printFromNode(Node head) {

        Node current = head;

        while (current != null) {

            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    // =========================================================
    // 11. MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        // -----------------------------------------------------
        // CREATE LINKED LIST
        // -----------------------------------------------------

        LinkedListDemo list = new LinkedListDemo();

        list.add(10);
        list.add(20);
        list.addAtIndex(1,35);
        list.add(30);
        list.add(40);
        list.add(50);

        System.out.println("Original List:");
        list.printList();


        // -----------------------------------------------------
        // SEARCH
        // -----------------------------------------------------

        System.out.println("\nSearch 30:");

        if (list.contains(30)) {
            System.out.println("30 found");
        } else {
            System.out.println("30 not found");
        }


        // -----------------------------------------------------
        // UPDATE
        // -----------------------------------------------------

        System.out.println("\nUpdate 30 -> 35:");

        list.update(30, 35);

        list.printList();


        // -----------------------------------------------------
        // DELETE
        // -----------------------------------------------------

        System.out.println("\nDelete 20:");

        list.delete(20);

        list.printList();


        // -----------------------------------------------------
        // FIND MIDDLE
        // -----------------------------------------------------

        System.out.println("\nMiddle Element:");

        System.out.println(list.findMiddle());


        // -----------------------------------------------------
        // CHECK CYCLE
        // -----------------------------------------------------

        System.out.println("\nCheck Cycle:");

        System.out.println(list.hasCycle());


        // -----------------------------------------------------
        // REVERSE
        // -----------------------------------------------------

        System.out.println("\nReverse List:");

        list.reverse();

        list.printList();


        // -----------------------------------------------------
        // CREATE FIRST SORTED LIST
        // -----------------------------------------------------

        LinkedListDemo list1 = new LinkedListDemo();

        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(7);


        // -----------------------------------------------------
        // CREATE SECOND SORTED LIST
        // -----------------------------------------------------

        LinkedListDemo list2 = new LinkedListDemo();

        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(8);


        // -----------------------------------------------------
        // MERGE TWO SORTED LISTS
        // -----------------------------------------------------

        System.out.println("\nList 1:");

        list1.printList();

        System.out.println("List 2:");

        list2.printList();

        Node mergedHead = merge(list1.head, list2.head);

        System.out.println("Merged List:");

        printFromNode(mergedHead);


        // -----------------------------------------------------
        // CREATE A CYCLIC LIST FOR TESTING
        // -----------------------------------------------------

        LinkedListDemo cyclicList = new LinkedListDemo();

        cyclicList.add(100);
        cyclicList.add(200);
        cyclicList.add(300);
        cyclicList.add(400);

        // Create cycle:
        // 400 -> 200
        cyclicList.tail.next = cyclicList.head.next;

        System.out.println("\nCyclic List:");

        System.out.println(cyclicList.hasCycle());
    }
}