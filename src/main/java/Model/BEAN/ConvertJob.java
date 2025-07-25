package Model.BEAN;

public class ConvertJob {
    private int id;
    private int userId;
    private String title;
    private String pdfPath;
    private String docPath;
    private String status;

    public ConvertJob() {
    }

    public ConvertJob(int id, int userId, String pdfPath, String docPath, String status, String title) {
        this.id = id;
        this.userId = userId;
        this.pdfPath = pdfPath;
        this.docPath = docPath;
        this.status = status;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
