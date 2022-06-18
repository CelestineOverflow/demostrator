package loginAMQ;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class Data implements Serializable{
    private final byte[] username;
    private final byte[] password;
    private final PublicKey publicKey;
    private static final long serialVersionUID = 1L;

    public Data(String username, String password, PublicKey publicKey) throws IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.publicKey = publicKey;
        this.username = EncryptedExchanger.encrypt(username, publicKey);
        this.password = EncryptedExchanger.encrypt(password, publicKey);
    }

    public byte[] getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
