package CyphersOptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Encryptor {
    public String key;
    public String word;

     Encryptor(String key, String word){
        this.key = key;
        this.word = word;
    }

}
