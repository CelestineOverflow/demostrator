package loginAMQ;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jms.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;

public class ServerProccesing extends JMSClient {
    private Data processData;
    private final EncryptedExchanger encryptedExchanger;

    public ServerProccesing() throws NoSuchAlgorithmException {
        super();
        encryptedExchanger = new EncryptedExchanger();
    }

    public PublicKey getPublicKey() {
        return encryptedExchanger.getPublicKey();
    }

    public int processData() throws JMSException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        super.connect(this);
        ArrayList<String> result = new ArrayList<>();
        result.add("empty");
        result.add("empty");
        Destination destination = session.createQueue(EncryptedExchanger.CREDENTIALS);
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        message.acknowledge();
        if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Data data = (Data) objectMessage.getObject();
            if (data.getPublicKey().hashCode() != encryptedExchanger.getPublicKey().hashCode()) {
                System.err.println("failed key");
            } else {
                System.out.println("Correct Key");
                result = encryptedExchanger.decryptData(data);

            }
        }
        super.disconnect(this);
        if (result.get(0).equals("admin") && result.get(1).equals("1234")) {
            return 0;
        } else {
            return 1;
        }
    }

    public void sendKey() throws JMSException {
        super.connect(this);
        Destination destination = session.createQueue(EncryptedExchanger.PUBLIC_KEY);
        MessageProducer producer = session.createProducer(destination);
        ObjectMessage classMessage = session.createObjectMessage(encryptedExchanger.getPublicKey());
        producer.send(classMessage);
        super.disconnect(this);
    }
}
