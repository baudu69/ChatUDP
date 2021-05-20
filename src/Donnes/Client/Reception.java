package Donnes.Client;

import vue.Chat;

import java.net.*;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;


public class Reception extends Observable implements Runnable {
    private final DatagramSocket socket;
    private final byte[] buffer;

    /**
     * @param socket socket de connexion
     */
    public Reception(DatagramSocket socket, Chat unChat) {
        this.socket = socket;
        buffer = new byte[1024];
        addObserver(unChat);
    }
    public Reception(DatagramSocket socket) {
        this.socket = socket;
        buffer = new byte[1024];
    }

    public void run() {
        String message = "";
        while (true) {
            try {
                Arrays.fill(buffer, (byte)0);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                byte[] data = packet.getData();
                message = new String(data).split("\0")[0];
                System.out.println(message);
                setChanged();
                notifyObservers(message);
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }
}