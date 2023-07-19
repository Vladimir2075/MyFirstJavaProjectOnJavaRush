import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class UtilsAlphabetAndCheckPath {
    private static UtilsAlphabetAndCheckPath instance;
    public final int CHAR_NOT_FOUND = -1;

    private UtilsAlphabetAndCheckPath(){
    }
    public static UtilsAlphabetAndCheckPath getInstance() {
        if (instance == null) {
            instance = new UtilsAlphabetAndCheckPath();
        }
        return instance;
    }
    public Path checkCorrectpath(String nameInputString, boolean checkExistsFile) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(nameInputString);
        Path resultPath = Path.of("");
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
    public final List<Character> CYR_LETTERS = Arrays.asList(' ','а','б','в','г', 'д','е','є','ж','з','и','і','ї','й','к',
            'л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ь','ю','я',
            'А','Б','В','Г', 'Д','Е','Є','Ж','З','И','І','Ї','Й','К',
            'Л','М','Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ю','Я',
            '.', ',','"',':','-','!','?');
}
