package ee.taltech.team_11_backend.security;

public enum Role {
    USER, ADMIN;

    public String toSpringRole(){
        return "ROLE_" + this.name();
    }

    public boolean isAdmin(){
        return this == ADMIN;
    }
}
