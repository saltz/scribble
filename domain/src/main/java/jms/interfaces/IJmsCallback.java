package jms.interfaces;

import javax.jms.Message;

public interface IJmsCallback {
    void callback(Message message);
}
