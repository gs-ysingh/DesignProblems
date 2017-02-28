package SearchPackage;

import java.util.*;

/**
 * Created by YSingh on 26/02/17.
 */
class Page {
    String name;
    List<Keyword> keywords;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}

class Keyword {
    String name;
    int rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

class Query {
    String name;
    List<Keyword> searchWords;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Keyword> getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(List<Keyword> searchWords) {
        this.searchWords = searchWords;
    }
}

class Strength implements Comparable<Strength> {
    String pageName;
    int rating;
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(Strength o) {
        return (int) o.rating - this.rating;
    }
}

public class Search {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        List<Page> pages = new ArrayList<>();
        int count = 1;
        int queryCount = 1;
        while (line != null) {
            String [] values = line.split(" ");

            if(values[0].equals("P")) {
                Page page = new Page();
                page.setName("P" + count);
                List<Keyword> keywords = new ArrayList<>();
                //hard coding n, but it can be taken from input
                int n = 8;
                for (int i = 1; i < values.length; i++) {
                    Keyword key = new Keyword();
                    key.setName(values[i]);
                    key.setRank(n--);
                    keywords.add(key);
                }
                page.setKeywords(keywords);
                pages.add(page);
                count++;
            }

            if(values[0].equals("Q")) {
                Query query = new Query();
                query.setName("Q" + queryCount);
                List<Keyword> searchWords = new ArrayList<>();
                //hard coding n, but it can be taken from input
                int n = 8;
                for (int i = 1; i < values.length; i++) {
                    Keyword key = new Keyword();
                    key.setName(values[i]);
                    key.setRank(n--);
                    searchWords.add(key);
                }
                query.setSearchWords(searchWords);

                System.out.println(getTopPages(pages, query));
                queryCount++;
            }

            if (line.isEmpty()) {
                break;
            }
            if (sc.hasNextLine()) {
                line = sc.nextLine();
            } else {
                line = null;
            }
        }
    }

    private static String getTopPages(List<Page> pages, Query query) {
        List<Strength> list = new ArrayList<>();

        for(Page page : pages) {
            int sum = 0;
            for (Keyword key : page.getKeywords()) {
                for(Keyword s : query.getSearchWords()) {
                    if(key.getName().equals(s.getName())) {
                        sum += (key.getRank() * s.getRank());
                        break; //considering there is no repetition of words in page and query
                    }
                }
            }
            Strength st = new Strength();
            st.setPageName(page.getName());
            st.setRating(sum);
            list.add(st);
        }
        Collections.sort(list);

        //top 5 records

        String res = "";

        for (int i = 0; i < 5; i++) {
            if(list.get(i).getRating() > 0) {
                res = res + list.get(i).getPageName() + " ";
            }
        }
        return query.getName() + ": " + res;
    }
}
