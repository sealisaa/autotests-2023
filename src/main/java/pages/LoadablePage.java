package pages;

public abstract class LoadablePage {
    abstract void checkPage();

    public LoadablePage() {
        checkPage();
    }
}
