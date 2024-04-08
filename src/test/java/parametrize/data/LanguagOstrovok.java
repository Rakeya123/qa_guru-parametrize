package parametrize.data;

public enum LanguagOstrovok {
    Italiano("Abbiamo un sacco di hotel e appartamenti!"),
    English("Soooo many hotels and apartments!");
   private final String description;

    LanguagOstrovok(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
