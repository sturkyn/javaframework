package qa.utils;

public enum PropKeys {
    URL("app.url"),
    HOMEPAGE_URL("homepage.url"),
    SCREEN_ON_STEP("screen.on.step"),
    RETRY_LIMIT("retry.limit"),
    SYSTEM_LOGS("system.logs"),
    HEADLESS("headless"),
    MAIL_API_KEY("mailslurp.api.key");

    private final String propName;

    PropKeys(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }
}
