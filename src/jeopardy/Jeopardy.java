/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardy;

//Import for Border Layout
import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Font;
//Import for Grid Layout
import java.awt.GridLayout; 
//Import for Mouse Events
import java.awt.event.MouseEvent; 
//Import for Mouse Listener: 
import java.awt.event.MouseListener; 
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//Import for Buffered Reader
import java.io.BufferedReader;
//Import for FileNotFoundException
import java.io.FileNotFoundException;
//Import for FileReader
import java.io.FileReader;
//Import for InputStreamReader: 
import java.io.InputStreamReader;
import java.lang.reflect.Array;
//Import for Array List 
import java.util.ArrayList;
import java.util.Arrays;
//Import for Image Icon
import javax.swing.ImageIcon; 
//Import for JButton
import javax.swing.JButton;
//Import for JFrame
import javax.swing.JFrame; 
//Import for JLabel
import javax.swing.JLabel; 
//Import for JOptionPane
import javax.swing.JOptionPane;
//Import for JPanel
import javax.swing.JPanel; 
//Import for JScrollBar
import javax.swing.JScrollBar;
//Import for JTextArea
import javax.swing.JTextArea;
//Import for JTextField
import javax.swing.JTextField;
//Import for Window Constants
import javax.swing.WindowConstants; 

/**@author – Natalie Chin
@version – 2.0 - started October 3rd. 
 */
public class Jeopardy implements MouseListener 
{   
    //Declaration for Score
    private long score = 0;
    //Declaration for Tracing Statement
    private boolean tracing = false;
    //private JTextField[] answers = new JTextField[55];
    private Category[] category = new Category[49];
    //Declaration for Main Button Array. 
    private JButton[] button = new JButton[56];
    private JFrame frame = new JFrame("Harry Potter Jeopardy");
    //Declaration for continue button
    private JButton proceed = new JButton(new ImageIcon("Continue.png")); 
    //Declaration for whichQuestion Variable. 
    private byte whichQuestion = 0; 
    //Declaration for userAnswer
    private JTextField userAnswer = new JTextField(); 
    //Declaration for JFrame
    private JFrame questionFrame = new JFrame("Question");
    //Declaration for JTextField to display score
    JTextField knuts;
    //Declaration for Buffered Reader
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //Declaration for constant values
    private final int FIRST = 7, SECOND = 17, THIRD = 47, FOURTH = 77, FIFTH = 97, SIXTH = 297, SEVENTH = 777;
    //Declaration for high score java file. 
    private HighScore[] highScores = new HighScore[7];
    //Declaration for Boolean Variable. 
    private boolean isZero = false; 
    //Declaration for Array List
    ArrayList highScoreList = new ArrayList();
    //Declaration for Main Font
    Font mainFont = new Font("Harry P", Font.PLAIN, 15); 
    //Declaration for Title Font
    Font titleFont = new Font("Harry P", Font.BOLD, 20);
    //Declaration for Integer Variable to see if the program has been completed: 
    private int over = 0; 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new Jeopardy();
    }//End of main method

    Jeopardy() 
    {
        initialize();
        mainScreen();
    } //end of constructor
    
/****************************************************************************************************************************************************************************
     * Description of Method: This is where everything that is needed throughout the program gets initialized - arrays, buttons, etc. 
     * @author - Natalie Chin. 
     * @version - 2.0
     * @param - No parameters. 
     * @return - This method must be able to reach into the other classes to create objects.
     * @precondition - The other classes must exist (Actors, BeedleBard, Characters, HOuses, Quote, Spells, StoryLine), and the arrays must be predeclared in the class. 
     * @postcondition - All the arrays should now have values in them. 
     * @throws - IOException - Buffered Reader. 
     * @see - Used to create a cross reference (BETWEEN WHAT AND WHAT? O_O) 
     ****************************************************************************************************************************************************************************/        
   
    private void initialize() 
    {
        score = 1000; 

            //Declaration for reading in from a file
        
            BufferedReader infile; 

            try
            {
                    infile = new BufferedReader(new FileReader("ReadFile.txt")); 


                //Initializing all the objects in this array. 
                for (int i = 0; i<category.length; i++)
                {
                    category[i] = new Category();
                } //end of for loop

                for (int i = 0; i<category.length; i++)
                {
                    String temp = infile.readLine().trim();
                    category[i].setQuestion(temp);
                    if (tracing) System.out.println("////////////Question: "+i+ "  " + temp);

                    temp = infile.readLine().trim();
                    category[i].setAnswer(temp);
                    if (tracing) System.out.println("////////////Answer: "+i+ "  " + temp);

                    temp = infile.readLine().trim();
                    category[i].setAnswered(true);

                    temp = infile.readLine().trim(); 
                    category[i].setFact(temp);
                    if (tracing) System.out.println("////////////Fact: "+i+ "  " + temp);

                } //end of for loop. 
                
                infile.close();
        
            } //end of try catch statement
            catch (Exception e)
            { 
                System.out.println("Unable to open first file. ");
            } //end of catch statement
            
            try
            {

                //Redeclaration for buffered reader. 
                infile = new BufferedReader(new FileReader("HighScore.txt"));

                for (int i = 0; i<highScores.length; i++)
                {
                    highScores[i] = new HighScore();
                } //end of for loop

                for (int i = 0; i<highScores.length; i++)
                {
                    String tempName = infile.readLine().trim();
                    highScores[i].setName(tempName);
                    if (tracing) System.out.println("+++++++++++++++++++++++Temp Name: " +highScores[i].getName());

                    int tempScore = Integer.parseInt(infile.readLine().trim());
                    highScores[i].setScore(tempScore);
                    if (tracing) System.out.println("+++++++++++++++++++++++Temp Score: " +highScores[i].getScore());
                    
                    highScoreList.add(highScores[i].getName());
                    highScoreList.add(highScores[i].getScore());
                    if (tracing) System.out.println("+++++++++++++++++++++++High Scores Value: " +highScoreList.get(i)); 
                    
                } //end of for loop. 

                infile.close();
            } //end of try statement
            
            catch (Exception e)
            {
                 System.out.println("Unable to open second file.");
            }
            
            //highScoreList.
            
            if (tracing) System.out.println("|||||Is high score list array empty?||||" +highScoreList.isEmpty());
            if (tracing) System.out.println("+++++++++++++++++++++++Print Size of Array: " +highScoreList.size()); 
            
            for (int i = 0; i<highScoreList.size(); i++)
            {
                //highScoreList.sort();
            } //end of for loop. 
        
    } //end of initialize method. 

/****************************************************************************************************************************************************************************
     * Description of Method: Creates the game board - refer to http://fandomjeopardy.blogspot.ca for more information. 
     * @author - Natalie Chin. 
     * @version - 2.0
     * @param - Does not receive any information. 
     * @return - Does not return any information. 
     * @precondition - The frame, in the class must be declared a private object. The score must be initialized at the top of the class as it must be accessible to every method in this class. Also, the imports must all be imported (example: JPanel, JButton, Label, etc)
     * @postcondition - The game will display the frame and allow the user to interact with it. 
     * @throws - None. 
     * @see - Used to create a cross reference (BETWEEN WHAT AND WHAT? O_O) 
     ****************************************************************************************************************************************************************************/        
       
    private void mainScreen() 
    {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(8, 7));
        JPanel north = new JPanel(new GridLayout(0, 2));
        JLabel label = new JLabel("Jeopardy");
        knuts = new JTextField("Your Current Score: " + score + " knuts.");
            knuts.setEditable(false);
        
        north.add(label); 
        north.add(knuts);
        
        if (tracing) 
        {
            System.out.println("before the jlabel for loop");
        }

        /**
         * for (int i = 0; i<button.length; i++) { button[i] = new JButton(); }
         * //end of for loop
         */
        if (tracing) 
        {
            System.out.println("About to edit buttons");
        }

        for (int i = 0; i < button.length; i++) 
        {
            if (tracing) 
            {
                System.out.println("inside the for loop for editing buttons");
            }
            
            //In the main folder, all the pictures are named in order - from left to right in each consecutive column. They are numbered from zero to fifty six, so the program can automatically see it.
            button[i] = new JButton(new ImageIcon(i+".png"));
            
            //button[i].setSize(5, 5);
            
            button[i].setSize(1, 1);

            if (tracing) 
            {
                System.out.println("created " + button[i]);
                System.out.println("+++++++++++++++++++++++++++ Width: " +button[i].getWidth()+ " ++++++++++++++++ Height: " +button[i].getHeight());
            }
            button[i].setVisible(true);
            if (tracing)
            {
                //System.out.println("added " + button[i] + " to mouselistener");
            }
        } //end of for loop

        if (tracing)
        {
            System.out.println("after the for loop for adding jlabels");
        }

        /**
         * for (int i = 0; i<button.length; i++) { if (tracing)
         * System.out.println("inside the for loop for adding button to grid
         * layout"); grid.add(button[i]); if (tracing)
         * System.out.println("Added: " +i+ " inside string: " +button[i]+ "
         * ."); } //end of for loop
         */
        for (int i = 0; i < button.length; i++) 
        {
            grid.add(button[i]);
            button[i].addMouseListener(this);
            grid.setVisible(true);
        } //end of for loop

        panel.add(north, BorderLayout.NORTH);
        panel.add(grid, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.setSize(400, 400);
        panel.setFont(mainFont);
        panel.setForeground(Color.GREEN);
        panel.setBackground(Color.WHITE);
            

        if (tracing) 
        {
            System.out.println("Panel size: " + panel.getSize() + "");
        }

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1250, 1000);
        frame.setFont(titleFont);

        if (tracing) 
        {
            System.out.println("Frame Size: " + frame.getSize());
        }

    } //end of main screen method

    /****************************************************************************************************************************************************************************
     * Description of Method: Checks to see whether the user has clicked something. If they have, the program will do something depending on what was clicked.
     * @author - Natalie Chin. 
     * @version - 2.0
     * @param whatClicked 
     * @precondition - The JFrame, the whichQuestion variable, and the userQuestion JTextField need to be initalized at the top of the class as 'private.' 
     * @postcondition - After the user selects a question, the program will test whether it is a valid input. If it is, it will proceed to check whether it is the right answer or contains the right answer. If it isn't any input, it will proceed to tell them that it is an invalid input and ask for more information. 
     * @see - Used to create a cross reference (BETWEEN WHAT AND WHAT? O_O) 
     ****************************************************************************************************************************************************************************/        
       
    public void mouseClicked(MouseEvent whatClicked) 
    {
        
        
            questionFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel questionPanel = new JPanel(new BorderLayout());
        JPanel questionAnswer = new JPanel(new GridLayout(2, 0));
        JLabel title = new JLabel("");
        JTextArea question = new JTextArea();
        String temp;         
        
        if (tracing) 
        {
            System.out.println("Entered mouseClicked method");
        }

        if (tracing) 
        {
            System.out.println("What was clicked: " + whatClicked.getSource() + " Button: " + button[0]);
        }
        
        /*#################################################################################### CHARACTER BUTTON (1ST ROW) ######################################################
         *#######################################################################################################################################################################
         */
        
        if (whatClicked.getSource() == (button[0]))//character button
        {
            if (tracing) 
            {
                System.out.println("Entereed the button[0] if statement");
            }

            JOptionPane.showMessageDialog(frame, "In this section of Jeopardy, you will be tested on difference characters and how they are related to each other. \n WARNING: ALWAYS ENTER CHARACTER'S FULL NAMES. \n As per Jeopardy, the lower numbers (7, 17, etc) have the easiest questions.\n The larger numbers (such as 297 or 777) have the harder questions.\n Good luck!", "Characters", JOptionPane.INFORMATION_MESSAGE);
        } //end of if statement
        
        /*#################################################################################### STORY LINE BUTTON (1ST ROW) ######################################################
         *#######################################################################################################################################################################
         */

        if (whatClicked.getSource() == button[1])//StoryLine
        {
            if (tracing)
            {
                System.out.println("Entered the button[1] if statement");
            }

            JOptionPane.showMessageDialog(frame, "In this section of Jeopardy, you will be tested on the story line of Harry Potter. \n Note: We are talking about the books, not the movies as they differ greatly.\n WARNING: ALWAYS ENTER CHARACTER'S FULL NAME \n As per Jeopardy, the lower numbers (7, 17, etc) have the easiest questions. \n The larger numbers (such as 297 or 777) have the harder questions. \n Good luck!", "Story Line", JOptionPane.INFORMATION_MESSAGE);

        } //end of if statement
        
        /*#################################################################################### BEEDLE THE BARD BUTTON (1ST ROW) ######################################################
         *#######################################################################################################################################################################
         */        

        if (whatClicked.getSource() == button[2]) //Tales of beedle the bard
        {
            JOptionPane.showMessageDialog(frame, "In this section of Jeopardy, you will be tested on the Tales of Beedle The Bard, \n JK Rowling's tales of the Wizarding World (much like our fairy tales). \n WARNING: ALWAYS ENTER CHARACTER'S FULL NAME. \n As per Jeopardy, the lower numbers (7, 17, etc) have the easiest questions. \n The larger numbers (such as 297 or 777) have the harder questions. \n Good luck!", "Tales of Beedle the Bard", JOptionPane.INFORMATION_MESSAGE);
        } //end of if statement
        
        /*#################################################################################### QUOTES BUTTON (1ST ROW) ######################################################
         *#######################################################################################################################################################################
         */        

        if (whatClicked.getSource() == button[3]) //Quotes
        {
            JOptionPane.showMessageDialog(frame, "In this section of Jeopardy, you will be tested on different quotes used throughout the movie. \n You will be required to name the person that said this quote.\n WARNING: ALWAYS ENTER THE CHARACTER'S FULL NAME. \n As per Jeopardy, the lower numebrs (7, 17, etc) have the easier questions. \n The larger numbers (such as 297 or 777) have the harder questions. \n Good luck!", "Quotes", JOptionPane.INFORMATION_MESSAGE);
        } //end of if statement
        
        /*#################################################################################### HOUSE BUTTON (1ST ROW) ######################################################
         *#######################################################################################################################################################################
         */        

        if (whatClicked.getSource() == button[4]) //houses
        {
            JOptionPane.showMessageDialog(frame, "In this section of Jeopardy, you will be tested on all four houses, their founders, their histories, and their special items. \n You will be required to answer questions based on any of these topics. \n WARNING: ALWAYS ENTER THE CHARACTER'S FULL NAME. \n As per Jeopardy, the lower numbers (7, 17, etc) have the easiest questions. \n The larger numbers (such as 297 or 777) have the harder questions. \n Good luck!", "Houses", JOptionPane.INFORMATION_MESSAGE);
        } //end of if statement
        
        /*#################################################################################### SPELLS BUTTON (1ST ROW) ######################################################
         *#######################################################################################################################################################################
         */        

        if (whatClicked.getSource() == button[5]) //spells
        {
            JOptionPane.showMessageDialog(frame, "In this section of Jeopardy, you will be tested on the spells used in the books and in the movies. \n You will be required to answer incantations or what certain spells do. \n WARNING: IF YOU ARE EVER ASKED TO ENTER A CHARACTER NAME, ENTER THE FULL NAME.\n As per Jeopardy, the lower numbers (7, 17, etc) have the easiest questions. \n The larger numbers (such as 297 and 777) have the harder questions. \n Good luck!", "Spells", JOptionPane.INFORMATION_MESSAGE);
        } //end of if statement
        
        /*#################################################################################### ACTORS OF THE CAST LINE BUTTON (1ST ROW) ######################################################
         *#######################################################################################################################################################################
         */        

        if (whatClicked.getSource() == button[6]) //actors of the cast. 
        {
            JOptionPane.showMessageDialog(frame, "In this section of Jeopardy, you will be tested on the actors of the cast. \n This is the only section that has to do with the Harry Potter movies. \n WARNING: ALWAYS ENTER THE CHARACTER'S FULL NAME. \n The easier questions will be based on questions of the main characters in Harry Potter. \n As per Jeopardy, the lower numbers (7, 17, etc) have the easiest questions. \n The larger numbers (such as 297 and 777) have the harder questions. \n Good luck!", "Actors of the Cast", JOptionPane.INFORMATION_MESSAGE);
        } //end of if statement
        
        /*#######################################################################################################################################################################
        #######################################################################################################################################################################
        #######################################################################################################################################################################      
        ###################################################################################### Documentation for all If Statements Beyond This Point: ######################################################
        #######################################################################################################################################################################
                 

                if (whatClicked.getSource() == button[numberOfButton]) //the number of button is represented by the buttonArray up, in the initialize method. Refer to fandomjeopardy.blogspot.ca
                {

                    whichQuestion = 7;  //the whichQuestions allows the program to refer to which if statement to run after the 'continue' button whatClicked if statement is down below. 

                    if (category[0].getAnswered() == true)
                    {       
                            title.setText("Characters: 7 Knut Question");                                           This value will constantly change based on which question is asked. 
                            question.setText(category[0].getQuestion());                                            Prints out the question. 
                                question.setEditable(false);//so the user cannot edit the question                  Make sure that the question field cannot be edited by the user. 
                                question.setLineWrap(true); //so the program will automatically wrap the text.      Forces the program to wrap the text (only problem is that it seems to cut words in half, so I might have to expand the JFrame size). 
                            userAnswer.setText("");                                                                 Make sure the JTextField that is in the Frame is cleared. 
                            proceed.addMouseListener(this);                                                         Add the continue button to the mouseListener method (this will be universal throughout the code, which is why the whichQuestion is needed). 


                            //add the jtextarea and the jtextfield to the grid layout (centre)
                            questionAnswer.add(question);                                                           Add the questionField to the gridLayout. 
                            questionAnswer.add(userAnswer);                                                         Add the answerField to the gridLayout. 

                            //add the quadrants north, centre and south.    
                            questionPanel.add(title, BorderLayout.NORTH);                                           Make sure the title is in the north (which is CATEGORY: __ KNUT QUESTION). 
                            questionPanel.add(questionAnswer, BorderLayout.CENTER);                                 Add the questinoAnswer (which already contains the question and the questionAnswer to the centre)
                            questionPanel.add(proceed, BorderLayout.SOUTH);                                         Add the proceed button. 

                            //add the questionPanel to the frame
                            questionFrame.setContentPane(questionPanel);                                            Set the questionPanel to the questionFrame (because setContentPane() is a method inside the JFrame class (I'd assume?)) 
                            questionFrame.setSize(400, 400);                                                        Sets the universal size of the questionFrame to a certain size (which should remain constant). 
                            questionFrame.setVisible(true);                                                         Makes sure that the frame is set as true. 
                                if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());

                    } //end of if statement

                    else
                    {
                      JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
                    } //end of if statement

                } //end of if statement
         *#######################################################################################################################################################################
         *####################################################################################################################################################################### 
         *#######################################################################################################################################################################
         *####################################################################################################################################################################### 
         */
        
        /*#################################################################################### CHARACTER BUTTON (2ND ROW - Question1) ######################################################
         *#######################################################################################################################################################################
         */        

        if (whatClicked.getSource() == button[7]) //7 knut question character
        {
            
            whichQuestion = 7;  //allows the program to determine what the right answer would be. 
            
            if (category[0].getAnswered() == true)
            {       
                    title.setText("Characters: 7 Knut Question");
                    question.setText(category[0].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement
        
        /*#################################################################################### STORY LINE BUTTON (2ND ROW  - Question 1) ######################################################
         *#######################################################################################################################################################################
         */         
        
        if (whatClicked.getSource() == button[8]) //7 knut question story line. 
        {
            
            whichQuestion = 8;  //allows the program to determine what the right answer would be. 
            
            if (category[7].getAnswered() == true)
            {       
                    title.setText("Story Line: 7 Knut Question");
                    question.setText(category[7].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### BEEDLE THE BARD BUTTON (2ND ROW) ######################################################
         *#######################################################################################################################################################################
         */         
        
        if (whatClicked.getSource() == button[9]) //7 knut question beedle. 
        {
            whichQuestion = 9;  //allows the program to determine what the right answer would be. 
            
            if (category[14].getAnswered() == true)
            {       
                    title.setText("Beedle the Bard: 7 Knut Question");
                    question.setText(category[14].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement   
        
        /*#################################################################################### QUOTES BUTTON (2ND ROW) ######################################################
         *#######################################################################################################################################################################
         */         
        
        if (whatClicked.getSource() == button[10]) //7 knut question quotes. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 10;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[21].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Quotes: 7 Knut Question");
                    question.setText(category[21].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement   
        
        /*#################################################################################### HOUSES BUTTON (2ND ROW) ######################################################
         *#######################################################################################################################################################################
         */         
        
        if (whatClicked.getSource() == button[11]) //7 knut question houses. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 11;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[28].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Houses: 7 Knut Question");
                    question.setText(category[28].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### SPELLS BUTTON (2ND ROW) ######################################################
         *#######################################################################################################################################################################
         */         
        
        if (whatClicked.getSource() == button[12]) //7 knut question spells. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 12;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[35].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Spells: 7 Knut Question");
                    question.setText(category[35].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### ACTORS OF THE CAST BUTTON (2ND ROW) ######################################################
         *#######################################################################################################################################################################
         */         
        
        if (whatClicked.getSource() == button[13]) //7 knut question actor
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 13;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[42].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Actors: 7 Knut Question");
                    question.setText(category[42].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### CHARACTER BUTTON (3RD ROW) ######################################################
         *#######################################################################################################################################################################
         */         
        
        if (whatClicked.getSource() == button[14]) //17 knut question character
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 14;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[1].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Characters: 17 Knut Question");
                    question.setText(category[1].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement 
        
        /*#################################################################################### STORY LINE BUTTON (3RD ROW) ######################################################
         *#######################################################################################################################################################################
         */         
        
        if (whatClicked.getSource() == button[15]) //17 knut question storyline. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 15;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[8].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Story Line: 17 Knut Question");
                    question.setText(category[8].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### BEEDLE THE BARD BUTTON (3RD ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[16]) //17 knut question beedle the bard. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 16;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[15].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Beedle the Bard: 17 Knut Question");
                    question.setText(category[15].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### QUOTES BUTTON (3RD ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[17]) //17 knut question quotes
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 17;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[22].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Quotes: 17 Knut Question");
                    question.setText(category[22].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### HOUSES BUTTON (3RD ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[18]) //17 knut question houses
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 18;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[29].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Houses: 17 Knut Question");
                    question.setText(category[29].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### SPELLS BUTTON (3RD ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[19]) //17 knut question spells
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 19;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[36].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Spells: 17 Knut Question");
                    question.setText(category[36].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### ACTORS BUTTON (3RD ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[20]) //17 knut question spells
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 20;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[43].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Actors: 17 Knut Question");
                    question.setText(category[43].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### CHARACTERS BUTTON (4TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[21]) //47 knut question character
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 21;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[2].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Characters: 47 Knut Question");
                    question.setText(category[2].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### STORY LINE BUTTON (4TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[22]) //47 knut question story line. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 22;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[9].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Story Line: 47 Knut Question");
                    question.setText(category[9].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement     
        
        /*#################################################################################### BEEDLE THE BARD BUTTON (4TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[23]) //47 knut question beedle the bard. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 23;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[16].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Beedle the Bard: 47 Knut Question");
                    question.setText(category[16].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### QUOTES (4TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[24]) //47 knut question quotes
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 24;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[23].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Quotes: 47 Knut Question");
                    question.setText(category[23].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement   
        
        /*#################################################################################### HOUSES (4TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[25]) //47 knut question housess
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 25;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[30].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Spells: 47 Knut Question");
                    question.setText(category[30].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### SPELLS (4TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[26]) //47 knut question spell
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 26;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[37].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Spells: 47 Knut Question");
                    question.setText(category[37].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### CAST (4TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[27]) //47 knut question cast
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 27;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[44].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Cast: 47 Knut Question");
                    question.setText(category[44].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### CHARACTERS (5TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[28]) //77 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 28;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++
            //if (tracing) System.out.println("print value of character[0+++++++Quote Value: " +quote[0].yesNo);
            
            if (category[3].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Character: 77 Knut Question");
                    question.setText(category[3].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement     
        
        /*#################################################################################### STORY LINE (5TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[29]) //77 knut question storyline. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 29;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[10].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Story Line: 77 Knut Question");
                    question.setText(category[10].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### BEEDLE THE BARD (5TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[30]) //77 knut question beedle
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 30;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[17].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Beedle: 77 Knut Question");
                    question.setText(category[17].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement 
        
        /*#################################################################################### QUOTE (5TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[31]) //77 knut question quotes
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 31;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[24].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Quote: 77 Knut Question");
                    question.setText(category[24].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement   
        
        /*#################################################################################### HOUSES (5TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[32]) //77 knut question quotes
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 32;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[31].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Houses: 77 Knut Question");
                    question.setText(category[31].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement          
        
        /*#################################################################################### SPELLS (5TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[33]) //77 knut question spells
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 33;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[38].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Spells: 77 Knut Question");
                    question.setText(category[38].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### ACTORS (5TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[34]) //77 knut question cast
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 34;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[45].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Cast: 77 Knut Question");
                    question.setText(category[45].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement 
        
        /*#################################################################################### CHARACTERS (6TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[35]) //97 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 35;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[4].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Character: 97 Knut Question");
                    question.setText(category[4].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### STORY LINE (6TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[36]) //97 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 36;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[11].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Story Line: 97 Knut Question");
                    question.setText(category[11].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### BEEDLE (6TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[37]) //97 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 37;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[18].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Beedle the Bard: 97 Knut Question");
                    question.setText(category[18].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### QUOTES (6TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[38]) //97 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 38;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[25].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Quotes: 97 Knut Question");
                    question.setText(category[25].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### HOUSES (6TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[39]) //97 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 39;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[32].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Houses: 97 Knut Question");
                    question.setText(category[32].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### SPELLS (6TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[40]) //97 knut question spells
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 40;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[39].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Spells: 97 Knut Question");
                    question.setText(category[39].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### CAST (6TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[41]) //97 knut question cast
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 41;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[46].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Cast: 97 Knut Question");
                    question.setText(category[46].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### CHARACTERS (7TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[42]) //297 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 42;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[5].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Characters: 297 Knut Question");
                    question.setText(category[5].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement
        
        /*#################################################################################### STORY LINE (7TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[43]) //297 knut question story
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 43;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[12].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Story LIne: 297 Knut Question");
                    question.setText(category[12].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### BEEDLE THE BARD (7TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[44]) //297 knut question beedle
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 44;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[19].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Beedle the Bard: 297 Knut Question");
                    question.setText(category[19].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement       
        
        /*#################################################################################### QUOTES (7TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[45]) //297 knut question quotes
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 45;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[26].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Quotes: 297 Knut Question");
                    question.setText(category[26].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement   
        
        /*#################################################################################### HOUSES (7TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[46]) //297 knut question houses
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 46;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[33].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Characters: 297 Knut Question");
                    question.setText(category[33].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### SPELLS (7TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[47]) //297 knut question spells
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 47;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[40].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Spells: 297 Knut Question");
                    question.setText(category[40].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement        
        
        /*#################################################################################### CAST (7TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[48]) //97 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 48;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[47].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Cast: 297 Knut Question");
                    question.setText(category[47].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement   
        
        /*#################################################################################### CHARACTERS (8TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[49]) //777 knut question characters
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 49;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[6].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Characters: 777 Knut Question");
                    question.setText(category[6].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### STORY LINE (8TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[50]) //777 knut question story line. 
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 50;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[13].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Story Line: 777 Knut Question");
                    question.setText(category[13].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### BEEDLE THE BARD (8TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[51]) //777 knut question beedle
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 51;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[20].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Beedle the Bard: 777 Knut Question");
                    question.setText(category[20].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### QUOTES (8TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[52]) //777 knut question quotes
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 52;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[27].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Quotes: 777 Knut Question");
                    question.setText(category[27].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement   
        
        /*#################################################################################### HOUSES (8TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[53]) //777 knut question house
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 53;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[34].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Houses: 777 Knut Question");
                    question.setText(category[34].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement  
        
        /*#################################################################################### SPELLS (8TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[54]) //777 knut question beedle
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 54;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[41].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Spells: 777 Knut Question");
                    question.setText(category[41].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement    
        
        /*#################################################################################### CAST (8TH ROW) ######################################################
         *#######################################################################################################################################################################
         */         
                
        if (whatClicked.getSource() == button[55]) //777 knut question beedle
        {
            //if (tracing) System.out.println("print value of character[0].yesno: " +character[0].yesNo);
            
            whichQuestion = 55;  //allows the program to determine what the right answer would be. 
            
            //if (tracing) System.out.println("++++++++++++++++++++++++++++++++++++++++++Quote Value: " +quote[0].yesNo);
            
            if (category[48].getAnswered() == true)
            {       
                
                //if (tracing) System.out.println("+++++++++++++++++++++++++++++++++++Quote value inside: " +quote[0].yesNo);
                    title.setText("Beedle the Bard: 777 Knut Question");
                    question.setText(category[48].getQuestion());
                        question.setEditable(false);//so the user cannot edit the question
                        question.setLineWrap(true); //so the program will automatically wrap the text. 
                    userAnswer.setText(""); 
                    proceed.addMouseListener(this);
                       
                    //add the jtextarea and the jtextfield to the grid layout (centre)
                    questionAnswer.add(question);
                    questionAnswer.add(userAnswer);
                    
                    //add the quadrants north, centre and south. 
                    questionPanel.add(title, BorderLayout.NORTH); 
                    questionPanel.add(questionAnswer, BorderLayout.CENTER);
                    questionPanel.add(proceed, BorderLayout.SOUTH);
                    
                    //add the questionPanel to the frame
                    questionFrame.setContentPane(questionPanel);
                    questionFrame.setSize(400, 400);
                    questionFrame.setVisible(true);
                        if (tracing) System.out.println("-----------------Question Frame:" +questionFrame.isVisible());
                    
            } //end of if statement
            
            else
            {
              JOptionPane.showMessageDialog(frame, "According to our records, you have already answered this question. Please choose another.", "Sorry", JOptionPane.INFORMATION_MESSAGE);   
            } //end of if statement
            
        } //end of if statement         
        
        //if (tracing) System.out.println("//////Button ok: " +proceed+ " //////////whatclicked: " +whatClicked.getSource());
        
        //**********************************************************************If statement for the ok button starts here******************************************
        //**********************************************************************************************************************************************************
        
        if (whatClicked.getSource() == proceed) //if they press the ok button
        {
            
            /******************************************************************************DOCUMENTATION*******************************************************************
             *******************************************************************************DOCUMENTATION*******************************************************************
             *******************************************************************************DOCUMENTATION*******************************************************************
             *******************************************************************************DOCUMENTATION*******************************************************************
             *******************************************************************************DOCUMENTATION*******************************************************************
             *******************************************************************************DOCUMENTATION*******************************************************************
             *******************************************************************************DOCUMENTATION*******************************************************************
             *******************************************************************************DOCUMENTATION*******************************************************************
             * 
            
            if (whichQuestion == 9 && category[14].getAnswered() == true) If the user has already answered this question, the category[numberThatCorresponds].getAnswered() will be set to false. When declared, it is true.  Also, whichQuestion represents whichever question we're talking about. 
            {
                    temp = userAnswer.getText().trim(); temp, being the variables that is declared as a local variable, should be equal to what is in the jTextField.getText, and then trimming the extra space that is used. 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase((category[14]).getAnswer()))
                                {
                                    score+=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly. 
                                {
                                    if ((score-FIRST)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIRST; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[14].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[9].setEnabled(false);
                                category[14].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++; 
                    } //end of if statement
            } //end of if statement  
             * 
             *******************************************************************************DOCUMENTATION*******************************************************************
             * ******************************************************************************DOCUMENTATION*******************************************************************
             * ******************************************************************************DOCUMENTATION*******************************************************************
             * ******************************************************************************DOCUMENTATION*******************************************************************
             * ******************************************************************************DOCUMENTATION*******************************************************************
             * ******************************************************************************DOCUMENTATION*******************************************************************
             * ******************************************************************************DOCUMENTATION*******************************************************************
             * ******************************************************************************DOCUMENTATION*******************************************************************
             */
            
            //JFrame scoreLess 
            
            if (tracing) System.out.println("Inside the ok getSource method right now.");
            
            if (tracing) System.out.println(" whichQuestion: " +whichQuestion);
            
            ///////////////////////////////////////////////////////////////////////Character 7 Knut Question//////////////////////////////////////////////
             
            if (whichQuestion == 7 && category[0].getAnswered() == true) //if the user is answering the 7 knut character question: 
            {
                
                        if (tracing) System.out.println("Inside the whichQuestion 7 right now.");

                        temp = userAnswer.getText().trim(); //the temporary variable is equal to the user's answer that they put in the JTextArea - without the trailing whitespace. 

                    if (temp.length()==0) //if the jtextfield contains no value
                    {
                                JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement

                    else //if the jtextfield has a value, check whether it is equal to the right answer. 
                    {
                        
                                if (temp.equalsIgnoreCase(category[0].getAnswer()))
                                {
                                    score+=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly. 
                                {
                                    if ((score-FIRST)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                    score-=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n"+ category[0].getFact()+"\n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement
                                } //end of else statement


                                button[7].setEnabled(false);
                                category[0].setAnswered(false);  
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
                
            } //end of if statement
            
            ///////////////////////////////////////////////////////////////////////Story line 7 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 8 && category[7].getAnswered() == true) //if the user has entered the answer for question 8, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[7].getAnswer()))
                                {
                                    score+=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly. 
                                {
                                    if ((score-FIRST)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIRST; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[7].getFact() +" \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement 
                                    
                                } //end of else statement

                                button[8].setEnabled(false);
                                category[7].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement
            
            ///////////////////////////////////////////////////////////////////////Beedle the Bard 7 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 9 && category[14].getAnswered() == true) //if the user has entered the answer for question 9, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase((category[14]).getAnswer()))
                                {
                                    score+=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly. 
                                {
                                    if ((score-FIRST)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIRST; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[14].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[9].setEnabled(false);
                                category[14].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement     
            
            ///////////////////////////////////////////////////////////////////////Quotes 7 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 10 && category[21].getAnswered() == true) //if the user has entered the answer for question 10, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[21].getAnswer()))
                                {
                                    score+=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly. 
                                {
                                    if ((score-FIRST)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIRST; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[21].getFact() +"  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                    
                                    
                                } //end of else statement


                                button[10].setEnabled(false);
                                category[21].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement   
            
            ///////////////////////////////////////////////////////////////////////Houses 7 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 11 && category[28].getAnswered() == true) //if the user has entered the answer for question 10, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase( category[28].getAnswer()))
                                {
                                    score+=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly. 
                                { 
                                    if ((score-FIRST)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIRST; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " + category[28].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement


                                button[11].setEnabled(false);
                                category[28].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement   
            
            ///////////////////////////////////////////////////////////////////////Spells 7 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 12 &&  category[35].getAnswered() == true) //if the user has entered the answer for question 10, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[35].getAnswer()))
                                {
                                    score+=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly. 
                                {
                                    if ((score-FIRST)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n "+category[35].getFact() + " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[12].setEnabled(false);
                                category[35].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement    
            
            ///////////////////////////////////////////////////////////////////////Cast 7 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 13 && category[42].getAnswered() == true) //if the user has entered the answer for question 10, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[42].getAnswer()))
                                {
                                    score+=FIRST; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly. 
                                {
                                    if ((score-FIRST)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=FIRST; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[42].getFact() + " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement

                                button[13].setEnabled(false);
                                category[42].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement     
            
            ///////////////////////////////////////////////////////////////////////Character 17 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 14 && category[1].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[1].getAnswer()))
                                {
                                    score+=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                    if ((score-SECOND)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=SECOND; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n "+ category[1].getFact()+ "\n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement


                                button[14].setEnabled(false);
                                category[1].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement    
            
            ///////////////////////////////////////////////////////////////////////Story Line 17 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 15 && category[8].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[8].getAnswer()))
                                {
                                    score+=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SECOND)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                      score-=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n "+category[8].getFact()+" \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE); 
                                    } //end of else statement                                      
                                    
                                } //end of else statement


                                button[15].setEnabled(false);
                                category[8].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement      
            
            ///////////////////////////////////////////////////////////////////////Beedle the Bard 17 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 16 && category[15].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[15].getAnswer()))
                                {
                                    score+=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SECOND)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[15].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement


                                button[16].setEnabled(false);
                                category[15].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement  
            
            ///////////////////////////////////////////////////////////////////////Quotes 17 Knut Question//////////////////////////////////////////////            
            
            if (whichQuestion == 17 && category[22].getAnswered()== true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[22].getAnswer()))
                                {
                                    score+=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SECOND)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[22].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement


                                button[17].setEnabled(false);
                                category[22].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement  
            
            ///////////////////////////////////////////////////////////////////////Houses 17 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 18 && category[29].getAnswered()== true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                 if (temp.equalsIgnoreCase(category[29].getAnswer()))
                                {
                                    score+=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                    if ((score-SECOND)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[29].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       
                                    
                                } //end of else statement

                                button[18].setEnabled(false);
                                category[29].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement   
            
            ///////////////////////////////////////////////////////////////////////Spells 17 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 19 && category[36].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[36].getAnswer()))
                                {
                                    score+=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SECOND)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[36].getFact() + " \n In the Philosopher's Stone, Hermione says (to Ron), 'Besides, you're saying it wrong. It's LeviOsa, not LeviosAR!' \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement


                                button[19].setEnabled(false);
                                category[36].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement    
            
            ///////////////////////////////////////////////////////////////////////Actors 17 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 20 && category[43].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[43].getAnswer()))
                                {
                                    score+=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SECOND)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=SECOND; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[43].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement


                                button[20].setEnabled(false);
                                category[43].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement    
            
            ///////////////////////////////////////////////////////////////////////Characters 47 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 21 && category[2].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[2].getAnswer()))
                                {
                                    score+=THIRD; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-THIRD)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=THIRD; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[2].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement


                                button[21].setEnabled(false);
                                category[2].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement    
            
            ///////////////////////////////////////////////////////////////////////Story Line 47 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 22 && category[9].getAnswered()== true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[9].getAnswer()))
                                {
                                    score+=THIRD; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-THIRD)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=THIRD; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n "+ category[9].getFact()+" \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[22].setEnabled(false);
                                category[9].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement     
            
            ///////////////////////////////////////////////////////////////////////Beedle the Bard 47 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 23 && category[16].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[16].getAnswer()))
                                {
                                    score+=THIRD; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-THIRD)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=THIRD; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n "+category[16].getFact()+" \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement


                                button[23].setEnabled(false);
                                category[16].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement   
            
            ///////////////////////////////////////////////////////////////////////Quotes 47 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 24 && category[23].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[23].getAnswer()))
                                {
                                    score+=THIRD; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-THIRD)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=THIRD; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " +category[23].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[24].setEnabled(false);
                                category[23].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement  
            
            ///////////////////////////////////////////////////////////////////////Houses 47 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 25 && category[30].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[30].getAnswer()))
                                {
                                    score+=THIRD; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-THIRD)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=THIRD; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " + category[30].getFact()+ "\n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[25].setEnabled(false);
                                category[30].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement  
            
            ///////////////////////////////////////////////////////////////////////Spells 47 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 26 && category[37].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[37].getAnswer()))
                                {
                                    score+=THIRD; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-THIRD)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=THIRD; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[37].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[26].setEnabled(false);
                                category[37].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement            
            
            ///////////////////////////////////////////////////////////////////////Cast 47 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 27 && category[44].getAnswered()== true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[44].getAnswer()) || temp.equalsIgnoreCase("true"))
                                {
                                    score+=THIRD; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-THIRD)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=THIRD; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n " + category[44].getFact()+ " \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[27].setEnabled(false);
                                category[44].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement  
            
            ///////////////////////////////////////////////////////////////////////Character 77 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 28 && category[3].getAnswered() == true) //if the user has entered the answer for question 14, and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[3].getAnswer()))
                                {
                                    score+=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FOURTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[3].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[28].setEnabled(false);
                                category[3].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement     
            
            ///////////////////////////////////////////////////////////////////////Story Line 77 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 29 && category[10].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[10].getAnswer()))
                                {
                                    score+=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FOURTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=FOURTH; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[10].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[29].setEnabled(false);
                                category[10].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
            
            ///////////////////////////////////////////////////////////////////////Beedle The Bard 77 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 30 && category[17].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {   
                                if (temp.equalsIgnoreCase(category[17].getAnswer()))
                                {
                                    score+=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FOURTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[17].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[30].setEnabled(false);
                                category[17].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Quotes 77 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 31 && category[24].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[24].getAnswer()))
                                {
                                    score+=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FOURTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=FOURTH; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[24].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[31].setEnabled(false);
                                category[24].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Houses 77 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 32 && category[31].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[31].getAnswer()))
                                {
                                    score+=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FOURTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                       score-=FOURTH; 
                                       JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[31].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[32].setEnabled(false);
                                category[31].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Spells 77 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 33 && category[38].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[38].getAnswer()))
                                {
                                    score+=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FOURTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                    score-=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[38].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      

                                } //end of else statement

                                button[33].setEnabled(false);
                                category[38].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Cast 77 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 34 && category[45].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                 if (temp.equalsIgnoreCase(category[45].getAnswer()))
                                {
                                    score+=FOURTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FOURTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FOURTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[45].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       
                                    
                                } //end of else statement

                                button[34].setEnabled(false);
                                category[45].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Characters 97 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 35 && category[4].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[4].getAnswer()))
                                {
                                    score+=FIFTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FIFTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIFTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[4].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                      
                                    
                                } //end of else statement

                                button[35].setEnabled(false);
                                category[4].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Story Line 97 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 36 && category[11].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[11].getAnswer()))
                                {
                                    score+=FIFTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FIFTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                         score-=FIFTH; 
                                         JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[11].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[36].setEnabled(false);
                                category[11].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Beedle the Bard 97 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 37 && category[18].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[18].getAnswer()))
                                {
                                    score+=FIFTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FIFTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                          score-=FIFTH; 
                                         JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[18].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[37].setEnabled(false);
                                category[18].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Quotes 97 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 38 && category[25].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[25].getAnswer()))
                                {
                                    score+=FIFTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FIFTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                          score-=FIFTH; 
                                         JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[25].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[38].setEnabled(false);
                                category[25].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Houses 97 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 39 && category[32].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[32].getAnswer()))
                                {
                                    score+=FIFTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FIFTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIFTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[32].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[39].setEnabled(false);
                                category[32].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Spells 97 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 40 && category[39].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[39].getAnswer()))
                                {
                                    score+=FIFTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FIFTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIFTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[39].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);                                        
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[40].setEnabled(false);
                                category[39].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Cast 97 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 41 && category[46].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[46].getAnswer()))
                                {
                                    score+=FIFTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-FIFTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=FIFTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[46].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[41].setEnabled(false);
                                category[46].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Characters 297 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 42 && category[5].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[5].getAnswer()))
                                {
                                    score+=SIXTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SIXTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SIXTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[5].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     

                                } //end of else statement

                                button[42].setEnabled(false);
                                category[5].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Story Line 297 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 43 && category[12].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[12].getAnswer()))
                                {
                                    score+=SIXTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SIXTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SIXTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[12].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       

                                } //end of else statement

                                button[43].setEnabled(false);
                                category[12].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Beedle the Bard 297 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 44 && category[19].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[19].getAnswer()))
                                {
                                    score+=SIXTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SIXTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SIXTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[19].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       

                                } //end of else statement

                                button[44].setEnabled(false);
                                category[19].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Quotes 297 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 45 && category[26].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[26].getAnswer()))
                                {
                                    score+=SIXTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SIXTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                         score-=SIXTH; 
                                         JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[26].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       

                                } //end of else statement

                                button[45].setEnabled(false);
                                category[26].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Houses 297 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 46 && category[33].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[33].getAnswer()))
                                {
                                    score+=SIXTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SIXTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                            score-=SIXTH; 
                                            JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[33].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       

                                } //end of else statement

                                button[46].setEnabled(false);
                                category[33].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Spells 297 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 47 && category[40].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[40].getAnswer()))
                                {
                                    score+=SIXTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SIXTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SIXTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[40].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       

                                } //end of else statement

                                button[47].setEnabled(false);
                                category[40].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Cast 297 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 48 && category[47].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[47].getAnswer()))
                                {
                                    score+=SIXTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SIXTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SIXTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[47].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       

                                } //end of else statement

                                button[48].setEnabled(false);
                                category[47].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Characters 777 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 49 && category[6].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[6].getAnswer()))
                                {
                                    score+=SEVENTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SEVENTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SEVENTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[6].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                       

                                } //end of else statement

                                button[49].setEnabled(false);
                                category[6].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Story Line 777 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 50 && category[13].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[13].getAnswer()))
                                {
                                    score+=SEVENTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SEVENTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SEVENTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[13].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     

                                } //end of else statement

                                button[50].setEnabled(false);
                                category[13].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Beedle the Bard 777 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 51 && category[20].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[20].getAnswer()))
                                {
                                    score+=SEVENTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SEVENTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SEVENTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[20].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[51].setEnabled(false);
                                category[20].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Quotes 777 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 52 && category[27].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[27].getAnswer()))
                                {
                                    score+=SEVENTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SEVENTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SEVENTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[27].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     

                                } //end of else statement

                                button[52].setEnabled(false);
                                category[27].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Houses 777 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 53 && category[34].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[34].getAnswer()))
                                {
                                    score+=SEVENTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SEVENTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SEVENTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[34].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                    
                                } //end of else statement

                                button[53].setEnabled(false);
                                category[34].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Spells 777 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 54 && category[41].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[41].getAnswer()))
                                {
                                    score+=SEVENTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SEVENTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SEVENTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[41].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     
                                } //end of else statement

                                button[54].setEnabled(false);
                                category[41].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement              
                        
            ///////////////////////////////////////////////////////////////////////Actors 777 Knut Question//////////////////////////////////////////////                        
            
            if (whichQuestion == 55 && category[48].getAnswered() == true) //if the user has entered the answer for question , and the question has not been answered yet: 
            {
                    temp = userAnswer.getText().trim(); 
                    
                    if (temp.length() == 0) //if the user has not entered a value. 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter your answer in the text field. ", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                    } //end of if statement
                    
                    else //if the user has entered a value. 
                    {
                                if (temp.equalsIgnoreCase(category[48].getAnswer()))
                                {
                                    score+=SEVENTH; 
                                    JOptionPane.showMessageDialog(null, "Congratulations!! \n You answered this question correctly! \n You now have: " +score+ " knuts.", "HIP HIP HURRAY!", JOptionPane.INFORMATION_MESSAGE);
                                } //end of if statement

                                else //if they answered it incorrectly
                                {
                                   if ((score-SEVENTH)<0)
                                    {
                                        isZero = true; 
                                        JOptionPane.showMessageDialog(null, "You have answered this question incorrectly. Unfortuantely, you have lost the game as your score is less than zero.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of if statement
                                    else
                                    {
                                        score-=SEVENTH; 
                                        JOptionPane.showMessageDialog(null, "Sorry!! \n You answered this question incorrectly! \n" +category[48].getFact()+ "  \n You now have: " +score+ " knuts.", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
                                    } //end of else statement                                     

                                } //end of else statement

                                button[55].setEnabled(false);
                                category[48].setAnswered(false);
                                if (tracing) System.out.println("----------Get Visible questionFrame: " +questionFrame.isVisible());
                                questionFrame.setVisible(false);
                                over++;
                    } //end of if statement
            } //end of if statement  
            
            if (whichQuestion == 0 )
            {
                JOptionPane.showMessageDialog(null, "Your score has been reset to its' default value, 1000. \n Happy playing! ", "Happy Playing!", JOptionPane.INFORMATION_MESSAGE);
            } //end of if statement
                        
            knuts.setText("Your Current Score: " +score+ " knuts.");
            
            if (isZero==true)
            {
                int answer = JOptionPane.showConfirmDialog(null, "Would you like to replay Harry Potter Jeopardy? ", "Sorry..", JOptionPane.YES_NO_OPTION);
                
                if (answer == 0) //yes? 
                {
                    initialize();
                    mainScreen();
                    isZero = false; 
                    score = 1000; 
                    whichQuestion = 0; 
                } //end of if statement
                
                else //no? 
                {
                    JOptionPane.showMessageDialog(null, "Thanks very much for playing our game. \n Hope you come back soon!", "So Long, Farewell", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } //end of else statement. 
                
            } //end of if statement 
            
            if (over == 49)
            {
                Arrays.sort(highScores);
                
                if (score>highScores[7].getScore())
                {
                    
                } //end of if statement
                
            } //end of if statement
            
        } //end of if statement
        
    } //end of mouseClicked method

    /****************************************************************************************************************************************************************************
     * Description of Method: Checks to see whether any component was pressed.
     * @author - Natalie Chin. 
     * @version - 2.0
     * @param 'e' 
     * @precondition - The component that we are checking to see if the user pressed on must exist. 
     * @postcondition - The program will have completed whatever had to be completed for interaction with that component.
     * @see - Used to create a cross reference (BETWEEN WHAT AND WHAT? O_O) 
     ****************************************************************************************************************************************************************************/        
           
    public void mousePressed(MouseEvent e) 
    {
    } //end of mousePressed method

    /****************************************************************************************************************************************************************************
     * Description of Method: Checks to see whether any component was released.
     * @param 'e' 
     * @author - Natalie Chin. 
     * @version - 2.0
     * @precondition - The component that we are checking to see if the user pressed on must exist. 
     * @postcondition - The program will have completed whatever had to be completed for interaction with that component.
     * @see - Used to create a cross reference (BETWEEN WHAT AND WHAT? O_O) 
     ****************************************************************************************************************************************************************************/        
           
    
    public void mouseReleased(MouseEvent e)
    {
    }

    /****************************************************************************************************************************************************************************
     * Description of Method: Checks to see whether the mouse entered any component.
     * @author - Natalie Chin. 
     * @version - 2.0
     * @param 'e' 
     * @precondition - The component that we are checking to see if the user pressed on must exist. 
     * @postcondition - The program will have completed whatever had to be completed for interaction with that component.
     * @see - Used to create a cross reference (BETWEEN WHAT AND WHAT? O_O) 
     ****************************************************************************************************************************************************************************/        
           
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    /****************************************************************************************************************************************************************************
     * Description of Method: Checks to see whether the mouse exited any component..
     * @param 'e' 
     * @author - Natalie Chin. 
     * @version - 2.0
     * @precondition - The component that we are checking to see if the user pressed on must exist. 
     * @postcondition - The program will have completed whatever had to be completed for interaction with that component.
     * @see - Used to create a cross reference (BETWEEN WHAT AND WHAT? O_O) 
     ****************************************************************************************************************************************************************************/        
               
    public void mouseExited(MouseEvent e) 
    {
    }
}//end of class