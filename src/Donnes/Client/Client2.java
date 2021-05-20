package Donnes.Client;
import java.net.*;

public class Client2 {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        DatagramSocket socket = new DatagramSocket();
        Reception r = new Reception(socket);
        Emission s = new Emission(socket, host);
        Thread rt = new Thread(r);
        Thread st = new Thread(s);
        rt.start(); st.start();
    }
}