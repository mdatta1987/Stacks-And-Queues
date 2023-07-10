import java.util.LinkedList;
import java.util.Queue;

class SiteStats {
    private String url;
    private int numVisits;

    /**
     * Constructor for the SiteStats class
     *
     * @param url
     *            String that represents an URL that the user has visited
     * @param numVisits
     *            An int that represents the number of times that the user has
     *            visited the url
     */
    public SiteStats(String url, int numVisits) {
        this.url = url;
        this.numVisits = numVisits;
    }

    /**
     * This method returns the number of times that the user has visited the url.
     *
     * @return An int that represents the number of times that the user has visited
     *         the url
     */
    public int getNumVisits() {
        return this.numVisits;
    }

    /**
     * This method returns the url that we are currently tracking
     *
     * @return A String that represents the url that we are currently tracking
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method updates the number of times that we have visited the url
     *
     * @param updatedNumVisits
     *           An int that represents the number that we want to set numVisits to
     */
    public void setNumVisits(int updatedNumVisits) {
        this.numVisits = updatedNumVisits;
    }

    public String toString() {
        return this.url + " | " + this.numVisits;
    }
}

public class SolutionB {
    private static Queue<SiteStats> sites = new LinkedList<SiteStats>();

    // Main method to list top n visited sites
    public static void listTopVisitedSites(Queue<SiteStats> sites, int n) {
        // WRITE CODE HERE
        for(int i = 1; i <= sites.size(); i++)
        {
            int minInd = minimumIndex(sites, sites.size() - i);
            insertMin(sites, minInd);
        }

        if(sites.isEmpty())
        {
            System.out.println(sites.toArray().toString());
        }

        for(int i = 1; i <= n; i++)
        {
            System.out.println("Rank " + "URL                 " + "       Visit Count");
            System.out.println(i + "    " + sites.peek().getUrl() + "               " + sites.peek().getNumVisits());
            sites.remove();
        }
    }

    public static void insertMin(Queue<SiteStats> list, int minimumIndex)
    {
        SiteStats minVal = null;
        int s = list.size();

        for (int i = 0; i < s; i++)
        {
            SiteStats current = list.peek();
            list.poll();

            if (i != minimumIndex)
            {
                list.add(current);
            }
            else
            {
                minVal = current;
            }
        }

        list.add(minVal);
    }

    public static int minimumIndex(Queue<SiteStats> list, int sortIndex)
    {
        int mmaxIndex = -1;
        int maxValue = Integer.MIN_VALUE;
        int s = list.size();

        for (int i = 0; i < s; i++)
        {
            SiteStats current = list.peek();
            list.poll();

            if (current.getNumVisits() >= maxValue && i <= sortIndex)
            {
                mmaxIndex = i;
                maxValue = current.getNumVisits();
            }

            list.add(current);
        }

        return mmaxIndex;
    }

    // Method to find the website in the queue and increment the visited count by 1, adding new node in case website is not found
    public static void updateCount(String url) {
        // WRITE CODE HERE
        for(int i = 0; i < sites.size(); i++)
        {
            if(sites.peek().getUrl().equals(url))
            {
                sites.peek().setNumVisits(sites.peek().getNumVisits() + 1);

                return;
            }

            SiteStats temp = sites.remove();
            sites.add(temp);
        }

        sites.add(new SiteStats(url, 1));
    }

    public static void main(String[] args) {
        String[] visitedSites = {"www.google.co.in", "www.google.co.in", "www.facebook.com", "www.upgrad.com",
                "www.google.co.in", "www.youtube.com", "www.facebook.com", "www.upgrad.com", "www.facebook.com",
                "www.google.co.in", "www.microsoft.com", "www.9gag.com", "www.netflix.com", "www.netflix.com",
                "www.9gag.com", "www.microsoft.com", "www.amazon.com", "www.amazon.com", "www.uber.com",
                "www.amazon.com", "www.microsoft.com", "www.upgrad.com"};

        for (String url : visitedSites) {
            updateCount(url);
        }

        listTopVisitedSites(sites, 5);
    }
}
