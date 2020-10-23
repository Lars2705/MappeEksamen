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

    public boolean inneholder(T verdi) {
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

    //Kildekoden er hentet fra kompendie Programkode 5.2 3 a) og har lagt til forlder noden nå er q
    public boolean leggInn(T verdi) {
            Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

            Node<T> p = rot, q = null;               // p starter i roten
            int cmp = 0;                             // hjelpevariabel

            while (p != null)       // fortsetter til p er ute av treet
            {
                q = p;                                 // q er forelder til p
                cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
                p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
            }

            // p er nå null, dvs. ute av treet, q er den siste vi passerte

            p = new Node<>(verdi,null,null,q);                  // oppretter en ny node
                                                       //referer til at q er forelder i hver node som oprettes

            if (q == null) rot = p;                  // p blir rotnode
            else if (cmp < 0) q.venstre = p;         // venstre barn til q
            else q.høyre = p;                        // høyre barn til q

            antall++;                                // én verdi mer i treet
            return true;                             // vellykket innlegging
        }


    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        //har tatt ibruk kildekoden fra kompendie. Innenfor ukeoppgave 9 oppgave 5.2.6 b)
        Node<T> p = rot; //definerer p som rot noden - altså første noden

        int antallLike_verider = 0; //definerer variabel som skal plusses for hver like node verdi

        while (p != null) //hvis p - rot noden ikke er tom
        {
            int cmp = comp.compare(verdi,p.verdi); //hvis verdien somn sammenlinges med p rot node verdi er mindre
            if (cmp < 0) p = p.venstre; //så settes verdien inn som venstre node
            else if(cmp>0){ //hvis nåværende verdi som sammenlignes med p rot verdien er større
                p = p.høyre; //så settes verdien inn som høyre node

            }else{ //hvis compare verdiene er like for den nåværende verdien vi sammenlinger med og verdien i p noden

               antallLike_verider++; //Det er tillatt med duplikater og det betyr at en verdi kan forekomme flere ganger.
               //vi plusser da for hver node som har lik verdi
                p=p.høyre; //setter den like nodeverdien til høyre for forldrenoden vi er på (P)
            }
        }
        return antallLike_verider; //returnere antall forekomster av verdi i treet.
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        //tatt utgangspunkt i kildekoden 5.1.7 h) fra kompendie

        /*
      public T førstPostorden()
  {
    if (tom()) throw new NoSuchElementException("Treet er tomt!");
    Node<T> p = rot;
    while (true)
    {
      if (p.venstre != null) p = p.venstre;
      else if (p.høyre != null) p = p.høyre;
      else return p.verdi;
    }
  }
         */
        //p blir definert som rotnoden q

        if(p==null){ // hvis p noden er tom

            throw new NoSuchElementException("Da er hele treet tomt"); //vi har da ikke et tree fordi for oss er:
                                                                      // P = rotnoden som vil si at treet er tomt

        }
        while(true){
            if(p.venstre != null){ // definerer at hvis venstre noden til foreldrenoden ikke er tom
                p = p.venstre; // så er p = venstre noden til foreldre noden

            }else if(p.høyre != null){ // eller hvis høyre noden til foreldrenoden ikke er tom
                p = p.høyre; //så setter vi p = foreldrenoden sin høyre node
            }else{ //
                return p; //eller hvis venstre noden eller høyre noden er null - tom så returneres kun rotnoden tilbake som her er (p)
            }
        }





     //   throw new UnsupportedOperationException("Ikke kodet ennå!");

    }

    private static <T> Node<T> nestePostorden(Node<T> p) {


       //Tenker å ta utganspunkt i fremgangsmåten postorden fra kompendie under 5.1.7
        /*
        Postorden:

Hvis p ikke har en forelder , så er p den siste i postorden.
Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
Hvis p er venstre barn til sin forelder f, gjelder:
Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
Hvis p ikke er enebarn (dvs. f.høyre er ikke null),
 så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.
         */

        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {



        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
