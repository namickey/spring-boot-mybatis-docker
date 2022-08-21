import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        // 公開鍵及び秘密鍵の生成
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        KeyFactory factory = KeyFactory.getInstance("RSA");
        KeySpec publicKeySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        KeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
        PublicKey publicKey = factory.generatePublic(publicKeySpec);
        PrivateKey privateKey = factory.generatePrivate(privateKeySpec);

        // 平文
        String plain = "1234";
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");

        // 公開鍵で暗号化
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        byte[] encrypted = cipher.doFinal(plain.getBytes());
        System.out.println("encrypted:" + encrypted.length + " bytes");
        String encryptedAndBase64Encoded = Base64.getEncoder().encodeToString(encrypted);
        System.out.println("base64:" + encryptedAndBase64Encoded);

        // 秘密鍵で復号
        cipher.init(Cipher.DECRYPT_MODE, privateKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedAndBase64Encoded));
        System.out.println("decrypted:" + decrypted.length + " bytes");
        System.out.println(new String(decrypted));
    }
}
