import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CaesarsCipher {
    private Utils  utils =Utils.getInstance();
    private final static CaesarsCipher instance=getInstance();
    private final int KEY_ENCRYPTION = 3;
    private final int KEY_DECRYPTION = -3;



    private CaesarsCipher() {
    }
    public  static CaesarsCipher  getInstance() {
        return instance;
    }

    private char getNewChar (char currentChar, int key) {
        int  currPosition =  utils.CYR_LETTERS.indexOf(currentChar);
        int newPosition;
        if (currPosition ==utils.CHAR_NOT_FOUND) {
            return currentChar;
        } else {
            if  ((currPosition + key) >=0) {
                newPosition = ((currPosition + key) > (utils.CYR_LETTERS.size() - 1)) ? (currPosition + key - utils.CYR_LETTERS.size()) : (currPosition + key);
            } else {
                newPosition =  utils.CYR_LETTERS.size() + (currPosition + key);
            }
            return utils.CYR_LETTERS.get(newPosition);
        }
    }


    void encryptionDecryption (boolean isEncryption){
        int localKey =  isEncryption?KEY_ENCRYPTION:KEY_DECRYPTION;
        Path sourceFilePath = utils.checkCorrectpath ("Вкажіть шляд до файлу який потрібно зчитати:", true);
        Path targetFile = utils.checkCorrectpath ("Вкажіть шляд до файлу куди записати результат:", false);
        if (!Files.exists(targetFile)) {
            try {
                Files.createFile(targetFile);
            } catch (IOException e) {e.printStackTrace();}

        }
        try (FileChannel fileChannelRead =FileChannel.open(sourceFilePath, StandardOpenOption.READ);
             FileChannel fileChannelWrite =FileChannel.open(targetFile,StandardOpenOption.WRITE)
        ) {
            ByteBuffer bufferRead = ByteBuffer.allocate(30);
            ByteBuffer  bufferWrite = ByteBuffer.allocate(30);
            int readByte =fileChannelRead.read(bufferRead);
            CharBuffer charBufferIn;
            CharBuffer charBufferOut= Charset.forName("WINDOWS-1251").decode(bufferWrite);
            while (readByte >0) {
                bufferRead.flip();
                charBufferIn = Charset.forName("WINDOWS-1251").decode(bufferRead);
                while (charBufferIn.hasRemaining()) {
                    char temp = charBufferIn.get();
                    charBufferOut.append(getNewChar(temp,localKey));
                }
                bufferWrite.flip();
                charBufferOut.flip();
                bufferWrite.put(Charset.forName("WINDOWS-1251").encode(charBufferOut));
                bufferWrite.flip();
                fileChannelWrite.write(bufferWrite);
                charBufferOut.clear();
                bufferRead.flip();
                readByte =fileChannelRead.read(bufferRead);
            }
        } catch ( IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
