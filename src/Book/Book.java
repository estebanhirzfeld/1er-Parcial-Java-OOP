package Book;

import User.User;

public class Book {
  private String title;
  private String author;
  private double price;
  private boolean isAvailable = true;

  private User rentUser;

  public Book(String title, String author, double price) {
    this.title = title;
    this.author = author;
    this.price = price;
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

  @Override
  public String toString() {
    return title + " by " + author;
  }

  public void setRentUser(User rentUser) {
    this.rentUser = rentUser;
    this.isAvailable = false;

    if (rentUser == null) {
      this.isAvailable = true;
    }
  }

}
