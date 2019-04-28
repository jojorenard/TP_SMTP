package Serveur_SMTP.Commandes;

import Serveur_SMTP.Connexion;

import java.io.*;

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
                mail+=data+"\n";
            } while (!(data = server.inputdata.readLine()).equals("."));
            mail+=".";

            if(writeFile(mail)){
                server.setStateNum(6);
            }

            return "250 OK ";
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return "CODE ERREUR - Request Failed";
    }

    @Override
    String[] extractContent(String content) {
        return content.split(" ");
    }

    boolean writeFile(String data) {
        try {
            File file = new File("src/Serveur_SMTP/BDD/Mails/" + server.getServerDomain() + server.autoincrement + ".txt");
            BufferedWriter outFile = new BufferedWriter(new FileWriter(file));
            server.autoincrement+= 1;
            outFile.write(data);
            outFile.close();
            return true;
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}