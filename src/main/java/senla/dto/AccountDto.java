package senla.dto;

import senla.util.Role;

public class AccountDto {
    private long id;
    private String nickname;
    private Role role;

    public AccountDto() {
    }

    public AccountDto(long id, String nickname, Role role) {
        this.id = id;
        this.nickname = nickname;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
