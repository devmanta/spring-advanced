package hello.advanced.part2.proxy.pureproxy.proxy.code;

public class ProxyPatternSubject {

    private final Subject subject;

    public ProxyPatternSubject(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        subject.operation();
    }
}
