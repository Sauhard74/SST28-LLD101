import java.util.*;

public class EligibilityEngine {
    private final EligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(EligibilityStore store, List<EligibilityRule> rules) {
        this.store = store;
        this.rules = rules;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            String violation = rule.checkViolation(s);
            if (violation != null) {
                reasons.add(violation);
                break;
            }
        }

        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        return new EligibilityEngineResult(status, reasons);
    }
}
