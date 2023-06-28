public class CaesarsCipher {
    private static CaesarsCipher instance;
    private CaesarsCipher() {
    }
    public  static synchronized CaesarsCipher  getInstance() {
        if (instance ==null) {
            instance = new CaesarsCipher();
        }
        return instance;
    }
}
