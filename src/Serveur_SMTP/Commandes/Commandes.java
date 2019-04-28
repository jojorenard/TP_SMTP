package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

public abstract class Commandes {
    //FIELDS
    Connexion server;
    private String command;
    String clientDomain;

    //CONSTRUCTEUR
    Commandes(Connexion server, String command) {
        this.server = server;
        this.command = command;
    }

    //GETTER SETTER
    public String getCommand() {
        return command;
    }

    //COMMANDES
    public void answerCommand(String content)
    {
        server.sendResponse(makeAnswer(content));
    }

    //ABSTRACT
    abstract String makeAnswer(String content);
    abstract String[] extractContent(String content);

}

