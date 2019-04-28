package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

import java.io.FileWriter;
import java.io.IOException;

public class CommandesDATA extends Commandes{

    public CommandesDATA(Connexion server, String command) {
        super(server, command);
    }

    @Override
    String makeAnswer(String content) {
        String[] s = extractContent(content);

        server.sendResponse("354 Début des entrées du message ; fin avec <CRLF>.<CRLF>");

        String data = "";
        String mail = "";
        try{
            do {
                System.out.println(data);
                mail+=data;
            } while (!(data = server.inputdata.readLine()).equals("."));

            writeFile(mail);

            server.setStateNum(6);

            return "250 OK";
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return "CODE ERREUR - Request Failed";
    }

    @Override
    String[] extractContent(String content) {
        return content.split(" ");
    }

    String writeFile(String data) {
        try {
            FileWriter outFile = new FileWriter("src//Server_SMTP//BDD//Mails//" + clientDomain + server.autoincrement + ".txt", true);
            server.autoincrement+= 1;
            outFile.write("\r\n");
            outFile.write(data);
            outFile.close();
        } catch(IOException e) {
            e.getMessage();
        }

        return null;
    }
}