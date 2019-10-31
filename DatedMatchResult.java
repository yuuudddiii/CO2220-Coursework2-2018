import java.io.Serializable; 
import java.time.LocalDate;
import java.util.Objects;

public class DatedMatchResult implements Serializable {
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private LocalDate date;

    public DatedMatchResult(LocalDate date, String homeTeam, int homeScore, String awayTeam, int awayScore) {
        this.homeTeam = homeTeam;
        this.homeScore = homeScore;
        this.awayTeam = awayTeam;
        this.awayScore = awayScore;
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public LocalDate getDate() {
        return date;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatedMatchResult)) {
            return false;
        }
        
        DatedMatchResult test = (DatedMatchResult) o;
        return homeScore == test.homeScore && 
        		awayScore == test.awayScore &&
        		Objects.equals(homeTeam, test.homeTeam) &&
        		Objects.equals(awayTeam, test.awayTeam) && 
        		Objects.equals(date, test.date);
   }

    @Override //should override Object's  hashCode() method when overriding equals()
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore, date);
    }
}
