import java.sql.Timestamp;

public class Score {
    private int scoreId;
    private double score;
    private int personId;
    private int categoryId;
    private Timestamp eventDateTime; // Add a datetime attribute

    public Score(int scoreId, double score, int personId, int categoryId, Timestamp eventDateTime) {
        this.scoreId = scoreId;
        this.score = score;
        this.personId = personId;
        this.categoryId = categoryId;
        this.eventDateTime = eventDateTime;
    }

    public double getScore() {
        return score;
    }

    public int getScoreId() {
        return scoreId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public Timestamp getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Timestamp eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
}

