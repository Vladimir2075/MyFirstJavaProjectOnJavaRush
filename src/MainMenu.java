import java.util.Scanner;

public class MainMenu {
    final String NAME_FIRST_OPTION = "1.Шифр Цезаря";
    final String NAME_FIRST_FIRST_OPTION = "1.Шифрування (для вибору введіть 1 ";
    final String NAME_FIRST_SECOND_OPTION = "2.Дешифрування (для вибору введіть 2)";
    final String NAME_FIRST_THIRD_OPTION = "3.Перехід в основне меню(для вибору введіть 3)";
    final String NAME_SECOND_OPTION = "2.Криптоаналіз методом brute force";
    final String YOUR_CHOICE = "Ваш вибір:";
    final String NAME_OPTION_EXIT = "3.Вихід (3)";
    private final CaesarsCipher CAESARS_CIPHER=CaesarsCipher.getInstance();
    public MainMenu() {
        System.out.println("Головне меню:");
        RunMainMenu(0);
    }

    //level =1  - головне меню; level =2- підменю
    public void RunMainMenu (int level){
        boolean isExit = false;
        Scanner keyboard = new Scanner(System.in);
        if (level ==0) {
            menuDrawing(NAME_FIRST_OPTION,NAME_SECOND_OPTION,NAME_OPTION_EXIT);
        } else {
            menuDrawing(NAME_FIRST_FIRST_OPTION,NAME_FIRST_SECOND_OPTION, NAME_FIRST_THIRD_OPTION);
        }
        int yourChoice=-1;
        while (!isExit) {
            if (keyboard.hasNextInt()) {
                yourChoice = keyboard.nextInt();
            }
            if (level == 0) {
                if (yourChoice == 1) {
                    RunMainMenu(1);
                } else if (yourChoice == 2) {

                    isExit = true;
                } else if (yourChoice == 3) {
                    isExit = true;
                }
            } else {
                if (yourChoice == 1) {
                    CAESARS_CIPHER.encryptionDecryption(true);
                    System.out.println("Файл успішно зашифрований");
                    menuDrawing(NAME_FIRST_OPTION, NAME_SECOND_OPTION, NAME_OPTION_EXIT);
                    isExit = true;
                } else if (yourChoice == 2) {
                    CAESARS_CIPHER.encryptionDecryption(false);
                    System.out.println("Файл успішно розшифрований");
                    menuDrawing(NAME_FIRST_OPTION, NAME_SECOND_OPTION, NAME_OPTION_EXIT);
                    isExit = true;
                } else if (yourChoice == 3) {
                    menuDrawing(NAME_FIRST_OPTION, NAME_SECOND_OPTION, NAME_OPTION_EXIT);
                    isExit = true;
                }
            }
        }
    }

    private void menuDrawing (String arg1, String arg2, String arg3){
        System.out.println(arg1);
        System.out.println(arg2);
        System.out.println(arg3);
        System.out.print(YOUR_CHOICE);
    }

}
