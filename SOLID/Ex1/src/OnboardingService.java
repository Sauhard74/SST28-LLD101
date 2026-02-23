import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final StudentParser parser;
    private final StudentValidator validator;

    public OnboardingService(StudentRepository db, StudentParser parser, StudentValidator validator) {
        this.db = db;
        this.parser = parser;
        this.validator = validator;
    }

    public RegistrationResult registerFromRawInput(String raw) {
        StudentRequest request = parser.parse(raw);

        List<String> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return RegistrationResult.failure(errors);
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, request.name, request.email, request.phone, request.program);
        db.save(rec);

        return RegistrationResult.success(rec);
    }
}
