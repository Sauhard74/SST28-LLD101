public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        FakeDb db = new FakeDb();
        OnboardingService svc = new OnboardingService(db, new StudentParser(), new StudentValidator());

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        System.out.println("INPUT: " + raw);
        RegistrationResult result = svc.registerFromRawInput(raw);

        if (result.success) {
            System.out.println("OK: created student " + result.student.id);
            System.out.println("Saved. Total students: " + db.count());
            System.out.println("CONFIRMATION:");
            System.out.println(result.student);
        } else {
            System.out.println("ERROR: cannot register");
            for (String e : result.errors)
                System.out.println("- " + e);
        }

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
