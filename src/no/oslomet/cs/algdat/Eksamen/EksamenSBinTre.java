package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) { // Avgjør om en verdi ligger i treet eller ikke.
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {

        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }


    public boolean leggInn(T verdi) {  //Programkoden er hentet fra kompendie 5.2 3 a) og har lagt til forldee referanse

        /* - referanse om programkoden hentet fra programkode 5.2.3 a)
        brukes to nodereferanser p og q. Referansen p starter i rotnoden.
        Den flyttes så nedover i treet - til venstre når verdi er mindre enn nodeverdien og til høyre ellers.
        Sammenligningene utføres ved hjelp av compare-metoden til komparatoren comp.
         Referansen q skal ligge et nivå over p, dvs. være forelder til p.
          Når p blir null, vil q være den siste noden som ble passert.
           Dermed skal verdi legges inn som et barn til q.
           Den siste verdien som compare-metoden returnerte, forteller om det skal være venstre eller høyre barn.
            Hvis treet i utgangspunktet var tomt, lages en rotnode.
         */

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot;
        Node<T> q = null;               // p starter i roten
        int compare = 0;                             // hjelpevariabel

        while (p != null) {      // fortsetter til p er ute av treet

            q = p;                                 // q er forelder til p
            compare = comp.compare(verdi, p.verdi);     // bruker komparatoren
            p = compare < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);  // oppretter en ny node  - tar ibruk konstruktøren private Node(T verdi, Node<T> forelder)
        //referer til at q er forelder til p

        if (q == null) {
            rot = p;                  // p blir rotnode

        } else if (compare < 0) {
            q.venstre = p;         // venstre barn til q

        } else {
            q.høyre = p;                        // høyre barn til q

        }

        antall++;                                // én verdi mer i treet
        endringer++;
        return true;                             // vellykket innlegging




        /*
        // Forelder må få riktig verdi ved hver innlegging, men forelder skal være null i rotnoden.
        // Lag metoden public boolean leggInn(T verdi). Der kan du kopiere Programkode 5.2 3 a),
        // men i tillegg må du gjøre de endringene som trengs for at referansen forelder får korrekt verdi i hver node.
        // Teknikken med en forelder-referanse brukes f.eks. i klassen TreeSet i java.util.
        // Sjekk at følgende kode er feilfri (ikke kaster noen unntak):
         */

    }


    public boolean fjern(T verdi) {
        //Lag metoden public boolean fjern(T verdi).
        // Der kan du kopiere Programkode 5.2 8 d),
        // men i tillegg må du gjøre de endringene som trengs for at pekeren forelder får korrekt,
        // verdi i alle noder etter en fjerning

        //Har implementert programkode 5.2.8 d)
        if (verdi == null) {
            return false;  // treet har ingen nullverdier
        }

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int compare = comp.compare(verdi, p.verdi);      // sammenligner
            if (compare < 0) {
                q = p;
                p = p.venstre; // går til venstre
            } else if (compare > 0) {
                q = p;
                p = p.høyre; // går til høyre
            } else break;    // den søkte verdien ligger i p
        }
        if (p == null) {
            return false;   // finner ikke verdi
        }

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) {
                rot = b;

            } else if (p == q.venstre) {
                q.venstre = b;
                if (b != null) { //hvis venstre barne noden verdi ikke er tom etter en fjerning
                    b.forelder = q; // så vet vi at foreldre noden koblet til venstre barne node heller ikke er tom
                    //foreldre noden for barne noden b er da q
                }
            } else {
                q.høyre = b;
                if (b != null) { //hvis høyre barne node verdi ikke er tom etter en fjerning
                    b.forelder = q; // så vet vi at foreldre noden koblet til høyre barne node heller ikke er tom
                }                    //foreldre noden for barne noden b er da q
            }
        } else { // Tilfelle 3) //hvis p.venstre og p.høyre ikke er null etter fjern

            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null) {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) { //hvis noden p ikke er lik foreldre noden s
                s.venstre = r.høyre; //så setter vi r høyre node lik s foreldre noden sin venstre node

                if (r.høyre != null) { //hvis r høyre node verdi ikke er null
                    r.høyre.forelder = s; // så må foreldre noden til r høyre node være s
                }
            } else { //hvis foreldre noden S sin node verdi er lik p.høyre sin node verdi
                s.høyre = r.høyre; // så setter vi foreldre noden sin høyre node verdi lik node r.høyre sin verdi

                if (r.høyre != null) { //hvis r høyre node verdi ikke er null
                    r.høyre.forelder = s; // så må foreldre noden til r høyre node være s
                }

            }
        }

        antall--;   // det er nå én node mindre i treet
        return true;

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        //Lag så metoden public int fjernAlle(T verdi).
        // Den skal fjerne alle forekomstene av verdi i treet. Husk at duplikater er tillatt.
        // Dermed kan en og samme verdi ligge flere steder i treet.
        // Metoden skal returnere antallet som ble fjernet.
        //Hvis treet er tomt, skal 0 returneres.

        if (tom()) { //Hvis treet er tomt, skal 0 returneres.

            return 0;
        }

        int AntallSomBleFjernet = 0;

        while (fjern(verdi)) { // Den skal fjerne alle forekomstene av (verdi) i treet.

            AntallSomBleFjernet++;  // Metoden skal returnere antallet som ble fjernet.

        }
        return AntallSomBleFjernet;
        //hrow new UnsupportedOperationException("Ikke kodet ennå!");
    }


    public int antall(T verdi) {  //Har tatt ibruk programkoden fra kompendie. Innenfor ukeoppgave 9 oppgave 5.2.6 b)

        Node<T> p = rot; //definerer p som rot noden - altså første noden

        int antallLike_NodeVerdier = 0; //definerer variabel som skal plusses for hver like node verdi

        while (!(p == null)) { //hvis p - rot noden ikke er tom
            int compare = comp.compare(verdi, p.verdi); //comparer verdiene vi har med p rot node verdien - Altså om verdiene vi har er mindre større eller lik p rot verdi

            if (compare < 0) {  // //hvis verdien somn sammenlinges med p rot node verdi er mindre =(compare value = -1)
                p = p.venstre; //så settes verdien inn som venstre node

            } else if (compare > 0) { //hvis nåværende verdi som sammenlignes med p rot verdien er større (compare value = 1)
                p = p.høyre; //så settes verdien inn som høyre node
            } else { //hvis compare verdiene er like for den nåværende verdien vi sammenlinger med og verdien i p noden (compare value = 0)

                antallLike_NodeVerdier++; //Det er tillatt med duplikater og det betyr at en verdi kan forekomme flere ganger.
                //vi plusser da for hver node som har lik verdi


                p = p.høyre; //setter den like nodeverdien til høyre for forldrenoden vi er på (P)
            }
        }
        return antallLike_NodeVerdier; //returnere antall like forekomster av verdier i treet.

        /*
        // Lag kode for metoden public int antall(T verdi).
        // Den skal returnere antall forekomster av verdi i treet.
        // Det er tillatt med duplikater og det betyr at en verdi kan forekomme flere ganger.
        //Hvis verdi ikke er i treet (null er ikke i treet), skal metoden returnere 0.
        // Test koden din ved å lage trær der du legger inn flere like verdier.
         */
    }

    public void nullstill() {
        //Lag så metoden public void nullstill().
        // Den skal traversere (rekursivt eller iterativt) treet i en eller annen rekkefølge og sørge for at samtlige
        //pekere og nodeverdier i treet blir nullet.
        // Det er med andre ord ikke tilstrekkelig å sette rot til null og antall til 0

        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) { //tatt utganspunkt i programkode 5.1.7 h) fra kompendie

        //(p blir definert som rotnoden q)

        if (p == null) { // hvis p noden er tom

            throw new NoSuchElementException("Da er hele treet tomt"); //vi har da ikke et tree fordi for oss er:
            // P = rotnoden som vil si at treet er tomt
        }

        while (true) {

            if (p.venstre != null) { // definerer at hvis venstre noden til foreldrenoden ikke er tom
                p = p.venstre; // så er p venstre noden til foreldre noden

            } else if (p.høyre != null) { // eller hvis høyre noden til foreldrenoden ikke er tom
                p = p.høyre; //så setter vi p høyre noden til foreldre noden

            } else {
                return p; //eller hvis venstre noden eller høyre noden er null - tom så returneres kun rotnoden tilbake som her er (p)
            }
        }

        /*
            Førstepostorden skal returnere første node post orden med p som rot

           //throw new UnsupportedOperationException("Ikke kodet ennå!");

         */

    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        //Tenker å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 om postorden
        //Kilde: under 5.1.7 h - om Postorden: I kompendie defineres fordeldre noden som (f), men jeg valgte å definere den som forelder

        Node<T> forelder = p.forelder; //definerer at forelder er foreldre noden til p

        if (p.forelder == null) { //hvis p ikke har forelder
            p = null; // da har ikke p en neste og vi returnere 0

        } else if (p == forelder.høyre) { //hvis p er høyre barn til sin forelder (q)
            p = forelder; // så er forelder (q) den neste

        } else if (forelder.venstre == p) { //else hvis p er det venstre barn til foreldre noden (q)

            if (forelder.høyre == null) { // og hvis høyre noden til foreldre q er tom så har vi kun en venstre node for p
                p = forelder; // da er foreldre den neste node for p. Siden høyre noden for q er tom

            } else { //derimot hvis foreldre noden i tilegg har en høyre node så det den neste den noden som kommer først i
                p = førstePostorden(forelder.høyre); //førstenpostoden i subtreet med foreldrens noden sin høyre som rot
            }
        }
        return p;


        //nestePostorden skal returnere den noden som kommer etter p i postorden

        //throw new UnsupportedOperationException("Ikke kodet ennå!");

    }

    public void postorden(Oppgave<? super T> oppgave) {

        //Oppgave kan for eksempel være skriv til skjerm, og da vil denne metoden skrive ut treet i post orden.
        //viktig! ->//Du skal implementere den første funksjonen uten bruk av rekursjon og uten bruk av hjelpevariabler som stack / queue.

        //tankegang:
        // 1) Start med å finne den første noden p i postorden. - rot noden
        // 2) Deretter vil (f.eks. i en while-løkke) setningen: p = nestePostorden(p); gi den neste. Osv. til p blir null
        // 3) Du skal bruke funksjonen nestePostorden fra forrige oppgave.

        //Målet med oppgaven er å traversere treet i post orden rekkefølge og skrive ut verdiene til nodene vi traverserer
        //vi definerer nodene som blir passert som node p og traverser treet og skriver ut verdiene i postorden rekkefølgen helt til den siste noden p vi traverserer har null verdi


        Node<T> p = førstePostorden(rot); // Starter med å finne den første noden p i postorden

        while (!(p == null)) { // bruker en while løkke for å definere at så lenge rot noden p ikke er tom så kjøres:

            oppgave.utførOppgave(p.verdi); // Interface oppgaven som kaller på den abstracte metoden utføroppgave som skal skrive ut verdiene som blir traversert i postorden rekkefølge til skjerm
            p = nestePostorden(p); // nestePostorden som returnere den noden som kommer etter p i postorden

            //  throw new UnsupportedOperationException("Ikke kodet ennå!");
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) { // Er den offentlige metoden som kaller hjelpemetoden
        postordenRecursive(rot, oppgave); //sjekker om treet vil være tomt - (vis da rot veriden p er tom)
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        //For den rekursive metoden skal du lage et rekursivt kall som traverserer treet i postorden rekkefølge.

        // Tar ibruk programkode 5.1.7 a) fra kompendie for å implementere en privat hjelpemetode som utfører rekursjonen
        //kaller på metoden postoderdenrecursive i stedet for pre orden metoden som blir gjort i (5.1.7 a fra kompendie)
        //postoderdenrecursive blir da koblet til Interfacet oppgave som kaller på den abstracte metoden - utføroppgave

        if (p.venstre != null) {//hvis venstre node verdi til (p-rot), noden ikke er null
            postordenRecursive(p.venstre, oppgave);  //, så vil vi i en postorden rekkefølge traverere treet fra venstre barn node

        }
        if (p.høyre != null) { //hvis høyre node verdi til p-rot ikke er null, så
            postordenRecursive(p.høyre, oppgave);  //så skal vi traversere videre til høyre barn

        }
        oppgave.utførOppgave(p.verdi);  // Interface oppgaven kaller på den abstracte metoden utføroppgave som skal skrive ut verdiene som blir traversert i postorden rekkefølge til skjerm

        // throw new UnsupportedOperationException("Ikke kodet ennå!");

    }


    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
