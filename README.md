# Vigen-reCipher

## about Vigen-reCipher:
The Vigenère cipher is a method of encrypting text by using a repeated keyword to shift each letter by a different amount.
Each letter of the keyword determines how many positions the corresponding letter in the plaintext is shifted in the alphabet.

1. You choose a keyword (e.g., Hello).

2. You repeat the keyword so it matches the length of the message.

3. For each letter:
- Convert the plaintext letter to its alphabetical index (A=0, B=1, …).
- Convert the keyword letter the same way.
- Add the indexes (mod 26).
4. Convert the result back to a letter — that’s the encrypted text.

### To decrypt, you subtract the keyword’s shift instead of adding it.

### Notes:
- This Cypher can ENCRYPT or DECRYPT your sentences.
- The Vigen-reCipher similar to Cesar Cipher but more protected(There is only one key(int) in the Cesar Cipher and different keys in the Vigen-reCipher along main text)
- You can find correct texts or sentences by using this program.
- You can decrypt or encrypt text by using Vigen-reCipher.
- Support for UKRAINIAN and ENGLISH languages in encryption, decryption, and also in brute force
- Supports upper and lower case letters, spaces + symbols (does not encrypt).
- The keyword gets the text length automatically.
- The project will be improved in the future(tests, CLI)

### Last Upgrades:
- We have an automatic program that can obtain decrypted text without a key.
- The program now takes into account spaces in the specified parameters.


### Brute force
- Сan decrypt text automatically (Vigen-re Cipher) and find the correct key.

### Commands:

- -e Encrypt
- -d Decrypt
- -b Brute force

### Arguments:

- You need to write the arguments in the order: encrypt/decrypt/bruteforce(e,d,bf) + key(Special word) + file path.
  In the case of bruteforce, a key is not needed.

### Example:

- -e -k "word" -f "/path/to/file.txt" - Encrypts file with key (special word)
- -d -k "word" -f "/path/to/file [ENCRYPTED].txt" - Decrypts file with key (special word)
- -bf -f "/path/to/file [ENCRYPTED] + key +.txt" - Brute force decrypts file without word and gives key automatically
