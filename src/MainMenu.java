public class MainMenu {
    final String NAME_FIRST_OPTION = "1.Шифр Цезаря";
    final String NAME_FIRST_FIRST_OPTION = "1.Шифрування (для вибору введіть 1 ";
    final String NAME_FIRST_SECOND_OPTION = "2.Дешифрування (для вибору введіть 2)";
    final String NAME_FIRST_THIRD_OPTION = "3.Перехід в основне меню(для вибору введіть 3)";
    final String NAME_SECOND_OPTION = "2.Криптоаналіз методом brute force";
    final String YOUR_CHOICE = "Ваш вибір:";
    final String NAME_OPTION_EXIT = "3.Вихід (3)";
    public MainMenu() {
        System.out.println("Головне меню:");
        RunMainMenu(0);
    }

    // level -Menu level
    public void RunMainMenu (int level){

    }

    private void menuDrawing (String arg1, String arg2, String arg3){
        System.out.println(arg1);
        System.out.println(arg2);
        System.out.println(arg3);
        System.out.print(YOUR_CHOICE);
    }

}
