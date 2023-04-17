import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//-----------------------------------------------------
//Assignment 4
//Part: 1
//Written by: Evan Teboul 40238390
//-----------------------------------------------------
/**
 * Evan Teboul and 40238390
 * COMP249
 * Assignment 4
 * April 17 2023
 * Question: 1
 * @author evanteboul
 */

public class Driver {
	/**
	 * Displays the menu
	 */
	public static void displayMenu() {
		System.out.println("\nWhat would you like me to do? Here are the options: ");
		System.out.println("================================================================================================");
		System.out.println("1) Give me a year # and I would extract all records of that year and store them in a file for that year;");
		System.out.println("2) Ask me to delete all consecutive repeated records;");
		System.out.println("3) Give me an author name and I will create a new list with the records of this author and display them;\n"
				+ "4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\n"
				+ "5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!\n"
				+ "6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!\n"
				+ "7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;\n"
				+ "8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
	}
	/**
	 * this is the main method
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner rd = null;
		PrintWriter wrt = null;
		ArrayList<Book> arrLst = new ArrayList<Book>();
		BookList bkLst = new BookList();
		Book book=null;
		
		try {
			rd = new Scanner(new FileInputStream("src/yearFiles/Book.txt"));
			while(rd.hasNextLine()) { //this does the first part, stores files in array list and booklist
				book = Book.toBook(rd.nextLine());
				if(book.getYear()>=2024) {
					arrLst.add(book);
				}
				else {
					bkLst.addToEnd(book);
				}
			}
			rd.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not Found");
		}
		
		try { //2. writes to year error file
			wrt = new PrintWriter(new FileOutputStream("src/yearFiles/YearErr.txt"));
			for(int i =0;i<arrLst.size();i++)
				wrt.println(arrLst.get(i).toString());
			wrt.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		//3. display book List
		
		
		//4. menu
		
		System.out.println("================================");
		System.out.println("Welcome to the Book menu\n");
		bkLst.displayContent();
//		Scanner 3kb1 = new Scanner(System.in);
		boolean commit = false;
		while (true) {
			Scanner kb1 = new Scanner(System.in);
		    displayMenu();
		    int option =0;
		    System.out.print("\nEnter your selection: ");
		    
		    while (!kb1.hasNextInt()) {
                System.out.println("Your input is invalid, please try again.");
                kb1.next(); // this is important!
            }
            option = kb1.nextInt();
		    
		    switch (option) {
		        case 1:
		            System.out.print("Enter the year for which you would like to search for: ");
		            int yr = 0;
		            while (!kb1.hasNextInt()) {
		                System.out.println("Your input is invalid, please try again.");
		                kb1.next(); // this is important!
		            }
		            yr = kb1.nextInt();
		            bkLst.storeRecordsByYear(yr);
		            bkLst.displayContent();
		            break;
		        case 2:
				bkLst.delConsecutiveRepeatedRecords();
				bkLst.displayContent();
				
				break;
			case 3:
				System.out.print("Enter the author's name: ");
				@SuppressWarnings("unused") String junk= kb1.nextLine();
				String author = kb1.nextLine();
				System.out.println(author);
				BookList authList = bkLst.extractAuthList(author);
				authList.displayContent();
				break;
			case 4:
				System.out.print("Enter the isbn of the book: ");
				while (!kb1.hasNextLong()) {
			        System.out.println("Your input is invalid, please try again.");
			        kb1.next(); // this is important!
			    }
				long isbn = kb1.nextLong();
				System.out.println("\nNow enter the info for the Book you would like to add to the Book List.\n"+
						"The order of the record information is:\n1.title\n2.author\n3.price\n4.isbn\n5.genre\n6.year");
				kb1.nextLine(); //junk line
				Book b = Book.toBook(kb1.nextLine());
				bkLst.insertBefore(isbn, b);
				bkLst.displayContent();
				
				break;
			case 5:
				System.out.print("Enter the isbn of the first book: ");
				while (!kb1.hasNextLong()) {
			        System.out.println("Your input is invalid, please try again.");
			        kb1.next();
			    }
				long isbn1 = kb1.nextLong();
				System.out.print("\nEnter the isbn of the second book: ");
				while (!kb1.hasNextLong()) {
			        System.out.println("Your input is invalid, please try again.");
			        kb1.next();
			    }
				long isbn2 = kb1.nextLong();
				System.out.println("\nNow enter the info for the Book you would like to add to the Book List.\n"+
				"The order of the record information is:\n1.title\n2.author\n3.price\n4.isbn\n5.genre\n6.year");
				String enter = kb1.nextLine();
				System.out.println(enter +"here");
				Book b1 = null;
				while(true) {
					try{
						b1 = Book.toBook(kb1.nextLine());
						break;
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("Your input is invalid, please try again.");
						 kb1.nextLine();
					}
				}
				bkLst.insertBetween(isbn1,isbn2,b1);
				bkLst.displayContent();
				break;
			case 6:
				System.out.print("\nEnter the isbn of the book you would like to swap: ");
				while (!kb1.hasNextLong()) {
			        System.out.println("Your input is invalid, please try again.");
			        kb1.next(); // this is important!
			    }
				long isbn3 = kb1.nextLong();
				System.out.print("\nEnter the isbn of the book you would like to swap this with: ");
				while (!kb1.hasNextLong()) {
			        System.out.println("Your input is invalid, please try again.");
			        kb1.next(); // this is important!
			    }
				long isbn4 = kb1.nextLong();
				bkLst.swap(isbn3,isbn4);
				System.out.println("Here are the contents of the swapped list:");
				System.out.println("==================================================================");
				break;
			case 7:
				commit = true;
				bkLst.commit();
				break;
			case 8:
				if(commit == false) {
					System.out.println("You must commit before I stop talking.");
					break;
				}	
				else {
					System.out.println("You have successfully committed, I will now stop talking and the program will terminate.");
					kb1.close();
					System.exit(0);
				}
				default:
					System.out.println("The selection entered is not in the list mentioned in the Book menu. Please try again.");
			}
		}
//		Dc Comics - A Celebration of The World's Favorite Comic Book Heroes,Les Daniels,19.95,0823079198,CCB,2003
	}
}
