package BuscaBinaria;

import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BSTree <Key extends Comparable<Key>, Value> {
	private Node root;

	public class Node {
		private Key key;
		private Value val;
		private Node father;
		private Node left;
		private Node rigth;

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;

		}

	}
	
	public boolean isEmpty() {
		return (root == null);
	}
	
	public void inorder() {
		inorder(root);
	}
	
	private void inorder(Node x) {
		if(x == null) return;
		inorder(x.left);
		System.out.println(x.key + " "+x.val);
		inorder(x.rigth);
	}
	
	public void revorder() {
		revorder(root);
	}
	
	public void revorder(Node x) {
		if(x == null) return;
		revorder(x.rigth);
		System.out.println(x.key+" "+x.val);
		revorder(x.left);
	}
	
	public void preorder() {
		preorder(root);
	}
	
	private void preorder(Node x) {
		if(x == null) return;
		System.out.println(x.key+ " "+x.val);
		preorder(x.left);
		preorder(x.rigth);
	}
	
	public void posorder() {
		posorder(root);
	}
	
	private void posorder(Node x) {
		if(x == null) return;
		posorder(x.left);
		posorder(x.rigth);
		System.out.println(x.key+ " "+x.val);
	}
	
	public BSTree() {
	}
	
	public boolean contains(Key key) {
		if(key == null) throw new NullPointerException("argument to contains() is null");
		return get(key) != null;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	public Value get(Node x, Key key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if 			(cmp < 0)	return get(x.left, key);
		else if 	(cmp > 0) 	return get(x.rigth, key);
		else					return x.val;
	}
	
	public void put(Key key, Value val) {
		int cmp;
		Node z = new Node(key, val);
		Node x = root;
		Node y = null;
		while(x != null) {
			y = x;
			cmp = key.compareTo(x.key);
			if(cmp < 0) x = x.left;
			if(cmp > 0) x = x.rigth;
			if(cmp == 0) {
				if(val == null) delete (key);
				else x.val = val;
				return;
			}
		}
		
		z.father = y;
		if(y == null) root = z;
		else {
			cmp = key.compareTo(y.key);
			if(cmp < 0) y.left = z;
			else y.rigth = z;
		}
	}
	
	public void transplant(Node u, Node v) {
		if(u.father == null) {
			root = v;
		}else {
			if(u == u.father.left) {
				u.father.left = v;
			}
			else {
				u.father.rigth = v;
			}
		}
		if(v != null) {
			v.father = u.father;
		}
	}
	
	public void delete(Key key) {
		if(key == null) throw new NullPointerException("argument to deleted() is null");
		delete(root, key);
	}
	
	public void delete(Node z, Key key) {
		if(z == null) return;
		int cmp = key.compareTo(z.key);
		if			(cmp < 0) 	delete(z.left,key);
		else if		(cmp > 0) 	delete(z.rigth, key);
		else {
			if(z.left == null) {
				transplant(z, z.rigth);
			}
			else {
				if(z.rigth == null) {
					transplant(z, z.left);
				}
				else {
					Node y = min(z.rigth);
					if(y.father != z) {
						transplant(y, y.rigth);
						y.rigth = z.rigth;
						y.rigth.father = y;
					}
					transplant(z,y);
					y.left = z.left;
					y.left.father = y;
				}
			}
		}
	}
	
	public Key min() {
		if(isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}
	
	private Node min(Node x) {
		if(x.left == null)	return x;
		else				return min(x.left);
	}
	
	public Key max() {
		if(isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
		return max(root).key;
	}
	
	private Node max(Node x) {
		if(x.rigth == null)	return x;
		else				return max(x.rigth);
	}
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("");
			System.exit(0);
		}
		
		int n;
		String tmp; 
		StringTokenizer st;
		
		BSTree<String, Cidade> mytree = new BSTree<String, Cidade> ();
		Cidade city;
		
		try {
			FileReader in1 = new FileReader(args[0]);
			BufferedReader br = new BufferedReader(in1);
			n = Integer.parseInt(br.readLine());
			
			for(int j = 0; j < n; j++) {
				tmp = br.readLine();
				st = new StringTokenizer(tmp);
				
				city = new Cidade(st.nextToken(),
						Integer.parseInt(st.nextToken()));
				mytree.put(city.get_nome(), city);
			}
			br.close();
			in1.close();
			
			in1 = new FileReader(args[1]);
			br = new BufferedReader(in1);
			
			n = Integer.parseInt(br.readLine());
			
			for(int j = 0; j < n; ++j) {
				tmp = br.readLine();
				
				city = mytree.get(tmp);
				if(city == null) System.out.print("\n[Failed] "+tmp+" não foi encontrada");
				
				else {
					System.out.print("\n[Ok]\t "+city.get_nome()+
							" foi encontrada. Temperatura lá é "+city.get_temp()+" F");
				}
			}
			br.close();
			in1.close();
			
			System.out.println("\n");
		}catch(IOException e) {
			
		}
		
	}

}
