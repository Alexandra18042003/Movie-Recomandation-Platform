package details;

public class Movie {
    private String name;
    private String category;
    private String date;
    private int likes;


    public Movie(String name, String category, String date, int likes) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.likes = likes;
    }
    public String getName(){ return name; }

    public String getCategory(){ return category; }
    public String getDate(){
        return date;
    }
    public int getLikes(){
        return likes;
    }
    public void incrementLikes() { likes++; }
}
