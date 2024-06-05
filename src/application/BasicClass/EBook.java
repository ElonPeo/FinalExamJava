package application.BasicClass;

public class EBook extends Book {

    private String fileFormat;
    private double fileSize;
    public EBook(String title, String author, int yearPublished, String fileFormat, double fileSize) {
        super(title, author, yearPublished);
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    public String getFileFormat() {
        return fileFormat;
    }
    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public double getFileSize() {
        return fileSize;
    }
    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }


    @Override
    public String toString() {
        return super.toString() + "\nFile Format: " + fileFormat + "\nFile Size: " + fileSize + " MB";
    }
}
