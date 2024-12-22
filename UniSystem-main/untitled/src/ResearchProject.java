import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class ResearchProject implements Serializable, Comparable<ResearchProject> {

    private String topic;
    private Vector<ResearchPaper> publishedPapers ;
    private Vector<Researcher> participants ;

    public ResearchProject(String topic, Vector<ResearchPaper> publishedPapers, Vector<Researcher> participants) {
        this.topic = topic;
        this.publishedPapers = publishedPapers;
        this.participants = participants;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Vector<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
        this.publishedPapers = publishedPapers;
    }

    public void setParticipants(Vector<Researcher> participants) {
        this.participants = participants;
    }

    public void removeParticipant(Researcher participant) {
        participants.remove(participant);
        participant.getResearchProjects().remove(this);
    }
    public void addParticipant(Researcher participant) {
        if(!participants.contains(participant)) {
            participants.add(participant);
            participant.getResearchProjects().add(this);
        }
    }
    public Vector<Researcher> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchProject)) return false;
        ResearchProject that = (ResearchProject) o;
        return Objects.equals(topic, that.topic) && Objects.equals(publishedPapers, that.publishedPapers) && Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, publishedPapers, participants);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "topic='" + getTopic() + '\'' +
                ", publishedPapers=" + getPublishedPapers() +
                ", participants=" + getParticipants() +
                '}';
    }

    @Override
    public int compareTo(ResearchProject o) {
        return getParticipants().size() - o.getParticipants().size();
    }
}