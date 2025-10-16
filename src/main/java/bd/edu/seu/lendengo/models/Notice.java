package bd.edu.seu.lendengo.models;

import java.time.LocalDateTime;

public class Notice {
    private int id;
    private String title;
    private String content;
    private String type;
    private String status;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Notice() {
    }

    public Notice(String title, String content, String type, String status, String createdBy) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.status = status;
        this.createdBy = createdBy;
    }

    public Notice(int id, String title, String content, String type, String status, String createdBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.status = status;
        this.createdBy = createdBy;
    }

    public Notice(int id, String title, String content, String type, String status, String createdBy, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime updatedAt) {
        this.modifiedAt = updatedAt;
    }
}
