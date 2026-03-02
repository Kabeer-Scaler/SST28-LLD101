public class EvaluationPipeline {
    private final PlagiarismCheck plagiarismCheck;
    private final CodeGrade codeGrade;
    private final ReportWrite reportWrite;
    private final Rubric rubric;

    public EvaluationPipeline(
            PlagiarismCheck plagiarismCheck,
            CodeGrade codeGrade,
            ReportWrite reportWrite,
            Rubric rubric) {

        this.plagiarismCheck = plagiarismCheck;
        this.codeGrade = codeGrade;
        this.reportWrite = reportWrite;
        this.rubric = rubric;
    }
    public void evaluate(Submission sub) {
        int plag = plagiarismCheck.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = codeGrade.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = reportWrite.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
