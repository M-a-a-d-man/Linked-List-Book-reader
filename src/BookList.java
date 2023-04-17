import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Evan Teboul and 40238390
 * COMP249
 * Assignment 4
 * April 17 2023
 * Question: 
 * @author evanteboul
 */
//-----------------------------------------------------
//Assignment 4
//Part: 1
//Written by: Evan Teboul 40238390
//-----------------------------------------------------

@SuppressWarnings("unused")
public class BookList {
	private Node head;
	private class Node {
		/**
		 * Book object
		 */
		private Book b;
		/**
		 * Node that links the list elements.
		 */
		private Node next;
		
		/**
		 * parameterized constructor using only the book
		 * @param b book
		 */
		public Node(Book b) {
			super();
			this.b = b;
		}
		/**
		 * Constructor that can set book object and linking node 
		 * @param b book object
		 * @param next Node, this links the current node to the next
		 */
		public Node(Book b, Node next) {
			super();
			this.b=b;
			this.next=next;
		}
		public Book getBook() {
			return b;
		}
	}
	/**
	 * default constructor for the BookList class
	 */
	public BookList() {
		head = null;
	}
	/**
	 * adds a book to the start of the LinkedList
	 * @param b book object
	 */
	public void addToStart(Book b) {
		if(head==null) {
			head = new Node(b);
			head.next = head;
		}
		//      1->23->4->1
		else {
			Node t = new Node(b,head);
			Node t1 = head;
			do { //must traverse and change the tail to point to the head
				if(t1.next==head) {
					t1.next = t; //locates the tail and sets the next node to be the new start value
					break;
				}
				t1=t1.next;
			}while(t1!=head);
			head = t;
		}
	}
	/**
	 * 
	 * what must be done:
	 * 1. we need to check each node
	 * 2. we need to then check n
	 * @param yr integer representing the year it was made.
	 */
	public void storeRecordsByYear(int yr) {
		if(head == null) {
			System.out.println("head is null");
		}
			
		else if(!this.contains(yr)) {
			System.out.println("there are no books with in this list that were written in the year " +yr);
		}
		else {
			int ctr=1;
			Node t = head;
			PrintWriter file=null;
			try {
				 file = new PrintWriter(new FileOutputStream("src/yearFiles/" + yr +".txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
			do {
				if(t.b.getYear()==yr) {
					ctr++;
					file.println(t.b.toString());
				}
				t = t.next;
			}while(t != head);
			file.close();
			System.out.println( ctr++ +"Files successfully added to file " + yr +".txt\n");
			
		}
	}
	/**
	 * This method checks if any there any books made in the year yr in the linked list
	 * @param yr year integer
	 * @return boolean value that represents whether the list contains the parameter
	 */
	public boolean contains(int yr) {
		if(this.find(yr)==null)
			return false;
		return true;
	}
	/**
	 * this method finds the node that has a book that was made in the year equivalent to yr.
	 * @param yr integer for the year
	 * @return Node that contains the book with the equivalent year
	 */
	public Node find(int yr) {
		if(head==null)
			return null;
		Node t = head;
		while(t.b.getYear()!=yr && t.next != head)  //will keep searching as long as long as the book year is not found
			t = t.next; 
		return t;
	}
	/**
	 * This method finds the node of any books in the linked list that have the isbn in the parameter.
	 * @param isbn long that represents a book's isbn
	 * @return Node where the first occurence of a book with matching isbn
	 */
	public Node find(long isbn) {
		if(head==null)
			return null;
		
		Node t = head;
		
		do {
			if(t.b.getIsbn()==isbn)
				return t;
			t=t.next;
		}
		while(t!=head);  //will keep searching as long as long as the book year is not found
		
		return null;
	}
	/**
	 * if there is a node that contains a book with the given isbn the function returns true.
	 * @param isbn, attribute of the Book Object
	 * @return boolean, if there is a node that contains a book with the given isbn the function returns true.
	 */
	public boolean contains(long isbn) {
		if(head==null) {
			return false;
		}
		else {
			if(find(isbn)==null)
				return false;
			else {
				return true;
			}
		}
	}
	
	
	/**
	 * finds the size of the list
	 * @return number of nodes.
	 */
	public int size() {
		int ctr=0;
		if(head==null)
			return 0;
		else {
			Node t = head;
			do {
				ctr+=1;
				t = t.next;
			}while(t!=head);
			return ctr;
		}
		
	}
	/**
	 * Inserts a Book object before the Node that has the book record with the isbn in the parameter
	 * @param isbn Book attribute, long isbn
	 * @param b Book object
	 * @return false if the head is null, or the isbn is not found
	 */
	public boolean insertBefore(long isbn, Book b) {
		if(head == null) {
			System.out.println("null head");
			return false;
		}
		else if(!this.contains(isbn)) {
			System.out.println("Book record with isbn: "+ isbn+" does not exist.");
			return false;
		}
		
		else {
			Node t1 = head;
			Node t2 = new Node(b);
			do {
				if(t1.next.b.getIsbn()==isbn) {
					t2.next=t1.next;
					t1.next=t2;
					return true;
				}
				t1=t1.next;
			}while(t1!=head);
			return false;
		}
	}
		
	/**
	 * This method inserts a node between two nodes, the two nodes are located by isbn
	 * @param isbn1 the isbn of the first node
	 * @param isbn2 the isbn of the second node
	 * @param b Book object
	 * @return boolean, returns true if the node was successfully inserted
	 */
	public boolean insertBetween(long isbn1,long isbn2, Book b) {
		if(head == null) {
			System.out.println("null head");
			return false;
		}
		else if(!this.contains(isbn1)||!this.contains(isbn2)) {
			if(!this.contains(isbn1)&&!this.contains(isbn2)) {
				System.out.println("Both books were not found.");
			}
			else if(!this.contains(isbn1)) {
				System.out.println("Book with ISBN, " +isbn1+ " was not found.");
			}
			else if(!this.contains(isbn2))
				System.out.println("Book with ISBN, " +isbn2+ " was not found.");
			return false;
		}
		
		else {
			Node t1 = head;
			Node t2 = new Node(b);
			do {
				if(t1.b.getIsbn()==isbn1&&t1.next.b.getIsbn()==isbn2) {
					t2.next=t1.next;
					t1.next=t2;
					return true;
				}
				t1=t1.next;
			}while(t1!=head);
		System.out.println("The isbn are not consecutive.");
		return false;
		}
	}
	
	/**
	 * Displays the book Object elements onto the console.
	 */
	public void displayContent() {
		if(head==null) {
			System.out.println("\nnull list");
		}
		else {
			Node t = head;
			do {
				System.out.println(t.getBook().toString() + " ==>");
				if(t.next==head)
					System.out.println("===> head");
				t = t.next;
			}while(t!=head);
		}
	}
	/**
	 * reads the text file, then stores each line in an Arraylist and then a for loop that starts at the last index and
	 * ends at index 0 will then give us the correct BookList.
	 * @return BookList, LinkedList of book Objects.
	 */
	public static BookList txtToList() {
		BookList bookList = new BookList();
		BufferedReader fl;
		ArrayList<Book> arrList = new ArrayList<Book>();
		Book book = null;
		String s="";
		try {
			fl=new BufferedReader(new FileReader("src/yearFiles/Book.txt"));
			while(true) {
				s = fl.readLine();
				if(s==null) 
					break;
				book = Book.toBook(s);
				arrList.add(book);
			}
			fl.close();
			for(int i = arrList.size()-1;i>=0;i--) {
				bookList.addToStart(arrList.get(i));
			}
		}catch(FileNotFoundException e) {
			System.out.println("file not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
//		bookList.displayContent();
		return bookList;
		}
	/**
	 * This method will remove all the consecutive repeated records.
	 * @return boolean, if the amount of repeated records is subtracted from the original size of the list, then the trimmed list size should be the the same as the size original size
	 * minus the number of repeated records, so the function will return true. If the method the returns false then the list head is null. 
	 */
	public boolean delConsecutiveRepeatedRecords() {
		if(head == null)
			return false;
		else {
			int ctr =0;
			int ogSize = this.size();
			Node t = head;
			Node t1 = head.next;
			
			do {
				if(t.b.equals(t1.b)) {
					ctr++; 
					if(t1==head) { //this is needed since if the last element is equal to the head element then it causes an infinite loop
						//infinite loop would be triggered because 
						t1 = head.next; //moves forward
						t.next = t1; // links tail to the t1
						head = t1; //makes the head point to t1
					}
					else {
						t.next = t1.next; //if t1 is the head then t.next = head.next.
						t1.next = null;
						t1 = t.next; //advances forward, but if t1 = t.next
					}
				}
				else { //if the elements are not equal then both the nodes move forward.
					t=t.next; 
					t1 = t1.next;
				}	
			}while(t!=head);
//			System.out.println(this.size());
			return (this.size() == (ogSize-ctr));
		}
	}
	
	/**
	 * This method returns a BookList containing only book records with the specified author in the parameter
	 * @param aut, the author attribute of the Book Object
	 * @return BookList that contains only the books of specified author
	 */
	public BookList extractAuthList(String aut) {
		if(head == null )
			return null;
		else {
			Node t = head;
			ArrayList<Book> rlist = new ArrayList<Book>();
			BookList list = new BookList();
			do {
				if(t.b.getAuthor().equals(aut)) 
					rlist.add(t.b);
				t=t.next;
			}while(t!=head); //list is reversed
			for(int i = rlist.size()-1;i>=0;i--) 
				list.addToStart(rlist.get(i)); // stores list in proper order
			return list;
		}
		
	}
	/**
	 * This method finds two records to swap and locates each by their isbn, if both isbns are found the records are swapped.
	 * @param isbn1 the first record that you would like to swap
	 * @param isbn2 the record that will the other record will swap with
	 * @return boolean, both isbns were not found or the head of the list is null the return value is false.
	 * If both the isbns were found then the swap will be successful and the function returns true.
	 */
	public boolean swap(long isbn1, long isbn2) {
		if(head == null) {
			return false;
		}
		else if(!this.contains(isbn1)||!this.contains(isbn2)) {
			if(!this.contains(isbn1)&&!this.contains(isbn2)) {
				System.out.println("Both books were not found.");
			}
			else if(!this.contains(isbn1)) {
				System.out.println("Book with ISBN, " +isbn1+ " was not found.");
			}
			else if(!this.contains(isbn2))
				System.out.println("Book with ISBN, " +isbn2+ " was not found.");
			return false;
		}
			
		else {
			Node t1 = head;
			do {
				if(t1.b.getIsbn()==isbn1) 
					break;
				t1 = t1.next;
				
			}while(t1!=head);
			Node t2 = head; 
			do {
				if(t2.b.getIsbn() == isbn2) 
					break;
				t2 = t2.next;
			}while(t2!=head);
			Book temp = t1.b;
			t1.b = t2.b;
			t2.b = temp;
			this.displayContent();
			return true;	
		}
	}
	/**
	 * This uploads the current version of the file to the updated.txt file
	 */
	public void commit() {
		if(head == null) { //if head is null do nothing
		}
		else {
			PrintWriter wrt = null;
			try {
				wrt = new PrintWriter(new FileOutputStream("src/yearFiles/Update_Books.txt"));
				Node t = head;
				do {
					wrt.println(t.b.toString());
					t = t.next;
				}while(t!=head);
				System.out.println("successfully commited.");
				wrt.close();
			}catch(FileNotFoundException e){
				System.out.println("File not found");
			}
		}
	}
	/**
	 * Appends the BookList. each book is added to the end of the list
	 * @param b book Object
	 */
	public void addToEnd(Book b) {
		if(head==null) {
			head = new Node(b);
			head.next = head;
		}
		else if(this.size()==1) {
			head.next = new Node(b,head);
		}
		else {
			Node t = head;
			Node t1 = new Node(b,head);
			while(t.next!=head) {
				t=t.next;
			}
			t.next = t1;
		}
	}
	/**
	 * Makes a deep copy of the bookList
	 * @return BookList copy of the bookList
	 */
	public BookList clone() {
		if(head ==null) 
			return null;
		else {
			BookList temp = new BookList();
			Node t = head;
			do {
				temp.addToEnd(t.getBook().clone());
				t = t.next;
			}while(t!=head);
			return temp;
		}
	}
	
	
//	//String title, String author, double price, long isbn, String genre, int year
//	public static void main(String[] args) {
//		Scanner rd = null;
//		PrintWriter wrt = null;
//		ArrayList<Book> arrLst = new ArrayList<Book>();
//		BookList bkLst = new BookList();
//		Book book=null;
//		
//		try {
//			rd = new Scanner(new FileInputStream("src/yearFiles/Book.txt"));
//			while(rd.hasNextLine()) { //this does the first part, stores files in array list and booklist
//				book = Book.toBook(rd.nextLine());
//				if(book.getYear()>=2024) {
//					arrLst.add(book);
//				}
//				else {
//					bkLst.addToEnd(book);
//				}
//			}
//		}catch(FileNotFoundException e) {
//			System.out.println("File not Found");
//		}
//		
//		try { //2. writes to year error file
//			wrt = new PrintWriter(new FileOutputStream("src/yearFiles/YearErr.txt"));
//			for(int i =0;i<arrLst.size();i++)
//				wrt.println(arrLst.get(i).toString());
//			wrt.close();
//		}catch(FileNotFoundException e) {
//			System.out.println("File not found");
//		}
//		Node t = bkLst.head;
//		do { //3. stores records by year
//			bkLst.storeRecordsByYear(t.b.getYear());
//			t=t.next;
//		}while(t!=bkLst.head);
//		
//		bkLst.delConsecutiveRepeatedRecords(); //4. delete all consecutive repeated records
//		BookList authList = bkLst.extractAuthList("Roy Malan"); //5. create a new bookList for all records of a specific author
//		bkLst.displayContent();
//		authList.displayContent();
//	}
}

	
	
	
	


 