package pro.sky.finalprojectsky.constant;

/**
 * Класс констант для проверки номера телефона и почты
 */
public final class Regexp {

    public static final String EMAIL_REGEXP = ".+@.+[.]..+";

    public static final String PHONE_REGEXP = "^\\+\\d\\(\\d\\d\\d\\)\\d\\d\\d\\-\\d\\d\\-\\d\\d$";

    private Regexp() {
    }
}
