package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

public class CommandesEHLO extends Commandes {

    public CommandesEHLO(Connexion server, String command) {
        super(server, command);
    }

    @Override
    String makeAnswer(String content) {
        String[] s = extractContent(content);
        clientDomain = s[1];

        server.setStateNum(3);

        return "250 OK " + server.getServerDomain() + " greets "+s[1];
    }

    @Override
    String[] extractContent(String content) {
        return content.split(" ");
    }
}