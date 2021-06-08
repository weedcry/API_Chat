package api.websockethandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler {
    private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = String.valueOf(message.getPayload());
        System.out.println("recive message"+msg);
        ;
        // Send back unique message depending on the id received from the client
        if(checkSession(session)==1) {
            sessions.add(session);
        }
        System.out.println("So Luong user:" + sessions.size());
        //tra ve tat ca client
        for(int i=0;i<sessions.size();i++) {
            sessions.get(i).sendMessage(new TextMessage(sessions.get(i).toString() + msg));
        }
    }
    public int checkSession(WebSocketSession session) {
        for(int i=0;i<sessions.size();i++) {
            if(sessions.get(i).getId()==session.getId()) return 0;
        }
        return 1;
    }

}