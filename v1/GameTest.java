package v1;

import java.lang.reflect.Field;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @author  DB
 * @version 2019.09.29
 */
public class GameTest
{
    private static String                   sClassName;
    private static String                   sPkg;
    private static String                   sFil;
    private static veref.ClassContent       sCla;
    private static String                   sAttName;
    private static String                   sAttType;
    private static veref.FieldContent       sAtt;
    private static String                   sProtoC;
    private static veref.ConstructorContent sCon;
    private static String                   sMetName;
    private static String                   sMetRT;
    private static String                   sProtoM;
    private static veref.MethodContent      sMet;
    private static int                      sPhase=3;

    /**
     * Default constructor for test class SRoomTest
     */
    public GameTest()
    {
        System.out.println( sPhase );
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        Class vClasse = Game.class;
        sPkg          = vClasse.getPackage().getName();
        sClassName    = vClasse.getSimpleName();
        veref.ClassContent.setRefPkg( sPkg );
        sFil = sPkg + "/" + sClassName + ".java";
        
        sProtoC = "()";
        veref.V.error( "===== ===== ===== ===== =====" );
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testClasse_1()
    {
        sPhase = 1;
        sCla = veref.V.getVClaFName( sClassName );
    } // testClasse_1()

    @Test
    public void testAttribut_2()
    {
        testClasse_1();
        sPhase++;
        if ( veref.V.nbAtt(sCla) == 1 ) {
            sAttName = "aCurrentRoom";
            sAttType = "Room";
            sAtt = veref.V.getV1AttFTN( sCla, sAttType, sAttName );
        }
        else if ( veref.V.nbAtt(sCla) >= 2 ) {
            sAttName = "aParser";
            sAttType = "Parser";
            sAtt = veref.V.getAttFTN( sCla, sAttType, sAttName );
            veref.V.failIfNot();
            veref.V.verifModAttribut( sAtt, "private", "static final" );
            veref.V.verifAttThis( sFil, sAttType, sAttName );
            if ( veref.V.nbAtt(sCla) >= 3 ) {
                sAttName = "aFinished";
                sAttType = "boolean";
                sAtt = veref.V.getAttFTN( sCla, sAttType, sAttName );
                veref.V.failIfNot();
                veref.V.verifModAttribut( sAtt, "private", "static final" );
                veref.V.verifAttThis( sFil, sAttType, sAttName );
                veref.V.verifNbAttOp( sCla, "==", 3 );
            }
        }
        else {
            veref.V.vrai( veref.V.nbAtt( sCla ) > 0 , "Aucun attribut ???" );
            veref.V.failIfNot();
        }
        // Accepter un 2ème attribut s'il est de type Parser aParser !!! pour 2019/2020
    } // testAttribut_2()
    
    @Test
    public void testcreateRooms_3()
    {
        testAttribut_2();
        sPhase++;
        sMetName = "createRooms";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
//         veref.V.vrai( veref.V.nbMet( sCla ) <= 1 ,
//                       "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();      
        veref.V.verifAttThisDebug(sFil,"Room","aCurrentRoom",false);   
    } // testcreateRooms_3()
        
    @Test
    public void testConstructeur_4()
    {
        testcreateRooms_3();
        sPhase++;
        veref.V.sDefCo = veref.V.hasCoDebug( sFil, sClassName, "public", false );
        veref.V.verifDefCon( sCla, "T" );
        sCon = veref.V.premierConstructeur( sCla );
        veref.V.vrai( sCon.getNbParameters()==0, "Le constructeur n'a pas le bon nombre de parametres !" );
        veref.V.failIfNot();
        veref.V.verifModCon( sCon, "public", "static final" );
        veref.V.vrai( veref.V.nbCon( sCla ) <= 1 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot();
        veref.V.error( sPhase+": Signature du constructeur verifiee." );
        
        if ( sPhase <= 4 )
        try {
            Game vIns = (Game)sCon.newInstance( new Object[]{} );
            sAttName = "aCurrentRoom";
            Field vF = Class.forName( sPkg+"."+sClassName ).getDeclaredField(sAttName);
            vF.setAccessible( true );
            Room vCR = (Room)(vF.get(vIns));        
            veref.V.vrai( vCR==null, "Le lieu de depart n'est pas initialise !?" );
            veref.V.failIf();
            
        // test du réseau de pieces :
        // North ? null  ok
        // East ? contient Theatre  ok
        // South ? contient Lab ok
        // West ? contien Pub ok
            sClassName = "Room";
            sCla = veref.V.getVClaFName( sClassName );
            sMetName = "getDescription";
            sMetRT   = "String";
            sProtoM  = "()";
            sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "public" );
            String vDes = (String)(sMet.invoke( vCR, new Object[]{ /* rien */ } ));
            String vWord="?";
            int vI;
            if ( vDes.contains("utside") )
                vWord = vDes.substring( vI=vDes.indexOf("utside")-1, vI+7 );
            else if ( vDes.contains("ntrance") )
                vWord = vDes.substring( vI=vDes.indexOf("ntrance")-1, vI+8 );
            veref.V.vrai( vWord.equals("?"), "Le lieu de depart n'est pas : outside the main entrance !?" );
            veref.V.failIf();
            
            sAttName = "aNorthExit";
            vF = Class.forName( sPkg+"."+sClassName ).getDeclaredField(sAttName);
            Room vCRN = (Room)(vF.get(vCR)); // get!!!
            veref.V.vrai( vCRN==null, "Il y a une sortie au nord de la main entrance !?" );
            veref.V.failIfNot();
            
            vWord="?";
            sAttName = "aEastExit";
            vF = Class.forName( sPkg+"."+sClassName ).getDeclaredField(sAttName);
            Room vCRE = (Room)(vF.get(vCR)); // get!!!
            veref.V.vrai( vCRE!=null, "Il n'y a pas de sortie a l'est de la main entrance !?" );
            veref.V.failIfNot();
            String vDesE = (String)(sMet.invoke( vCRE, new Object[]{ /* rien */ } ));
            if ( vDesE.contains("heatre") )
                vWord = vDesE.substring( vI=vDesE.indexOf("heatre")-1, vI+7 );
            System.out.println( "<"+vWord+">" );
            veref.V.vrai( vWord.equals("?"), "Le theatre n'est pas a l'est de la main entrance !?" );
            veref.V.failIf();
            
            vWord="?";
            sAttName = "aSouthExit";
            vF = Class.forName( sPkg+"."+sClassName ).getDeclaredField(sAttName);
            Room vCRS = (Room)(vF.get(vCR)); // get!!!
            veref.V.vrai( vCRS!=null, "Il n'y a pas de sortie au sud de la main entrance !?" );
            veref.V.failIfNot();
            String vDesS = (String)(sMet.invoke( vCRS, new Object[]{ /* rien */ } ));
            if ( vDesS.contains("ab") )
                vWord = vDesS.substring( vI=vDesS.indexOf("ab")-1, vI+3 );
            System.out.println( "<"+vWord+">" );
            veref.V.vrai( vWord.equals("?"), "Le lab n'est pas au sud de la main entrance !?" );
            veref.V.failIf();
            
            vWord="?";
            sAttName = "aWestExit";
            vF = Class.forName( sPkg+"."+sClassName ).getDeclaredField(sAttName);
            Room vCRW = (Room)(vF.get(vCR)); // get!!!
            veref.V.vrai( vCRW!=null, "Il n'y a pas de sortie a l'ouest de la main entrance !?" );
            veref.V.failIfNot();
            String vDesW = (String)(sMet.invoke( vCRW, new Object[]{ /* rien */ } ));
            if ( vDesW.contains("ub") )
                vWord = vDesW.substring( vI=vDesW.indexOf("ub")-1, vI+3 );
            System.out.println( "<"+vWord+">" );
            veref.V.vrai( vWord.equals("?"), "Le pub n'est pas a l'ouest de la main entrance !?" );
            veref.V.failIf();
        // FIN VERIF Outside

        // Theatre ? null null null outside
        // FIN VERIF Theatre

            sClassName = "Game";
            sCla = veref.V.getVClaFName( sClassName );
        }
        catch ( final Exception pE ) {
            veref.V.vrai( false, pE+" + On ne peut pas appeler le constructeur !?" );
            veref.V.failIfNot();
        }
    } // testConstructeur_4()

    @Test
    public void testgoRoom_5()
    {
        testConstructeur_4();
        sPhase++;
        sMetName = "goRoom";
        sMetRT   = "void";
        sProtoM  = "( " + sPkg + ".Command p1 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.verifFinal1Type( sFil, sMetName, "Command" );
        veref.V.verifParamP1Type( sFil, sMetName, "Command" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 2 ,
                      "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();        
        veref.V.verifAttThisDebug(sFil,"Room","aCurrentRoom",false);      
    } // testgoRoom_5()
        
    @Test
    public void testprint_6()
    {
        testConstructeur_4();
        sPhase++;
        sMetName = "printWelcome";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );

        sMetName = "printHelp";
        sMetRT   = "void";
        sProtoM  = "()";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 4 ,
                      "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();        
    } // testprint_6()
    
    @Test
    public void testquit_7()
    {
        testConstructeur_4();
        sPhase++;
        sMetName = "quit";
        sProtoM  = "( " + sPkg + ".Command p1 )";
        
        // Ne fonctionne pas car verifie l'existence de void quit ou int quit :
        // sMetRT   = "void";
        // sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        // veref.V.failIf( sPhase+": "+sMetName+" est une procedure ?" );
        
        // sMetRT   = "int";
        // sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        // veref.V.failIf( sPhase+": "+sMetName+" doit retourner un nombre ?" );

        sMetRT   = "boolean";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.verifFinal1Type( sFil, sMetName, "Command" );
        veref.V.verifParamP1Type( sFil, sMetName, "Command" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 5 ,
                      "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();        
    } // testquit_7()
    
    @Test
    public void testprocessCommand_8()
    {
        testConstructeur_4();
        sPhase++;
        sMetName = "processCommand";
        sMetRT   = "boolean";
        sProtoM  = "( " + sPkg + ".Command p1 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "private" );
        veref.V.verifFinal1Type( sFil, sMetName, "Command" );
        veref.V.verifParamP1Type( sFil, sMetName, "Command" );
        veref.V.error( sPhase+": Signature de "+sMetName+" verifiee." );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 6 ,
                      "Il y a au moins une methode de trop a la phase " + sPhase );
        veref.V.mesIfNot();        
    } // testprocessCommand_8()

} // GameTest
