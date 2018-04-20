/**
 * Created by Владимир on 07.07.2017.
 */
public class List {

    Cell head;
    String key;

    public List() {
        head = new Cell();
        head.next = null;
    }

    public void addFirst(Object value) {
        Cell newCell = new Cell();
        newCell.value = value;
        newCell.next = head.next;
        head.next = newCell;
    }

    public void deleteFirst() {
        Cell curr = head.next;
        head.next = head.next.next;
        curr.next = null;
    }

    public void deleteLast() {
        Cell head2 = head;
        Cell prev = head;
        while (head2.next != null) {
            prev = head2;
            head2 = head2.next;
        }
        prev.next = null;
    }

    public Object delete(Object value) {
        Cell head2 = head;
        while (Integer.parseInt(head2.next.value.toString()) != Integer.parseInt(value.toString()) && head2.next != null)
            head2 = head2.next;
        if(head2.next == null)
            return null;
        else {
            Cell curr = head2.next;
            head2.next = head2.next.next;
            curr.next = null;
            return value;
        }
    }

    public void addLast(Object value) {
        Cell head2 = head;
        Cell newCell = new Cell();
        newCell.value = value;
        while (head2.next != null)
            head2 = head2.next;
        head2.next = newCell;
        newCell.next = null;
    }

    public void setLoop() {
        Cell tail = head;
        while (tail.next != null)
            tail = tail.next;
        tail.next = head.next;
    }

    public boolean hasLoop() {
        Cell cell = head;
        while (cell.next != null) {
            Cell tracer = head;
            while (tracer != cell) {
                if (tracer.next == cell.next) {
                    // Для разрыва нужно ---- cell.next = null
                    return true;
                }
                tracer = tracer.next;
            }
            cell = cell.next;
        }
        return false;
    }

    /*public List sort() {
        List list = new List();
        Cell sent = list.head;
        sent.next = null;
        Cell curr = head;
        curr = curr.next;
        while (curr != null) {
            Cell next_cell = curr;
            curr = curr.next;
            Cell after_me = sent;
            while (after_me.next != null && after_me.next.value < next_cell.value) {
                after_me = after_me.next;
            }
            next_cell.next = after_me.next;
            after_me.next = next_cell;
        }
        head = list.head;
        return list;
    }*/

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Cell head2 = head.next;
        str.append(key+" = ");
        while (head2 != null) {
            str.append(head2.value.toString() + " ");
            head2 = head2.next;
        }
        return str.toString();
    }
}
