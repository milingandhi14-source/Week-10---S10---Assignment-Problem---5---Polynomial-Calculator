
public class p41 {

    // Node of a singly linked list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Reverse a linked list and return its new head
    static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
    }

    // Check whether the linked list is a palindrome
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle using slow and fast pointers
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        Node secondHalf = reverse(slow.next);

        // Save the reversed half's head
        Node secondHalfHead = secondHalf;

        // Step 3: Compare both halves
        Node firstHalf = head;
        boolean palindrome = true;

        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
                palindrome = false;
                break;
            }

            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // Step 4: Restore the original linked list
        slow.next = reverse(secondHalfHead);

        return palindrome;
    }

    // Append a digit to the linked list
    static Node append(Node head, int data) {
        Node newNode = new Node(data);

        if (head == null) {
            return newNode;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        return head;
    }

    // Print the linked list
    static void printList(Node head) {
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

    public static void main(String[] args) {

        // Sample 1: 1 -> 2 -> 3 -> 2 -> 1
        Node head1 = null;
        head1 = append(head1, 1);
        head1 = append(head1, 2);
        head1 = append(head1, 3);
        head1 = append(head1, 2);
        head1 = append(head1, 1);

        System.out.print("Train code 1: ");
        printList(head1);

        System.out.println("Is palindrome? " + isPalindrome(head1));

        System.out.print("List after checking: ");
        printList(head1);

        // Sample 2: 1 -> 2 -> 3 -> 2
        Node head2 = null;
        head2 = append(head2, 1);
        head2 = append(head2, 2);
        head2 = append(head2, 3);
        head2 = append(head2, 2);

        System.out.print("\nTrain code 2: ");
        printList(head2);

        System.out.println("Is palindrome? " + isPalindrome(head2));

        System.out.print("List after checking: ");
        printList(head2);

        // Sample 3: 1 -> 2 -> 2 -> 1
        Node head3 = null;
        head3 = append(head3, 1);
        head3 = append(head3, 2);
        head3 = append(head3, 2);
        head3 = append(head3, 1);

        System.out.print("\nTrain code 3: ");
        printList(head3);

        System.out.println("Is palindrome? " + isPalindrome(head3));

        System.out.print("List after checking: ");
        printList(head3);
    }
}
