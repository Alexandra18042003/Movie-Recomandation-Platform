import java.io.*;
import java.time.LocalDate;
import java.util.*;

import details.User;
import details.Movie;

public class Platform {
    private HashMap<String, ArrayList<Movie>> map= new HashMap<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<User> users= new ArrayList<>();
    private ArrayList<String> admins = new ArrayList<>();
    public void setPlatform() throws IOException {
        String adminName;

        String username;
        String password;

        String movieName;
        String movieCategory;
        String movieDate;
        LocalDate date;
        int movieLikes;

        //INCHIDE RESURSA AUTOMAT : TRY-WITH-RESOURCES
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\FACULTATE\\AN 2 SEM 1\\MIP\\Movie Recomandation Platform\\src\\movies"))) {
            while((movieName = br.readLine()) != null) {
                movieCategory=br.readLine();
                movieDate=br.readLine();
                movieLikes=Integer.parseInt(br.readLine());
                Movie movie = new Movie(movieName,movieCategory,movieDate,movieLikes);
                movies.add(movie);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        //de refacut ca mai sus
        BufferedReader in1 = new BufferedReader(new FileReader("D:\\FACULTATE\\AN 2 SEM 1\\MIP\\Movie Recomandation Platform\\src\\users"));
        while ((username = in1.readLine()) != null) {
            password=in1.readLine();
            User user = new User(username,password);
            users.add(user);
        }
        in1.close();


        //de refacut ca mai sus
        BufferedReader in2 = new BufferedReader(new FileReader("D:\\FACULTATE\\AN 2 SEM 1\\MIP\\Movie Recomandation Platform\\src\\admins"));
        while ((adminName = in2.readLine()) != null) {
            admins.add(adminName);
        }
        in2.close();

    }
    public void printDataset() {

        try {
            for(int i=0;i<movies.size();i++)
                System.out.println(movies.get(i).getName()+" - "+ movies.get(i).getDate()+" - "+ movies.get(i).getLikes());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }
    public void setRandomFeed() {
        ArrayList<Movie> copy= new ArrayList<>();
        copy.addAll(movies);
        Collections.shuffle(copy);
        for(int i=0;i<users.size();i++)
            map.put(users.get(i).getUsername(),copy);

    }
    public void printFeedOfUser(String username) {
        int count = 0;
        for (int i = 0; i < map.get(username).size(); i++) {
            System.out.println(count+")  " + map.get(username).get(i).getName() + " - " + map.get(username).get(i).getDate() + " - " + map.get(username).get(i).getLikes());
            count++;
        }
    }
    public void setCustomFeed(int input, String username){
        String category= map.get(username).get(input).getCategory();
        map.get(username).sort(new CategoryComparator(category));

        for (Movie movie : map.get(username)) {
            System.out.println(movie.getName() + " (" + movie.getCategory() + ","+ " Likes: "+movie.getLikes()+", Date release: "+movie.getDate()+" )");
        }
        map.get(username).get(input).incrementLikes();
    }
    public void addUser(String username, String password) throws IOException {
        FileWriter fw;
        BufferedWriter bw;

        fw = new FileWriter("D:\\FACULTATE\\AN 2 SEM 1\\MIP\\Movie Recomandation Platform\\src\\users", true);
        bw = new BufferedWriter(fw);

        try {
            bw.newLine();
            bw.write(username);
            bw.newLine();
            bw.write(password);
        } catch (FileNotFoundException e) {
            System.out.println("users was not found!");
        } catch (IOException e) {
            System.out.println("No users found!");
        }
        bw.close();
    }
    public boolean existUser(String username, String password){

        User newUSer= new User(username,password);
        for(int i=0;i<users.size();i++)
            if(users.get(i).equals(newUSer)==true)
                return true;
        return false;
    }
    public boolean existAdmin(String name){
        if(admins.contains(name))
            return true;
        return false;
    }
    public void addMovie(String mN, String mC, String mD,int likes) throws IOException {
        FileWriter fw;
       BufferedWriter bw;
        fw = new FileWriter("D:\\FACULTATE\\AN 2 SEM 1\\MIP\\Movie Recomandation Platform\\src\\movies", true);
        bw = new BufferedWriter(fw);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            bw.newLine();
            bw.write(mN);
            bw.newLine();
            bw.write(mC);
            bw.newLine();
            bw.write(mD);
            bw.newLine();
            bw.write(likes+"");

            Movie movie=new Movie(mN,mC,mD,likes);
            movies.add(movie);
            setRandomFeed();

        } catch (FileNotFoundException e) {
            System.out.println("Movies was not found!");
        } catch (IOException e) {
            System.out.println("No movies found!");
        }
        bw.close();
        fw.close();

        //
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\\\FACULTATE\\\\AN 2 SEM 1\\\\MIP\\\\Movie Recomandation Platform\\\\src\\\\movies"))) {
//            bw.newLine();
//            bw.write(mN);
//            bw.newLine();
//            bw.write(mC);
//            bw.newLine();
//            bw.write(mD);
//            bw.newLine();
//            bw.write(likes+"");
//
//            Movie movie=new Movie(mN,mC,mD,likes);
//            movies.add(movie);
//            setRandomFeed();
//
//        } catch (IOException e) {
//            System.out.println("Error writing to file: " + e.getMessage());
//        }
}

    public void printAll() {
        for(int i=0;i<movies.size();i++)
            System.out.println(movies.get(i).getName()+" - "+ movies.get(i).getDate()+" - "+ movies.get(i).getLikes());

        for(int i=0;i<users.size();i++)
            System.out.println(users.get(i).getUsername()+" - "+ users.get(i).getPassword());

        for(int i=0;i<admins.size();i++)
            System.out.println(admins.get(i));

        System.out.println("Map-ul este:");
        Set<String> setOfKeySet = map.keySet();

        // for-each loop
        for(String key : setOfKeySet) {

            System.out.println("\nUsername " + key + "\nFeed :" );

            for(Movie movie : map.get(key)) {
                System.out.println("\t\t\t\t" + movie.getName());
            }
        }
}
}
