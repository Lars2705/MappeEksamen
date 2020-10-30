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


    public boolean leggInn(T verdi) {

        //Programkoden er hentet fra kompendie 5.2 3 a) og har lagt til forldee referanse

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

        p = new Node<>(verdi, q);  // oppretter en ny node
        //referer til at q er forelder til p

        if (q == null) {
            rot = p;                  // p blir rotnode

        } else if (compare < 0) {
            q.venstre = p;         // venstre barn til q

        } else {
            q.høyre = p;                        // høyre barn til q

        }

        antall++;                                // én verdi mer i treet - antall dubplikater av samme verdi i treet
        endringer++;
        return true;                             // vellykket innlegging

    }


    public boolean fjern(T verdi) {

        //Henter programkode 5.2.8 d) fra kompendie
        if (verdi == null) {
            return false;
        } // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p


        while (p != null)   // leter etter at noden inneholder en verdi
        {
            int compare = comp.compare(verdi, p.verdi);  // sammenlinger verdi som settes inn med p.node verdi

            if (compare < 0) { //går til venstre - (compare value = -1) og venstre barne node får refereansen node p
                q = p;
                p = p.venstre;

            } else if (compare > 0) { //går til høyre - (compare value = 1) og høyre barne node for referansen p
                q = p;
                p = p.høyre;

            } else  //Hvis verdien ligger i p, så vi går ut av while løkken
                break;
        }


        if (p == null) { //vi finner ikke verdi
            return false;
        }

        //hvis p ikke har barn eller har nøyaktig 1 barn
        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2) -
        {

            Node<T> b; //lager barn noden b

            if (p.venstre != null) {  // Hvis p har venstre barn, så får barne node b referansen til p sin venstre node
                b = p.venstre;

            } else {  //Hvis p har høyre barn, så får node b referanse til p sin høyre
                b = p.høyre;
            }

            if (b != null) {  //setter pekeren fra barne noden b til foreldre noden q hvis node b ikke er tom
                b.forelder = q;
            }

            if (p == rot) { //hvis p er rot noden så settes rotreferansen til b lik( enten er p.venstre, p.høyre eller null)
                rot = b;

            } else if (p == q.venstre) { // Hvis p er venstre barnet til q så settes q.venstre som forelder til b
                q.venstre = b; //


            } else { //Hvis p ikke er venstre barnet til q //så settes q.høyre som forelder til b
                q.høyre = b;
            }


        } else { // Tilfelle 3) - p har 2 barn

            Node<T> s = p, r = p.høyre;   // finner neste i inorden

            while (r.venstre != null) { // traverser ned node r sin venstre side

                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p


            if (r.høyre != null) { // foreldre noden til r.høyre node for referansen til node s
                r.høyre.forelder = s;
            }


            if (s != p) {   // Hvis s ikke peker på p så er sin venstre peker over til r sitt høyre barn
                s.venstre = r.høyre;


            } else { // hvis s peker på p så er foreldre noden s.høyre node verdi lik node r.høyre sin verdi
                s.høyre = r.høyre; //

            }
        }

        antall--;   // det er nå én node mindre i treet
        endringer++; // Øker med antall endringer
        return true;

    }

    public int fjernAlle(T verdi) {
        //Tar ibruk programkode - 5.2.8 oppgave 5 fra kompendie for fjernalle metoden


        if (tom()) {
            return 0;
        }

        int AntallSomBleFjernet = 0; //hjelpevariabel

        while (fjern(verdi)) { // fjerner alle forekomstene av (verdi) i treet.

            AntallSomBleFjernet++;

        }
        return AntallSomBleFjernet; // Returnerer antallet verdier som ble fjernet.

    }

    public int antall(T verdi) {
        //Har tatt ibruk programkode - oppgave 5.2.6 oppgave 2 fra kompendie.

        Node<T> p = rot; // P er rot noden

        int antallLike_NodeVerdier = 0; // Hjelpevariabel

        while (!(p == null)) {
            int compare = comp.compare(verdi, p.verdi); //comparerator som sammenlinger current verdi med p nodens verdi

            if (compare < 0) { //(compare value = -1), traverser og sammenling med verdier til venstre
                p = p.venstre;

            } else if (compare > 0) {  //compare value = 1, traverserer og sammenling med verdier til høyre
                p = p.høyre;

            } else { //(compare value = 0)

                antallLike_NodeVerdier++; //øker for hver node som har lik verdi

                //setter like verdier til høyre for noden
                p = p.høyre;
            }
        }

        return antallLike_NodeVerdier; //returnere antall like forekomster av verdier i treet.
    }

    public void nullstill() {

        //Har tatt utgangspunkt i programkode - oppgave 5 fra 5.2.8 fra kompendie for nulstill metoden

        Node<T> p = rot; //definerer rot noden lik node p

        if (!tom()) { // kaller på metoden slett rekursivt hvis treet ikke er tomt
            slett(p);

            rot = null; //setter rot noden sin verdi lik 0
            antall = 0; //setter antall noder lik 0

        }

    }

    private static <T> void slett(Node<T> Node_p) {

        //Traverser høyre subtree og setter verdienene som er til høyre/venstre for rotnoden lik null
        if (Node_p.høyre != null) {
            slett(Node_p.høyre);
            Node_p.høyre = null;
        }

        //Traverser venstre subtree og sett verdienene som er til venstre for rotnoden lik null
        if (Node_p.venstre != null) {
            slett(Node_p.venstre);
            Node_p.venstre = null;
        }

        Node_p.verdi = null; //setter  p noden lik null
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        //tatt utganspunkt i programkode 5.1.7 h) fra kompendie


        if (p == null) { // rot noden er null, som betyr at treet ikke eksisterer

            throw new NoSuchElementException("Da er hele treet tomt");
        }
        while (true) {

            if (p.venstre != null) {
                p = p.venstre;      // node p traverserer venstre subtree for foreldre noden

            } else if (p.høyre != null) { // Høyre barne node til foreldrenoden ikke er tom
                p = p.høyre;             // node p  høyre subtree for foreldre noden

            } else {
                return p; // returneres første node post orden med (p) som rotnoden tilbake.
            }
        }

    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        //Tenker å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 h) - om postorden:

        Node<T> parent = p.forelder; // parent noden er foreldre noden til p

        if (p.forelder == null) { //hvis p ikke har forelder så eksiterer ikke barne noden
            p = null; //

        } else if (p == parent.høyre) {  // p er høyre barn til sin forelder
            p = parent;

        } else if (parent.venstre == p) { // p er venstre barn til foreldre noden

            if (parent.høyre == null) { // foreldre den neste node for p, Siden høyre noden for foreldre noden er tom
                p = parent;

            } else { //Hvis foreldre noden har en høyre node, så er den neste vi traverserer til altså noden som kommer først i postorden
                p = førstePostorden(parent.høyre);
            }

        }
        return p;  //returner den neste noden i postorden

    }

    public void postorden(Oppgave<? super T> oppgave) {

        //tatt ibruk progrmakode 5.1.7 - oppgave 7

        Node<T> p = førstePostorden(rot);// finner den første noden p i postorden

        while (!(p == null)) {

            oppgave.utførOppgave(p.verdi); //Kobler til interface og skrivet ut verdiene som blir traversert i postorden rekkefølge til skjerm
            p = nestePostorden(p); // nestePostorden returnere den noden som kommer etter p i postorden
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave); //sjekker om treet vil være tomt
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        // Tar ibruk programkode 5.1.7 a) fra kompendie

        if (p.venstre != null) { // sender verdiene til de nodene som blir traversert fra venstre subtree til oppgave interfacet
            postordenRecursive(p.venstre, oppgave);
        }

        if (p.høyre != null) { //sender verdiene til de nodene som blir traversert videre til høyre subtree til oppgave interfacet
            postordenRecursive(p.høyre, oppgave);

        }
        oppgave.utførOppgave(p.verdi);  // skal skrive ut verdiene som blir traversert i postorden rekkefølge til skjerm


    }

    public ArrayList<T> serialize() {

        //tar ibruk programkode 5.1.6 a) fra kompendie

        ArrayList<T> verdier_til_Liste = new ArrayList<>(); //laget en arraylist

        Queue<Node<T>> queue = new LinkedList<>();   // laget en kø i lenkekt Liste form
        queue.add(rot);                             // legger inn rot vedien inn i køen til linkedList

        while (!queue.isEmpty()) {                   // så lenge køen ikke er tom

            Node<T> p = queue.remove();             // tar ut verdiene fra køen
            verdier_til_Liste.add(p.verdi);         //legger inn verdiene i arraylisten

            if (p.venstre != null) { //legger inn verdi for nodene som blir traversert i venstre subtree hvis venstre p.node ikke er null
                queue.add(p.venstre);
            }

            if (p.høyre != null) { //legger inn verdi for nodene som blir traversert i høyre subtree hvis høyre p.node ikke er null
                queue.add(p.høyre);
            }
        }
        return verdier_til_Liste; //returner verdiene i alle nodene i nivå orden til arraylisten

    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        //har tatt ibruk programkode 5.2.3 c fra kompendie
        {
            EksamenSBinTre<K> tre = new EksamenSBinTre<>(c); //Tar ibruk EksamenSBinTre som Constructior
            data.forEach(tre::leggInn);   //bygger opp et tre
            return tre;                   //returnere tree
        }


    }


} // ObligSBinTre
