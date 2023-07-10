import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Platform obj = new Platform();
        obj.setPlatform();
        Scanner scan=new Scanner(System.in);

        boolean ok=true;
        do {
            System.out.println("Choose an option : 1.Admin, 2.User, 3.Exit.");
            int option = Integer.parseInt(scan.nextLine());

            switch (option) {
                case 1:
                {
                        System.out.println("Hello, admin! Introduce your name : ");
                        String name = scan.nextLine();
                        boolean ok1=true;
                       do {
                            if (obj.existAdmin(name)) {
                                System.out.println("Hi, " + name + "!");

                                do {
                                    System.out.println("Enter 1.To add a new movie. 2.Exit");
                                    int choice = Integer.parseInt(scan.nextLine());
                                    if (choice == 1) { String movieName;
                                        String movieCategory;
                                        String movieDate;
                                        int movieLikes;
                                        System.out.println("Introduce the movie name, the category and the date : ");
                                        movieName = scan.nextLine();
                                        movieCategory = scan.nextLine();
                                        movieDate = scan.nextLine();
                                        movieLikes = 0;

                                        obj.addMovie(movieName,movieCategory,movieDate,movieLikes);
                                        obj.printDataset();
                                    }
                                    else ok1=false;
                                }while(ok1==true);
                            }
                            else {
                                    System.out.println("Your name is not valid.Enter 1-try again, 2-exit");
                                    int choice1 = Integer.parseInt(scan.nextLine());
                                    if(choice1==1) {
                                        System.out.println("Introduce your name: ");
                                        name = scan.nextLine();
                                    }
                                    else ok1=false;

                                }
                            }while(ok1==true);
                       break;
                        }

                case 2:
                {
                        System.out.println("1.Login, 2.Sign up :");

                        int input = Integer.parseInt(scan.nextLine());
                        System.out.println("Hello, user! Introduce your username & password: ");
                        String username, password;

                        username = scan.nextLine();
                        password = scan.nextLine();

                        if (input == 1) {
                            if (obj.existUser(username, password)) {
                                System.out.println("Your account doesn't exist or is incorrect.");
                                break;
                            }
                        } else if (input == 2) {
                            obj.addUser(username, password);
                        }

                        boolean ok2=true;
                        do {
                            System.out.println("Choose 4 -to see all movies, 5 -to see your feed, 6 -to like a movie, 7 -exit.");
                            option = Integer.parseInt(scan.nextLine());

                            switch (option) {
                                case 4:
                                    obj.printDataset();
                                    break;
                                case 5:
                                    obj.printFeedOfUser(username);
                                    break;
                                case 6:
                                    obj.printFeedOfUser(username);
                                    System.out.println("Choose a movie to like: 0-11");
                                    int number = Integer.parseInt(scan.nextLine());
                                    obj.setCustomFeed(number,username);
                                    break;
                                case 7:
                                    ok2=false;
                                    break;
                            }
                        }while(ok2==true);

                        break;
                }
                case 3: {
                    System.out.println("Exit.");
                    return;
                }
                default: {
                    System.out.println("Incorrect option.");
                    break;
                }
            }
        }while(ok==true);
    }
}