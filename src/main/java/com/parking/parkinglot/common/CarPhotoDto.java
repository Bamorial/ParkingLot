package com.parking.parkinglot.common;

public class CarPhotoDto {
    private long id;
    private String filename;
    private String fileType;
    private byte[] fileContent;

    public long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public CarPhotoDto(long id, String filename, String fileType, byte[] fileContent) {
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileContent = fileContent;
    }
}
