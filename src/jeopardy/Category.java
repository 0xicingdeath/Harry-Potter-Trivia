/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardy;

/**
 *
 * @author nataliechin
 */
public class Category 
{
    private String question; 
    private String answer; 
    private boolean answered; 
    private String fact; 
    
    protected String getQuestion()
    {
        return question; 
    }//end of method
    
    protected String getAnswer()
    {
        return answer; 
    } //end of method.
    
    protected boolean getAnswered()
    {
        return answered; 
    } //end of method
    
    protected String getFact()
    {
        return fact; 
    } //end of method
    
    protected void setQuestion(String other)
    {
        question = other; 
    } //end of method
    
    protected void setAnswer(String other)
    {
        answer = other; 
    } //end of method
    
    protected void setFact(String other)
    {
        fact = other; 
    } //end of method
    
    protected void setAnswered(boolean other)
    {
        answered = other; 
    } //end of setAnswered method 
            
}
