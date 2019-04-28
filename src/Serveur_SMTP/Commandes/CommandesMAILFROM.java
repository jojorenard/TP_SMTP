package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

public class CommandesMAILFROM extends Commandes{

    public CommandesMAILFROM(Connexion server, String command) {
        super(server, command);
    }

    @Override
    String makeAnswer(String content) {
        return null;
    }

    @Override
    String[] extractContent(String content) {
        return new String[0];
    }
}
