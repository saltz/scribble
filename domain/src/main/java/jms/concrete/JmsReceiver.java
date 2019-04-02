package jms.concrete;

import jms.abstractions.JmsBase;
import jms.enums.JmsType;
import jms.interfaces.IJmsCallback;

import javax.jms.JMSException;

public class JmsReceiver extends JmsBase {
    private final JmsType type;
    private final String name;

    public JmsReceiver(JmsType type, String name) {
        this.type = type;
        this.name = name;
    }

    public void setCallback(IJmsCallback callback) {
        super.setupConnection(type, name);

        try {
            super.getConnection().start();
            super.getSession().createConsumer(super.getDestination()).setMessageListener(callback::callback);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
