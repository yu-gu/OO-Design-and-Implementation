
public class BookCollection {

	Book[] coll;
	int numberOfBooks;
	
	public BookCollection(int maxSize){
		coll = new Book[maxSize];
		numberOfBooks = 0;
	}

	
	public void addBook(Book b){
		if (numberOfBooks >= 0 && numberOfBooks < coll.length){
			coll[numberOfBooks] = b;
			numberOfBooks++;
		}
		
	}

	
	public Book getBook(int i){
		if (i >=0 && i < numberOfBooks){
			return coll[i];
		}else return null;
		
	}
	
	public static void main(String[] args){
		BookCollection myColl = new BookCollection(100);
		Book b = new Book();
		myColl.addBook(b);
		Book b2 = myColl.getBook(0);
		System.out.print(b2.getTitle());
		
		
	}
	
}
