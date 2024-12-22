import java.util.Comparator;
import java.util.Date;

class CitationComparatorDesc implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return Integer.compare(p2.getCitations(), p1.getCitations());
    }
}

class CitationComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return Integer.compare(p1.getCitations(), p2.getCitations());
    }
}

class PageComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return Integer.compare(p1.getPages(), p2.getPages());
    }
}

class DateComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return p1.getPublicationDate().compareTo(p2.getPublicationDate());
    }
}

class NewsComparator implements Comparator<News> {
    @Override
    public int compare(News n1, News n2) {
        boolean isResearch1 = "Research".equalsIgnoreCase(n1.getTopic());
        boolean isResearch2 = "Research".equalsIgnoreCase(n2.getTopic());
        if (isResearch1 && !isResearch2) return -1;
        if (!isResearch1 && isResearch2) return 1;
        return n2.getDate().compareTo(n1.getDate());
    }
}