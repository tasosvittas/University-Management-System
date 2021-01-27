 
package ergasiamain;

public class Student {
    private String name,lastname;
    private int am,semester;
    
    Student(String n,String l, int a, int s)
    {
        name=n;
        lastname=l;
        am=a;
        semester=s;
    }
    
    public void setName(String in)
    {
        name=in;
    }
    public String getName()
    {
        return name;
    }
    public void setLastName(String in)
    {
        lastname = in;
    }
    public String getLastName()
    {
        return lastname;
    }
    public void setAm(int in)
    {
        am = in;
    }
    public int getAm()
    {
        return am;
    }
    public void setSemester(int in)
    {
        semester = in;
    }
    public int getSemester()
    {
        return semester;
    }
    public String toString()
    {
       return name+","+lastname+","+am+","+semester;
    }
            
}
