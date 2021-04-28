import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton; 
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
//import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import java.net.URL;
//import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2019)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JPanel     aButton;
    private JButton     aButtonN;
    private JButton     aButtonU;
    private JButton     aButtonD;
    private JButton     aButtonS;
    private JButton     aButtonE;
    private JButton     aButtonW;
    private JButton     aButtonEat;
    private JButton     aButtonLook;
    private JButton     aButtonHelp;
    /**
     * Constru  ct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * @param pText text will be print into the text area
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText text will be print into the text area
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * @param pImageName image name
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource("image/"+vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     * @param pOnOff enable/disable
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)
    
    /**
     * Enable or disable buttons if there is no exits.
     * @param pRoom room
     */
    public void boutonvisibility(Room pRoom){
        this.aButtonN.setVisible(pRoom.getExit("north")!=null);
        if (pRoom.getExit("north")==null){this.aButtonN.setVisible(false);}
        else {this.aButtonN.setVisible(true);}
        if (pRoom.getExit("south")==null){this.aButtonS.setVisible(false);}
        else {this.aButtonS.setVisible(true);}
        
        if (pRoom.getExit("west")==null){this.aButtonW.setVisible(false);}
        else {this.aButtonW.setVisible(true);}
        
        if (pRoom.getExit("east")==null){this.aButtonE.setVisible(false);}
        else {this.aButtonE.setVisible(true);}
        
        this.aMyFrame.pack();
    }
    
    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Zork" ); // change the title
        this.aEntryField = new JTextField( 34 );
        
        
        
        
        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();

        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        
        this.makeBoutonBar();
        
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.WEST );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );

        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            } );
        this.aButtonN.setVisible(false);
        this.aButtonS.setVisible(false);
        this.aButtonE.setVisible(false);
        
        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        if(pE.getSource() == this.aButtonN){ this.aEngine.interpretCommand("go north");}
        else if(pE.getSource() == this.aButtonS){ this.aEngine.interpretCommand("go south");}
        else if(pE.getSource() == this.aButtonE){ this.aEngine.interpretCommand("go east");}
        else if(pE.getSource() == this.aButtonW){ this.aEngine.interpretCommand("go west");}
        else if(pE.getSource() == this.aButtonEat){ this.aEngine.interpretCommand("eat cookie");}
        else if(pE.getSource() == this.aButtonLook){ this.aEngine.interpretCommand("look");}
        else if(pE.getSource() == this.aButtonHelp){ this.aEngine.interpretCommand("help");}
        else if(pE.getSource() == this.aEntryField) {processCommand();}    
    } // actionPerformed(.)

    /**
     * make Bouton Bar.
     */
    public void makeBoutonBar()
    {
        aButton = new JPanel();
        aButton.setLayout(new GridLayout(0,1,3,5));
        
        this.aButtonN = new JButton("north");        
        this.aButtonN.addActionListener(this);
        this.aButtonN.setBackground(Color.RED);
        
        this.aButtonS = new JButton("south");
        this.aButtonS.addActionListener(this);
        this.aButtonS.setBackground(Color.GREEN);
        
        this.aButtonE = new JButton("east");
        this.aButtonE.addActionListener(this);
        this.aButtonE.setBackground(Color.YELLOW);
        
        this.aButtonW = new JButton("west");
        this.aButtonW.addActionListener(this);
        this.aButtonW.setBackground(Color.MAGENTA   );
       
        this.aButtonEat = new JButton("eat cookie"); 
        this.aButtonEat.addActionListener(this);
        this.aButtonEat.setBackground(Color.CYAN);
        
        this.aButtonLook = new JButton("look"); 
        this.aButtonLook.addActionListener(this);
        this.aButtonLook.setBackground(Color.WHITE);
        
        this.aButtonHelp = new JButton("help"); 
        this.aButtonHelp.addActionListener(this);
        this.aButtonHelp.setBackground(Color.ORANGE);

        aButton.add( this.aButtonN);
        aButton.add( this.aButtonS);
        aButton.add( this.aButtonE);
        aButton.add( this.aButtonW);
        aButton.add( this.aButtonEat);  
        aButton.add( this.aButtonHelp);
        aButton.add( this.aButtonLook);
        
        this.aMyFrame.getContentPane().add(aButton, BorderLayout.EAST);
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
