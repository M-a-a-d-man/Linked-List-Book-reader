/**
 * Evan Teboul and 40238390
 * COMP249
 * Assignment 4
 * April 17 2023
 * Question: part1 and 2
 * @author evanteboul
 */

// -----------------------------------------------------
// Assignment 4
// Part: 1
// Written by: Evan Teboul 40238390
// -----------------------------------------------------
public class Book {
	private String title;
	private String author;
	private double price;
	private long isbn;
	private String genre;
	private int year;
	
	/**
	 * copy constructor
	 * @param b Book Object
	 */
	public Book(Book b) {
		title = b.title;
		author = b.author;
		price = b.price;
		isbn = b.isbn;
		genre = b.genre;
		year= b.year;
	}
	
	public Book clone() {
		return new Book(this);
	}
	/**
	 * 
	 * parameterized constructor
	 * @param title string that represents the title of the book
	 * @param author String that represents the author of the book
	 * @param price double which is the cost of the book
	 * @param isbn long which is the isbn
	 * @param genre String genre
	 * @param year integer representing the year written
	 */
	public Book(String title, String author, double price, long isbn, String genre, int year) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}
	/**
	 * turns a String line into a book Object
	 * @param s String
	 * @return Book Object
	 */
	public static Book toBook(String s) {
		String[] arr = s.split(",");
		return new Book(arr[0],arr[1],Double.parseDouble(arr[2]),Long.parseLong(arr[3]),arr[4],Integer.parseInt(arr[5]));
	}
	
	@Override 
	public String toString() {
		return title+","+ author +","+price+","+isbn+","+genre+","+year;
	}
	
	@Override
	public boolean equals(Object x) {
		if(x==null||x.getClass()!=this.getClass() )
		return false;
		Book o = (Book) x;
		return title.equals(o.title)&& author.equals(o.author)&& price==o.price && isbn == o.isbn&&genre.equals(o.genre)&&year==o.year;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
