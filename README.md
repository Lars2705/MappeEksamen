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

jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 59 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.
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

* oppgave 0: Løste ved å implementere en Test_klasse for Main metoder (3 linjer under)
            Kjørte deretter instanser av klassen EksamensBinTre som ble opgitt igjennom main metoden.
            Dette gjør jeg for å se at jeg fikk ut korrekt utskrift ifølge det oppgavetsksten ber om. 
            Gjorde deretter det samme for oppgave 1, 2 og 6. 

* Oppgave 1: Løste ved å implementere programkoden 5.2.3 a) som er hentet fra kompendie ( 8 linjer under):
             Starter oppgaven med å sjekke at legginn metoden ikke kan ha nullverdier. 
             Definerer en node peker p som starter i rot og en node q som er lik null. 
             I while løkken brukes det en kompertor som sammenlinger current verdien vi legger inn, med p.noden sin verdi 
             Når p er null dvs ute av treet , er q den siste vi passerte. Vi oppretter da en ny node med q som foreldreferanse.
             vis compare verdien er mindre enn node p.verdi(-1) så flytter vi peker p til venstre, og den nye noden den blir venstre barn til q.
             Vis compare verdien er større en node p.verdi(1) så skal vi flytte pekeren p til høyre og den nye noden blir høyre barn til q.
             Vis verdien vi sammenligner med p.verdi er lik, betyr dette at compare verdien er lik(0) og da legges noden til høyre for p noden.
             Hvis q er lik null , blir p rotnode og bruker også en while løkke for å sjekke om p er/ikke er utefor treet så lenge den ikke er lik null.
             
             

* Oppgave 2: Løse ved å ta ibruk programkode 5.2.6 oppgave 2) fra kompendie - (8 linjer under): 
             Definerte først node p peker til rot noden. 
             Definerte så en hjelpevariabel antallLike_NodeVerdier som skal plusses for antall like verdier i treet.
             Brukte så en while løkke som kjører så lenge rot noden ikke er tom. 
             I while løkken så bruker komperator for å sammenlinge de generiske verdiene vi setter inn med verdien med p.verdi. 
             Hvis compare value = -1 så flytter vi ned node p til venstre. 
             Eller hvis compare value = 1, så flytter vi ned node p til høyre.
             Hvis compare value = 0, e, da øker hjelpevariabelen for hver node vi funnet som har samme verdi som er i p peker noden. 
             Deretter setter vi de like nodeverdiene til høyre for forldrenoden som inneholder lik verdi og returnerer antall like forekomster av samme verdier i treet.
             

* Oppgave 3a : Løste oppgave 3a førstepostorden ved å ta utganspunkt i programkode 5.1.7 h) fra kompendie (5 linjer under):
             Her er det vikig å vite at p blir definert som rotnoden. Starter først med å finne ut om vi får en nullreferanse 
             Deretter setter vi at en true statment inn i en while løkke som sier at så lenge dette er sant sjekker vi at:
             1) Hvis p.venstre ikke er null, så forsetter vi videre ned venstre subtree for foreldrenoden for å lete i treet
             2)Eller p.høyre ikke er null, så går vi videre ned høyre subtree for foreldrenoden og leter i treet.
             3) hvis både venstre og høyre barne barne node er null, så returneres den neste noden som er den første noden p i postorden. 
           
* Oppgave 3b)  Løse oppgave 3b-nestepostorden ved å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 h) - om postorden - (5 linjer under):
              Starter med å definerer en hjelpenode - (parent) og sier at denne er foreldre noden til p. 
              Tar så ibruk en if statment som sier at hvis p ikke har forelder, da har ikke p en neste og vi returner null. 
              Hvis hvis p er høyre barn til sin forelder, så er forelder den neste i postorden.
              Derimot hvis p er venstre barn til foreldre noden, og vi vet at p noden er et enebarn , så vet vi at neste noden i postorden er foreldrenoden til p. 
              Hvis foreldre noden i tilegg har en høyre node, så er den neste vi traverserer til altså noden som kommer først i postorden i subtreet
      
             
* Oppgave 4a) Løste Oppgave 4a - postorden ved å ta ibruk programkode 5.1.7 - oppgave 7 - (4 linjer under):
            Startet med å kalle på første posorden noden ved å ta ibruk førstepostorden metoden i oppgave 3.
            Brukete så en while løkke for å definere at så lenge noden p ikke er null så skal while løkken kjøres.
            Tok deretter å tok ibruk Interfacet oppgave som kaller på den abstracte metoden utføroppgave som skriver ut nodenes verdi 
            Til slutt så retunrer vi den neste i postorden som kommer etter den første Postorden
           
* Oppgave 4b) Løste oppgave 4b - posrordenRecursive ved å ta ibruk programkode 5.1.7 a) - fra kompendie - (5 linjer under):
                     1) I koden så startet jeg med å definere i en if statement at hvis venstre node verdi til p(p.venstre ikke er null 
                         så kaller vi reqursivt på samme metode og starter i p.venstre som node
                     2) Å hvis hvis høyre node verdi til p(p.høyre) ikke er null.
                         så kaller vi reqursivt på denne metoden og starter i p.høyre som node 
                     Til slutt så kaller vi på interface oppgaven som skal skrive ut nodene sine verdier til skjerm 


* Oppgave 5a) Løste oppgave 5a - seralize() ved å ta ibruk programkode tar ibruk programkode 5.1.6 a) fra kompendie - (5 linjer under):
             Startet med å implementere en arraylist som jeg kalte (verdier_til_Liste). VI tar ibruk hjelpemetoden nivåroden bruker for å traversere treet i nivåorden.
             Laget deretter en kø i lenkekt Liste form og lå deretter inn rot vedien inn i køen til linkedList.
             Brukte deretter en while løkke som sier at så lenge køen ikke er tom så så kan vi ta verdiene ut fra køen og legge inn i arraylisten 
             så lenge p.venstre og p.høyre node ikke er null. vi Legger da inn verdiene fra venstre/høyre subtreet som ble traversert i nivåorden rekkefølge inn i køen linkedlist.
             Deretter så returner vi køen og legger verdiene fra listen inn i arraylisten og dette programmet itterer til køen er tom.
             
             
* Oppgave 5b) Løste oppgave 5b - deseralise() ved å ta ibruk programkode 5.2.3 c fra kompendie - (3 linjer under):
             Implementere løsningen ved ta ibruk EksamenSBinTre som Constructør som tar inn komparatoren c for typen T.
             Bruker deretter en for each løkke som bygger opp et tre ved å hente én og én verdi fra arraylisten.
             Returnerer deretter det ferdigbyggede treet.
             
           

* oppgave 6a)  Løste oppgave 6a fjern() ved å ta ibruk programkode programkode 5.2.8 d) fra kompendie - (7 linjer under):
               I fjern metoden sjekker vi om det er mulig å fjerne en node ved å gå igjennom 3 tilfeller: Disse er
               1)Om node p ikke har noen bar - er en bladnode
               2)P har nøyaktig et barn - dette kan være enten høyre eller venstre barn
               3)p har 2 barn
               I tilfelle 3 i fjern metoden sjekker vi at hvis r.høyre ikke er null og inneholder en verdi,
               så må foreldre noden til r.foreldre.høyre node være lik s sin peker. Dette gir da korrekt foreldrereferanse.
               I tilfelle 1 og 2 så sjekker vi at hvis barne noden b inneholder en verdi , så setter q som forelder til noden b.                                                   

* Oppgave 6b) Løste oppgave ved å ta ibruk programkode - 5.2.8 oppgave 5 fra kompendie for fjernalle metoden; (4 linjer under):
            Starter med å lage en if statment som skal sjekke om treet er tomt 
            Definer så en int hjelpevariabel. Går deretter igjennom en while løkken som skal fjerne alle forekomstene av (verdi) i treet.
            For hver verdi som ble fjernet så skal metoden skal returnere antallet som ble fjernet (plusser AntallSomBleFjernet variabelen)
            Vi returnerer så hjelpevariabelen tilbake med antallet som ble fjernet. 
 
 
*( Løste oppgave 6c) - nullstill() ved å ta utgangspunkt i programkode - oppgave 5 fra 5.2.8 fra kompendie - (5 linjer under):                        
            Setter noden p som rot node og kjører igjennom en if statment som sier at hvis noden ikke er tom så:
            1)så sender vi p igjennom hjelpetoden for å slette verdiene i treet, og kaller på metoden slett - (rekursivt)
            Tar ibruk hjelpemetoden slett som sier at hvis høyre/venstre peker node fra rot noden ikke er null så: 
            1)Traverser høyre/venstre subtree og setter verdienene som er til høyre/venstre for rotnoden lik null 
            2)Setter så verdien i selve p noden lik null, og vi går tilbake til nullstill metoden og setter rot noden sin verdi lik null og setter antall noder lik 0 i treet    