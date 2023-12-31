Wozu dient das Schlüsselwort synchronized?
   - Methode kann nicht unterbrochen werden und atomarer zugriff auf das Objekt wird garantiert, andere Methoden können
     auf das Objekt so lange nicht zugreifen.

Warum ist die tell-Methode synchronized?
   - Damit immer nur eine Nachricht von einem Actor verarbeitet wird.

Was ist ein ExecutorService?
    Erweiter den Executer, welcher Threads nacheinander abarbeitet. Der ExecuterService sorgt für die parallele Abarbeitung der Threads.
    Der ExecuterService bietet zusätzlich Methoden zum beenden des Executers.
    
Was ist ein Daemon-Thread?
    Ist ein Thread, welcher beim beenden der JVM terminiert wird (der Thread aber noch läuft). 
    Wenn alle nicht demon-Threads terminert sind würde JVM stoppen auch wenn Demon-Threads noch nicht abgearbeitet sind.
    Kindprozess von Demon-Threads erben diesen Status.

Was ist ein User-Thread?
    Sind Threads von hoher Prio. JVM wartet bis alle User-Threads abgearbeitet werden bevor es termineren kann.
    Also ungefähr das Gegenteil von Daemon-Threads.

Haben die Aktoren des Actor-Frameworks eine Message-Queue?
    Ja

Wenn ja: Wo ist sie denn?
    Es befindet sich im ExecuterService, in unserem Beispiel im AbstractActor in der Tell-Methode (executor.execute).

Was ist ein Semaphor?
    Eine Kontrollinstanz, die sicherstellt, daß nur eine bestimmte Anzahl von Threads auf ein Objekt bzw, Resource zugreifen können.
    Ein Semaphor erlaubt einer festen und damit begrenzten Anzahl an Threads eine Resource zu verwenden (kritischer Abschnitt). 
    Die Threads können sich während der Laufzeit ändern. Jeder Thread muß eine Erlaubnis einholen. 
    Ist die maximale Anzahl erreicht, gibt es keine Erlaubnis mehr bis ein zugelassener Thread seine Arbeit beendet hat. 
    Das Semaphor verwaltet intern eine Menge sogenannter Erlaubnisse.

Warum verwendet das Ping-Pong-Beispiel ein Semaphor?
    Damit das Spiel nicht direkt nach dem initialisieren des Programms gleich wieder terminiert wird. Der Semaphor wird mit einer Erlaubnis initialisiert. 
    In Zeile 24 gibt der Semaphor dann die Erlaubnis Player1+2 die kritische Resource zu nutzen. 
    Dann werden die 10 definierten Messages von den Playern genutzt und der semaphore wird in Zeile 17 wieder released vom Referee.
    Dieser terminiert dann in Zeile 26 das Programm. 

Führen Sie das Ping-Pong-Beispiel ohne Semaphor aus.
Was können Sie beobachten?
Das Programm terminiert ummittelbar nach der Ausführung. 

Warum ist das so?
    Weil der Main Thread jetzt sofort nach der Ausführung terminiert und das Programm beendet. 
    Vorher hat der Semaphor sichergestellt das die Player auch rechnen dürfen.

UserThreadFactory
Warum terminiert das Programm nicht?
    Weil UserThreads die Eigenschaft haben, dass das Programm erst terminiert, wenn alle UserThreads ihre Aufgaben erledigt haben.


