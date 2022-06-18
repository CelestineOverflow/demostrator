package loginAMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

public abstract class JMSClient {
    protected final String url = "tcp://localhost:61616";
    protected final String username = "admin";
    protected final String password = "admin";
    public boolean connected;
    protected Connection connection;
    protected Session session;
    private ActiveMQConnectionFactory connectionFactory;

    public JMSClient() {
        try {
            connectionFactory = new ActiveMQConnectionFactory(url);
            // Set the trusted packages to move back and forth on the ActiveMQ JMS service.
            connectionFactory.setTrustAllPackages(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect(Object instanceName) {
        try {
            connection = connectionFactory.createConnection(username, password);
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.print("Connected: ");
            System.out.println(instanceName.toString());
            connected = true;
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void disconnect(Object instanceName) {
        try {
            connection.close();
            connected = true;
            System.out.print("Disconnected: ");
            System.out.println(instanceName.toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
