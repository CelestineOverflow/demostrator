package loginAMQ;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.ArrayList;

public class EncryptedExchanger {
    protected static String CREDENTIALS = "CREDENTIALS";
    protected static String PUBLIC_KEY = "PUBLIC_KEY";
    private static final int KEY_SIZE = 2048;
    private static final String ALGORITHM_USED = "RSA";
    private KeyPairGenerator keyPairGenerator;
    private KeyPair keyPair;
    private Signature signature;

    public EncryptedExchanger() {
        initSignature();
        initKeyGenerator();
        initKeyPair();
    }

    public static byte[] encrypt(String data, PublicKey publicKey) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_USED);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] input = data.getBytes();
        cipher.update(input);
        byte[] cipherText = cipher.doFinal();
        System.out.println("Data Encrypted");
        return cipherText;
    }

    private static String decrypt(byte[] data, PrivateKey privateKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_USED);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String decrypted = new String(cipher.doFinal(data));
        return decrypted;
    }

    private void initKeyGenerator() {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_USED);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void initKeyPair() {
        keyPairGenerator.initialize(KEY_SIZE);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    public void initSignature() {
        try {
            signature = Signature.getInstance("SHA256WithDSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> decryptData(Data data) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        ArrayList<String> result = new ArrayList<>();
        String username = decrypt(data.getUsername(), keyPair.getPrivate());
        String password = decrypt(data.getPassword(), keyPair.getPrivate());
        System.out.println("Data Decrypted");
        System.out.print("Username: ");
        System.out.println(username);
        System.out.print("Password: ");
        System.out.println(password);
        result.add(username);
        result.add(password);
        return result;
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

}
