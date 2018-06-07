package com.heshammassoud;

public final class Messages {
    public static final String MAIN_MENU_MESSAGE = "1. Play around with commercetools project data.<br>"
            + "2. Play table tennis.";
    public static final String WELCOME_MESSAGE = "Hi %s! What can I do for you today?:)<br>" + MAIN_MENU_MESSAGE;
    public static final String WISH_IS_MY_COMMAND_MESSAGE = "Your wish is my command (obey) I just need a small "
            + "favour to completely obey your wishes.";
    public static final String ASK_CREDENTIALS = "Please provide me the credentials to be able to access your CTP "
            + "project (Those can be found here **link**).";
    public static final String ASK_CREDENTIALS_MESSAGE_FIRST_TIME =
            WISH_IS_MY_COMMAND_MESSAGE + "\n" + ASK_CREDENTIALS;
    public static final String ASK_CURRENT_PROJECT_KEY = "Please enter the project key";
    public static final String ASK_CURRENT_CLIENT_SECRET = "Please enter the client secret";
    public static final String ASK_CURRENT_CLIENT_ID = "Please enter the client id";
    public static final String SUCCESSFULLY_ACCESSED_PROJECT = "YAYY!! SUCESS!! GREEN GREEN GUGGY GUGGY!";
    public static final String WISH_IS_TRULY_MY_COMMAND = "Now, your wish is truly my command (geeny)";
    public static final String CTP_PLAYGROUND_MENU =
            "1. View products.\n"
                    + "2. View product types.\n"
                    + "3. View inventory entries.\n"
                    + "4. Delete all products.\n"
                    + "5. Delete all product types.\n"
                    + "6. Delete all inventory entries.\n"
                    + "7. Sync products to a target commercetools project. (Please make sure all the prerequisites are"
                    + " set: references)\n" + "8. Sync product types to a target commercetools project. (Please make "
                    + "sure all the prerequisites are set: references)\n"
                    + "9. Sync categories to a target commercetools project. "
                    + "(Please make sure all the prerequisites are set: references)\n"
                    + "10. Sync inventory entries to a target commercetools project. (Please "
                    + "make sure all the prerequisites are set: references)\n"
                    + "11. Change context. (back to main menu)";
    public static final String FUN_FACT_1 = "Nice! Fun fact: the sync process uses a pretty cool java "
            + "library (commercetools-sync-java) underneath which is actually developed by the 'professional-services'"
            + " library. Give them some Kudos for it!";

    public Messages() {
    }
}
