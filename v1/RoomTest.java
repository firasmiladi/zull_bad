package v1;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoomTest.
 *
 * @author  DB
 * @version 2015.02.01
 */
public class RoomTest
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
    private static int                      sStep=1;

    /**
     * Default constructor for test class SRoomTest
     */
    public RoomTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        Class vClasse = Room.class;
        sPkg          = vClasse.getPackage().getName();
        sClassName    = vClasse.getSimpleName();
        veref.ClassContent.setRefPkg( sPkg );
        sFil = sPkg + "/" + sClassName + ".java";

        sAttName = "aDescription";
        sAttType = "String";

        sProtoC = "( String p1 )";
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
    public void testClasse_1_1()
    {
        sCla = veref.V.getVClaFName( sClassName );
    } // testClasse_1()

    @Test
    public void testAttribut_2()
    {
        testClasse_1_1();
        //         veref.V.verifNbAttOp( vCla, "==", 1 );
        //         veref.FieldContent vAtt = veref.V.getAttFType( vCla, "String" );
        //         veref.V.failIfNot();
        //         veref.V.verifModAttribut( vAtt, "private", "static final" );
        //         veref.V.verifNomAttribut( vAtt );
        //         veref.V.vrai( vAttName.equals( vAtt.fieldName() ), "$n n'est pas le nom attendu pour l'attribut" );
        //         veref.V.mesIfNot();
        if ( veref.V.nbAtt(sCla) > 1 )
            veref.V.verifNbAttOp( sCla, "==", 5 );
        else if ( veref.V.nbAtt(sCla) == 1 )
            sAtt = veref.V.getAttFTN( sCla, sAttType, sAttName );
        else {
            veref.V.vrai( veref.V.nbAtt( sCla ) > 0 , "Aucun attribut ???" );
            veref.V.failIfNot();
        }
    } // testAttribut_2()

    @Test
    public void testConstructeur_3()
    {
        testAttribut_2();
        //         veref.V.verifDefCon( vCla, "F" );
        //         veref.V.verifConNbP( vCla, 1, "T" );
        //         veref.ConstructorContent vCon = veref.V.getConFProto( vCla, vProtoC, "T" );
        //         veref.V.verifModCon( vCon, "public", "static final" );
        sCon = veref.V.getVConFProto( sCla, sProtoC );
        veref.V.verifFinal1Type( sFil, sClassName, "String" );
        veref.V.verifParamP1Type( sFil, sClassName, "String" );
        veref.V.vrai( veref.V.nbCon( sCla ) <= 1 , "Il y a au moins un constructeur de trop ..." );
        veref.V.mesIfNot();        
        String vValue1 = "Room du constructeur";
        if ( veref.V.nbAtt(sCla) == 1 )
            veref.V.getAndVerifIns1( sCon, sAtt, vValue1 ); //object discarded
        veref.V.verifAttThis( sFil, sAttType, sAttName );
    } // testConstructeur_3()

    @Test
    public void testAccesseur_4()
    {
        testConstructeur_3();
        sMetName = "getDescription";
        sMetRT   = "String";
        sProtoM  = "()";
        //         veref.V.verifMet( vCla, vMetName, "T" );
        //         veref.V.verifMetRT( vCla, vMetName, vMetRT, "T" );
        //         veref.V.verifMetRTNP( vCla, vMetName, vMetRT, 0, "T" );
        //         veref.MethodContent vMet = veref.V.getMetFProto( vCla, vMetName, vMetRT, vProtoM, "T" );
        //         veref.V.verifModMet( vMet, "public", "static final" );
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "public" );
        //         veref.V.verifFinal( sFil, vMetName, vProtoM ); // inutile pour 0 param
        if ( veref.V.nbAtt(sCla) == 1 )
            veref.V.vrai( veref.V.nbMet( sCla ) <= 1 ,
            "Il y a au moins une méthode de trop ..." );
        veref.V.mesIfNot();        
        if ( veref.V.nbAtt(sCla) == 1 ) {
            String vValue2 = "Room de l'attribut";
            Object vObj2 = veref.V.getAndVerifIns1( sCon, sAtt, vValue2 );
            veref.V.verifGetter( vObj2, sMet, vValue2 );
        }
        veref.V.verifAttThis( sFil, sAttType, sAttName );
    } // testAccesseur_4()

    @Test
    public void testAttributs_5()
    {
        sStep=5; // nb attributs
        testConstructeur_3();
        auxTest5( "North" );
        auxTest5( "East" );
        auxTest5( "South" );
        auxTest5( "West" );
        veref.V.verifNbAttOp( sCla, "==", 5 );

        veref.ConstructorsContent vLCo;
        for ( int vNP=5; vNP>=2; vNP-- )
            vLCo = veref.V.verifConstructorNP( sClassName, vNP, "F" );
    } // testAttributs_5()

    private void auxTest5( final String pDir )
    {
        sAttName = "a" + pDir + "Exit";
        sAttType = "Room";
        veref.FieldContent vAtt = veref.V.getAttFTN( sCla, sAttType, sAttName );
        veref.V.failIfNot();
        veref.V.verifModAttribut( vAtt, "public", "static final" );
        veref.V.verifAttThis( sFil, sAttType, sAttName );
        //         sAtt = veref.V.getV1AttFTN( sCla, sAttType, sAttName );
    } // auxTest5(.)

    @Test
    public void testsetExits_6()
    {
        testAttributs_5();
        sMetName = "setExits";
        sMetRT   = "void";
        sProtoM  = "( "+sPkg+".Room p1, "+sPkg+".Room p2, "+sPkg+".Room p3, "+sPkg+".Room p4 )";
        sMet = veref.V.getVMetFProto( sCla, sMetName, sMetRT, sProtoM, "public" );
        veref.V.vrai( veref.V.nbMet( sCla ) <= 2 , "Il y a au moins une methode de trop ..." );
        veref.V.mesIfNot();        
        //         veref.V.verifFinal( sFil, vMetName, vProtoM ); // inutile pour 0 param
        for ( int vI=1; vI<=4; vI++ ) {
            String vRet = veref.V.verifFinalN( sFil, sMetName, vI );
            veref.V.vrai( vRet.equals( "OK" ), vRet );
            veref.V.failIfNot();        
        } // for

        Object vIns  = null;
        Room   vRoom = null;
        try { // tenir compte de l'ordre de déclaration des attributs !!! pour 2019/2020
            // car pour l'instant impose l'ordre des paramètres de setExits
            List<String> vList = Arrays.asList( sCla.getFields().toString().split(";\n",0) );
            List<String> vSubList;
            String[] vTab = { "1", "2", "3", "4" };
            int vI = 0;
            for ( String vAtt : vList )
                if ( vAtt.endsWith( "Exit" ) ) {
                    vSubList = Arrays.asList( vAtt.split(" ",0) );
                    vTab[vI] = vSubList.get( vSubList.size()-1 ).substring(1);
                    vI++;
                }
            veref.V.vrai( vI==4, "Il n'y a pas 4 attributs pour les sorties ???" );
            veref.V.failIfNot();

            Room[] vTR = new Room[4];
            for ( vI=0; vI<4; vI++ )
                vTR[vI] = (Room)(sCon.newInstance( new Object[]{ "value for "+vTab[vI] } ));
            vIns = sCon.newInstance( new Object[]{ "room description" } );
            vRoom = (Room)vIns;
            sMet.invoke( vRoom, new Object[]{ vTR[0], vTR[1], vTR[2], vTR[3] } );
            veref.FieldContent[] vTFC = new veref.FieldContent[4];
            for ( vI=0; vI<4; vI++ )
                vTFC[vI] = veref.V.getAttFTN( sCla, "Room", "a"+vTab[vI] );

            Object  vObj  = null;
            veref.MethodContent vMet = veref.V.getVMetFProto( sCla, "getDescription", "String", "()", "public" );
            for ( vI=0; vI<4; vI++ ) {
                vObj = Class.forName( sPkg+"."+sClassName )
                .getDeclaredField( "a"+vTab[vI] ).get(vIns);
                veref.V.vrai( vObj == vTR[vI],
                    "Si les parametres de setExits sont bien dans le meme ordre\n"
                    + "que la declaration des attributs, il y a une erreur dans les instructions\n"
                    + "de setExits : attribute "+vTab[vI]
                    + " contains "+ vMet.invoke( vObj, new Object[]{} ) );
                //              ((Room)vObj).getDescription() );
                veref.V.failIfNot(); 
            }
        } catch( final Exception pE ) {
            fail( "la methode " + sMet.getName() /*+ sMet.getParameterString()*/
                + " genere l'exception " + pE );
        } // t/c

    } // testsetExits_6()
} // RoomTest
