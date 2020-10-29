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

        //Programkoden er hentet fra kompendie 5.2 3 a) og har lagt til forlde referanse

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

        // treet har ingen nullverdier
        if (verdi == null) {
            return false;
        }


        // q skal være forelder til p
        Node<T> p = rot, q = null;


        // leter etter at noden inneholder en verdi
        while (p != null) {
            // sammenlinger verdi som settes inn med p.node verdi
            int compare = comp.compare(verdi, p.verdi);

            //går til venstre
            if (compare < 0) {
                q = p; // q noden blir rot verdien og videre forelder til p
                p = p.venstre; // venstre barne node får refereansen p

                //går til høyre
            } else if (compare > 0) {
                q = p;  //q noden blir rot verdien og videre forelder til p
                p = p.høyre; // og høyre barne node for referansen p


                //den søkte verdien ligger i p så vi går ut av while loopen
            } else
                break;
        }

        //vi finner ikke verdi
        if (p == null) {
            return false;
        }


        //hvis p ikke har barn eller har nøyaktig 1 barn
        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b; //lager barn noden b

            // Hvis p har venstre barn, så får barne node b referansen til p sin venstre node
            if (p.venstre != null) {
                b = p.venstre;


            } else {
                b = p.høyre; // p sin høyre node og får nodereferanse b
            }

            if (b != null) {
                b.forelder = q; //setter pekeren fra barne noden b til foreldre noden q
            }

            if (p == rot) { //hvis p er rot noden
                rot = b;  // settes rotreferansen til b som enten er p.venstre, p.høyre eller null

            } else if (p == q.venstre) { // Hvis p er venstre barnet til q
                q.venstre = b; //så settes q.venstre som forelder til b

            } else { // hvis p ikke er venstre barnet til q
                q.høyre = b; //så settes q.høyre som forelder til b
            }


        } else { // Tilfelle 3) -  // Hvis p har 2 barn

            Node<T> s = p, r = p.høyre;   // finner neste i inorden

            // traverser ned r sin venstre side
            while (r.venstre != null) {

                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            //hvis r høyre-node verdi ikke er null og inneholder en verdi
            if (r.høyre != null) {
                r.høyre.forelder = s; // foreldre noden til r.høyre node være s
            }


            //Hvis s ikke peker på p
            if (s != p) {
                s.venstre = r.høyre; // Setter S sin venstre peker over til r sitt høyre barn

                //hvis s peker på p
            } else {
                s.høyre = r.høyre; // foreldre noden s.høyre node verdi lik node r.høyre sin verdi


            }
        }

        antall--;   // det er nå én node mindre i treet
        endringer++; // Øker med antall endringer
        return true;

        //throw new UnsupportedOperationException("Ikke kodet ennå!");

    }

    public int fjernAlle(T verdi) {
        //Tar ibruk programkode - 5.2.8 oppgave 5 fra kompendie for fjernalle metoden

        if (tom()) { //Hvis treet er tomt, skal 0 returneres.
            return 0;
        }

        int AntallSomBleFjernet = 0; //hjelpevariabel

        while (fjern(verdi)) { // Den skal fjerne alle forekomstene av (verdi) i treet.

            AntallSomBleFjernet++;  // Metoden skal returnere antallet som ble fjernet.

        }
        return AntallSomBleFjernet;
        //hrow new UnsupportedOperationException("Ikke kodet ennå!");

    }

    public int antall(T verdi) {

        //Har tatt ibruk programkode - oppgave 5.2.6 oppgave 2 fra kompendie.

        Node<T> p = rot; //definerer p som rot noden

        int antallLike_NodeVerdier = 0; //hjelpevaribael

        //hvis p - rot noden ikke er tom så comparer verdi
        while (!(p == null)) {
            int compare = comp.compare(verdi, p.verdi);

            //compare value = -1 - sammenligner på venstre side
            if (compare < 0) {
                p = p.venstre;

                //compare value = 1 - sammenlinger på høyre side
            } else if (compare > 0) {
                p = p.høyre;

            } else {
                //(compare value = 0), så plusser da for hver node som har lik verdi
                antallLike_NodeVerdier++;

                //setter den like nodeverdien til høyre for forldrenoden vi er på (P)
                p = p.høyre;
            }
        }
        return antallLike_NodeVerdier; //returnere antall like forekomster av verdier i treet.
    }

    public void nullstill() {

        //Har tatt utgangspunkt i programkode - oppgave 5 fra 5.2.8 fra kompendie

        Node<T> p = rot; //definerer rot noden lik node p

        //hvis noden ikke er tom
        if (!tom()) {

            //sender noden p igjennom hjelpetoden for å slette verdiene i treet og kaller på metoden slett rekursivt
            slett(p);

            rot = null; //setter rot noden sin verdi lik 0
            antall = 0; //setter antall noder lik 0

        }

    }

    private static <T> void slett(Node<T> Node_p) {

        //Høyre subtree blir fjernet fra rotnoden
        if (Node_p.høyre != null) { //hvis høyre peker node fra rot noden ikke er null
            slett(Node_p.høyre); //sletter subtreet på høyre side av rotnoden
            Node_p.høyre = null; //setter høyre node verdi her lik 0
        }

        //venster subtree blir fjernet fra rotnoden
        if (Node_p.venstre != null) { //hvis venstre peker node fra rot noden ikke er null
            slett(Node_p.venstre); //slettet subtreet på venstre side av rotnoden
            Node_p.venstre = null; //setter venstre node verdi her lik null
        }

        Node_p.verdi = null; //setter verdien i selve p noden lik null
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        //tatt utganspunkt i programkode 5.1.7 h) fra kompendie


        // hvis p noden er tom så kast da en exception
        if (p == null) {

            throw new NoSuchElementException("Da er hele treet tomt");
        }

        while (true) {
            if (p.venstre != null) {

                //  p.venstre barne node til venstre for foreldre noden
                p = p.venstre;

            } else if (p.høyre != null) {

                // p.høyre barne node til høyre for foreldre noden
                p = p.høyre; //

                // venstre og høyre barne node er tom
            } else {
                return p; // returneres første node post orden med (p) som rotnoden tilbake.
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        //Tenker å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 h) - om postorden:

        // parent noden er foreldre noden til p
        Node<T> parent = p.forelder;

        //hvis p ikke har forelder da har ikke p en neste og vi returner null
        if (p.forelder == null) {
            p = null;

            //hvis p er høyre barn til sin forelder så er forelder den neste
        } else if (p == parent.høyre) {
            p = parent;

            //hvis p er venstre barn til foreldre noden
        } else if (parent.venstre == p) {

            // hvis høyre node til foreldre noden er tom så er  foreldre den neste node for p.
            if (parent.høyre == null) {
                p = parent;

            } else {

                // setter node p lik den neste noden i høyre subtreet for foreldrenoden i førstenpostoden
                p = førstePostorden(parent.høyre);
            }
        }

        //returner den neste noden i postorden
        return p;


    }

    public void postorden(Oppgave<? super T> oppgave) {

        //tatt ibruk progrmakode 5.1.7 - oppgave 7

        // Starter med å finne den første noden p i postorden
        Node<T> p = førstePostorden(rot);

        while (!(p == null)) {

            // Interface oppgaven som kaller på den abstracte metoden utføroppgave
            oppgave.utførOppgave(p.verdi);

            // Returnerer den noden som kommer etter p i postorden
            p = nestePostorden(p);


        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {

        //sjekker om treet vil være tomt
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        // Tar ibruk programkode 5.1.7 a) fra kompendie

        // traverere treet fra venstre subtree
        if (p.venstre != null) {
            postordenRecursive(p.venstre, oppgave);
        }

        // traversere videre til høyre subtree
        if (p.høyre != null) {
            postordenRecursive(p.høyre, oppgave);

        }
        // skriver ut verdiene som blir traversert i postorden rekkefølge til skjerm
        oppgave.utførOppgave(p.verdi);


    }

    public ArrayList<T> serialize() {

        //tar ibruk programkode 5.1.6 a) fra kompendie

        ArrayList<T> verdier_til_Liste = new ArrayList<>(); //laget en arraylist

        Queue<Node<T>> queue = new LinkedList<>();   // laget en kø i lenkekt Liste form
        queue.add(rot);                             // legger inn rot vedien inn i køen til linkedList

        while (!queue.isEmpty()) {                   // så lenge køen ikke er tom

            Node<T> p = queue.remove();             // tar ut fra køen
            verdier_til_Liste.add(p.verdi);         //legger inn verdi

            //legger inn i kø de verdiene for nodene som blir traversert i venstre subtree
            if (p.venstre != null) {
                queue.add(p.venstre);
            }

            //legger inn i kø de verdiene for nodene som blir traversert i høyre subtree
            if (p.høyre != null) {
                queue.add(p.høyre);
            }
        }

        return verdier_til_Liste; //returner listen med verdiene

    }


    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        //har tatt ibruk programkode 5.2.3 c fra kompendie


        {
            EksamenSBinTre<K> tre = new EksamenSBinTre<>(c); //Tar ibruk EksamenSBinTre som Constructøt
            data.forEach(tre::leggInn);   //bygger opp treet i nivåorden
            return tre;                   //returnerer tree
        }


    }


} // ObligSBinTre
