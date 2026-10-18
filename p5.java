
public class p5 {

    // Node represents one term of a polynomial
    static class Node {
        int coefficient;
        int exponent;
        Node next;

        Node(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
            this.next = null;
        }
    }

    // Add two polynomials and return a new linked list
    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exponent > q.exponent) {
                tail.next = new Node(p.coefficient, p.exponent);
                p = p.next;
            } 
            else if (p.exponent < q.exponent) {
                tail.next = new Node(q.coefficient, q.exponent);
                q = q.next;
            } 
            else {
                // Same exponent: add the coefficients
                int sum = p.coefficient + q.coefficient;

                // Skip terms whose coefficients cancel to zero
                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                }

                p = p.next;
                q = q.next;

                if (sum == 0) {
                    continue;
                }
            }

            tail = tail.next;
        }

        // Copy the remaining terms of p
        while (p != null) {
            tail.next = new Node(p.coefficient, p.exponent);
            tail = tail.next;
            p = p.next;
        }

        // Copy the remaining terms of q
        while (q != null) {
            tail.next = new Node(q.coefficient, q.exponent);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    // Append a term to a polynomial
    static Node append(Node head, int coefficient, int exponent) {
        Node newNode = new Node(coefficient, exponent);

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

    // Print a polynomial in standard mathematical form
    public static void printPolynomial(Node head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        boolean first = true;

        while (head != null) {
            int c = head.coefficient;
            int e = head.exponent;

            if (c != 0) {
                if (!first) {
                    System.out.print(c > 0 ? " + " : " - ");
                } else if (c < 0) {
                    System.out.print("-");
                }

                int absC = Math.abs(c);

                if (e == 0 || absC != 1) {
                    System.out.print(absC);
                }

                if (e > 0) {
                    System.out.print("x");
                    if (e > 1) {
                        System.out.print("^" + e);
                    }
                }

                first = false;
            }

            head = head.next;
        }

        if (first) {
            System.out.print("0");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1:
        // p = 5x^3 + 4x^2 + 2
        // q = 5x^2 - 3x + 1

        Node p = null;
        p = append(p, 5, 3);
        p = append(p, 4, 2);
        p = append(p, 2, 0);

        Node q = null;
        q = append(q, 5, 2);
        q = append(q, -3, 1);
        q = append(q, 1, 0);

        System.out.print("Polynomial P: ");
        printPolynomial(p);

        System.out.print("Polynomial Q: ");
        printPolynomial(q);

        Node result = addPolynomials(p, q);

        System.out.print("Sum: ");
        printPolynomial(result);

        // Sample 2:
        // p = 3x^2 + 1
        // q = -3x^2 + 4x

        Node p2 = null;
        p2 = append(p2, 3, 2);
        p2 = append(p2, 1, 0);

        Node q2 = null;
        q2 = append(q2, -3, 2);
        q2 = append(q2, 4, 1);

        Node result2 = addPolynomials(p2, q2);

        System.out.print("Sample 2 Sum: ");
        printPolynomial(result2);
    }
}
