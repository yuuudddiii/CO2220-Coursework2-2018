	import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
  This class parses match results in text files into DatedMatchResult objects

  The text file MUST be structured this way:
  Date
    <blank line>
    team score-score team
    team score-score team
    <blank line>
  Date
    <blank line>
    team score-score team
    <blank line>

  etc.

  Where the date must be in the format DAY-OF-WEEK DAY-OF-MONTH MONTH YEAR
 */
public class DatedMatchResultParser {
    private List<DatedMatchResult> results;

    public List<DatedMatchResult> parse(File file) throws IOException {
        List<String> fileContents = Files.readAllLines(file.toPath());
        return parse(fileContents);
    }

    public List<DatedMatchResult> parse(List<String> file) {
        results = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy");
        for (Iterator<String> i = file.iterator(); i.hasNext(); ) {
            String line = i.next();

            if (line.matches("\\w+day \\d+ [JFMASOND]\\w+ [12]\\d\\d\\d")) { //regex to match dates in this format: Tuesday 8 May 2018
                LocalDate currentDate = LocalDate.parse(line, dateFormatter);
                processResultsForGivenDate(i, currentDate);
            }
        }

        return results;
    }

    private void processResultsForGivenDate(Iterator<String> iterator, LocalDate date) {
        //skip 1st blank line
        iterator.next();

        while (iterator.hasNext()) {
            String line = iterator.next().trim();
            //2nd blank line tells us we're at the end of results for this date
            if (line.trim().isEmpty()) {
                break;
            }
            parseLine(line, date);
        }
    }

    private void parseLine(String line, LocalDate date) {
        //line looks like e.g.: 'Crystal Palace 2-0 West Brom'
        String[] resultSplit = line.split("-");
        //resultSplit[0] will look like "<team> <score>", [1] will look like "<score> <team>"
        String[] homeResult = resultSplit[0].split(" ");
        String homeTeam = parseHomeTeam(homeResult);
        int homeGoals = parseHomeScore(homeResult);

        String[] awayResult = resultSplit[1].split(" ");
        String awayTeam = parseAwayTeam(awayResult);
        int awayGoals = parseAwayScore(awayResult);

        DatedMatchResult matchResult = new DatedMatchResult(date, homeTeam, homeGoals, awayTeam, awayGoals);
        results.add(matchResult);
    }

    //The results returned in the 4 methods below must be replaced with results parsed from their String[] parameters
    private String parseHomeTeam(String[] homeResult) {
    	if (teamNameHasSpacesInIt(homeResult))
    		return homeResult[0] + " " + homeResult[1];
    	else return homeResult[0];
    }

    private int parseHomeScore(String[] homeResult) {
    	if (teamNameHasSpacesInIt(homeResult)) 
    		return Integer.parseInt(homeResult[2]);

    	else return Integer.parseInt(homeResult[1]);
    }

    private String parseAwayTeam(String[] awayResult) {
    	if (teamNameHasSpacesInIt(awayResult))
         return awayResult[1] + " " + awayResult[2];
    	else return awayResult[1];
    }

    private int parseAwayScore(String[] awayResult) {
        return Integer.parseInt(awayResult[0]);
    }

    private boolean teamNameHasSpacesInIt(String[] result) {
        return result.length > 2;
    }
}
