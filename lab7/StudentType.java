/**
 */
public class StudentType {

    private String Fname;
    private String Lname;
    private String SID;
    private double [] Scores;
    private char Letter;
    private static int MaxScores = 10;

        // initialize all String attributes to an empty String,
        // initialize all numeric values to zero.
    public StudentType () {
        Fname = "";
        Lname = "";
        SID = "";
        Scores = new double [MaxScores];
        for (int i = 0; i < MaxScores; ++i)
            Scores [i] = 0;
    }
    public StudentType (String lname, String fname, String sid) {
        Fname = fname;
        Lname = lname;
        SID = sid;
        Scores = new double [MaxScores];
        for (int i = 0; i < MaxScores; ++i)
            Scores [i] = 0;
    }

        // set and get Last Name
    public void setLname(String lname) {
        Lname = lname;
    }
    public String getLname() {
        return (Lname);
    }

        // set and get First Name
    public void setFname(String fname) {
        Fname = fname;
    }
    public String getFname() {
        return (Fname);
    }

        // set and get SID
    public void setSID(String sid) {
        SID = sid;
    }
    public String getSID() {
        return (SID);
    }
        // get a formatted SID
    public String getFormatedSID() {
        StringBuffer tSID = new StringBuffer (SID);

        if (SID.length () != 0) {
            tSID.insert (5, '-');
            tSID.insert (3, '-');
        }
        return tSID.toString ();
    }
        
        // set and get Score
    public void setScore(int quizNum, double score) {
        if (quizNum > 0 && quizNum <= MaxScores)
            Scores [quizNum - 1] = score;
    }
    public double getScore(int quizNum) {
        double rScore = 0.0;
        if (quizNum > 0 && quizNum <= MaxScores)
            rScore = Scores [quizNum - 1];
        return rScore;
    }

    public double getTotal() {
        double sum = 0;
        for (int i = 0; i < MaxScores; ++i)
            sum += Scores [i];
        return sum;
    }
        // set and get letter grade for the current quiz
   public void setLetter (char letterGrade) {
        Letter = letterGrade;
   }
   public char getLetter () {
        return Letter;
   }

        // get First Name Last Name formatted ID as a String
        // there will be a space between the first name and the
        // last name and a tab between the last name and the ID
    public String toString() {
        return Fname + " " + Lname + "\t" + getFormatedSID ();
    }
}
