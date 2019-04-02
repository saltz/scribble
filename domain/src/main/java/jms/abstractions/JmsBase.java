package jms.abstractions;

import jms.enums.JmsType;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public abstract class JmsBase {
    private Destination destination;
    private Connection connection;
    private Session session;

    protected void setupConnection(JmsType type, String name) {
        try {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            properties.put(String.format("%s.%s", type.toString().toLowerCase(), name), name);

            Context context = new InitialContext(properties);
            ConnectionFactory connectionFactory = (ConnectionFactory) context
                    .lookup("ConnectionFactory");

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = (Destination) context.lookup(name);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    protected Destination getDestination() {
        return destination;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected Session getSession() {
        return session;
    }
}