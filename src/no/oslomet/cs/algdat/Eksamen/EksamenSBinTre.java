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

        p = new Node<>(verdi, q);  // oppretter en ny node  - tar ibruk konstruktøren private Node(T verdi, Node<T> forelder)
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


        //Henter programkode 5.2.8 d) fra kompendie
        if (verdi == null) {
            return false;
        } // treet har ingen nullverdier


        Node<T> p = rot, q = null;   // q skal være forelder til p
        // q blir får foreldre referanse


        while (p != null)   // leter etter at noden inneholder en verdi
        {
            int compare = comp.compare(verdi, p.verdi); // sammenlinger verdi som settes inn med p.node verdi

            //går til venstre
            if (compare < 0) { //hvis compare verdien er mindre = -1
                q = p; // q noden blir rot verdien og videre forelder til p
                p = p.venstre; // venstre barne node får refereansen node p

                //går til høyre
            } else if (compare > 0) { //hvis compare verdieen er større = 1
                q = p;  //q noden blir rot verdien og videre forelder til p
                p = p.høyre; // og høyre barne node for referansen p


            } else //den søkte verdien ligger i p så vi går ut aa while loopen
                break;    // hvis verdien vi comparer med p noden er lik (0) så har vi funnet den noden p med lik verdi
        }

        if (p == null) { // hvis vi ikke finner ikke verdi
            return false;   // så retunrer vi false
        }


        //hvis p ikke har barn eller har nøyaktig 1 barn
        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b; //lager barn noden b

            if (p.venstre != null) { // Hvis p har venstre barn, så får barne node b referansen til p sin venstre node
                b = p.venstre;

            } else { //så settes barne noden b sin referanse til p sin høyre node og blir høyre barmet til p
                b = p.høyre;
            }

            if (b != null) { //hvis da b node verdi ikke er tom
                b.forelder = q; // så setter vi pekeren fra barne noden b til foreldre noden q
            }                   //noden q blir foreldre til barne noden b

            if (p == rot) { //hvis p er rot noden
                rot = b;  //så settes rotreferansen til b som enten er p.venstre, p.høyre eller null

            } else if (p == q.venstre) { // Hvis p er venstre barnet til q
                q.venstre = b; //så settes q.venstre som forelder til b

            } else { //Derimot hvis p ikke er venstre barnet til q
                q.høyre = b; //så settes q.høyre som forelder til b
            }

            // Hvis p har 2 barn
        } else { // Tilfelle 3) //hvis p.venstre og p.høyre ikke er null etter fjern

            Node<T> s = p, r = p.høyre;   // finner neste i inorden

            // traverser ned r sin venstre side
            while (r.venstre != null) {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (r.høyre != null) { //hvis r høyre-node verdi ikke er null og inneholder en verdi
                r.høyre.forelder = s; // så må foreldre noden til r.høyre node være s
            }


            if (s != p) {  // Hvis s ikke peker på p
                s.venstre = r.høyre; // så Setter S sin venstre peker over til r sitt høyre barn


            } else { //Eller hvis s peker på p
                s.høyre = r.høyre; // så setter vi foreldre noden s.høyre node verdi lik node r.høyre sin verdi


            }
        }

        antall--;   // det er nå én node mindre i treet
        endringer++; // Øker med antall endringer
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

        int AntallSomBleFjernet = 0; //hjelpevariabel

        while (fjern(verdi)) { // Den skal fjerne alle forekomstene av (verdi) i treet.

            AntallSomBleFjernet++;  // Metoden skal returnere antallet som ble fjernet.

        }
        return AntallSomBleFjernet;
        //hrow new UnsupportedOperationException("Ikke kodet ennå!");

    }

    public int antall(T verdi) {

        //Har tatt ibruk programkode - oppgave 5.2.6 oppgave 2 fra kompendie.

        Node<T> p = rot; //definerer p som rot noden - altså første noden

        int antallLike_NodeVerdier = 0; //definerer variabel som skal plusses for hver like node verdi

        while (!(p == null)) { //hvis p - rot noden ikke er tom
            int compare = comp.compare(verdi, p.verdi); //comparer verdiene vi har med p rot node verdien - Altså om verdiene vi har er mindre større eller lik p rot verdi

            if (compare < 0) {  // //hvis verdien somn sammenlinges med p node verdi er mindre =(compare value = -1)
                p = p.venstre; //så traverser vi ned venstre subtree for p noden og setter den current verdien som høyre node

            } else if (compare > 0) { //hvis nåværende verdi som sammenlignes med p rot verdien er større (compare value = 1)
                p = p.høyre; //så traverser vi ned høyre subtree for p noden og setter den current verdien som høyre node
            } else { //hvis compare verdiene er like for den nåværende verdien vi sammenlinger med verdien i p noden (compare value = 0)

                antallLike_NodeVerdier++; //Det er tillatt med duplikater og det betyr at en verdi kan forekomme flere ganger.
                //vi plusser da for hver node som har lik verdi


                p = p.høyre; //setter den like nodeverdien til høyre for forldrenoden vi er på (P)
            }
        }
        return antallLike_NodeVerdier; //returnere antall like forekomster av verdier i treet.
    }

    public void nullstill() {

        //Har tatt utgangspunkt i programkode - oppgave 5 fra 5.2.8 fra kompendie for nulstill metoden

        //Lag så metoden public void nullstill().
        // Den skal traversere rekursivt treet i en eller annen rekkefølge og sørge for at samtlige
        //pekere og nodeverdier i treet blir nullet.
        // Det er med andre ord ikke tilstrekkelig å sette rot til null og antall til 0


        Node<T> p = rot; //definerer rot noden lik node p

        if (!tom()) { //hvis noden ikke er tom
            slett(p); //så sender vi rot noden p igjennom hjelpetoden for å slette verdiene i treet, og kaller på metoden slett rekursivt
            //Viktig å huske at jeg traverserr treet i det requsive kallet i postoden rekkefølge som betyr at rotnoden er den siste verdien som blir nullstilt

            rot = null; //setter rot noden sin verdi lik 0
            antall = 0; //setter antall noder lik 0

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

        //tatt utganspunkt i programkode 5.1.7 h) fra kompendie

        //    Tankegangen min for å løse oppgaven var ifølge oppgavetesksten:
        //  1) Start med å finne den første noden p i postorden. - rot noden
        //  2) Deretter vil (f.eks. i en while-løkke) setningen: p = nestePostorden(p); gi den neste. Osv. til p blir null
        //  3) Du skal bruke funksjonen nestePostorden fra forrige oppgave.

        if (p == null) { // hvis p noden er tom
            //vis rotnoden er tom så har (ikke) venstre eller høyre peker en foreldre node, noe som betyr at treet ikke eksisterer

            throw new NoSuchElementException("Da er hele treet tomt");  //kaster da en exception og sier at hele treet er tomt
        }

        while (true) {

            if (p.venstre != null) { // at hvis venstre barne node til foreldrenoden ikke er tom
                p = p.venstre; // så er p venstre barne node til venstre subtree for foreldre noden

            } else if (p.høyre != null) { // eller hvis høyre barne node til foreldrenoden ikke er tom
                p = p.høyre; //så setter vi p høyre barne node til høyre subtree for foreldre noden

            } else {
                return p; //eller hvis både venstre og høyre barne barne node er tom, så returneres første node post orden med (p) som rotnoden tilbake.
            }
        }

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        //Tenker å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 h) - om postorden:

        Node<T> parent = p.forelder; //definerer at parent noden er foreldre noden til p

        if (p.forelder == null) { //hvis p ikke har forelder
            p = null; // da har ikke p en neste og vi returner null

        } else if (p == parent.høyre) { //hvis p er høyre barn til sin forelder
            p = parent; // så er forelder den neste

        } else if (parent.venstre == p) { //else hvis p er venstre barn til foreldre noden

            if (parent.høyre == null) { // og hvis høyre node til foreldre noden er tom, så har vi kun en venstre node for p
                p = parent; // er foreldre den neste node for p. Siden høyre noden for foreldre noden er tom

            } else { //derimot hvis foreldre noden i tilegg har en høyre node så er den neste noden vi traverserer altså neste noden i postorden
                p = førstePostorden(parent.høyre); //setter node p lik den neste noden i høyre subtreet for foreldrenoden i førstenpostoden
            }
        }
        return p;  //returner den neste noden i postorden

        //throw new UnsupportedOperationException("Ikke kodet ennå!");

    }

    public void postorden(Oppgave<? super T> oppgave) {

        //tatt ibruk progrmakode 5.1.7 - oppgave 7

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

        // Tar ibruk programkode 5.1.7 a) fra kompendie

        if (p.venstre != null) {//hvis venstre node verdi til p noden ikke er null
            postordenRecursive(p.venstre, oppgave);  // så vil vi i en postorden rekkefølge traverere treet fra venstre subtree og sende node verdiene til de nodene som blir traversert til oppgave interfacet
        }

        if (p.høyre != null) { //hvis høyre node verdi til p noden ikke er null, så
            postordenRecursive(p.høyre, oppgave);  //så skal vi traversere videre til høyre subtree og sende node verdiene til de nodene som blir traversert til oppgave interfacet

        }
        oppgave.utførOppgave(p.verdi);  // Interface oppgaven kaller på den abstracte metoden utføroppgave som skal skrive ut verdiene som blir traversert i postorden rekkefølge til skjerm

        // throw new UnsupportedOperationException("Ikke kodet ennå!");

    }

    public ArrayList<T> serialize() {

        //tar ibruk programkode 5.1.6 a) fra kompendie for å lagre fra arraylist til fil verdi  - NivOrden

        ArrayList<T> verdier_til_Liste = new ArrayList<>(); //laget en arraylist

        Queue<Node<T>> queue = new LinkedList<>();   // laget en kø i lenkekt Liste form
        queue.add(rot);                             // legger inn rot vedien inn i køen til linkedList

        while (!queue.isEmpty()) {                   // så lenge køen ikke er tom så så kan vi ta verdiene ut fra køen og legge inn i arraylisten

            Node<T> p = queue.remove();             // tar ut fra køen
            verdier_til_Liste.add(p.verdi);         //legger inn verdi for p noden og adder det i arraylisten

            if (p.venstre != null) {//hvis p.venstre node ikke er lik null så legger vi de verdiene fra subtreet inn i køen linkedlist
                queue.add(p.venstre); //legger inn verdi for nodene som blir traversert i venstre subtree og addere det i lindeklist i nivåorden rekkefølge
            }

            if (p.høyre != null) { //hvis p.høyre node ikke er lik null så legger vi de verdiene fra subtreet inn i køen linkedlist
                queue.add(p.høyre); //legger inn verdi for nodene som blir traversert i venstre subtree og addere det i lindeklist i nivåorden rekkefølge
            }
        }

        return verdier_til_Liste; //returner listen med verdiene som skal inneholde verdiene i alle nodene i nivå orden til arraylisten

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        //har tatt ibruk programkode 5.2.3 c fra kompendie

        // 1) Deserialize skal da ta dette arrayet,
        // 2)legge inn alle verdiene (igjen i nivå orden),
        // 3) Å dermed gjenskape treet.

        {
            EksamenSBinTre<K> tre = new EksamenSBinTre<>(c); //Tar ibruk EksamenSBinTre som Constructior som tar inn komparatoren c for typen T.
            data.forEach(tre::leggInn);   //bygger opp et tre ved å hente én og én verdi fra arraylisten og bygger opp treet i nivåorden
            return tre;                   //returnere tree
        }

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
