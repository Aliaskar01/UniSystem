import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class Journal implements Serializable, Comparable<Journal> {

    private Vector<ResearchPaper> researchPapers; 
    private String topic;
    private String name; 
    private int journalId;
    private Vector<User> subscribers; 

   
    public Journal(int journalId, String name, String topic) {
        this.journalId = journalId;
        this.name = name;
        this.topic = topic;
        this.researchPapers = new Vector<>();
        this.subscribers = new Vector<>();
    }


    public Vector<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJournalId() {
        return journalId;
    }

    public void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public Vector<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Vector<User> subscribers) {
        this.subscribers = subscribers;
    }


    public void addResearchPaper(ResearchPaper researchPaper) {
        if (researchPaper == null) {
            throw new IllegalArgumentException("Publication can't be null.");
        }
        researchPapers.add(researchPaper);
        System.out.println("Publication added: " + researchPaper.getTitle());
        notifySubscribers(researchPaper);
    }

    public void printJournalInfo() {
        System.out.println("Journal: " + name);
        System.out.println("Topic: " + topic);
        System.out.println("Publication count: " + getPaperCount());
        System.out.println("Publications:");
        for (ResearchPaper paper : researchPapers) {
            System.out.println("- " + paper.getTitle());
        }
        System.out.println("Subscribers:");
        for (User subscriber : subscribers) {
            System.out.println("- " + subscriber.getFirstName() + subscriber.getLastName() );
        }
    }

   
    public Integer getPaperCount() {
        return researchPapers.size();
    }

  
    public ResearchPaper findResearchPaper(String name) {
        for (ResearchPaper paper : researchPapers) {
            if (paper.getTitle().equalsIgnoreCase(name)) {
                return paper;
            }
        }
        System.out.println("Publication with name \"" + name + "\" not found.");
        return null;
    }

    public void removeResearchPaper(ResearchPaper researchPaper) {
        if (researchPapers.remove(researchPaper)) {
            System.out.println("Publication deleted: " + researchPaper.getTitle());
        } else {
            System.out.println("Publication is missing in the journal.");
        }
    }


    public void subscribe(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null.");
        }
        if (!subscribers.contains(user)) {
            subscribers.add(user);
            System.out.println("User " + user.getFirstName() + user.getLastName() + " has been subscribed on: " + name);
        } else {
            System.out.println("User is already subscribed: " + name);
        }
    }


    public void unsubscribe(User user) {
        if (subscribers.remove(user)) {
            System.out.println("User " + user.getFirstName() + user.getLastName()  + " unsubscribed from: " + name);
        } else {
            System.out.println("User is not subscribed.");
        }
    }

    public void notifySubscribers(ResearchPaper paper) {
        for (User user : subscribers) {
            user.addNotification(Hub.getInstance().getFactory().createMessage("The Journal: " + toString() + " Have been updated with: " + paper.toString(), user, (User) paper.getAuthors().get(0)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journal)) return false;
        Journal journal = (Journal) o;
        return journalId == journal.journalId && Objects.equals(researchPapers, journal.researchPapers) && Objects.equals(topic, journal.topic) && Objects.equals(name, journal.name) && Objects.equals(subscribers, journal.subscribers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(researchPapers, topic, name, journalId, subscribers);
    }

    @Override
    public String toString() {
        return "Journal{" +
                "researchPapers=" + getResearchPapers() +
                ", topic='" + getTopic() + '\'' +
                ", name='" + getName() + '\'' +
                ", journalId=" + getJournalId() +
                ", subscribers=" + getSubscribers() +
                '}';
    }

    @Override
    public int compareTo(Journal o) {
        return subscribers.size() - o.getSubscribers().size();
    }
}

