package BruteForce;



import static CyphersOptions.Decryptor.decrypt;

public class BruteForce {
    public static BruteForceImpl bruteForceImpl = new BruteForceImpl();

    public static String bruteforce(String encryptedWord){
        String decryptedWord = "";
        int matchesMax = 0;
        String key = null;
        StringBuilder builder;
        String[] dictionary = bruteForceImpl.getLanguage(encryptedWord);
        for(String decrypted : dictionary){
            builder = new StringBuilder(decrypt(decrypted,encryptedWord));
            int matches = bruteForceImpl.counter(String.valueOf(builder), dictionary);
            if(matches > matchesMax){
                matchesMax = matches;
                decryptedWord = String.valueOf(builder);
                key = decrypted;
            }
        }


        return "key: " + key + " text: " + decryptedWord;
    }
}
