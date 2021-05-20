package Donnes.Client;

import java.net.*;
import java.util.Scanner;

public class Emission implements Runnable {
    public final static int PORT = 7331;
    private final DatagramSocket sock;
    private final String ip;

    /**
     * @param sock Socket de connexion
     * @param ip ip de la connexion
     */
    public Emission(DatagramSocket sock, String ip) {
        this.sock = sock;
        this.ip = ip;
    }


    /**
     * Envoie le message S en parametre
     * @param s Message a envoyer
     * @throws Exception e
     */
    private void envoyer(String s) throws Exception {
        byte[] buffer = s.getBytes();
        InetAddress ip = InetAddress.getByName(this.ip);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, PORT);
        sock.send(packet);
    }


    public void run() {
        boolean connected = false;
        //Attente de la connexion
        do {
            try {
                envoyer("Je suis nouveau");
                connected = true;
            } catch (Exception ignored) {

            }
        } while (!connected);

        //Envoie du code tape dans la console
        String message = "";
        Scanner sc = new Scanner(System.in);
        while (!message.equals("exit")) {
            try {
                message = sc.nextLine();
                envoyer(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}