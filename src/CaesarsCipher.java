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
    private static CaesarsCipher instance;
    private final int KEY = 3;

    private final List<Character> CYR_LETTERS = Arrays.asList('а','б','в','г', 'д','е','є','ж','з','и','і','ї','й','к',
            'л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ь','ю','я',
            'А','Б','В','Г', 'Д','Е','Є','Ж','З','И','І','Ї','Й','К',
            'Л','М','Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ю','Я',
            '.', ',','"',':','-','!','?',' ');

    private CaesarsCipher() {
    }
    public  static synchronized CaesarsCipher  getInstance() {
        if (instance ==null) {
            instance = new CaesarsCipher();
        }
        return instance;
    }

    private char newChar (char ch, int key) {
        int  currPosition =  CYR_LETTERS.indexOf(ch);
        int newPosition;
        if (currPosition ==-1) {
            return ch;
        } else {
            if  ((currPosition + key) >=0) {
                newPosition = ((currPosition + key) > (CYR_LETTERS.size() - 1)) ? (currPosition + key - CYR_LETTERS.size()) : (currPosition + key);
            } else {
                newPosition =  CYR_LETTERS.size() + (currPosition + key);
            }
            return CYR_LETTERS.get(newPosition);
        }
    }
    private Path checkCorrectpath (String nameInputString, boolean checkExistsFile) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(nameInputString);
        Path resultPath =Path.of("");
        if (checkExistsFile) {
            boolean isCorrectivePath = false;
            while (!isCorrectivePath) {
                resultPath = Path.of(keyboard.nextLine());
                if (Files.isRegularFile(resultPath)) {
                    isCorrectivePath = true;
                } else {
                    System.out.println(nameInputString);
                }
            }
        } else {
            resultPath = Path.of(keyboard.nextLine());
        }

        return resultPath;
    }

    void encryptionDecryption (boolean isEncryption){
        int localKey =  isEncryption?KEY:(-1*KEY);
        Path sourceFilePath = checkCorrectpath ("Вкажіть шляд до файлу який потрібно зчитати:", true);
        Path targetFile = checkCorrectpath ("Вкажіть шляд до файлу куди записати результат:", false);
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
                    charBufferOut.append(newChar(temp,localKey));
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
