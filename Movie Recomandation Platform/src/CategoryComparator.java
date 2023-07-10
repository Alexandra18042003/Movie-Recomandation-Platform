import details.Movie;

import java.time.LocalDate;
import java.util.Comparator;

public class CategoryComparator implements Comparator<Movie> {
    private final String category;

    public CategoryComparator(String category) {
        this.category = category;
    }
    @Override
    public int compare(Movie a, Movie b) {
        if (a.getCategory().equals(category) && !b.getCategory().equals(category)) {
            return -1;
        }
        if (!a.getCategory().equals(category) && b.getCategory().equals(category)) {
            return 1;
        }
        if (a.getCategory().equals(category) && b.getCategory().equals(category)) {
            int likesDiff = Integer.compare(b.getLikes(), a.getLikes());
            if (likesDiff != 0) {
                return likesDiff;
            } else {
                LocalDate date1 = LocalDate.parse(a.getDate());
                LocalDate date2 = LocalDate.parse(b.getDate());
                return -1*date1.compareTo(date2);
            }
        }
        return 0;
    }

}
