package jms.concrete;

import jms.abstractions.JmsBase;
import jms.enums.JmsType;

import javax.jms.JMSException;
import javax.jms.Message;

public class JmsSender extends JmsBase {
    private final JmsType type;
    private final String name;

    public JmsSender(JmsType type, String name) {
        this.type = type;
        this.name = name;
    }
    public void send(String data) {
        super.setupConnection(type, name);

        try {
            Message message = getSession().createTextMessage(data);
            super.getSession().createProducer(super.getDestination()).send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
