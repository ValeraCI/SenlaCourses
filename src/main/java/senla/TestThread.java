package senla;

import senla.controllers.AccountController;

public class TestThread extends Thread{

    private final String json;
    AccountController accountController;

    public TestThread(String json, AccountController accountController) {
        this.json = json;
        this.accountController = accountController;
    }

    @Override
    public void run() {
        accountController.add(json);
    }
}
