import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Comment implements Serializable, Comparable<Comment> {
    private Integer commentId; 
    private String authorName; 
    private String content; 
    private Date timestamp; 
    private Integer relatedObjectId;
    private boolean isPinned; 

    
    public Comment(Integer commentId, String authorName, String content, Integer relatedObjectId) {
        this.commentId = commentId;
        this.authorName = authorName;
        this.content = content;
        this.timestamp = new Date();
        this.relatedObjectId = relatedObjectId;
        this.isPinned = false; 
    }

   
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getRelatedObjectId() {
        return relatedObjectId;
    }

    public void setRelatedObjectId(Integer relatedObjectId) {
        this.relatedObjectId = relatedObjectId;
    }

   
    public void pinComment() {
        this.isPinned = true;
        System.out.println("Comment was pinned.");
    }

   
    public void unpinComment() {
        this.isPinned = false;
        System.out.println("Comment is no longer pinned.");
    }

  
    public void editContent(String newContent) {
        this.content = newContent;
        System.out.println("Comment was edited.");
    }

 
    public void deleteComment(Vector<Comment> commentList) {
        if (commentList.remove(this)) {
            System.out.println("Comment was successfully deleted.");
        } else {
            System.out.println("Comment is not found in the list.");
        }
    }

    public boolean isPinned() {
        return isPinned;
    }

  
    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", authorName='" + authorName + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", relatedObjectId=" + relatedObjectId +
                ", isPinned=" + isPinned +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return isPinned == comment.isPinned && Objects.equals(commentId, comment.commentId) && Objects.equals(authorName, comment.authorName) && Objects.equals(content, comment.content) && Objects.equals(timestamp, comment.timestamp) && Objects.equals(relatedObjectId, comment.relatedObjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, authorName, content, timestamp, relatedObjectId, isPinned);
    }

    @Override
    public int compareTo(Comment o) {
        return timestamp.compareTo(o.timestamp);
    }
}
