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

jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 53 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.
Jeg har lagret den opprinnelige kildekoden ved bruk av github detoskope og pushet inn. 

**(Viktig!!!) - Min Hoved-branch heter ("Main") og ikke ("Master") på github detoskope.**

# Warnings i EksamenSBinTre klassen:

Jeg har totalt 5 warnings i java klassen min:

1) - 2/5 warnings - (kommer av Non-ASCII characters in an identifier) 
Disse kommer av at "høyre" og "førstepostorden" begge inneholder bokstaven (Ø).

2) - 1/5 warnings - (kommer av Private field 'endringer' is assigned but never accessed)
Kommer av at jeg ikke bruker verdien fra endringer i noen av oppgavne selv om den kpden inn som en hjelpevariabel.

3) - 1/5 warnings - (kommer av Return value of the method is never used) - for boolean legginn metoden()
Kommer av at geg returnerer true value tilbake, men får likevel opp feilmelding om at verdien true som blir sendt tilbake ikke blir brukt.

4) - 1/5 warnings - kommer av (Method 'inneholder(T)' is never used)
kommer av at jeg aldri bruker hjelemetoden inneholder(T) som allerde er ferdigkodet i en løsningene mine for de ulike oppgavene.

# Forklaring for hvordan jeg løste oppgavene:

* oppgave 0: Løste ved å implementere en Test_klasse for Mianmetoder (3 linjer under)
            Kjørte deretter instanser av klassen EksamensBinTre som ble opgitt igjennom main metoden.
            Dette gjør jeg for å se at jeg fikk ut korrekt utskrift ifølge det oppgavetsksten ber om. 
            Gjorde deretter det samme for oppgave 1, 2 og 6. 

* Oppgave 1: Løste ved å implementere programkoden 5.2.3 a) som er hentet fra kompendie ( 8 linjer under):
             Brukte to nodereferanseere p og q, og definerer p som barn og q som forelder.(P starter på rotnoden) 
             Ved bruk av compare (sammenlinger vi current verdien vi legger inn, med p.noden sin verdi) 
             Ved bruk av compare har vi 3 mulige utfall.(Enten -1,0 eller 1 som verdi)
             vis verdien vi sammenligner med er lavere enn node p.verdi(-1) så flytter vi peker p til venstre barn, og verdien settes som venstre barn til p noden.
             Vis compare verdien er større en node p.verdi(1) så skal vi flytte pekeren til høyre side av foreldre noden.
             Vis verdien vi sammenligner med p.verdi er lik, betyr dette at compare verdien er lik(0) - (altså lik) og da legges noden til høyre for p noden.
             Deretter referer vi til at q skal være foreldre noden til p.(q noden som er forelder skal ligge et nivå over barne noden p)
             Hvis treet var tomt i utgangspunktet så lages da rotnoden.  
           

* Oppgave 2: Løse ved å ta ibruk programkode 5.2.6 oppgave 2) fra kompendie - (8 linjer under): 
             Definerte først node p som rot noden. 
             Definerte så variabelelen antallLike_NodeVerdier som skal plusses for hver gang en verdi sammenlignes med en lik node verdi i treet.
             Brukte så en while loop som kjører så lenge rot noden ikke er tom. 
             comparer så de generiske verdiene vi setter inn med verdien til rot noden p. 
             Hvis compare value = -1 så traverser vi ned venstre subtree for p noden og sammenlinger current verdien med verdiene i venstre subtree. 
             Eller hvis compare value = 1, så traverser vi ned høyre subtree for p noden og sammenlinger current verdien med verdiene i høyre subtree.
             Hvis compare value = 0, e, så plusser vi da antallLikeNode-variabelen for hver node som har lik verdi. 
             Deretter setter vi de like nodeverdiene til høyre for forldrenoden som inneholder lik verdi og returnerer antall like forekomster verdier i treet.
             
              
* Oppgave 3a : Løste oppgave 3a førstepostorden ved å ta utganspunkt i programkode 5.1.7 h) fra kompendie (8 linjer under):
             Her er det vikig å vite at p blir definert som rotnoden. 
             Implementerte videre at hvis p noden er tom. 
             Altså vis rotnoden er tom så har ikke venstre eller høyre peker en foreldre node, noe som betyr at treet ikke eksisterer.
             Vi kaster da en kaster da en exception og sier at hele treet er tomt. 
             Deretter setter vi at en true statment inn i en while loop som sier at så lenge dette er sant så skal loopen kjøres:
             1) Hvis venstre barne node til foreldrenoden ikke er tom, så skal p.venstre barne node settes til venstre subtree for foreldre noden.
             2)Eller hvis høyre barne node til foreldrenoden ikke er tom, så skal p.høyre barne node settes til høyre subtree for foreldre noden.
             3) eller hvis både venstre og høyre barne barne node er tom, så returneres første node post orden med (p) som rotnoden tilbake. 
           
* Oppgave 3b)  Løse oppgave 3b-nestepostorden ved å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 h) - om postorden - (7 linjer under):
              Starter med å, definerer at parent noden er foreldre noden til p. 
              Tar så ibruk en if statment som sier at hvis p ikke har forelder, da har ikke p en neste og vi returner null. 
              Eller hvis hvis p er høyre barn til sin forelder, så er forelder den neste.
              Eller hvis p er venstre barn til foreldre noden, og hvis høyre node til foreldre noden er tom, så har vi kun en venstre node for p.
              Da er foreldre den neste node for p. Siden foreldre noden sin høyre node er tom.
              Derimot hvis foreldre noden i tilegg har en høyre node, så er den neste vi traverserer altså neste noden som kommer etter p i postorden.
              Vi setter da node p lik den neste noden i høyre subtreet for foreldrenoden i førstenpostoden og neste noden i postorden.
             
* Oppgave 4a) Løste Oppgave 4a - postorden ved å ta ibruk programkode 5.1.7 - oppgave 7 - (4 linjer under):
            Startet med å finne den første noden p i postorden.
            Brukete så en while løkke for å definere at så lenge rot noden p ikke er tom så skal while løkken kjøres.
            Tok deretter å tok ibruk Interfacet oppgave som kaller på den abstracte metoden utføroppgave 
            Denne abstracte metoden skriver ut verdiene som blir traversert i postorden rekkefølge til skjerm
           
* Oppgave 4b) Løste oppgave 4b - posrordenRecursive ved å ta ibruk programkode 5.1.7 a) - fra kompendie - (6 linjer under):
                     1) I koden så startet jeg med å definere i en if statement at hvis venstre node verdi til p noden ikke er null.
                         så vil vi i en postorden rekkefølge traverere treet fra venstre subtree, og sende verdiene til de nodene som blir traversert til oppgave interfacet.  
                     2) Å hvis hvis høyre node verdi til p noden ikke er null.
                         så skal vi traversere videre til høyre subtree og sende verdiene til de nodene som blir traversert til oppgave interfacet. 
                     3)Både venstre å høyre subtree blir traversert i postorden rekkefølge ved å reqursivt kalle på metoden postordenRecursive        
                         Interfacer oppgaven kaller så på den abstracte metoden utføroppgave, som skal skrive ut verdiene som blir traversert i postorden rekkefølge til skjerm 

* Oppgave 5a) Løste oppgave 5a - seralize() ved å ta ibruk programkode tar ibruk programkode 5.1.6 a) fra kompendie - (7 linjer under):
             Startet med å implementere en arraylist som jeg kalte (verdier_til_Liste).
             Laget deretter en kø i lenkekt Liste form og lå deretter inn rot vedien inn i køen til linkedList.
             Brukte deretter en while løkke som sier at så lenge køen ikke er tom så så kan vi ta verdiene ut fra køen og legge inn i arraylisten.
             Tok deretter ibruk to if-statement som sier at hvis p.venstre/p.høyre node ikke er lik null så,
             legger vi inn verdiene fra venstre/høyre subtreet som ble traversert i nivåorden rekkefølge inn i køen linkedlist.
             Deretter så returner vi køen som skal inneholde verdiene i alle nodene som ble traversert i nivåorden rekkefølge og legger verdiene fra listen inn i arraylisten.  
             Programmet itterer til køen er tom og verdiene har blitt lagt inn i arraylist.
             
* Oppgave 5b) Løste oppgave 5b - deseralise() ved å ta ibruk programkode 5.2.3 c fra kompendie - (3 linjer under):
             Implementere løsningen ved ta ibruk EksamenSBinTre som Constructør som tar inn komparatoren c for typen T.
             Tok deretter å bygget opp et tre ved å hente én og én verdi fra arraylisten og bygger opp treet i nivåorden
             Returnerer deretter det ferdigbyggede treet.
 
* oppgave 6a)  Løste oppgave 6a fjern() ved å ta ibruk programkode programkode 5.2.8 d) fra kompendie - (8 linjer under):
               Starter med å sette at q skal være forelder til p. Leter deretter etter at noden p inneholder en verdi.
               Sammenlinger så verdi som settes inn med p.node verdi. Går til venstre hvis compare = -1 og til høyre i treet hvis compare value = 1.
               Hvis den søkte verdien ligger i p så vi går ut av while loopen og hvis node p sin value er tom så retunrer vi false.
               Vi sjekker igjennom to tilfeller for at for at pekeren forelder får korrekt verdi i alle noder etter en fjerning:
               1) Hvis p ikke har barn eller har nøyaktig 1 barn: Lager her barne noden b. Hvis da b node verdi ikke er tom, så
               setter vi pekeren fra barne noden b til foreldre noden q.
               2) Hvis p har 2 barn: Finner neste i inorden. Tar så å setter at hvis r høyre-node verdi ikke er null og inneholder en verdi
               så må foreldre noden til r.høyre node være s.
 
* Oppgave 6b) Løste oppgave ved å ta ibruk programkode - 5.2.8 oppgave 5 fra kompendie for fjernalle metoden; (5 linjer under):
            Starter med å lage en if statment som sier at hvis treet er tomt, så skal 0 returneres.
            Definer så en int hjelpevariabel AntallSomBleFjernet. 
            Går deretter igjennom en while loop som skal fjerne alle forekomstene av (verdi) i treet.
            For hver verdi som ble fjernet så skal metoden skal returnere antallet som ble fjernet (plusser AntallSomBleFjernet variabelen)
            Vi returnerer så hjelpevariabelen tilbake med antallet som ble fjernet. 
 
 
*( Løste oppgave 6c) - nullstill() ved å ta utgangspunkt i programkode - oppgave 5 fra 5.2.8 fra kompendie - (6 linjer under):                        
            Definerer først at rot noden er lik node p og kjører igjennom en if statment som sier at hvis noden ikke er tom så:
            1)så sender vi noden p igjennom hjelpetoden for å slette verdiene i treet, og kaller på metoden slett - (rekursivt)
            Tar ibruk hjelpemetoden slett som sier at hvis høyre/venstre peker node fra rot noden ikke er null så: 
            1) Fjern Høyre subtree som er til høyre for rot noden
            2) fjern venstre subtree som er til venstre for rot noden 
            3)Setter så verdien i selve p noden lik null, og tilbake til nullstill metoden og setter rot noden sin verdi lik null og setter antall noder lik 0    