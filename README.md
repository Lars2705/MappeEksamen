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
              Vis verdien vi legger inn er lik p.verdi betyr dette at compare verdien er lik(0) - (altså lik) og da legges noden til høyre for p noden.
             Så referer vi til q skal være foreldre noden til p. q noden som er forelder skal ligge et nivå over barne noden p.
             For å starte traversering så må vi traversere fra foreldre noden til barne node. 
             Hvis treet var tomt i utgangspunktet så lages da rotnoden.  
           

* Oppgave 2: ...

