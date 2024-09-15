package Book;

import java.time.LocalDate;

import User.User;

public class Book {
  private String title;
  private String author;
  private double price;
  private boolean isAvailable = true;
  LocalDate rentedDate;

  private User rentUser;

  public Book(String title, String author, double price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  public Book(String title, String author, double price, boolean isAvailable, LocalDate rentedDate, User rentUser) {
    this.title = title;
    this.author = author;
    this.price = price;
    this.isAvailable = isAvailable;
    this.rentedDate = rentedDate;
    this.rentUser = rentUser;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public double getPrice() {
    return price;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public User getRentUser() {
    return rentUser;
  }

  public LocalDate getRentedDate() {
    return rentedDate;
  }

  @Override
  public String toString() {
    return title + " by " + author;
  }

  public void setRentUser(User rentUser) {
    if (rentUser == null) {
      this.isAvailable = true;
      this.rentedDate = null;
    } else {
      this.rentUser = rentUser;
      this.isAvailable = false;
      this.rentedDate = LocalDate.now();
    }
  }

}
