import java.util.Scanner;

public class MainMenu {
    private final String NAME_FIRST_OPTION = "1.Шифр Цезаря";
    private final String NAME_FIRST_FIRST_OPTION = "1.Шифрування (для вибору введіть 1 ";
    private final String NAME_FIRST_SECOND_OPTION = "2.Дешифрування (для вибору введіть 2)";
    private final String NAME_FIRST_THIRD_OPTION = "3.Перехід в основне меню(для вибору введіть 3)";
    private final String NAME_SECOND_OPTION = "2.Криптоаналіз методом brute force";
    private final String YOUR_CHOICE = "Ваш вибір:";
    private final String NAME_OPTION_EXIT = "3.Вихід (3)";

    private final int MAIN_FIRST_LEVEL=0;
    private final int MAIN_SECOND_LEVEL=1;
    private final int CHOICE_FIRST_OPTION =1;
    private final int CHOICE_SECOND_OPTION = 2;
    private final int CHOICE_EXIT=3;

    final boolean IS_ENCRYPTION = true;
    final boolean IS_DECRYPTION = false;
    private final CaesarsCipher CAESARS_CIPHER=CaesarsCipher.getInstance();
    private final BrutForceCryptoAnalysis brutForceCryptoAnalysis = BrutForceCryptoAnalysis.getInstance();
    public MainMenu() {
        System.out.println("Головне меню:");
        RunMainMenu(MAIN_FIRST_LEVEL);
    }

    public void RunMainMenu (int level){
        boolean isExit = false;
        Scanner keyboard = new Scanner(System.in);
        if (level == MAIN_FIRST_LEVEL) {
            menuDrawing(NAME_FIRST_OPTION,NAME_SECOND_OPTION,NAME_OPTION_EXIT);
        } else {
            menuDrawing(NAME_FIRST_FIRST_OPTION,NAME_FIRST_SECOND_OPTION, NAME_FIRST_THIRD_OPTION);
        }
        int yourChoice=-1;
        while (!isExit) {
            if (keyboard.hasNextInt()) {
                yourChoice = keyboard.nextInt();
            }
            if (level == MAIN_FIRST_LEVEL) {
                if (yourChoice == CHOICE_FIRST_OPTION) {
                    RunMainMenu(MAIN_SECOND_LEVEL);
                } else if (yourChoice == CHOICE_SECOND_OPTION) {
                    System.out.println(brutForceCryptoAnalysis.getKeyValueByBrutForceCryptoAnalysis());
                    menuDrawing(NAME_FIRST_OPTION,NAME_SECOND_OPTION,NAME_OPTION_EXIT);
                } else if (yourChoice == CHOICE_EXIT) {
                    isExit = true;
                }
            } else {
                if (yourChoice == CHOICE_FIRST_OPTION) {
                    CAESARS_CIPHER.encryptionDecryption(IS_ENCRYPTION);
                    System.out.println("Файл успішно зашифрований");
                    menuDrawing(NAME_FIRST_OPTION, NAME_SECOND_OPTION, NAME_OPTION_EXIT);
                    isExit = true;
                } else if (yourChoice == CHOICE_SECOND_OPTION) {
                    CAESARS_CIPHER.encryptionDecryption(IS_DECRYPTION);
                    System.out.println("Файл успішно розшифрований");
                    menuDrawing(NAME_FIRST_OPTION, NAME_SECOND_OPTION, NAME_OPTION_EXIT);
                    isExit = true;
                } else if (yourChoice == CHOICE_EXIT) {
                    menuDrawing(NAME_FIRST_OPTION, NAME_SECOND_OPTION, NAME_OPTION_EXIT);
                    isExit = true;
                }
            }
        }
    }

    private void menuDrawing (String nameFirstOption, String nameSecondOption, String nameThirdOption){
        System.out.println(nameFirstOption);
        System.out.println(nameSecondOption);
        System.out.println(nameThirdOption);
        System.out.print(YOUR_CHOICE);
    }

}
