import java.util.*;

public class RegistrationResult {
    public final boolean success;
    public final StudentRecord student;
    public final List<String> errors;

    private RegistrationResult(boolean success, StudentRecord student, List<String> errors) {
        this.success = success;
        this.student = student;
        this.errors = errors;
    }

    public static RegistrationResult success(StudentRecord student) {
        return new RegistrationResult(true, student, List.of());
    }

    public static RegistrationResult failure(List<String> errors) {
        return new RegistrationResult(false, null, errors);
    }
}
