package sk.balaz.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
