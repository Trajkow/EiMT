package mk.ukim.finki.backend.model.dto.inputDTO;

public record RegisterRequestDTO(String username, String password, String role) {
    public String resolvedRole() {
        if (role != null && role.equals("ROLE_ADMINISTRATOR")) {
            return "ROLE_ADMINISTRATOR";
        }
        return "ROLE_USER";
    }
}
