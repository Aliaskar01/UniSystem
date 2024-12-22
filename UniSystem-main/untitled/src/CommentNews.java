import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class CommentNews implements Serializable, Comparable<CommentNews> {

    private News parentNews; 
    private Date timestamp; 
    private String content;
    private User author; 
    private boolean pinned; 


    public CommentNews(News parentNews, String content, User author) {
        this.parentNews = parentNews;
        this.timestamp = new Date(); 
        this.content = content;
        this.author = author;
        this.pinned = false; 
    }

    public News getParentNews() {
        return parentNews;
    }

    public void setParentNews(News parentNews) {
        this.parentNews = parentNews;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }


    public void pinComment() {
        this.pinned = true;
        System.out.println("Comment pinned.");
    }

    public void unpinComment() {
        this.pinned = false;
        System.out.println("Comment is no longer pinned.");
    }

    public void editComment(String newContent) {
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


    @Override
    public String toString() {
        return "Comment{" +
                "parentNews=" + (parentNews != null ? parentNews.getTitle() : "null") +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                ", author=" + (author != null ? author.getFirstName() + author.getLastName() : "null") +
                ", pinned=" + pinned +
                '}';
    }

    @Override
    public int compareTo(CommentNews o) {
        return timestamp.compareTo(o.getTimestamp());
    }
}
