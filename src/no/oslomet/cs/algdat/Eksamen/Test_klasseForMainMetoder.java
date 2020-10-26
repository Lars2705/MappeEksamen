package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class Test_klasseForMainMetoder {

    public static void main(String args[]){

        /*
    For at det skal virke må grensesnittet Beholder være tilgjengelige. Lag så noen instanser av
    klassen EksamenSBinTre. Sjekk at det ikke gir noen syntaksfeil (eller kjørefeil).
    Bruk f.eks. både Integer, Character og String som datatyper.
    Da kan du bruke en «naturlig» komparator i konstruktøren. Dvs. slik for datatypen String:

         */

         /*
        Oppgave 0

        EksamenSBinTre<String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall()); // Utskrift: 0


         EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall()); //utskrift 0

         EksamenSBinTre<Character> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall()); //utskrift 0


         */


/*
        Oppgave 1

        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall()); // Utskrift: 10
 */

           //oppgave 2
/*
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.antall());      // Utskrift: 10
        System.out.println(tre.antall(5));     // Utskrift: 0
        System.out.println(tre.antall(4));     // Utskrift: 3
        System.out.println(tre.antall(7));     // Utskrift: 2
        System.out.println(tre.antall(10));    // Utskrift: 1


 */

        // tester for oppgave 6

        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.fjernAlle(4)); // 3
        tre.fjernAlle(7); tre.fjern(8);
        System.out.println(tre.antall()); // 5
       // System.out.println(tre + " " + tre.toString());

        // [1, 2, 6, 9, 10] [10, 9, 6, 2, 1]
        // OBS: Hvis du ikke har gjort oppgave 4 kan du her bruke toString()





    }
}
