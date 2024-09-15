package User;

import Book.Book;

public class User {
  private String fullName;
  private String email;

  private Book[] rentedBooks;
  private String[] history;

  public User(String fullName, String email) {
    this.fullName = fullName;
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public String[] getHistory() {
    return history;
  }
  
  @Override
  public String toString() {
    return email;
  }

  public void addRentedBook(Book newBook) {
    if (this.rentedBooks == null) {
      this.rentedBooks = new Book[1];
      this.rentedBooks[0] = newBook;
    } else {
      Book[] combinedBooks = new Book[this.rentedBooks.length + 1];

      for (int i = 0; i < this.rentedBooks.length; i++) {
        combinedBooks[i] = this.rentedBooks[i];
      }
      combinedBooks[this.rentedBooks.length] = newBook;
      this.rentedBooks = combinedBooks;
    }
  }

  public Book[] getRentedBooks() {
    return rentedBooks;
  }

  public void removeRentedBook(Book bookToRemove) {
    if (this.rentedBooks == null || this.rentedBooks.length == 0) {
      return;
    }

    if (this.rentedBooks.length == 1) {
      this.rentedBooks = null;
      return;
    }

    int indexToRemove = -1;
    for (int i = 0; i < this.rentedBooks.length; i++) {
      if (this.rentedBooks[i].equals(bookToRemove)) {
        indexToRemove = i;
        break;
      }
    }
    if (indexToRemove == -1) {
      return;
    }

    Book[] updatedBooks = new Book[this.rentedBooks.length - 1];

    for (int i = 0; i < indexToRemove; i++) {
      updatedBooks[i] = this.rentedBooks[i];
    }

    for (int i = indexToRemove + 1; i < this.rentedBooks.length; i++) {
      updatedBooks[i - 1] = this.rentedBooks[i];
    }

    this.rentedBooks = updatedBooks;
  }

  

}
