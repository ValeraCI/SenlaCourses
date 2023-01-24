package senla.models;

public class LoginDetails {
    private final long accountId;
    private final String email;
    private String password;

    public LoginDetails(Long accountId, String email, String password) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
