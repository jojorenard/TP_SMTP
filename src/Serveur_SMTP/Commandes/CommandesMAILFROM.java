package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

public class CommandesMAILFROM extends Commandes{

    public CommandesMAILFROM(Connexion server, String command) {
        super(server, command);
    }

    @Override
    String makeAnswer(String content) {
        if(server.getStateNum().equals(3)){
            String[] s = extractContent(content);
            System.out.println(s[2]);

            server.setStateNum(4);

            return "250 OK";
        }

        return "CODE ERREUR - Bad request";
    }

    @Override
    String[] extractContent(String content) {
        return content.split(" ");
    }
}
