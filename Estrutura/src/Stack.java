import java.util.NoSuchElementException;

public class Stack<Item> {
	private Node<Item> first;
	private int n;

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;

	}

	public Stack() {
		first = null;
		n = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}

	public void push(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}

	public Item pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}
}
