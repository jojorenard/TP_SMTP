package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

public class CommandesEHLO extends Commandes {

    public CommandesEHLO(Connexion server, String command) {
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
