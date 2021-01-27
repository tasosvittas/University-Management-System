package ergasiamain;

public class Lesson {
    private String lessonName, lessonCode;
    private int lessonSemester;
    
    Lesson(String ln, String lc,int ls)
    {
        lessonName = ln;
        lessonCode = lc;
        lessonSemester = ls;
    }
    
    public void setLessonName(String in)
    {
        lessonName = in;
    }
    public String getLessonName()
    {
        return lessonName;
    }
    public void setLessonCode(String in)
    {
        lessonCode = in;
    }
    public String getLessonCode()
    {
        return lessonCode;
    }
    public void setLessonSemester(int in)
    {
        lessonSemester = in;
    }
    public int getLessonSemester()
    {
        return lessonSemester;
    }
    public String toString()
    {
        return lessonName+","+lessonCode+","+lessonSemester;
    }
}
