/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardy;

/**
 *
 * @author nataliechin
 */
public class HighScore implements Comparable
{
    private String name; 
    private long score;
    
    protected String getName()
    {
        return name; 
    } //end of method
    
    protected long getScore()
    {
        return score; 
    } //end of method
    
    protected void setScore(long other)
    {
        score+=other; 
    } //end of method
    
    protected void setName(String other)
    {
        name = other; 
    } //end of method
    
    public int compareTo(Object other)
    {
                    //if the score is less than the one under it, you do not need to sort (therefore, return false). 
                    if (score < ((HighScore)other).getScore()) 
                    {
                        return -1; 
                    } //end of if statement

                    else if (score>((HighScore)other).getScore())//if the score is greater than the one under it, the program needs to sort. 
                    {
                        return 1; 
                    } //end of else statement

                    else
                    {
                        return 0; 
                    }
       

    } //end of method


       
    
}
