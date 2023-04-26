public class Frame {
    char letter;
    int page;
    int frozen;
    boolean used;

    public Frame(char c){
        this.letter = c;
        page = 100;
        frozen = 0;
        used=false;
    }

    public boolean isThisPage(int page)     { return this.page == page; }
    public void setPage(int newPage)        { this.page = newPage; }
    public boolean isUsed()                 { return used; }
    public void setUsed(boolean isUsed)     { used = isUsed; }
    public boolean isNotFrozen()               { return frozen <= 0; }
    public void decrFrozen()                { frozen -= 1; }
    public void resetFrozen()               { frozen = 3; }
    public void releaseFrozen()             { frozen = 0; }
    public char getLetter()                 { return letter; }

}
