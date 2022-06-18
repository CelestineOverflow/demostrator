package loginAMQ;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jms.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class LoginManager extends JMSClient {
    private String username = "Celeste";
    private String password = "123451233136";
    private final EncryptedExchanger encryptedExchanger;
    private PublicKey externalKey;

    public LoginManager(String username, String password) {
        super();
        encryptedExchanger = new EncryptedExchanger();
        this.username = username;
        this.password = password;
    }

    public void sendData() throws JMSException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        super.connect(this);
        Destination destination = session.createQueue(EncryptedExchanger.CREDENTIALS);
        MessageProducer producer = session.createProducer(destination);
        ObjectMessage classMessage = session.createObjectMessage(initData(externalKey));
        producer.send(classMessage);
        super.disconnect(this);
    }

    protected void setKey() throws JMSException {
        super.connect(this);
        Destination destination = session.createQueue(EncryptedExchanger.PUBLIC_KEY);
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        message.acknowledge();
        if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            PublicKey data = (PublicKey) objectMessage.getObject();
            externalKey = data;
        }
        super.disconnect(this);
    }

    private Data initData(PublicKey publicKey) throws IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return new Data(username, password, publicKey);
    }
}
