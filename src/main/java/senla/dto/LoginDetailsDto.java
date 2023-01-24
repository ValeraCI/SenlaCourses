package senla.dto;

public class LoginDetailsDto {
    private long accountId;
    private String email;
    private String password;

    public LoginDetailsDto(){}

    public LoginDetailsDto(long accountId, String email, String password) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
