
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

class NotResearcherException extends Exception {
	public NotResearcherException(String message) {
		super(message);
	}
}

public  interface Researcher
{
	public void createResearchPaper(String title, Journal journal, int pages, String doi, int citations, Date publicationDate) throws NotResearcherException;
	public void createResearchProject(String topic, Vector<ResearchPaper> publishedPapers)  throws NotResearcherException;
	public boolean getIsReseacher();
	public double calculateHIndex() throws NotResearcherException;
	public Vector<ResearchPaper> getResearchPapers();
	public Vector<ResearchProject> getResearchProjects();
	public void joinResearchProject(ResearchProject parameter) throws NotResearcherException;
	public void printPapers(Comparator c) throws NotResearcherException;
	public int getAllCitations();
	
	
	
}

