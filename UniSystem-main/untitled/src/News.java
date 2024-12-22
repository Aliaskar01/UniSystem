import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class News implements Serializable, Comparable<News> {

    private int newsId; 
    private String title; 
    private String content; 
    private String topic; 
    private Vector<Comment> comments;
    private Date date;


    public News(int newsId, String title, String content, String topic, Date date) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.comments = new Vector<>();
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Vector<Comment> getComments() {
        return comments;
    }

    public void setComments(Vector<Comment> comments) {
        this.comments = comments;
    }


    public void addComment(Comment comment) {
        if (comment != null) {
            comments.add(comment);
            System.out.println("Comment added: " + comment.getContent());
        } else {
            System.out.println("Comment can't be null.");
        }
    }

  
    public void removeComment(Comment comment) {
        if (comments.remove(comment)) {
            System.out.println("Comment deleted: " + comment.getContent());
        } else {
            System.out.println("Comment not found.");
        }
    }


    public void editNews(String newTitle, String newContent) {
        this.title = newTitle;
        this.content = newContent;
        System.out.println("News is edited: " + title);
    }


    public void printNewsInfo() {
        System.out.println("News ID: " + newsId);
        System.out.println("Title: " + title);
        System.out.println("Topic: " + topic);
        System.out.println("Content: " + content);
        System.out.println("Comments: ");
        for (Comment comment : comments) {
            System.out.println("- " + comment.getContent());
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + getNewsId() +
                ", title='" + getTitle() + '\'' +
                ", content='" + getContent() + '\'' +
                ", topic='" + getTopic() + '\'' +
                ", comments=" + getComments() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return newsId == news.newsId && Objects.equals(title, news.title) && Objects.equals(content, news.content) && Objects.equals(topic, news.topic) && Objects.equals(comments, news.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, title, content, topic, comments);
    }

    @Override
    public int compareTo(News o) {
        return date.compareTo(o.getDate());
    }
}
