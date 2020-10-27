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

*oppgave 0: Løste ved å implementere en Test_klasse for Mianmetoder. Kjørte deretter instansenr av klassen EksamensBinTre som ble opgitt igjennom main metoden,
            for å se at jeg fikk ut korrekt utskrift ifølge det oppgavetsksten ber om. Gjorde deretter det samme for oppgave 1, 2 og 6. 

* Oppgave 1: Løste ved å implementere programkoden 5.2.3 a) som er hentet fra kompendie. Har videre lagt til forlde referanse.
             Brukte de to nodereferanseere p og q. Definerer p som barn og q som forelder. P starter på rotnoden. 
             Ved bruk av compare(sammenlinger verdi med p noden sin verdi. Ved bruk av compare har vi 3 mulige utfall. Enten -1,0 eller 1 som verdi.
             vis verdien vi sammenligner med er lavere enn node p.verdi(-1) så flytter vi peker p til venstre barn. og verdien settes som venstre barn til p noden
             Vis compare verdien er større en node p.verdi(1) så skal vi flytte pekeren til høyre side av foreldre noden.
             Vis verdien vi sammenligner med p.verdi er lik, betyr dette at compare verdien er lik(0) - (altså lik) og da legges noden til høyre for p noden.
             Så referer vi til q skal være foreldre noden til p. q noden som er forelder skal ligge et nivå over barne noden p.
             For å starte traversering så må vi traversere fra foreldre noden til barne node. 
             Hvis treet var tomt i utgangspunktet så lages da rotnoden.  
           

* Oppgave 2: Løse ved å ta ibruk programkode 5.2.6 oppgave 2) fra kompendie. Definerte først node p som rot noden. Definerte så
             variabelelen antallLike_NodeVerdier som skal plusses for hver en node med lik verdi traverseres igjennom treet.
             Brukte så en while loop som kjører så lenge rot noden ikke er tom. comparer så verdiene vi har i treet med p node verdien. 
              (Altså om verdiene vi har er mindre større eller lik p.verdi). Hvis verdien somn sammenlinges med p rot node verdi er mindre =(compare value = -1),
              så traverser vi ned venstre subtree for p noden og setter den current verdien som høyre node. 
              Eller hvis hvis nåværende verdi som sammenlignes med p rot verdien er større (compare value = 1), 
              så traverser vi ned høyre subtree for p noden og setter den current verdien som høyre node.
              Hvis compare verdiene er like for den nåværende verdien vi sammenlinger med verdien i p noden (compare value = 0),
              så plusser vi da antallLikeNode-variabelen for hver node som har lik verdi. Deretter setter vi de like nodeverdiene til høyre for forldrenoden.
              Mot slutten så eturnere antall like forekomster verdier i treet (AntallLikeNoder-variablen).  
              
Oppgave 3a : Løste oppgave 3a førstepostorden ved å tatt utganspunkt i programkode 5.1.7 h) fra kompendie. Her er det vikig å vite at p blir definert som rotnoden. 
           Implementerte videre at hvis p noden er tom. altså vis rotnoden er tom så har (ikke) venstre eller høyre peker en foreldre node, noe som betyr at treet ikke eksisterer.
           Vi kaster da en kaster da en exception og sier at hele treet er tomt. Deretter setter vi at en true statment inn i en while loop som sier at så lenge dette er sant så skal loopen kjøres:
           1) Hvis venstre barne node til foreldrenoden ikke er tom,  så skal p.venstre barne node settes til venstre subtree for foreldre noden
           2)Eller hvis høyre barne node til foreldrenoden ikke er tom, så skal p.høyre barne node settes til høyre subtree for foreldre noden
           3) eller hvis både venstre og høyre barne barne node er tom, så returneres første node post orden med (p) som rotnoden tilbake 
           
Oppgave 3b) Løse oppgave 3b-nestepostorden ved å ta ibruk fremgangsmåten for postorden fra kompendie under 5.1.7 h) - om postorden.
            I kompendie defineres fordeldre noden som (f), men jeg valgte å definere den som parent. Starter med å
            Definerer at parent noden er foreldre noden til p. Tar så ibruk en if statment som sier at hvis p ikke har forelder, 
            da har ikke p en neste og vi returner null. Eller hvis hvis p er høyre barn til sin forelder, så er forelder den neste.
            Eller hvis p er venstre barn til foreldre noden, og hvis høyre node til foreldre noden er tom, så har vi kun en venstre node for p.
            Da er foreldre den neste node for p. Siden høyre noden for foreldre noden er tom.
            Derimot hvis foreldre noden i tilegg har en høyre node, så er den neste vi traverserer altså neste noden som kommer etter p i postorden.
            Vi setter da node p lik den neste noden i høyre subtreet for foreldrenoden i førstenpostoden og neste noden i postorden.