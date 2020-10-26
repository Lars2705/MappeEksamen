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

* Oppgave 1: Løste ved å implementere programkoden er hentet fra kompendie 5.2 3 a) og har lagt til forlde referanse.
             Brukte de to nodereferanseere p og q. Definerer p som barn og q som forelder. P starter på rotnoden. 
             Ved bruk av compare(sammenlinger verdi) vis verdi verdi er lavere enn node p.verdi(-1) så flytter vi peker p til venstre barn.
             Vis compare verdien er større en node p.verdi(1) så skal vi flytte pekeren til høyre side av foreldre noeden.
             Så referer vi q skal være foreldre noen til p så, q noden som er forelder skal ligge et nivå
             over barne noden p. For å starte traversering så må vi traversere fra foreldre noden til barne node. 
             Vis verdien vi legger inn er lik p.verdi betyr dette at compare verdien er lik(0) og da legges noden til høyre.
             Hvis treet var tomt i utgangspunktet så lages da rotnoden.  
           

* Oppgave 2: ...

