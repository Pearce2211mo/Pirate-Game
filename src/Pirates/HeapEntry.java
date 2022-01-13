package Pirates;

public class HeapEntry implements Comparable<HeapEntry>
{
    protected String element; // The entry's data.
    protected int priority; // The entry's priority.

    public HeapEntry()
    {
      element = null;
      priority = 100;
    }

    public HeapEntry (String element, int priority)
    {
      this.element = element;
      this.priority = priority;
    } // PQLinkEntry constructor

    // Return the element of the link entry.
    public String getElement ()
    {
     return element;
    } // getElement method

    // Return the entry's priority.
    public int getPriority ()
    {
     return priority;
    } // getPriority method
    
//Number of dots for display returned
    public String dots (String dot)
    {
    	String s = "";
    	for(int i= 15-dot.length(); i>0; i--)
    			s+=".";
    	return s;		
    }
    public int compareTo (HeapEntry that)
    {
    	int i = 0;
    	if (this.getPriority()>that.getPriority())
    		i=-1;
    	if (this.getPriority()<that.getPriority())
    		i=1;
    	
    	return i;
    }
    public String toString2 ()
    {
    	return element+dots(element)+ priority;
    }
    public String toString ()
    {
    	return element+" "+ priority;
    }

}//HeapEntry
