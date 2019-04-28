package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

public class CommandesEHLO extends Commandes {

    public CommandesEHLO(Connexion server, String command) {
        super(server, command);
    }

    @Override
    String makeAnswer(String content) {
        String domain = "gmail.com";
        String domainClient = "hotmail.fr";
        System.out.println(content);
        String[] s = extractContent(content);
        System.out.println(s[1]);
        //if state ?{
        //if(s[1] = domain du serveur){
        //set nouveau state
        return "250-"+domain+" greets"+domainClient;
        //}
        //}
    }

    @Override
    String[] extractContent(String content) {
        return content.split(" ");
    }
}
