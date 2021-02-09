package veref;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * classe V 
 * 
 * @author DB 
 * @version 2013-mod.2015.02.01
 */
public class V
{
    private static boolean sDebug = false;
    private PrintStream    out     /*= null*/;
    private PrintStream    console /*= null*/;
    private static String  sNature /*= null*/;  // $a
    private static String  sPkg    /*= null*/;
    private static String  sClasse /*= null*/;  // $c
    private static String  sType   /*= null*/;  // $t
    private static String  sNom    /*= null*/;  // $n
    private static Object  sRetour /*= null*/;  // $r
    private static String  sMes    /*= null*/;
    private static boolean sFail   /*= false*/;
    private static boolean sError  /*= false*/; // $e
    public  static boolean sDefCo  = true;
    
// ========== methods for class ==========zcla

    @Test
    public static ClassContent getVClaFName( final String pClassName )
    {
        verifClaY( pClassName );
        ClassContent vCla = getClaFName( pClassName );
        return vCla;
    } // getVClaFName(.)
    
    @Test
    public static void verifClaY( String pGood )
    {
        ClassContent vCla  = selClaFName( pGood );
        failIfNot();
    } // verifClaY(.)

    @Test
    public static void verifClaYN( String pGood, String[] pTBad )
    {
        ClassContent vCla0 = null;
        for ( String vBad : pTBad ) {
            vCla0 = selClaFName( vBad );
            failIf();
        } // for
        ClassContent vCla  = selClaFName( pGood );
        failIfNot();
    } // verifClaYN(..)

    public static ClassContent getClaFName( final String pClass )
    {
        ClassContent vRetour;
		if ( ClassContent.getClass( pClass ) == null )
		    vRetour = null;
		else
		    vRetour = new ClassContent( pClass );

		sRetour = vRetour;
        return vRetour;
    } // getClaFName(.)

    public static ClassContent selClaFName( final String pName )
    {
        return selClaFNMI( pName, null, null );
    } // selClaFName(.)

    public static ClassContent selClaFNMI( final String pName, final String pMere, final String pInter )
    {
        if (pMere!=null || pInter!=null)   fail( "NOT YET IMPLEMENTED !" );
        sNature = "la classe";
        sClasse = pName;
        sMes = "$a $c $e !";
        
        ClassContent vRetour = getClaFName( pName );
        sRetour = vRetour;
        return vRetour;
    } // selClaFNMI(...)
        
// //     public static boolean etend( String pFille, String pMere )
// //     {
// //         if ( !findClass( pFille ) || !findClass( pMere ) )  return false;
// //         
// //         boolean res;
// //         try { res= Class.forName( pFille ).getSuperclass().equals( Class.forName( pMere ) ); }
// //         catch( ClassNotFoundException e ) { res= false; }
// //         return res;
// //     }
// //     
// //     public static boolean implemente( String pFille, String pMere )
// //     {
// //         if ( !findClass( pFille ) || !findClass( pMere ) )  return false;
// //         
// //         boolean res =false;
// //         try {
// //             Class<?>[] tabInt =Class.forName( pFille ).getInterfaces();
// //             if ( tabInt.length == 0 )  res= false;
// //             else for ( Class<?> vCla : tabInt ) {
// //                 res= res || vCla.equals( Class.forName( pMere ) );
// //             }
// //         } catch( ClassNotFoundException e ) { res= false; }
// //         return res;
// //     }
//     
// //     public static void verifClasse( String pCla, String pSup, String pInt )
// //     {
// //         if ( !findClass( pCla ) ) { error( "7.Vous n'avez pas d�fini la classe "+pCla+" !" ); }
// //         else {
// //           if ( pSup != null ) {
// //             if ( !findClass( pSup ) ) { error( "8.Vous n'avez pas d�fini la super-classe "+pSup+" !" ); }
// //             else if ( !etend( pCla, pSup ) ) { error( pCla+" n'h�rite pas de "+pSup+" !" ); }
// //           }
// //         
// //           if ( pInt != null ) {
// //             if ( !findClass( pInt ) ) { error( "9.Vous n'avez pas d�fini l'interface "+pInt+" !" ); }
// //             else if ( !implemente( pCla, pInt ) ) { error( pCla+" n'impl�mente pas "+pInt+" !" ); }
// //           }
// //         }
// //     }
//     
// //     public static void verifClasse( String pCla, String pSup, String[] pInt )
// //     {
// //         if ( !findClass( pCla ) ) { error( "10.Vous n'avez pas d�fini la classe "+pCla+" !" ); }
// //         else {
// //           if ( pSup != null ) {
// //             if ( !findClass( pSup ) ) { error( "11.Vous n'avez pas d�fini la classe "+pSup+" !" ); }
// //             else if ( !etend( pCla, pSup ) ) { error( pCla+" n'h�rite pas de "+pSup+" !" ); }
// //           }
// //         
// //           if ( pInt != null )
// //             for ( String vInt : pInt )
// //               if ( vInt != null ) {
// //                 if ( !findClass( vInt ) ) { error( "12.Vous n'avez pas d�fini la classe "+vInt+" !" ); }
// //                 else if ( !implemente( pCla, vInt ) ) { error( pCla+" n'impl�mente pas "+vInt+" !" ); }
// //             }
// //         }
// //     }
    
// ========== methods for field ==========zatt
//     @Test public static FieldContent unAttFTN( ClassContent pCla, String pType, String pName )
//           public static int          nbAttSE0( final ClassContent pCla )
//           public static int          nbAttOp( final ClassContent pCla, String pOp, int pNb )
//           public static int          nbAtt( ClassContent pCla )
//     @Test public static FieldContent selAttFTN( final ClassContent pCla, final String pType, final String pName )
//           public static FieldContent getPremierAtt( ClassContent pCla )
//     @Test public static FieldContent getAttFTN( ClassContent pCla, String pType, String pName )
//     @Test public static FieldContent getAttFName( ClassContent pCla, String pName )
//     @Test public static FieldContent getAttFType( final ClassContent pCla, final String pType )
//           public static boolean      verifNomAttribut( final FieldContent pF )
//           public static boolean      verifModAttribut( final FieldContent pF, final String pYes, final String pNo )

    @Test
    public static FieldContent getV1AttFTN( ClassContent pCla, String pType, String pName )
    {
        return getV1AttFTN( pCla, pType, pName, 1 );
    } // getV1AttFTN(...)

    @Test
    public static FieldContent getV1AttFTN( ClassContent pCla, String pType, String pName, int pNbAtt )
    {
        verifNbAttOp( pCla, "==", pNbAtt );
        FieldContent vAtt = getAttFType( pCla, pType );
        failIfNot();
        verifModAttribut( vAtt, "private", "static final" );
        verifNomAttribut( vAtt );
        vrai( pName.equals( vAtt.fieldName() ), "$n n'est pas le nom attendu pour l'attribut" );
        failIfNot();
        return vAtt;
    } // getV1AttFTN(....)
    
    @Test
    public static FieldContent unAttFTN( ClassContent pCla, String pType, String pName )
    {
        int vNbAttr0 = nbAttSE0( pCla );
        failIfNot();
        int vNbAttr = nbAttOp( pCla, "==", 1 );
        failIfNot();

        FieldContent vAttr0 = selAttFTN( pCla, "*", "*" );
        String vNomAt = vAttr0.fieldName();
        FieldContent vAttr = selAttFTN( pCla, pType, vNomAt );
        failIfNot();
        
//         vrai( vAttr.isPrivate(), "$a $n n'est pas prive !" );
        verifModAttribut( vAttr, "private", "static final" );
        verifNomAttribut(vAttr);
        vrai( vAttr.fieldName().equals( pName ), "$a ne s'appelle pas "+pName+" !" );
        failIfNot();
        return vAttr;
    } // unAttFTN(...)

    public static int nbAttSE0( final ClassContent pCla )
    {
        return nbAttOp( pCla, ">=", 0 );
    } // nbAttSE0(.)

    public static void verifNbAttOp( final ClassContent pCla, String pOp, int pNb )
    {
        sNature = "le nb d'attributs";
        sClasse = pCla.className();
        sMes = "$c : $a n'est pas "+pOp+pNb;
        
        vrai( verifNbOp( nbAtt( pCla ), pOp, pNb ), "*" );
        failIfNot();
    } // verifNbAttOp(...)

    public static int nbAttOp( final ClassContent pCla, String pOp, int pNb )
    {
        sNature = "le nb d'attributs";
        sClasse = pCla.className();
        sMes = "$c : $a n'est pas "+pOp+pNb;
        
        int vRetour = nbAtt( pCla );
        return vRetour;
    } // nbAttOp(...)

    public static int nbAtt( ClassContent pCla )
    {
       return pCla.getFields().getNbFields();
    } // nbAtt(...)

    @Test
    public static FieldContent selAttFTN( final ClassContent pCla, final String pType, final String pName )
    {
        sNature = "l'attribut";
        sClasse = pCla.className();
        if ( ! pType.equals("*") )   sType = pType;
        if ( ! pName.equals("*") )   sNom  = pName;
        
        FieldContent vRetour = null;
        if ( pType.equals("*") && pName.equals("*") )
          vRetour = getPremierAtt( pCla );
        else if ( !pType.equals("*") && !pName.equals("*") )
          vRetour = getAttFTN( pCla, pType, pName );
        else if ( !pName.equals("*") )
          vRetour = getAttFName( pCla, pName );
        else /* if ( !pType.equals("*") ) */
          vRetour = getAttFType( pCla, pType );

        sRetour = vRetour;
        return vRetour;
    } // selAttFTN(...)

    public static FieldContent getPremierAtt( ClassContent pCla )
    {
        FieldsContent vFields = pCla.getFields();
        if ( vFields.getNbFields() < 1 ) return null;
        return vFields.firstField();
    } // getPremierAtt()

    @Test
    public static FieldContent getAttFTN( ClassContent pCla, String pType, String pName )
    {
          sNature = "L'attribut";
          sType = pType;
          sNom = pName;
          FieldContent vRetour = pCla.getFields().findField( pName );
          sMes = "$a $n n'existe pas !";
          if (vRetour != null) {
              String vType = vRetour.fieldType();
              vRetour = (vType.equals( pType )) ? vRetour : null;
              sMes = "$a $n n'est pas de type $t !" /*("+vType+") !"*/;
          }
        sRetour = vRetour;
        return vRetour;
    } // getAttFTN(...)

    @Test
    public static FieldContent getAttFName( ClassContent pCla, String pName )
    {
          FieldContent vRetour = pCla.getFields().findField( pName );
          sMes = "$a $n n'existe pas !";
          sNom = pName;
        return vRetour;
    } // getAttFName(..)

    @Test
    public static FieldContent getAttFType( final ClassContent pCla, final String pType )
    {
          FieldContent vRetour = pCla.getFields().findFieldType( pType );
          if ( vRetour != null )   sNom = vRetour.fieldName();
          sMes = "aucun attribut de type $t n'existe !";
          sType = pType;
          sRetour = vRetour;
        return vRetour;
    } // getAttFType(..)

    public static void verifNomAttribut( final FieldContent pF )
    {
        sNature = "le nom d'attribut";
        sMes = "$a $n ne commence pas par 'a' et/ou 'a' n'est pas suivi d'une majuscule";
        sNom = pF.fieldName();
        boolean vOK = sNom.startsWith("a")
             && Character.isUpperCase( sNom.charAt(1) );
        if ( vOK )
          sRetour = true;
        sRetour = false;
        vrai( vOK, "*" );
        failIfNot();
    } // verifNomAttribut(.)

    public static void verifModAttribut( final FieldContent pF, final String pYes, final String pNo )
    {
        if ( pF == null )   fail( sMes );
        sNature = "l'attribut";
        vrai( ifModAttribut( pF, pYes, pNo ), "*" );
        failIfNot();
    } // verifModAttribut(...)

    public static boolean ifModAttribut( final FieldContent pF, final String pYes, final String pNo )
    {
        ModifiersContent vMod = pF.getModifiers();
        
        sNature = "l'attribut";
        String[] vYes = pYes.split( " " );
        sMes = "$a $n n'est pas $r !";
        for ( String vM : vYes )
            if ( ! vMod.hasModifier( vM ) ) {
                sRetour = vM;
                return false;
            }
        String[] vNo = pNo.split( " " );
        sMes = "$a $n ne devrait pas etre $r !";
        for ( String vM : vNo )
            if ( vMod.hasModifier( vM ) ) {
                sRetour = vM;
                return false;
            }
        return true;
    } // verifModAttribut(...)
    
// //     public static String nbFields2( String pOp, int pNb, String pCla )
// //     {
// //         return nbFields( pOp, pNb, new ClassContent( pCla ) );
// //     }
// // 
// //     public static String nbFields( String pOp, int pNb, ClassContent pCla )
// //     {
// //         int vNb =pCla.getFields().getNbFields();
// //         boolean vRes;
// //         if ( pOp.equals("==") )        vRes= vNb == pNb;
// //         else if ( pOp.equals("!=") )   vRes= vNb != pNb;
// //         else if ( pOp.equals("<=") )   vRes= vNb <= pNb;
// //         else if ( pOp.equals(">=") )   vRes= vNb >= pNb;
// //         else   vRes= false;
// //         if ( vRes )   return "";
// //         else   return pCla.className()+": "+vNb+" attribut(s) n'est pas "+pOp+pNb+" !\n";
// //     } // nbFields
//     
// //     public static FieldContent premierTypeAttribut( String pCla, String pType )
// //     {
// //         if ( pType == null || pType.length() < 2 )  return null;
// //         ClassContent vCla = new ClassContent( pCla );
// //         FieldsContent vFields = vCla.getFields();
// //         if ( vFields.getNbFields() < 1 ) return null;
// //         if ( ! vFields.hasFieldType( pType ) ) return null;
// //         return vFields.findFieldType( pType );
// //     } // premierTypeAttribut()
// 
// //     public static String privateField2( boolean pRes, String pC, String pF )
// //     {
// //         if ( ! findClass( pC ) )   return "La classe "+pC+" n'existe pas !";
// //         FieldContent vF =new FieldsContent( pC ).findField( pF );
// //         if ( vF == null )   return pC+": l'attribut "+pF+" n'existe pas !";
// //         else   return privateField( pRes, vF );
// //     }
// 
// //     public static String privateField( boolean pRes, FieldContent pF )
// //     {
// //         if ( pF.getModifiers().hasModifier( "private" ) )   return "";
// //         return pF.getTheClass().getName()+": l'attribut "+pF.fieldName()
// //                +" est "+pF.getModifiers().accessString()+" !";
// //     } // privateField(..)
//     
// //     public static boolean attributPrive( String pCla, String pAtt )
// //     {
// //         if ( !existeAttribut( pCla, pAtt ) )  return false;
// //         
// //         boolean res;
// //         try { res = Modifier.isPrivate( Class.forName( pCla ).getDeclaredField( pAtt ).getModifiers() ); }
// //         catch( NoSuchFieldException e ) { res= false; }
// //         catch( ClassNotFoundException e ) { res= false; }
// //         return res;
// //     }
    
// ========== new constructor ==========zcon


    @Test
    public static ConstructorContent getVConFProto( ClassContent pCla, String pProto )
    {
        verifDefCon( pCla, "F" );
        verifConNbP( pCla, calcNPFProto( pProto ), "T" );
        ConstructorContent vCon = getConFProto( pCla, pProto, "T" );
        verifModCon( vCon, "public", "static final" );
        return vCon;
    } // getVConFProto(..)
    
    // Algo a revoir s'il y a plusieurs constructeurs !!!
    @Test
    public static ConstructorContent unConstructeur( ClassContent pCla, String pProto )
    {
        int vNbCo0 = nbCo( pCla );
        failIfNot();
        int vNbCo = nbCo( pCla, "==", 1 );
        failIfNot();

//         ConstructorContent vCo0 = selCo( pCla, "*", "*" );
//         String vNomCo = vCo0.constructorName();
        sNature = "le constructeur";
        sNom = "";
        ConstructorContent vCo = premierConstructeur( pCla );
        failIfNot();
        
        vrai( ifModConstructor( vCo, "public", "static final" ), "*" );
        failIfNot();
//         vrai( verifNomAttribut(vAttr), "$a $n ne commence pas par 'a' ou celui-ci n'est pas suivi d'une majuscule !" );
//         failIfNot();
//         vrai( vAttr.fieldName().equals( pName ), "$a ne s'appelle pas "+pName+" !" );
//         failIfNot();
        return vCo;
    } // unConstructeur(...)

    public static int nbCon( final ClassContent pCla )
    {
        return pCla.getConstructors().getNbConstructors();
    } // nbCon(.)

    public static int nbCo( final ClassContent pCla )
    {
        return nbConstructeurs( pCla, ">=", 0 );
    } // nbCo(.)

    public static int nbCo( final ClassContent pCla, String pOp, int pNb )
    {
        sNature = "le nb de constructeurs";
        sClasse = pCla.className();
        sMes = "$c : $a n'est pas "+pOp+pNb;
        
        int vRetour = nbConstructeurs( pCla, pOp, pNb );
        return vRetour;
    } // nbCo(...)

    public static int nbConstructeurs( ClassContent pCla, String pOp, int pNb )
    {
       int vNbCo = pCla.getConstructors().getNbConstructors();
       verifNbOp( vNbCo, pOp, pNb );
       return vNbCo;
    } // nbConstructeurs(...)

    public static ConstructorContent premierConstructeur( ClassContent pCla )
    {
        ConstructorsContent vConstructors = pCla.getConstructors();
        if ( vConstructors.getNbConstructors() < 1 ) return null;
        return vConstructors.firstConstructor();
    } // premierConstructeur()

    @Test
    public static ConstructorsContent verifConstructor( final String pClass )
    {
		return verifConstructor( pClass, "t" );
    } // verifConstructor(.)

    @Test
    public static ConstructorsContent verifConstructor( final String pClass, final String pExist )
    {
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		Class<?> vCla = getClaFName( pClass ).getTheClass();
		ConstructorsContent vLCo = new ConstructorsContent( vCla );
		
		if ( vLCo.hasConstructor() != vWanted )
		  if ( Character.isUpperCase( pExist.charAt(0) ) )
		    fail( "Le constructeur de la classe " + pClass + " " + vMessage + " !" );
		  else
		    return null;
		    
		return vLCo;
    } // verifConstructor(..)

    @Test
    public static void verifDefCon( final ClassContent pCla, final String pExist )
    {
        sNature = "le constructeur par defaut";
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted
          ? "n'existe pas ou bien auriez-vous ecrit un autre constructeur ?"
          : "ne devrait pas exister ou bien auriez-vous oublie d'ecrire un constructeur ?" );
		ConstructorsContent vLCo = verifConstructor( pCla.className() );
        vrai( vLCo.hasConstructorNP( 0 ) == vWanted, "$a " + vMessage );
        failIfNot();
    } // verifDefCon(..)

    @Test
    public static void verifConNbP( final ClassContent pCla, final int pNP, final String pExist )
    {
        sNature = "le constructeur a $n parametre(s)";
        sNom = "" + pNP;
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		ConstructorsContent vLCo = verifConstructor( pCla.className() );
        vrai( vLCo.hasConstructorNP( pNP ) == vWanted, "$a " + vMessage );
        failIfNot();
    } // verifConNbP(...)

    @Test
    public static ConstructorContent getConFProto( final ClassContent pCla, final String pProto, final String pExist )
    {
        sNature = "le constructeur";
        sNom = pProto;
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		ConstructorsContent vLCo = verifConstructor( pCla.className() );
        vrai( vLCo.hasConstructor( pProto ) == vWanted, "$a $n " + vMessage );
        failIfNot();
        return vLCo.getConstructor( pProto );
    } // getConFProto(..)

    @Test
    public static ConstructorsContent verifConstructorNP( final String pClass, final int pNP )
    {
		return verifConstructorNP( pClass, pNP, "t" );
    } // ifConstructorNP(..)

    @Test
    public static ConstructorsContent verifConstructorNP( final String pClass, final int pNP, final String pExist )
    {
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		ConstructorsContent vLCo = verifConstructor( pClass );
		
		if ( vLCo.hasConstructorNP(pNP) != vWanted )
		  if ( Character.isUpperCase( pExist.charAt(0) ) )
		    fail( "Le constructeur" + getParamMes(pNP) + " de la classe " + pClass + " " + vMessage + " !" );
		  else
		    return null;
		    
		return vLCo;
    } // ifConstructorNP(...)

    @Test
    public static ConstructorsContent verifConstructorPar( final String pClass, final String pPL )
    {
		return verifConstructorPar( pClass, pPL, "t" );
    } // verifConstructorPar(...)

    @Test
    public static ConstructorsContent verifConstructorPar( final String pClass, final String pPL, final String pExist )
    {
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );		
		ConstructorsContent vLCo;
		
		if ( vWanted )
		  vLCo = verifConstructorNP( pClass, nbPar( pPL ) );
		else
		  vLCo = verifConstructor( pClass );
		  
		if ( vLCo.hasConstructor(pPL) != vWanted )
		  if ( Character.isUpperCase( pExist.charAt(0) ) )
		    fail( "Le constructeur " + pClass + pPL + " " + vMessage + " !" );
		  else
		    return null;
		    
		return vLCo;
    } // verifConstructorPar(....)

    public static void verifModCon( final ConstructorContent pC, final String pYes, final String pNo )
    {
        sNature = "le constructeur";
        vrai( ifModConstructor( pC, pYes, pNo ), "*" );
        failIfNot();
    } // verifModCon(...)

    public static boolean ifModConstructor( final ConstructorContent pC, final String pYes, final String pNo )
    {
        ModifiersContent vMod = pC.getModifiers();
        
        String[] vYes = pYes.split( " " );
        sMes = "$a $n n'est pas $r !";
        for ( String vM : vYes )
            if ( ! vMod.hasModifier( vM ) ) {
                sRetour = vM;
                return false;
            }
        String[] vNo = pNo.split( " " );
        sMes = "$a $n ne devrait pas etre $r !";
        for ( String vM : vNo )
            if ( vMod.hasModifier( vM ) ) {
                sRetour = vM;
                return false;
            }
        return true;
    } // ifModConstructor(...)
    
    @Test
    public static Object getAndVerifIns1( final ConstructorContent pC, final FieldContent pF, final Object pV )
    {
        Object vIns = null;
        try {
            vIns = pC.newInstance( new Object[]{ pV } );
        } catch( final Exception pE ) {
            fail( "le constructeur " + pC.getName() + pC.getParameterString() + " genere une exception !" );
        } // t/c
        vrai( pV.equals( pF.fieldValue( vIns ) ), "Le constructeur n'initialise pas correctement l'attribut" + pF );
        failIfNot();
        return vIns;
    } // getAndVerifIns1(...)
    
    @Test
    public static Object getAndVerifIns2( final ConstructorContent pC, final FieldContent pF1, final Object pV1,
                                          final FieldContent pF2, final Object pV2 )
    {
        Object vIns = null;
        try {
            vIns = pC.newInstance( new Object[]{ pV1, pV2 } );
        } catch( final Exception pE ) {
            fail( "le constructeur " + pC.getName() + pC.getParameterString() + " genere une exception !" );
        } // t/c
        vrai( pV1.equals( pF1.fieldValue( vIns ) ), "Le constructeur n'initialise pas correctement l'attribut" + pF1 );
        failIfNot();
        vrai( pV2.equals( pF2.fieldValue( vIns ) ), "Le constructeur n'initialise pas correctement l'attribut" + pF2 );
        failIfNot();
        return vIns;
    } // getAndVerifIns2(.....)

// ===== methodes =====zmet

    @Test
    public static MethodContent getVMetFProto( final ClassContent pCla, final String pMethod, final String pRT, final String pProto )
    {
        return getVMetFProto( pCla, pMethod, pRT, pProto, "public" );
    } // getVMetFProto(....)

    @Test
    public static MethodContent getVMetFProto( final ClassContent pCla, final String pMethod,
                                               final String pRT, final String pProto, final String pAcces )
    {
        verifMet( pCla, pMethod, "T" );
        verifMetRT( pCla, pMethod, pRT, "T" );
        verifMetRTNP( pCla, pMethod, pRT, calcNPFProto(pProto), "T" );
        MethodContent vMet = getMetFProto( pCla, pMethod, pRT, pProto, "T" );
        verifModMet( vMet, pAcces, "static final" );
        return vMet;
    } // getVMetFProto(.....)
    
    private static int calcNPFProto( final String pProto )
    {
        if ( pProto.equals( "()" ) )   return 0;
        int vCnt = 0;
        for ( int vI=pProto.length()-1; vI>=0; vI-- )
          if ( pProto.charAt(vI) == ',' )   vCnt++;
        return vCnt+1;
    } // calcNPFProto(.)
    
    @Test
    public static void verifMet( final ClassContent pCla, final String pMethod, final String pExist )
    {
        sNature = "la methode";
        sNom    = pMethod;
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );		
		MethodsContent vLMet = new MethodsContent( pCla.className() );
		vrai( vLMet.hasMethod( pMethod ) == vWanted, "$a $n " + vMessage + " !" );
        failIfNot();
    } // verifMet(...)

    @Test
    public static void verifMetRT( final ClassContent pCla, final String pMethod,
                                   final String pRT, final String pExist )
    {
        sNature = "la methode";
        sNom    = pMethod;
        sType   = pRT;
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		MethodsContent vLMet = new MethodsContent( pCla.className() );
		vrai( vLMet.hasMethodRT( pMethod, pRT ) == vWanted, "$a  $t $n " + vMessage + " !" );
        failIfNot();
    } // verifMetRT(....)

    @Test
    public static void verifMetRTNP( final ClassContent pCla, final String pMethod,
                                     final String pRT, final int pNP, final String pExist )
    {
        sNature = "la methode $t " + pMethod;
        sNom    = "" + pNP;
        sType   = pRT;
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		MethodsContent vLMet = new MethodsContent( pCla.className() );
		vrai( vLMet.hasMethodRTNP( pMethod, pRT, pNP ) == vWanted, "$a a $n parametres(s) " + vMessage + " !" );
        failIfNot();
    } // verifMetRTNP(.....)

    @Test
    public static MethodContent getMetFProto( final ClassContent pCla, final String pMethod,
                                              final String pRT, final String pProto, final String pExist )
    {
        sNature = "la methode";
        sNom    = pMethod + pProto;
        sType   = pRT;
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		MethodsContent vLMet = new MethodsContent( pCla.className() );
        vrai( vLMet.hasMethod( pMethod, pRT, pProto ) == vWanted, "$a  $t $n " + vMessage );
        failIfNot();
        return vLMet.getMethod( pMethod, pRT, pProto );
    } // getMetFProto(..)

    @Test
    public static MethodsContent uneMethode( ClassContent pCla, String pName )
    {
        MethodsContent vLMet = verifMethod( pCla.className(), pName );
        failIfNot();
        
//         vrai( verifModMethod( vMe, "public", "static final" ), "*" );
        failIfNot();
        
        return vLMet;
    } // uneMethode(..)

    @Test
    public static MethodsContent verifMethod( final String pClass, final String pMethod )
    {
		return verifMethod( pClass, pMethod, "t" );
    } // verifMethod(..)

    @Test
    public static MethodsContent verifMethod( final String pClass, final String pMethod, final String pExist )
    {
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		Class<?> vCla = getClaFName( pClass ).getTheClass();
		MethodsContent vLMet = new MethodsContent( vCla );
		
		if ( vLMet.hasMethod( pMethod ) != vWanted )
		  if ( Character.isUpperCase( pExist.charAt(0) ) )
		    fail( "La methode " + pMethod + " " + vMessage + " !" );
		  else
		    return null;
		    
		return vLMet;
    } // verifMethod(...)

    @Test
    public static MethodsContent verifMethodRT( final String pClass, final String pMethod, final String pRT )
    {
		return verifMethodRT( pClass, pMethod, pRT, "t" );
    } // verifMethodRT(...)

    @Test
    public static MethodsContent verifMethodRT( final String pClass, final String pMethod, final String pRT, final String pExist )
    {
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		MethodsContent vLMet = verifMethod( pClass, pMethod );
		
		if ( vLMet.hasMethodRT( pMethod, pRT ) != vWanted )
		  if ( Character.isUpperCase( pExist.charAt(0) ) )
		    fail( "La methode " + pRT + " " + pMethod + " " + vMessage + " !" );
		  else
		    return null;
		    
		return vLMet;
    } // verifMethodNP(....)

    @Test
    public static MethodsContent verifMethodNP( final String pClass, final String pMethod, final int pNP )
    {
		return verifMethodNP( pClass, pMethod, pNP, "t" );
    } // verifMethodNP(...)

    @Test
    public static MethodsContent verifMethodNP( final String pClass, final String pMethod, final int pNP, final String pExist )
    {
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		MethodsContent vLMet = verifMethod( pClass, pMethod );
		
		if ( vLMet.hasMethodNP( pMethod, pNP ) != vWanted )
		  if ( Character.isUpperCase( pExist.charAt(0) ) )
		    fail( "La methode " + pMethod + getParamMes(pNP) + " " + vMessage + " !" );
		  else
		    return null;
		    
		return vLMet;
    } // verifMethodNP(....)

    @Test
    public static MethodsContent verifMethodPar( final String pClass, final String pMethod, String pPL )
    {
		return verifMethodPar( pClass, pMethod, pPL, "t" );
    } // verifMethodPar(...)

    @Test
    public static MethodsContent verifMethodPar( final String pClass, final String pMethod, final String pPL, final String pExist )
    {
        boolean vWanted = pExist.toUpperCase().equals("T");
        String vMessage = ( vWanted ? "n'existe pas" : "ne devrait pas exister" );
		MethodsContent vLMet;
		
		if ( vWanted )
		  vLMet = verifMethodNP( pClass, pMethod, nbPar( pPL ) );
		else
		  vLMet = verifMethod( pClass, pMethod );
		  
		if ( vLMet.hasMethod( pMethod, pPL ) != vWanted )
		  if ( Character.isUpperCase( pExist.charAt(0) ) )
		    fail( "La methode " + pMethod + pPL + " " + vMessage + " !" );
		  else
		    return null;
		    
		return vLMet;
    } // verifMethodPar(....)

    public static void verifModMet( final MethodContent pM, final String pYes, final String pNo )
    {
        sNature = "la methode";
        vrai( ifModMethod( pM, pYes, pNo ), "*" );
        failIfNot();
    } // verifModMet(...)

    public static boolean ifModMethod( final MethodContent pC, final String pYes, final String pNo )
    {
        ModifiersContent vMod = pC.getModifiers();
        
        String[] vYes = pYes.split( " " );
        sMes = "$a $n n'est pas $r !";
        for ( String vM : vYes )
            if ( ! vMod.hasModifier( vM ) ) {
                sRetour = vM;
                return false;
            }
        String[] vNo = pNo.split( " " );
        sMes = "$a $n ne devrait pas etre $r !";
        for ( String vM : vNo )
            if ( vMod.hasModifier( vM ) ) {
                sRetour = vM;
                return false;
            }
        return true;
    } // ifModMethod(...)

    public static int nbMet( final ClassContent pCla )
    {
        return pCla.getMethods().getNbMethods();
    } // nbMet(.)
    
    @Test
    public static void verifGetter( final Object pObj, final MethodContent pM, final Object pV )
    {
        sNature = "L'accesseur";
        sNom = pM.getName() + pM.getParameterString();
        Object vRes = null;
        try {
            vRes = pM.invoke( pObj, null );
        } catch( final Exception pE ) {
            fail( "$a  $n  genere une exception !" );
        } // t/c
        vrai( pV.equals( vRes ), "$a  $n  ne retourne pas la valeur de l'attribut !?" );
        failIfNot();
    } // verifGetter(..)
    
//     public static boolean findMethod( String pCla, String pMet )
//     {
// 		Class<?> vCla = new ClassContent( pCla ).getTheClass();
// 		MethodsContent vLMet = new MethodsContent( vCla );
// 		return vLMet.hasMethod( pMet );
//     } // findMethod(..)
    
//     public static boolean findMethod( String pCla, String pMet, String pPar )
//     {
// 		Class<?> vCla = new ClassContent( pCla ).getTheClass();
// 		MethodsContent vLMet = new MethodsContent( vCla );
// 		return vLMet.hasMethod( pMet, pPar );
//     } // findMethod(...)

// ===== entrees/sorties =====zfil

    public boolean redirectTerminal()
    {
        boolean ok = false;
        try {
            console = System.out;
            out = new PrintStream( new BufferedOutputStream(
                      new FileOutputStream( "test.out" ) ));
            System.setOut( out );
            ok = true;
        }
        catch( Exception e ) { e.printStackTrace(); }
        return ok;
    } // redirectTerminal()

    public String compareTerminal( String refName )
    {
        try {
            out.flush();
            out.close();
            
            Scanner ref = new Scanner( new File( refName ) );
            Scanner res = new Scanner( new File( "test.out" ) );
            while ( ref.hasNextLine() ) {
                String liRef = ref.nextLine();
                if ( ! res.hasNextLine() )
                    return "Il manque au moins une ligne d'affichage !" ;
                String liRes = res.nextLine();
                if ( ! liRef.equals( liRes ) )
                    return "Display expected <" + liRef + "> but was <" + liRes + "> !" ;
            } // while
        }
        catch( FileNotFoundException fnfe ) {
            return "Fichier " + refName + " introuvable !" ;
        }
        catch( Exception e ) { e.printStackTrace(); }
        finally { System.setOut( console ); }
        return "OK";
    } // compareTerminal(.)

    public static void verifFinal1Type( String pFil, String pMet, String pType ) // un seul parametre
    {
//        System.out.println(System.getProperty("user.dir"));
        String vClassName = pFil.substring( 0, pFil.length() - 5 );
        int vI = vClassName.lastIndexOf( "/" );
        vClassName = vClassName.substring( vI+1 );
        sNom    = pMet;
        sType   = pType;
        sNature = vClassName.equals( pMet )
          ? "du constructeur"
          : "de la methode";
        Scanner vJ = null;
        try {
          vJ = new Scanner( new File( pFil ) );
        } catch( FileNotFoundException pE ) {
          error( "Pas de "+pFil+" !" );
        } // t/c
        
        // Pourquoi ne peut-on pas ajouter \\)  avant .*  ?
        final String vAvec = ".*\\w\\s+"+pMet+"\\s*\\(\\s*"+"final\\s+"+pType+"\\s+\\w\\s*.*";
        final String vSans = ".*\\w\\s+"+pMet+"\\s*\\(\\s*"            +pType+"\\s+\\w\\s*.*";
        String vL;
        int vCpt = 0;
        try {
          while ( vJ.hasNextLine() ) {
              vL = vJ.nextLine();
              if ( vL.matches( vAvec ) )
                return;
              if ( vL.matches( vSans ) ) {
                sRetour = null;
                failIfNot( "le parametre $a $n($t) n'est pas final !" );
              } // if bad
          } // while
        } catch( Exception pE ) {
          error( "code source de "+pFil+" non analysable : " + pE );
        } // t/c
    } // verifFinal1Type(...)

    public static void verifParamP1Type( String pFil, String pMet, String pType ) // un seul parametre
    {
        String vClassName = pFil.substring( 0, pFil.length() - 5 );
        int vI = vClassName.lastIndexOf( "/" );
        vClassName = vClassName.substring( vI+1 );
        sNom    = pMet;
        sType   = pType;
        sNature = vClassName.equals( pMet )
          ? "du constructeur"
          : "de la methode";
        Scanner vJ = null;
        try {
          vJ = new Scanner( new File( pFil ) );
        } catch( FileNotFoundException pE ) {
          error( "Pas de "+pFil+" !" );
        } // t/c
        
        // Pourquoi ne peut-on pas ajouter \\)  avant .*  ?
        final String vDebut = ".*\\w\\s+"+pMet+"\\s*\\(\\s*"+"final\\s+"+pType+"\\s+";
        final String vFin = "\\w\\s*.*";
        final String vAvec = vDebut + "p" + vFin;
        final String vSans = vDebut + "" + vFin;
        String vL;
        int vCpt = 0;
        try {
          while ( vJ.hasNextLine() ) {
              vL = vJ.nextLine();
              if ( vL.matches( vAvec ) )
                return;
              if ( vL.matches( vSans ) ) {
                sRetour = null;
                failIfNot( "le parametre 1 $a $n ne commence pas par 'p' !" );
              } // if bad
          } // while
        } catch( Exception pE ) {
          error( "code source de "+pFil+" non analysable : " + pE );
        } // t/c
    } // verifParamP1Type(...)

    public static void verifParamPNType( String pFil, String pMet, String pType, int pN ) // Neme parametre
    {
        String vClassName = pFil.substring( 0, pFil.length() - 5 );
        int vIdx = vClassName.lastIndexOf( "/" );
        vClassName = vClassName.substring( vIdx+1 );
        sNom    = pMet;
        sType   = pType;
        sNature = vClassName.equals( pMet )
          ? "du constructeur"
          : "de la methode";
        if ( pN == 1 ) {
          verifParamP1Type( pFil, pMet, pType );
          return;
        }
        
        Scanner vJ = null;
        try {
          vJ = new Scanner( new File( pFil ) );
        } catch( FileNotFoundException pE ) {
          error( "Pas de "+pFil+" !" );
        } // t/c
        
        // Pourquoi ne peut-on pas ajouter \\)  avant .*  ?
        String vDebut = ".*\\w\\s+"+pMet+"\\s*\\(";
        final String vParam = "\\s*"+"final\\s+"+"\\w+"+"\\s+p\\w+\\s*\\,";
        for ( int vI=1; vI<pN; vI++ )   vDebut += vParam;
        vDebut += "\\s*final\\s+"+pType+"\\s+";
        final String vFin = "\\w+\\s*.*";
        final String vAvec = vDebut + "p" + vFin;
        final String vSans = vDebut + "" + vFin;
        String vL;
        int vCpt = 0;
        try {
          while ( vJ.hasNextLine() ) {
              vL = vJ.nextLine();
              if ( vL.matches( vAvec ) )
                return;
              if ( vL.matches( vSans ) ) {
                sRetour = null;
                failIfNot( "le parametre "+pN+" $a $n ne commence pas par 'p' !" );
              } // if bad
          } // while
        } catch( Exception pE ) {
          error( "code source de "+pFil+" non analysable : " + pE );
        } // t/c
    } // verifParamPNType(....)

    public static void verifAttThis( String pFil, String pType, String pAtt ) // un seul parametre
    { // attention : ne marche pas si attribut cite dans commentaires
        verifAttThisDebug( pFil, pType, pAtt, false );
    } // verifAttThis(...)
    
    public static void verifAttThisDebug( String pFil, String pType, String pAtt, boolean pDebug ) // un seul parametre
    { // attention : ne marche pas si attribut cite dans commentaires
        sType   = pType;
        sNom    = pAtt;
        sNature = "L'attribut";
        Scanner vJ = null;
        try {
          vJ = new Scanner( new File( pFil ) );
        } catch( FileNotFoundException pE ) {
          error( "Pas de "+pFil+" !", true );
        } // t/c
        
        // Pourquoi ne peut-on pas ajouter \\)  avant .*  ?
        final String vAvec = ".*"+"this\\."+pAtt+".*";
        final String vSans = ".*"          +pAtt+".*";
        final String vSauf = ".*"+pType+"\\s+"+pAtt+".*";
        final String vSauf2 = ".*"+"et"+pAtt+".*"; // pour get et set
//         final String vSauf = ".*"+"\\LETTREouCHIFFRE+"+pAtt+".*";
        final String vCom1 = ".*/\\*\\*.*\\*/.*"; // commentaire javadoc sur une seule ligne
        String vL;
        int vCpt = 0;
        boolean vInCom = false; // si on est dans un commentaire javadoc
        try {
          while ( vJ.hasNextLine() ) {
              vCpt++;
              vL = vJ.nextLine();
              if ( vL.matches( vCom1 ) )
                continue;
              if ( vL.matches( "\\s*/\\*\\*.*" ) ) {
                  vInCom = true;
                  continue;
              }
              if ( vL.matches( ".*\\*/.*" ) ) {
                  vInCom = false;
                  continue;
              }
              if ( pDebug )
                System.out.println( ""+vInCom+":"+vL.matches(vAvec)+vL.matches(vSans)+vL.matches(vSauf)+vL );
              if ( vL.matches( vCom1 ) )
                continue;
              if ( vL.matches( vAvec ) )
                continue;
              if ( vL.matches( vSans ) ) {
                if ( vL.matches( vSauf ) || vL.matches( vSauf2 ) )
                  continue;
                sRetour = null;
                if ( ! vInCom )
                  failIfNot( "$a $n n'est pas precede de this. a la ligne " + vCpt );
              } // if bad
          } // while
        } catch( Exception pE ) {
          error( "code source de "+pFil+" non analysable : " + pE );
        } // t/c
    } // verifAttThisDebug(....)
        
    public static String verifFinalN( String pFil, String pMet, int pN ) // Neme parametre
    {
//        System.out.println(System.getProperty("user.dir"));
        sNature = sClasse.equals( pMet )
          ? "du constructeur"
          : "de la methode";
        if ( pN == 1 )   return verifFinal1( pFil, pMet );
        
        Scanner vJ = null;
        try {
          vJ = new Scanner( new File( pFil ) );
        } catch( FileNotFoundException pE ) {
          return "Pas de "+pFil+" !";
        } // t/c
        
        String vParam = "\\s*final\\s+\\w+\\s+\\w+\\s*\\,";
        String vDebut = ".*\\w\\s+"+pMet+"\\s*\\(";
        for ( int vI=1; vI<pN; vI++ )   vDebut += vParam;
        String vL;
        int vCpt = 0;
        try {
          while ( vJ.hasNextLine() ) {
            vL = vJ.nextLine();
            // rechercher la parenthese et ajouter a vL, puis verifier la regexp !!!
            if (     vL.matches( vDebut + ".*\\).*" ) ) {
              vCpt++;
              if ( ! vL.matches( vDebut + "\\s*final\\s+\\w.*" ) )
                return "("+vCpt+") Il manque final pour le parametre "+pN+" "+sNature+" "+pMet+" !";
            } // if
          } // while
        } catch( Exception pE ) {
          return "code source de "+pFil+" non analysable : " + pE;
        } // t/c
        
        return "OK";
    } // verifFinalN(...)

    public static String verifFinal1( String pFil, String pMet ) // premier parametre
    {
//        System.out.println(System.getProperty("user.dir"));
        Scanner vJ = null;
        try {
          vJ = new Scanner( new File( pFil ) );
        } catch( FileNotFoundException pE ) {
          return "Pas de "+pFil+" !";
        } // t/c
        
        String vL;
        int vCpt = 0;
        try {
          while ( vJ.hasNextLine() ) {
            vL = vJ.nextLine();
//             if (     vL.matches( ".*\\p{Alpha}\\s+"+pMet+"\\s*\\(\\s*\\p{Alpha}.*" ) ) {
            if (     vL.matches( ".*\\w\\s+"+pMet+"\\s*\\(\\s*\\w.*" ) ) {
              vCpt++;
//               if ( ! vL.matches( ".*\\p{Alpha}\\s+"+pMet+"\\s*\\(\\s*final\\s+\\p{Alpha}.*" ) )
              if ( ! vL.matches( ".*\\w\\s+"+pMet+"\\s*\\(\\s*final\\s+\\w.*" ) )
                return "("+vCpt+") Il manque final pour le parametre 1 de "+pMet+" !";
            } // if
          } // while
        } catch( Exception pE ) {
          return "code source de "+pFil+" non analysable : " + pE;
        } // t/c
        
        return "OK";
    } // verifFinal1(..)
    
    public static boolean hasCoDebug( String pFil, String pCla, String pAcc, boolean pDebug )
    {        
        Scanner vJ = null;
        try {
          vJ = new Scanner( new File( pFil ) );
        } catch( FileNotFoundException pE ) {
          error( "Pas de "+pFil+" !", true );
        } // t/c
        
        // Pourquoi ne peut-on paas ajouter \\)  avant .*  ?
        final String vPub = ".*"+"public\\s+"+pCla+"\\s*\\("+".*";
        final String vPri = ".*"+"private\\s+"+pCla+"\\s*\\("+".*";
        String vL;
        int vCpt = 0;
        try {
          while ( vJ.hasNextLine() ) {
              vCpt++;
              vL = vJ.nextLine();
              boolean vIsPub = vL.matches( vPub );
              boolean vIsPri = vL.matches( vPri );
              if ( pDebug )
                System.out.println( ""+vIsPub+vIsPri+vL );
              if ( vIsPub && ! pAcc.equals("private") )
                return true;
              if ( vIsPri && ! pAcc.equals("public") )
                return true;
          } // while
        } catch( Exception pE ) {
          error( "code source de "+pFil+" non analysable : " + pE );
        } // t/c
        return false;
    } // hasCoDebug(....)

// ===== utilitaires =====zuti
//     @Test public static void failIf()
//     @Test public static void failIfNot()
//     @Test public static void mesIf()
//     @Test public static void mesIfNot()
//     @Test public static void failIf(    final String pMes )
//     @Test public static void failIfNot( final String pMes )
//     @Test public static void mesIf(     final String pMes )
//     @Test public static void mesIfNot(  final String pMes )
//     public static void verif( final String pMes, final boolean pFail, final boolean pError )
//     public static boolean verifNbop( final int pAV, String pOp, int pNb )
//     public static void vrai( final boolean pB, final String pMes )
//     public static String getParamMes( final int pN )
//     public static int nbPar( final String pPL )   
//     private static void error( String mes )
//     private static void error( String mes, boolean exit )
//     private static void debug( final String pMes )
   
    @Test public static void failIf()     { failIf(    sMes ); }
    @Test public static void failIfNot()  { failIfNot( sMes ); }
    @Test public static void mesIf()      { mesIf(     sMes ); }
    @Test public static void mesIfNot()   { mesIfNot(  sMes ); }
    @Test public static void failIf(    final String pMes )  { verif( pMes, true,  true  ); }
    @Test public static void failIfNot( final String pMes )  { verif( pMes, true,  false ); }
    @Test public static void mesIf(     final String pMes )  { verif( pMes, false, true  ); }
    @Test public static void mesIfNot(  final String pMes )  { verif( pMes, false, false ); }
    
    @Test
    public static void verif( final String pMes, final boolean pFail, final boolean pError )
    {
        sFail  = pFail;
        sError = pError;
        sMes   = pMes.replace( "$a", ""+sNature )
                     .replace( "$c", ""+sClasse )
                     .replace( "$t", ""+sType )
                     .replace( "$n", ""+sNom )
                     .replace( "$e", (sError ? "ne devrait pas exister" : "n'existe pas") );

        if ( sRetour != null )
            sMes = sMes.replace( "$r", sRetour.toString() );
        debug("would be "+sMes);
        if ( (sRetour!=null) == sError )
            if ( sFail )
                fail( sMes );
            else
                System.err.println( sMes );
    } // verif(...)

    public static boolean verifNbOp( final int pAV, String pOp, int pNb )
    {
        int vNb = pAV;
        boolean vRes;
        if ( pOp.equals("==") )        vRes= vNb == pNb;
        else if ( pOp.equals("!=") )   vRes= vNb != pNb;
        else if ( pOp.equals("<=") )   vRes= vNb <= pNb;
        else if ( pOp.equals(">=") )   vRes= vNb >= pNb;
        else if ( pOp.equals("<" ) )   vRes= vNb <  pNb;
        else if ( pOp.equals(">" ) )   vRes= vNb >  pNb;
        else   vRes= false;
//         sRetour = (vRes) ? vNb : null;
        return vRes;
    } // verifNbOp(...)
        
    public static void vrai( final boolean pB, final String pMes )
    {
        String vMes = sMes;
        if ( ! pMes.equals( "*" ) ) {
          vMes = pMes;
          vMes = vMes.replace( "$r", ""+pB );
        }
        else if ( sRetour != null )
          vMes = vMes.replace( "$r", sRetour.toString() );
          
        vMes   = vMes.replace( "$a", ""+sNature )
                     .replace( "$c", ""+sClasse )
                     .replace( "$t", ""+sType )
                     .replace( "$n", ""+sNom )
                     .replace( "$e", (sError ? "ne devrait pas exister" : "n'existe pas") );
        sMes = vMes;
        debug("would be "+sMes);
        sRetour = (pB) ? true : null;
    } // vrai(..)

    public static String getParamMes( final int pN )
    {
        String vMes = " a " + pN + " parametre";
        if ( pN >= 2 )
          vMes += "s";
        return vMes;
    } // getParamMes(.)

    public static int nbPar( final String pPL )
    {
        if ( pPL.equals( "()" ) )   return 0;
        int vCnt = 0;
        for ( int vI=0; vI<pPL.length(); vI++ )
          if ( pPL.charAt(vI) == ',' )
            vCnt++;
        return 1 + vCnt;
    } // nbPar(.)
    
    public static void error( String mes )
    {
        error( mes, false );
    }
    
    private static void error( String mes, boolean exit )
    {
        System.err.println( mes );
        if (exit) System.exit( 1 );
    }
    
    private static void debug( final String pMes )
    {
        if (sDebug) System.err.println( "debug: " + pMes );
    } // debug(.)

// ===== ===== ===== ===== =====    
    
// 
// /*
// findClass( true, "Pile" );
// ClassContent maClassePile =new ClassContent( "Pile" );
// nbFields( "==", 1, maClassePile );
// FieldContent monAttribut =new FieldsContent( maClassePile ).getField(0);
// FieldContent monAttribut =new FieldsContent( maClassePile ).findField("tab");
// goodName( monAttribut );
// privateField( true, monAttribut );
// fieldType( true, int.class, monAttribut );
// constantField( true, monAttribut );
//  */
    
//     private static String myReplace( final String pMes, final String pFrom, final String pTo )
//     {
//         String vRes = "";
//         int vI = 0;
//         int vP;
//         do {
//             vP = pMes.indexOf( pFrom, vI );
//             if ( vP >= 0 ) {
//                 vRes += pMes.substring( vI, vP ) + pTo;
//                 vI = vP + pFrom.length();
//             }
//             else
//                 vRes += pMes.substring( vI, pMes.length() );
//         } while ( vP >= 0 );
//         sMes = vRes;
//         return sMes;
//     } // myReplace(.)
    
} // V
