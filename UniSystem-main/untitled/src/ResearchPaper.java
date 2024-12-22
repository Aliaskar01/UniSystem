import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {

    private String title; 
    private Vector<Researcher> authors; 
    private ResearchProject researchProject; 
    private int pages; 
    private Date publicationDate; 
    private String doi; 
    private int citations; 


    public ResearchPaper(String title, Vector<Researcher> authors, ResearchProject researchProject, int pages, String doi, int citations, Date publicationDate) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty.");
        }
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException("Author list can't be empty.");
        }
        if (researchProject == null) {
            throw new IllegalArgumentException("Research project can't be null");
        }
        this.title = title;
        this.authors = authors;
        this.researchProject = researchProject;
        this.pages = pages;
        this.doi = doi;
        this.citations = citations;
        this.publicationDate = publicationDate;
    }

    public ResearchPaper(String title, Vector<Researcher> authors, int pages, String doi, int citations, Date publicationDate) {
        this.title = title;
        this.authors = authors;
        this.pages = pages;
        this.doi = doi;
        this.citations = citations;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Paper name can't be empty");
        }
        this.title = title;
    }

    public Vector<Researcher> getAuthors() {
        return authors;
    }

    public void setAuthors(Vector<Researcher> authors) {
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException("Author list can't be empty or null");
        }
        this.authors = authors;
    }

    public ResearchProject getResearchProject() {
        return researchProject;
    }

    public void setResearchProject(ResearchProject researchProject) {
        if (researchProject == null) {
            throw new IllegalArgumentException("Research project can't be null");
        }
        this.researchProject = researchProject;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages' count must be greater than 0");
        }
        this.pages = pages;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        if (publicationDate == null) {
            throw new IllegalArgumentException("Publication Date can't be null");
        }
        this.publicationDate = publicationDate;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        if (doi == null || doi.isEmpty()) {
            throw new IllegalArgumentException("DOI can't be empty");
        }
        this.doi = doi;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        if (citations < 0) {
            throw new IllegalArgumentException("Citation count can't be negative");
        }
        this.citations = citations;
    }


    public String getCitation(Format format) {
        switch (format) {
            case PLAIN_TEXT:
                return String.format("%s. %s. %s. DOI: %s. Citations: %d.",
                        authors.toString(), title, researchProject.getTopic(), doi, citations);
            case BIBTEX:
                return String.format("@article{\n  title={%s},\n  author={%s},\n  journal={%s},\n  pages={%d},\n  year={%tY},\n  doi={%s}\n}",
                        title, authors.toString(), researchProject.getTopic(), pages, publicationDate, doi);
            default:
                throw new IllegalArgumentException("Unknown format");
        }
    }

    @Override
    public String toString() {
        return String.format("ResearchPaper{title='%s', authors='%s', project='%s', pages=%d, publicationDate=%tF, doi='%s', citations=%d}",
                title, authors.toString(), researchProject.getTopic(), pages, publicationDate, doi, citations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper that = (ResearchPaper) o;
        return pages == that.pages && citations == that.citations && Objects.equals(title, that.title) && Objects.equals(authors, that.authors) && Objects.equals(researchProject, that.researchProject) && Objects.equals(publicationDate, that.publicationDate) && Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, researchProject, pages, publicationDate, doi, citations);
    }

    @Override
    public int compareTo(ResearchPaper o) {
        return citations - o.citations;
    }
}

