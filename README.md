# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Vi har brukt git til å dokumentere arbeidet vårt. Jeg har 16 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

Jeg har lagret den opprinnelige kildekoden ved bruk av github detoskope og pushet inn

*oppgave 0: Løste ved å implementere en Test_klasse for Mianmetoder.
            Kjørte deretter instanser av klassen EksamensBinTre som ble opgitt igjennom main metoden.
            Dette gjør jeg for å se at jeg fikk ut korrekt utskrift ifølge det oppgavetsksten ber om. 
            Gjorde deretter det samme for oppgave 1, 2 og 6. 

* Oppgave 1: Løste ved å implementere programkoden 5.2.3 a) som er hentet fra kompendie:
             Har videre lagt til korrekt foreldre referanse.
             Brukte de to nodereferanseere p og q. 
             Definerer p som barn og q som forelder. 
             P starter på rotnoden. 
             Ved bruk av compare(sammenlinger verdi med p noden sin verdi. 
             Ved bruk av compare har vi 3 mulige utfall.
             Enten -1,0 eller 1 som verdi.
             vis verdien vi sammenligner med er lavere enn node p.verdi(-1) så flytter vi peker p til venstre barn, og verdien settes som venstre barn til p noden.
             Vis compare verdien er større en node p.verdi(1) så skal vi flytte pekeren til høyre side av foreldre noden.
             Vis verdien vi sammenligner med p.verdi er lik, betyr dette at compare verdien er lik(0) - (altså lik) og da legges noden til høyre for p noden.
             Så referer vi til q skal være foreldre noden til p. 
             q noden som er forelder skal ligge et nivå over barne noden p.
             For å starte traversering så må vi traversere fra foreldre noden til barne node. 
             Hvis treet var tomt i utgangspunktet så lages da rotnoden.  
           

* Oppgave 2: Løse ved å ta ibruk programkode 5.2.6 oppgave 2) fra kompendie: 
             Definerte først node p som rot noden. 
             Definerte så variabelelen antallLike_NodeVerdier som skal plusses for hver en node med lik verdi traverseres igjennom treet.
             Brukte så en while loop som kjører så lenge rot noden ikke er tom. 
             comparer så verdiene vi har i treet med p node verdien. 
             (Altså om verdiene vi har er mindre større eller lik p.verdi). 
             Hvis verdien som sammenlinges med p rot node verdi er mindre =(compare value = -1), så traverser vi ned venstre subtree for p noden og setter den current verdien som høyre node. 
             Eller hvis hvis nåværende verdi som sammenlignes med p rot verdien er større (compare value = 1), så traverser vi ned høyre subtree for p noden og setter den current verdien som høyre node.
             Hvis compare verdiene er like for den nåværende verdien vi sammenlinger med verdien i p noden (compare value = 0),  så plusser vi da antallLikeNode-variabelen for hver node som har lik verdi. 
             Deretter setter vi de like nodeverdiene til høyre for forldrenoden.
             Mot slutten så returnerer vi antall like forekomster verdier i treet (AntallLikeNoder-variablen).  
              
Oppgave 3a : Løste oppgave 3a førstepostorden ved å tatt utganspunkt i programkode 5.1.7 h) fra kompendie:
             Her er det vikig å vite at p blir definert som rotnoden. 
             Implementerte videre at hvis p noden er tom. 
             Altså vis rotnoden er tom så har (ikke) venstre eller høyre peker en foreldre node, noe som betyr at treet ikke eksisterer.
             Vi kaster da en kaster da en exception og sier at hele treet er tomt. 
             Deretter setter vi at en true statment inn i en while loop som sier at så lenge dette er sant så skal loopen kjøres:
             1) Hvis venstre barne node til foreldrenoden ikke er tom, så skal p.venstre barne node settes til venstre subtree for foreldre noden.
             2)Eller hvis høyre barne node til foreldrenoden ikke er tom, så skal p.høyre barne node settes til høyre subtree for foreldre noden.
             3) eller hvis både venstre og høyre barne barne node er tom, så returneres første node post orden med (p) som rotnoden tilbake. 
           
Oppgave 3b)  Løse oppgave 3b-nestepostorden ved å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 h) - om postorden:
             I kompendie defineres fordeldre noden som (f), men jeg valgte å definere den som parent.
              Starter med å, definerer at parent noden er foreldre noden til p. 
              Tar så ibruk en if statment som sier at hvis p ikke har forelder, da har ikke p en neste og vi returner null. 
              Eller hvis hvis p er høyre barn til sin forelder, så er forelder den neste.
             Eller hvis p er venstre barn til foreldre noden, og hvis høyre node til foreldre noden er tom, så har vi kun en venstre node for p.
             Da er foreldre den neste node for p. Siden høyre noden for foreldre noden er tom.
             Derimot hvis foreldre noden i tilegg har en høyre node, så er den neste vi traverserer altså neste noden som kommer etter p i postorden.
             Vi setter da node p lik den neste noden i høyre subtreet for foreldrenoden i førstenpostoden og neste noden i postorden.
             
Oppgave 4a)  Løste Oppgave 4a - postorden ved å ta ibruk progrmakode 5.1.7 - oppgave 7:
             Tankegangen min for å løse oppgaven var ifølge oppgavetesksten:  
               1) Start med å finne den første noden p i postorden. - rot noden
               2) Deretter vil (f.eks. i en while-løkke) setningen: p = nestePostorden(p); gi den neste. Osv. til p blir null
               3) Du skal bruke funksjonen nestePostorden fra forrige oppgave.
            Startet derfor med å finne den første noden p i postorden.
            Brukete så en while løkke for å definere at så lenge rot noden p ikke er tom så skal while løkken kjøres.
            Tok deretter å tok ibruk Interfacet oppgave som kaller på den abstracte metoden utføroppgave, som skal skrive ut verdiene som blir traversert i postorden rekkefølge til skjerm
           
Oppgave 4b) Løste oppgave 4b - posrordenRecursive ved å ta ibruk programkode 5.1.7 a) - fra kompendie.
                     1) I koden så startet jeg med å definere i en if statement at hvis venstre node verdi til p noden ikke er null.
                        så vil vi i en postorden rekkefølge traverere treet fra venstre subtree, og sende node verdiene til de nodene som blir traversert til oppgave interfacet.  
                     2) Å hvis hvis høyre node verdi til p noden ikke er null.
                        så skal vi traversere videre til høyre subtree og sende node verdiene til de nodene som blir traversert til oppgave interfacet. 
                     3) (Både venstre å høyre subtree blir traversert i postorden rekkefølge ved å reqursivt kalle på metoden postordenRecursive)        
                         Interfacer oppgaven kaller så på den abstracte metoden utføroppgave, som skal skrive ut verdiene som blir traversert i postorden rekkefølge til skjerm 
                     Viktig!
                     I forskjell til programkoden fra kompendie tok jeg å: 
                      -kalte på metoden postoderdenrecursive i stedet for pre-orden metoden som blir gjort i (5.1.7 a fra kompendie)). 
                      -Gjorde dette fordi postoderdenrecursive blir da koblet til Interfacet oppgave som kaller på den abstracte metoden - utføroppgave)         
 
 Oppgave 5a) Løste oppgave 5a - seralize() ved å ta ibruk programkode tar ibruk programkode 5.1.6 a) fra kompendie for å lagre fra arraylist til fil verdi  - NivOrden.
             Startet med å implementere en arraylist som jeg kalte (verdier_til_Liste).
             Laget deretter en kø i lenkekt Liste form.
             Lå deretter inn rot vedien inn i køen til linkedList.
             Brukte deretter en while løkke som sier at så lenge køen ikke er tom så så kan vi ta verdiene ut fra køen og legge inn i arraylisten.
             Definerte så node p og tar den ut fra køen, og legger inn verdi for de ulike p noden som blir traversert og adder det i arraylisten.
             Tok deretter ibruk en if statement som sier at hvis p.venstre node ikke er lik null så legger vi inn verdiene fra subtreet inn i køen linkedlist.
             Legger da inn verdi for noden som blir traversert i venstre subtree og addere det i lindeklist i nivåorden rekkefølge.
             Tok på lik måte å definere i en annen if statment at hvis p.høyre node ikke er lik null så legger vi de verdiene fra subtreet inn i køen linkedlist. 
             Vi legger da inn verdi for nodene som blir traversert i venstre subtree og addere det i lindeklist i nivåorden rekkefølge.
             Deretter så returner vi Linked listen som skal inneholde verdiene i alle nodene som ble traversert i nivåorden rekkefølge og legger det i arraylisten.  
             Programmet itterer til køen er tom og verdiene har blitt lagt inn i arraylist.
             
 Oppgave 5b) Løste oppgave 5b - deseralise() ved å ta ibruk programkode 5.2.3 c fra kompendie:
             Implementere løsningen ved ta ibruk EksamenSBinTre som Constructior som tar inn komparatoren c for typen T.
             Tok deretter å bygget opp et tre ved å hente én og én verdi fra arraylisten og bygger opp treet i nivåorden
             Returnerer deretter det ferdigbyggede treet.
                       