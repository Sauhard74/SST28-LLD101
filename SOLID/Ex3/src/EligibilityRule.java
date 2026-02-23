public interface EligibilityRule {
    /**
     * Check if the rule is violated for the given student profile.
     * @param student The student profile to check
     * @return The reason string if rule is violated, null if rule passes
     */
    String checkViolation(StudentProfile student);
}
