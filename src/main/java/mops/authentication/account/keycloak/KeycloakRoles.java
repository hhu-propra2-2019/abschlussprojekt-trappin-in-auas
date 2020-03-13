package mops.authentication.account.keycloak;

//TODO: make this work with @Secured annotation, to have a singular place in which we can configure roles and shit
public enum KeycloakRoles{
    ORGA("ROLE_orga"),
    STUDENT("ROLE_student");

    private final String roleString;

    /**
     * @param roleString
     */
    KeycloakRoles(final String roleString) {
        this.roleString = roleString;
    }

    @Override
    public String toString() {
        return roleString;
    }
}