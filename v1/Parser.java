package v1;

import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 */
public class Parser 
{
    private CommandWords aValidCommands;  // (voir la classe CommandWords)
    private Scanner      aReader;         // permettra de lire les commandes au clavier

    /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
        this.aReader        = new Scanner( System.in );
        // System.in designe le clavier, comme System.out designe l'ecran
    } // Parser()

    /**
     * @return The next command from the user.
     */
    public Command getCommand() 
    {
        String vInputLine;    // contiendra toute la ligne tapee
        String vWord1 = null;
        String vWord2 = null;

        System.out.print( "> " );  // affiche le prompt (invite de commande)

        vInputLine = this.aReader.nextLine(); // lit la ligne tapee au clavier

        // cherche jusqu'a 2 mots dans la ligne tapee
        Scanner vTokenizer = new Scanner( vInputLine );
        if ( vTokenizer.hasNext() ) {
            vWord1 = vTokenizer.next();      // recupere le premier mot
            if ( vTokenizer.hasNext() ) {
                vWord2 = vTokenizer.next();  // recupere le deuxieme mot
                // note : on ignore tout le reste de la ligne tapee !
            } // if
        } // if

        // Veifie si le premier mot est une commande connue. Si oui, cree une Command avec.
        // Sinon, cree une commande vide avec "null" (pour dire 'commande inconnue').
        if ( this.aValidCommands.isCommand( vWord1 ) ) {
            return new Command( vWord1, vWord2 );
        }
        else {
            return new Command( null, null ); // C'est surtout le premier null qui est important ici.
        }
    } // getCommand()
} // Parser
