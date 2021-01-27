package ergasiamain;

public class Enroll {
    private String lessonCode;
    private int am;
    private double studentGrade;
    
    Enroll(int a, String lc, double sg)
    {
        am = a;
        lessonCode = lc;
        studentGrade =sg;
    }
    public void setAmEnroll(int in)
    {
        am = in;
    }
    public int getAmEnroll()
    {
        return am;
    }
    public void setLessonCodeEnroll(String in)
    {
        lessonCode = in;
    }
    public String getLessonCodeEnroll()
    {
        return lessonCode;
    }
    public void setStudentGrade(double in)
    {
        studentGrade = in;
    }
    public double getStudentGrade()
    {
        return studentGrade;
    }
    public String toString()
    {
        return am+","+lessonCode+","+studentGrade;
    }
}
