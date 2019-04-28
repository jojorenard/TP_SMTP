package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

public class CommandesQUIT extends Commandes{

    public CommandesQUIT(Connexion server, String command) {
        super(server, command);
    }

    @Override
    String makeAnswer(String content) {
        if(server.getStateNum().equals(3) || server.getStateNum().equals(6)) {
            server.setClose(true);
            return "221 " + server.getServerDomain() + " Cloture du canal de transmission du service";
        }

        return "CODE ERREUR - Bad Request";
    }

    @Override
    String[] extractContent(String content) {
        return new String[0];
    }
}