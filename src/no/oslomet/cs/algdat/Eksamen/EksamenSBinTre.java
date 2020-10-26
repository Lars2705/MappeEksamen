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


    //Programkoden er hentet fra kompendie 5.2 3 a) og har lagt til forldee referanse

    public boolean leggInn(T verdi) {

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


        // Forelder må få riktig verdi ved hver innlegging, men forelder skal være null i rotnoden.
        // Lag metoden public boolean leggInn(T verdi). Der kan du kopiere Programkode 5.2 3 a),
        // men i tillegg må du gjøre de endringene som trengs for at referansen forelder får korrekt verdi i hver node.
        // Teknikken med en forelder-referanse brukes f.eks. i klassen TreeSet i java.util.
        // Sjekk at følgende kode er feilfri (ikke kaster noen unntak):
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
            if (b != null) { //hvis venstre barne node verdi ikke er tom etter en fjerning
                b.forelder = q; // så setter setter vi pekeren fra barne noden b til foreldre noden q
            }                   //foreldre noden q for lik verdi som barne noden b

            if (p == rot) {
                rot = b;

            } else if (p == q.venstre) {
                q.venstre = b;

            } else {
                q.høyre = b;
            }

        } else { // Tilfelle 3) //hvis p.venstre og p.høyre ikke er null etter fjern

            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null) {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (r.høyre != null) { //hvis r høyre-node verdi ikke er null og inneholder en verdi
                r.høyre.forelder = s; // så må foreldre noden til r.høyre node være s
            }

            if (s != p) { //hvis noden p ikke er lik foreldre noden s
                s.venstre = null; //så setter vi at venstre peker noden til s må være null


            } else { //hvis foreldre noden S er lik p
                s.høyre = r.høyre; // så setter vi foreldre noden s.høyre node verdi lik node r.høyre sin verdi


            }
        }

        antall--;   // det er nå én node mindre i treet
        return true;

        //throw new UnsupportedOperationException("Ikke kodet ennå!");

    }

    public int fjernAlle(T verdi) {
        //Tar ibruk programkode - 5.2.8 oppgave 5 fra kompendie for fjernalle metoden

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

    public int antall(T verdi) {

        //Har tatt ibruk programkode - oppgave 5.2.6 oppgave 2 fra kompendie. Fant denne fra ukeoppgave 9

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

        // Lag kode for metoden public int antall(T verdi).
        // Den skal returnere antall forekomster av verdi i treet.
        // Det er tillatt med duplikater og det betyr at en verdi kan forekomme flere ganger.
        //Hvis verdi ikke er i treet (null er ikke i treet), skal metoden returnere 0.
        // Test koden din ved å lage trær der du legger inn flere like verdier.

    }

    public void nullstill() {

        //Har tatt utgangspunkt i programkode - oppgave 5 fra 5.2.8 fra kompendie for nulstill metoden

        //Lag så metoden public void nullstill().
        // Den skal traversere (rekursivt eller iterativt) treet i en eller annen rekkefølge og sørge for at samtlige
        //pekere og nodeverdier i treet blir nullet.
        // Det er med andre ord ikke tilstrekkelig å sette rot til null og antall til 0


        Node<T> p = rot; //definerer rot noden lik node p

        if (!tom()) { //hvis noden ikke er tom
            slett(p); //så sender vi rot noden p igjennom hjelpetoden for å slette verdiene i treet, og kaller på metoden slett rekursivt
            //Viktig å huske at jeg traverserr treet i det requsive kallet i postoden rekkefølge som betyr at rotnoden er den siste verdien som blir nullstilt

            rot = null; //setter rot noden sin verdi lik 0
            antall = 0;
        }

    }

    private static <T> void slett(Node<T> Node_p) {

        //Høyre subtree blir fjernet fra rotnoden
        if (Node_p.høyre != null) { //hvis høyre peker node fra rot noden ikke er null
            slett(Node_p.høyre); //setter høyre node barn til rotnoden inn i slett metoden slik at den nå er lik Node_p slik at vi kan slette subtreet på høyre side av rotnoden
            Node_p.høyre = null; //setter venstre node verdi her lik 0
        }

        //venster subtree blir fjernet fra rotnoden
        if (Node_p.venstre != null) { //hvis venstre peker node fra rot noden ikke er null
            slett(Node_p.venstre); //setter venstre node barn til rotnoden inn i slett metoden slik at den nå er lik Node_p slik at vi kan slette subtreet på venstre side av rotnoden
            Node_p.venstre = null; //setter høyre node verdi her lik null
        }

        Node_p.verdi = null; //setter verdien i selve p noden lik null
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        // Førstepostorden skal returnere første node post orden med p som rot

        //tatt utganspunkt i programkode 5.1.7 h) fra kompendie
        //(p blir definert som rotnoden q)

        if (p == null) { // hvis p noden er tom
            //vis rotnoden er tom så har (ikke) venstre eller høyre peker en foreldre node, noe som betur at treet ikke eksisterer

            throw new NoSuchElementException("Da er hele treet tomt");  //kaster da en exception og sier at hele treet er tomt
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

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //nestePostorden skal returnere den noden som kommer etter p i postorden

    private static <T> Node<T> nestePostorden(Node<T> p) {

        //Tenker å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 om postorden
        //Kilde: under 5.1.7 h - om Postorden: I kompendie defineres fordeldre noden som (f), men jeg valgte å definere den som parent

        Node<T> parent = p.forelder; //definerer at forelder er foreldre noden til p

        if (p.forelder == null) { //hvis p ikke har forelder
            p = null; // da har ikke p en neste og vi returner null

        } else if (p == parent.høyre) { //hvis p er høyre barn til sin forelder (q)
            p = parent; // så er forelder (q) den neste

        } else if (parent.venstre == p) { //else hvis p er det venstre barn til foreldre noden (q)

            if (parent.høyre == null) { // og hvis høyre noden til foreldre q er tom så har vi kun en venstre node for p
                p = parent; // da er foreldre den neste node for p. Siden høyre noden for q er tom

            } else { //derimot hvis foreldre noden i tilegg har en høyre node så det den neste den noden som kommer først i
                p = førstePostorden(parent.høyre); //førstenpostoden i subtreet med foreldrens noden sin høyre som rot
            }
        }
        return p;

        //throw new UnsupportedOperationException("Ikke kodet ennå!");

    }

    public void postorden(Oppgave<? super T> oppgave) {

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


        // Tar ibruk programkode 5.1.7 a) fra kompendie for å implementere en privat hjelpemetode som utfører rekursjonen

        //kaller på metoden postoderdenrecursive i stedet for pre-orden metoden som blir gjort i (5.1.7 a fra kompendie)
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

        //Metodene skal henholdsvis serialisere (lage et kompakt format egnet for lagring til f.eks. fil - array)
        // 1) Selve metoden serialize skal være iterativ
        // 2) må bruke en kø til å traversere treet i nivå orden.
        // 3) Arrayet som returneres av serialize skal inneholde verdiene i alle nodene i nivå orden.

        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        // 1) Deserialize skal da ta dette arrayet,
        // 2)legge inn alle verdiene (igjen i nivå orden),
        // 3) Å dermed gjenskape treet.

        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
