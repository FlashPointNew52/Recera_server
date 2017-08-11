import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

import org.glassfish.tyrus.server.Server;
/**
 * Created by Eugenu Modenov on 11.08.2017.
 */
public class Recera_server {
    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {
        Server server = new Server("127.0.0.1", 48000, "/", Socket.class);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }

}
