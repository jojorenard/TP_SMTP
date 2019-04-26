package Serveur_SMTP;

import javafx.util.Pair;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Serveur_SMTP {

    private String domain;

    //CONSTANTS
    final Boolean SERVER_IS_RUNNING = true;
    final int SERVER_PORT= 1025;

    //INITIALIZE
    private SSLServerSocket server;
    private SSLSocket client;

    public Serveur_SMTP() {
        initServeurSSL();
    }

    public Serveur_SMTP(String domain){
        this.domain = domain;
        initServeurSSL();
    }

    private void initServeurSSL(){
        try{
            ServerSocketFactory sslServerSocketFactory = SSLServerSocketFactory.getDefault();
            server = (SSLServerSocket) sslServerSocketFactory.createServerSocket(SERVER_PORT);
            String[] allCipherSuites = server.getSupportedCipherSuites();
            List<String> listAnonCipherSuites = new ArrayList<>();
            for(String cipherSuites : allCipherSuites){
                if(cipherSuites.contains("anon")){
                    listAnonCipherSuites.add(cipherSuites);
                }
            }
            String[] newCipherSuites = new String[listAnonCipherSuites.size()];
            for(int i=0;i<newCipherSuites.length;i++){
                newCipherSuites[i] = listAnonCipherSuites.get(i);
            }
            server.setEnabledCipherSuites(newCipherSuites);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            while(SERVER_IS_RUNNING) {
                client = (SSLSocket) server.accept();
                new Thread(new Connexion(client, domain)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Lancement du serveur

    public static void main(String[] args) {
        Serveur_SMTP srv = new Serveur_SMTP();
        srv.run();
    }


}
