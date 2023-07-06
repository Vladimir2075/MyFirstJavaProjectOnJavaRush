import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BrutForceCryptoAnalysis {

    private static BrutForceCryptoAnalysis instance;
    private final Utils  utils=Utils.getInstance();
    private final String keyValueNotFound  ="Невозможно определить значение ключа";
    private BrutForceCryptoAnalysis (){}
    private String getKeyValue (char currentChar) {
        char charOriginal = ' ';
        int  positionCurrentChar =  utils.CYR_LETTERS.indexOf(currentChar);
        int  positionCharOriginal = utils.CYR_LETTERS.indexOf(charOriginal);

        if (positionCurrentChar ==utils.CHAR_NOT_FOUND | positionCharOriginal ==utils.CHAR_NOT_FOUND) {
            return keyValueNotFound;
        } else {
            return "Значення ключа ="+ String.valueOf((positionCurrentChar - positionCharOriginal));
        }
    }

    public static BrutForceCryptoAnalysis getInstance() {
        if (instance == null) {
            instance = new BrutForceCryptoAnalysis();
        }
        return instance;
    }

    public String getKeyValueByBrutForceCryptoAnalysis() {
        String key=null;
        Path sourceFilePath = utils.checkCorrectpath ("Вкажіть шляд до зашифрованого файлу:", true);
        try (FileChannel fileChannel =FileChannel.open(sourceFilePath, StandardOpenOption.READ)) {
            ByteBuffer byteBufferReader = ByteBuffer.allocate(4);
            if(fileChannel.read(byteBufferReader) >0) {
                byteBufferReader.flip();
                CharBuffer charBuffer = Charset.forName("WINDOWS-1251").decode(byteBufferReader);
                key  =getKeyValue(charBuffer.get(0));
            } else {
                key = keyValueNotFound;
            }


        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return key;
    }

}
