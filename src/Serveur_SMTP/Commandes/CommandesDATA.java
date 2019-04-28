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
        System.out.println(content);
        String[] s = extractContent(content);
        System.out.println(s[2]);

        server.sendResponse("354 Début des entrées du message ; fin avec <CRLF>.<CRLF>");

        String data = "";
        try{
            data = server.inputdata.readLine();
            writeFile(data);
            server.setStateNum(6);

            return "250 OK";

        } catch(IOException e) {
            e.getMessage();
        }

        return "CODE ERREUR - Request Failed";
    }

    @Override
    String[] extractContent(String content) {
        return content.split(" ");
    }

    String writeFile(String data) {
        try {
            FileWriter outFile = new FileWriter("src/Server_SMTP/Mails/" + clientDomain + server.autoincrement + ".txt", true);
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
