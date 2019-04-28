package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

public class CommandesRCPTTO extends Commandes{

    public CommandesRCPTTO(Connexion server, String command) {
        super(server, command);
    }

    @Override
    String makeAnswer(String content) {
        if(server.isStateAuthentified()){
            //extract mail domain
            String domain = content.split("@")[1];
            domain = domain.split(".")[0].toUpperCase();
            if(!domain.equals(server.getServerDomain())){
                server.accessNewServer();
                return null; //Ou autre chose
            }
            //A completer
        }
        return null;
    }

    @Override
    String[] extractContent(String content) {
        //A completer? fonction a garder? nécessaire?
        return new String[0];
    }
}
