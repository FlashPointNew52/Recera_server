/**
 * Created by Eugenu Modenov on 11.08.2017.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.EndpointConfig;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/")

public class Server_socket {
    private static Map<String, Session> clients = new HashMap<String, Session>();
    private static final Logger LOGGER =
            Logger.getLogger(Server_socket.class.getName());

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        clients.put(session.getId(), session);
        LOGGER.log(Level.INFO, "New connection with client");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        LOGGER.log(Level.INFO, "New message");
        for (Map.Entry<String, Session> entry : clients.entrySet()) {
            if(entry.getKey() !=  session.getId())
                entry.getValue().getBasicRemote().sendText(message);
        }
        //return "";
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.log(Level.INFO, "Close connection for client: {0}", session.getId());
    }

    @OnError
    public void onError(Throwable exception, Session session) {
        LOGGER.log(Level.INFO, "Error for client: {0}", exception.getMessage());
    }
}
