public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    public boolean canSend(Notification n) {
        return n != null;
    }

    public abstract void send(Notification n);
}
