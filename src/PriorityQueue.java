/**
 * Created by Владимир on 24.07.2017.
 */
public class PriorityQueue extends List {

    PriorityQueue() {
        super();
    }

    public void deleteLast() {
        Cell head2 = head;
        Cell prev = findMinPriority();
        if(prev != null) {
            Cell curr = prev.next;
            prev.next = prev.next.next;
            curr.next = null;
        }
    }

    public void addLast(int value, int priority) {
        Cell head2 = head;
        Cell newCell = new Cell();
        newCell.value = value;
        newCell.priority = priority;
        while (head2.next != null)
            head2 = head2.next;
        head2.next = newCell;
        newCell.next = null;
    }

    public Cell findMinPriority() {
        Cell head2 = head;
        Cell prev = head;
        if(head2.next == null)
            return null;
        int min = head2.next.priority;
        Cell cMin = head2.next;
        while (head2.next != null) {
            if(min>head2.next.priority) {
                min = head2.next.priority;
                cMin = head2.next;
                prev = head2;
            }
            head2 = head2.next;
        }
        return prev;
    }

    public void print() {
        Cell head2 = head.next;
        while (head2 != null) {
            System.out.print(head2.value + "  ("+head2.priority+")  ");
            head2 = head2.next;
        }
        System.out.println();
    }
}
