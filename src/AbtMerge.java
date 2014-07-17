import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

//Class for defining a point 
class AbtPoint 
{
    @Override
  //Hashcode method for the AbtPoint class
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	//Equals method for the AbtPoint class
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbtPoint)) {
			return false;
		}
		AbtPoint other = (AbtPoint) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public AbtPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public AbtPoint() {}
    
}

//Comparator for points
class AbtComparator implements Comparator<AbtPoint>
{
	public int compare(AbtPoint p1, AbtPoint p2) 
	 {
	    if(p1.getX()<p2.getX())
	    	return -1;
	    else if(p1.getX()==p2.getX())
	    	if(p1.getY()<p2.getY())
	    		return -1;
	    	else
	    		return 1;
	    else
	    		return 1;
	    	
	    }
}

//Class for the node of the Circular Doubly Linked List
class Node
{
    protected AbtPoint point;

    protected Node next, prev;

    public Node()
    {
        next = null;
        prev = null;
        point = new AbtPoint();
    }

   public Node(AbtPoint p, Node nx, Node pr)
   {
        point = p;
        next = nx;
        prev = pr;
   }

   public void setLinkNext(Node n)
   {
        next = n;
   }

   public void setLinkPrev(Node p)
   {
       prev = p;
   }    

   public Node getLinkNext()
   {
       return next;
   }

   public Node getLinkPrev()
   {
       return prev;
   }

   public void setData(AbtPoint p)
   {
        point = p;
   }

   public AbtPoint getData()
   {
        return point;
   }

@Override
//Hashcode method for the Node class
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((next == null) ? 0 : next.hashCode());
	result = prime * result + ((point == null) ? 0 : point.hashCode());
	result = prime * result + ((prev == null) ? 0 : prev.hashCode());
	return result;
}

@Override
//Equals method for the Node class
public boolean equals(Object obj) 
{
	if (this == obj) {
		return true;
	}
	if (obj == null) {
		return false;
	}
	if (!(obj instanceof Node)) {
		return false;
	}
	
	Node candi = (Node) obj;
	
	AbtPoint c = this.getData();
	AbtPoint n = this.next.getData();
	AbtPoint p = this.prev.getData();
	
	if(candi.point.equals(c) && candi.next.getData().equals(n) && candi.prev.getData().equals(p))
		return true;
	else
		return false;
		
}

}

//Class for the Circular Doubly Linked List
class CircularHull
{

    public Node start;
    public Node end ;
    public int size;
    public CircularHull()

    {
        start = null;
        end = null;
        size = 0;
    }

    /* Function to check if list is empty */

    public boolean isEmpty()
    {
        return start == null;
    }

    /* Function to get size of list */

    public int getSize()
    {
        return size;
    }

    /* Function to insert element at beginning */

    public void insertAtStart(AbtPoint val)
    {

        Node nptr = new Node(val, null, null);    

        if (start == null)
        {            
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            start = nptr;
            end = start;            
        }

        else
        {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            start = nptr;        
        }

        size++ ;
    }

    /*Function to insert element at end */

    public void insertAtEnd(AbtPoint val)
    {

        Node nptr = new Node(val, null, null);        

        if (start == null)
        {
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            start = nptr;
            end = start;
        }

        else
        {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            end = nptr;    
        }

        size++;
    }

    /* Function to insert element at position */

    public void insertAtPos(AbtPoint val , int pos)
    {

        Node nptr = new Node(val, null, null);    

        if (pos == 1)
        {
            insertAtStart(val);
            return;
        }            

        Node ptr = start;

        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Node tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                tmp.setLinkPrev(nptr);
            }

            ptr = ptr.getLinkNext();            
        }

        size++ ;
    }

    /* Function to delete node at position  */

    public void deleteAtPos(int pos)
    {        
        if (pos == 1) 
        {
            if (size == 1)
            {
                start = null;
                end = null;
                size = 0;
                return; 
            }

            start = start.getLinkNext();
            start.setLinkPrev(end);
            end.setLinkNext(start);
            size--; 

            return ;
        }

        if (pos == size)
        {
            end = end.getLinkPrev();
            end.setLinkNext(start);
            start.setLinkPrev(end);
            size-- ;
        }

        Node ptr = start.getLinkNext();

        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Node p = ptr.getLinkPrev();
                Node n = ptr.getLinkNext();
                p.setLinkNext(n);
                n.setLinkPrev(p);
                size-- ;
                return;
            }

            ptr = ptr.getLinkNext();
        }        
    }    

    /* Function to display status of list */

    public void display()

    {

        System.out.print("\nCircular Doubly Linked List = ");

        Node ptr = start;

        if (size == 0) 
        {
            System.out.print("empty\n");
            return;
        }

        if (start.getLinkNext() == start) 
        {
            System.out.print("("+start.getData().getX()+","+start.getData().getY()+")"+ " <-> "+"("+ptr.getData().getX()+","+ptr.getData().getY()+")"+"\n");
            return;
        }

        System.out.print("("+start.getData().getX()+","+start.getData().getY()+")"+ " <-> ");
        ptr = start.getLinkNext();

        while (ptr.getLinkNext() != start) 
        {
            System.out.print("("+ptr.getData().getX()+","+ptr.getData().getY()+")"+" <-> ");
            ptr = ptr.getLinkNext();
        }

        System.out.print("("+ptr.getData().getX()+","+ptr.getData().getY()+")"+ " <-> ");

        ptr = ptr.getLinkNext();

        System.out.print("("+ptr.getData().getX()+","+ptr.getData().getY()+")"+ "\n");

    }

}

//The Class for the Applet
@SuppressWarnings("serial")
public class AbtMerge extends Applet
implements MouseListener, MouseMotionListener, ActionListener
{
	private ArrayList<AbtPoint> input = new ArrayList<AbtPoint>();//Input
	
	private ArrayList<AbtPoint> hullPoints = new ArrayList<AbtPoint>();//Output
	
	private AbtPoint noob = null;
	
	Image im;
	
	static int level=0;
	int insize=0;
	int width, height;
	boolean ok=false,JarvisDraw = false, QuickDraw = false, IncrementalDraw = false, MergeDraw = false;
	Random rndm;   
	Button Go,StartOver, RandomPoints, Jarvis, QuickHull, Incremental, MHull;
	TextField numPoints; 
	
	//Initializing the Applet
	public void init()
	{
	      width = getSize().width;
	      height = getSize().height;
	      setBackground( Color.black );
	      rndm = new Random();
	      addMouseListener( this );
	      addMouseMotionListener( this );
	      
	      setLayout(null);
	      
	      StartOver  = new Button("Start Over");
	      StartOver.addActionListener(this);
	  	  add(StartOver);
	  	  StartOver.setBounds(70,220,90,240);
	  	  StartOver.setSize(100, 20);
	  	  StartOver.setFont(new Font("Comic Sans MS", Font.BOLD,12));
	  	  StartOver.setBackground(Color.yellow);
	  	  
	  	  numPoints = new TextField("",15); 
	  	  numPoints.addActionListener(this);
	  	  add(numPoints);
	  	  numPoints.setBounds(70,260,90,280);
	  	  numPoints.setSize(100, 20);
	  	  numPoints.setFont(new Font("Comic Sans MS", Font.BOLD,12));
	  	  
	  	  RandomPoints = new Button("Random Points");
	  	  RandomPoints.addActionListener(this);
	  	  add(RandomPoints);
	  	  RandomPoints.setBounds(70,300,90,320);
	  	  RandomPoints.setSize(100, 20);
	  	  RandomPoints.setFont(new Font("Comic Sans MS", Font.BOLD,12));
	  	  RandomPoints.setBackground(Color.cyan);
	  	
	  	  Jarvis = new Button("Gift Wrapping");
	  	  Jarvis.addActionListener(this);
	  	  add(Jarvis);
	  	  Jarvis.setBounds(1200,220,590,240);
	  	  Jarvis.setSize(100, 20);
	  	  Jarvis.setFont(new Font("Comic Sans MS", Font.BOLD,12));
	  	  Jarvis.setBackground(Color.blue);
	  	  
	  	  QuickHull = new Button("Quick Hull");
	  	  QuickHull.addActionListener(this);
	  	  add(QuickHull);
	  	  QuickHull.setBounds(1200,260,590,280);
	  	  QuickHull.setSize(100, 20);
	  	  QuickHull.setFont(new Font("Comic Sans MS", Font.BOLD,12));
	  	  QuickHull.setBackground(Color.magenta);
	  	
       	  Incremental = new Button("Incremental");
	  	  Incremental.addActionListener(this);
	  	  add(Incremental);
	  	  Incremental.setBounds(1200,300,590,320);
	  	  Incremental.setSize(100, 20);
	  	  Incremental.setFont(new Font("Comic Sans MS", Font.BOLD,12));
	  	  Incremental.setBackground(Color.orange);
	  	  
	  	  MHull = new Button("Merge Hull");
	  	  MHull.addActionListener(this);
	  	  add(MHull);
	  	  MHull.setBounds(1200,340,590,360);
	  	  MHull.setSize(100, 20);
	  	  MHull.setFont(new Font("Comic Sans MS", Font.BOLD,12));
	  	  MHull.setBackground(Color.red);
	  }

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent ev) 
	{
		/*In this method, I set various flags indicating the progress of the applet
		 * the method calls for the computation of the hull are done in the paint method */
		if ( ev.getSource() == StartOver ) 
		{
			//Set all flags to false and clear the input list and the hullPoints list
			
			QuickDraw = false;
			IncrementalDraw = false;
			MergeDraw=false;
	    	input.clear();
			hullPoints.clear();
			noob = null;
			  
		}
		
		if ( ev.getSource() == Jarvis)
		{
			/*Clear the current output list and set JarvisDraw to true and 
			 * all other flags to false	*/
			
			QuickDraw = false;
			IncrementalDraw = false;
			MergeDraw=false;
		    hullPoints.clear();
		    JarvisDraw=true;
		}
		
		if ( ev.getSource() == Incremental)
		{
			/*Clear the current output list and set IncrementalDraw to true and 
			 * all other flags to false	*/
			
			QuickDraw = false;
			MergeDraw=false;
			JarvisDraw=false;
		   	hullPoints.clear();
			hullPoints=Incrementalfun((ArrayList<AbtPoint>)input.clone());
			IncrementalDraw = true;
		}
		
		if ( ev.getSource() == QuickHull)
		{
			/*Clear the current output list and set QuickDraw to true and 
			 * all other flags to false	*/
		   
		   MergeDraw=false;
		   JarvisDraw=false;
		   IncrementalDraw = false;
		   hullPoints.clear();
		   QuickDraw = true;
		}
		if ( ev.getSource() == MHull)
		{
			/*Clear the current output list and set MergeDraw to true and 
			 * all other flags to false	*/
			
			JarvisDraw=false;
			IncrementalDraw = false;
			QuickDraw = false;
			hullPoints.clear();
			MergeDraw=true;
		}
		
		if ( ev.getSource() == RandomPoints)
		{
			//Set all flags to false and clear the input list and the hullPoints list
			   
			   input.clear();
			   hullPoints.clear();
			   noob=null;
			   JarvisDraw=false;
			   IncrementalDraw = false;
			   QuickDraw = false;
			   MergeDraw = false;
			   
			   //Generate random input
			   for ( int i = 0; i < Integer.parseInt(numPoints.getText()); i++ ) 
				   
				 input.add(new AbtPoint ((300 + rndm.nextInt(width+600)),(100 + rndm.nextInt(height+350))));
			}
				
		repaint();
	}

	/*Function for returning Hull as per the Gift Wrapping algorithm. Also,the function
	  takes the Graphics object as input so that we can draw things dynamically*/
	@SuppressWarnings("unchecked")
	public void Jarvisfun(Graphics g) throws NullPointerException
	{
		//If Input size is less than 3, then return
		  if (input.size() < 3) 
			  hullPoints=(ArrayList<AbtPoint>)input.clone();
		  
		  insize=input.size();
		  //Determine the leftmost point and its index
		  int minx=input.get(0).getX();
		  int minin=0;
		   for(int i=1; i<input.size(); i++)
		   {
			   if(input.get(i).getX()<minx)
				   {
				   		minin=i;
				   		minx=input.get(i).getX();
				   }
			   if(input.get(i).getX()==minx)
			   {
				   if(input.get(i).getY()<input.get(minin).getY())
				   {
					    minin=i;
				   		minx=input.get(i).getX();
				   }
			   }
		   }
		   
		   /*p becomes the index of the leftmost point and q will represent the index
		    * of the next best candidate for the hull*/
		   int p = minin, q;
		   //Add the leftmost point to the hull
		   hullPoints.add(input.get(p));
		   
		   int n = input.size();
	       
		    do
	        {
		    	//Get the point at index p from the input and color it blue
			    g.setColor(Color.blue);
	 		    g.fillOval(input.get(p).getX()-4, input.get(p).getY()-4, 10, 10);
	 		  
	 		    //Determine q randomly, while not picking anything from the hull
	 		   do{
	 			  q =  (int) (Math.random() * insize);
	 			  if(hullPoints.size()==input.size())
	 			  {
	 				  q=minin;
	 				  break;
	 			  }
	 		  }while(hullPoints.contains(input.get(q)));
	           
	            //Color the point with index q as Cyan
	            g.setColor(Color.cyan);
	 		    g.fillOval(input.get(q).getX()-4, input.get(q).getY()-4, 10, 10);
	 		    
	 		    //qprev is initialized as q
	            int qprev=q;
	            
	            //for all the points in the input
	            for (int i = 0; i < n; i++)
	            {  
	            	//Draw a white line between the points at index p and at index q
	            	 g.setColor(Color.white);
	 	    		 g.drawLine(input.get(p).getX(), input.get(p).getY(), input.get(q).getX(), input.get(q).getY());
	 	    		
	 	    		try {Thread.sleep(200);} 
	 	    		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	            
	 	    		/*Make the current point in consideration blink as green
	 	    		 * however, only if index i is neither p nor q and does not
	 	    		 * represent a point in the hull*/
	            	if(i!=p && i!=q && !hullPoints.contains(input.get(i)))
	            	{
	            		g.setColor(Color.green);
	            		g.fillOval(input.get(i).getX()-4, input.get(i).getY()-4, 10, 10);
	            		try {Thread.sleep(200);} 
        	    		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	            		g.setColor(Color.red);
	            		g.fillOval(input.get(i).getX()-4, input.get(i).getY()-4, 10, 10);
	            		
	            	}	
	            	
	            	/*Perform the orientation test of the point at index i with respect to
	            	 * the line between the points at index p and at index q. The test 
	            	 * returns 1 if the point at index i lies to the right of the line*/
	            	    if (orientation(input.get(p),input.get(q), input.get(i))==1)
	            		{
	            	    	//Remove the existing white line between p and q
	            	    		g.setColor(Color.black);
	            	    		g.drawLine(input.get(p).getX(), input.get(p).getY(), input.get(q).getX(), input.get(q).getY());
	    	            	
	            	    		//If q is not a part of the hull, then re-color it Red
	            	    		if(!hullPoints.contains(input.get(q)))
		    	            	{	
	            	    			g.setColor(Color.red);
	            	 		    	g.fillOval(input.get(q).getX()-4, input.get(q).getY()-4, 10, 10);
		    	            	}
	            	 		    
	            	    		//Now, q becomes i. 
	            	    		q = i;
	            	    	
	            	    	try {Thread.sleep(200);} 
	        	    		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	        	            
	            	    	/*If q is not already on the hull, then color it Cyan and 
	            	    	 * draw a white line between p and q */
	            	    	if(!hullPoints.contains(input.get(q)))
	    	            	{
	            	    		g.setColor(Color.cyan);
	            	 		    g.fillOval(input.get(q).getX()-4, input.get(q).getY()-4, 10, 10);
	        	            	g.setColor(Color.white);
	            	    		g.drawLine(input.get(p).getX(), input.get(p).getY(), input.get(q).getX(), input.get(q).getY());
	    	            	}	
	        	     }
	            }
	           
	            /*If qprev is the same as q, meaning the value of q did not change, then
	             * draw a white line between p and q as it might have been erased in the 
	             * above for loop*/
	            if(qprev==q)
	            {
	            	g.setColor(Color.white);
	            	g.drawLine(input.get(p).getX(), input.get(p).getY(), input.get(q).getX(), input.get(q).getY());
	            }
	            //Add the input point with index q to the hull	
	            hullPoints.add(input.get(q));
	           // Draw a white line between p and q
	            g.setColor(Color.white);
	            g.drawLine(input.get(p).getX(), input.get(p).getY(), input.get(q).getX(), input.get(q).getY());
        		//Now, p moves forward and becomes q
        		p = q; 
        		//Add the input point with index p to the hull
        		g.setColor(Color.blue);
	 		    g.fillOval(input.get(p).getX()-4, input.get(p).getY()-4, 10, 10);
	        
	            //iterate until we get to the index of the leftmost point
	        } while (p != minin);
	        
	}
	@SuppressWarnings("unchecked")
	//Function for returning Hull as per the Incremental algorithm
	private ArrayList<AbtPoint> Incrementalfun(ArrayList<AbtPoint> inpoints) 
	{
		  ArrayList<AbtPoint> convexHull = new ArrayList<AbtPoint>(); //Output
		  
		  //If Input size is less than 3, then return
		    if (inpoints.size() < 3)  return (ArrayList<AbtPoint>)inpoints.clone();
			
			int minPoint = -1, maxPoint = -1;
			int minX = Integer.MAX_VALUE;
			int maxX = Integer.MIN_VALUE;
			
			//Find out the Left and Right extremes
			for (int i = 0; i < inpoints.size(); i++) {
				if (inpoints.get(i).getX() < minX) {
					minX = inpoints.get(i).getX();
					minPoint = i;
				} 
				if (inpoints.get(i).getX() > maxX) {
					maxX = inpoints.get(i).getX();
					maxPoint = i;				
				}
			}
			
			AbtPoint A = inpoints.get(minPoint);//Left extreme
			AbtPoint B = inpoints.get(maxPoint);// Right extreme
			//Add these to the hull
			convexHull.add(A);
			convexHull.add(B);
			
			//Remove the points just added to the hull from the input 
			inpoints.remove(A);
			inpoints.remove(B);
			
			ArrayList<AbtPoint> left = new ArrayList<AbtPoint>();//Left half of the remaining input points
			ArrayList<AbtPoint> right = new ArrayList<AbtPoint>();//Right half of the remaining input points
			
			//Populate the Left and Right sets with the help of the orientation test
			for (int i = 0; i < inpoints.size(); i++) {
				AbtPoint p = inpoints.get(i);
				if (orientation(A,B,p) == -1)
					left.add(p);
				else
					right.add(p);
			}
			
			//Recursively call the algorithm which computes the hull for both sets
			AbtInc(B,A,left,convexHull);
			AbtInc(A,B,right,convexHull);
			//Return the convex hull
			return convexHull;
	}

	/*Function which computes the convex hull by calculating the farthest point 
	  in the input list 'set' with respect to the extreme points A and B*/
	private void AbtInc(AbtPoint A, AbtPoint B, ArrayList<AbtPoint> set,ArrayList<AbtPoint> hull) 
	{
		int insertPosition = hull.indexOf(B);
		//If the input set is empty then return
		if (set.size() == 0) return;
		//If the input set has a single element then return this element as the extreme point
		if (set.size() == 1) {
			AbtPoint p = set.get(0);
			set.remove(p);
			//Add this point to the hull
			hull.add(insertPosition,p);
			
			return;
		}
		int dist = Integer.MIN_VALUE;
		int farthestPoint = -1;
		//Calculate the farthest point
		for (int i = 0; i < set.size(); i++) {
			AbtPoint p = set.get(i);
			int distance  = distance(A,B,p);
			if (distance > dist) {
				dist = distance;
				farthestPoint = i;
			}
		}
		
		AbtPoint P = set.get(farthestPoint);//Farthest point
		//Add this point to the hull and remove it from the input set
		set.remove(farthestPoint);
		hull.add(insertPosition,P);
		
		// Determine who's to the left of AP with the help of the orientation test
		ArrayList<AbtPoint> leftAP = new ArrayList<AbtPoint>();
		
		for (int i = 0; i < set.size(); i++) {
			AbtPoint M = set.get(i);
			if (orientation(A,P,M)==1) {
				leftAP.add(M);
			}
		}
		
		// Determine who's to the left of PB with the help of the orientation test
		ArrayList<AbtPoint> leftPB = new ArrayList<AbtPoint>();
		
		for (int i = 0; i < set.size(); i++) {
			AbtPoint M = set.get(i);
			if (orientation(P,B,M)==1) {
				leftPB.add(M);
			}
		}
		
		/*Recursive calls on this function. Now the extreme points and the input sets have 
		changed*/
		AbtInc(A,P,leftAP,hull);
		
		AbtInc(P,B,leftPB,hull);
	}

	//Function for returning Hull as per the Quick Hull algorithm
	@SuppressWarnings("unchecked")
	public ArrayList<AbtPoint> Quickfun(ArrayList<AbtPoint> inpoints, Graphics g) throws NullPointerException
	   {
		  ArrayList<AbtPoint> convexHull = new ArrayList<AbtPoint>();//Output
		  
		//If Input size is less than 3, then return
		    if (inpoints.size() < 3)  return (ArrayList<AbtPoint>)inpoints.clone();
			
			int minPoint = -1, maxPoint = -1;
			int minX = Integer.MAX_VALUE;
			int maxX = Integer.MIN_VALUE;
			
			//Find out the Left and Right extremes
			for (int i = 0; i < inpoints.size(); i++) {
				if (inpoints.get(i).getX() < minX) {
					minX = inpoints.get(i).getX();
					minPoint = i;
				} 
				if (inpoints.get(i).getX() > maxX) {
					maxX = inpoints.get(i).getX();
					maxPoint = i;				
				}
			}
			
			AbtPoint A = inpoints.get(minPoint);//Left extreme
			AbtPoint B = inpoints.get(maxPoint);//Right extreme
			//Add these to the hull
			convexHull.add(A);
			convexHull.add(B);
			//Remove the points just added to the hull from the input 
			inpoints.remove(A);
			inpoints.remove(B);
			
			ArrayList<AbtPoint> left = new ArrayList<AbtPoint>();//Left half of the remaining input points
			ArrayList<AbtPoint> right = new ArrayList<AbtPoint>();//Right half of the remaining input points
			
			//Populate the Left and Right sets with the help of the orientation test
			for (int i = 0; i < inpoints.size(); i++) {
				AbtPoint p = inpoints.get(i);
				if (orientation(A,B,p) == -1)
					left.add(p);
				else
					right.add(p);
			}
			
			/* To deal with a Degeneracy when the left part is empty, 
			 * we must be careful in what order we call the next method */
			if(left.size()==0)
			{
				//Recursively call the algorithm which computes the hull for both sets
				AbtQuick(A,B,right,convexHull,g);
				AbtQuick(B,A,left,convexHull,g);
			}
			
			else
			{
				//Recursively call the algorithm which computes the hull for both sets
				AbtQuick(B,A,left,convexHull,g);
				AbtQuick(A,B,right,convexHull,g);
			}
			
			//Return the convex hull
			return convexHull;
	   }
	
	/*Function which computes the convex hull by calculating the farthest point 
	  in the input list 'set' with respect to the extreme points A and B. Also,the function
	  takes the Graphics object as input so that we can draw things dynamically*/
	public void AbtQuick(AbtPoint A, AbtPoint B, ArrayList<AbtPoint> set, ArrayList<AbtPoint> hull, Graphics g) 
	{
		//Color the points A and B, blue
		g.setColor(Color.blue);
		g.fillOval(A.getX()-4, A.getY()-4, 10, 10);
		g.fillOval(B.getX()-4, B.getY()-4, 10, 10);
		//Sleep for 500ms
		try {Thread.sleep(500);} 
		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
		//Draw a green line between A and B
		g.setColor(Color.green);
		g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
		//Sleep for 500ms
		try {Thread.sleep(500);} 
		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
		
		int insertPosition = hull.indexOf(B);
		 
		 /* If the input set is empty then this means that A and B 
		   must form an edge of the hull. So, draw a white line between
		   them and return */
		   if (set.size() == 0)
			{ 
			   	g.setColor(Color.white);
				g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
				
				return;
			}
		   
		   /* If the input set is just one point then this means that this point
		    * is the one farthest from A and B */
			if (set.size() == 1) 
			{
				//Get the point
				AbtPoint p = set.get(0);
				
				//Color the point yellow
				g.setColor(Color.yellow);
				g.fillOval(p.getX()-4, p.getY()-4, 10, 10);
				//Sleep for 500 ms
				try {Thread.sleep(500);} 
				catch(InterruptedException ex) {Thread.currentThread().interrupt();}
				//Color the point blue
				g.setColor(Color.blue);
				g.fillOval(p.getX()-4, p.getY()-4, 10, 10);
				//Sleep for 500 ms
				try {Thread.sleep(500);} 
				catch(InterruptedException ex) {Thread.currentThread().interrupt();}
				//Remove the existing green line between A and B
				g.setColor(Color.black);
				g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
				//Draw a white line between A and P
				g.setColor(Color.white);
				g.drawLine(A.getX(), A.getY(), p.getX(), p.getY());
				//Draw a white line between B and P
				g.drawLine(B.getX(), B.getY(), p.getX(), p.getY());
				//Remove P from the input
				set.remove(p);
				//Add P in place of B in the convex hull.
				hull.add(insertPosition,p);
				
				return;
			}
			
			//If input size is greater than 1, determine the farthest point
			int dist = Integer.MIN_VALUE;
			int farthestPoint = -1;
			
			for (int i = 0; i < set.size(); i++) {
				AbtPoint p = set.get(i);
				int distance  = distance(A,B,p);
				if (distance > dist) {
					dist = distance;
					farthestPoint = i;
				}
			}
			
			AbtPoint P = set.get(farthestPoint);//Farthest point
			
			//Make the farthest point P blink as yellow and then color it blue
			g.setColor(Color.yellow);
			g.fillOval(P.getX()-4, P.getY()-4, 10, 10);
			
			try {Thread.sleep(500);} 
			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
			
			g.setColor(Color.blue);
			g.fillOval(P.getX()-4, P.getY()-4, 10, 10);
			
			try {Thread.sleep(500);} 
			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
			//Remove the existing green line between A and B
			g.setColor(Color.black);
			g.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
			//Draw a white line between A and P
			g.setColor(Color.white);
			g.drawLine(A.getX(), A.getY(), P.getX(), P.getY());
			//Draw a white line between B and P
			g.drawLine(B.getX(), B.getY(), P.getX(), P.getY());
			//Remove P from the input
			set.remove(farthestPoint);
			//Add P in place of B in the convex hull.
			hull.add(insertPosition,P);
			
			// Determine who's to the left of AP
			ArrayList<AbtPoint> leftAP = new ArrayList<AbtPoint>();
			for (int i = 0; i < set.size(); i++) {
				AbtPoint M = set.get(i);
				if (orientation(A,P,M)==1) {
					
					leftAP.add(M);
				}
			}
			
			// Determine who's to the left of PB
			ArrayList<AbtPoint> leftPB = new ArrayList<AbtPoint>();
			for (int i = 0; i < set.size(); i++) {
				AbtPoint M = set.get(i);
				if (orientation(P,B,M)==1) {
					
					leftPB.add(M);
				}
			}
			/*Recursive calls on this function. Now the extreme points and the input sets have 
			changed*/
			AbtQuick(A,P,leftAP,hull,g);
			
			AbtQuick(P,B,leftPB,hull,g);
		}
	
	/* This function returns the convex hull as per the Merge Hull algorithm
	 * it takes the sorted set of input point as input*/
	public ArrayList<AbtPoint> MDivide(ArrayList<AbtPoint> mabt, Graphics g)
	 {	
		//If Input size is less than 4, then return
		 if(mabt.size()<4)
			 return mabt;
		 
		 else
		 {
			 //Determine the split index of the input
			 int split = (mabt.size()-1)/2;
			 
			 ArrayList<AbtPoint> chull = new ArrayList<AbtPoint>();//Output
			 
			 //Populate the Left half of the input
			 ArrayList<AbtPoint> lefty = new ArrayList<AbtPoint>();
				
				for(int i=0; i<=split; i++)
					
					lefty.add(mabt.get(i));
					
			//Populate the Right half of the input
			ArrayList<AbtPoint> righty = new ArrayList<AbtPoint>();
				
				for(int i=split+1; i<mabt.size(); i++)
					
					righty.add(mabt.get(i));
					
				//Represents the hull returned by the left half
				ArrayList<AbtPoint> lhull = new ArrayList<AbtPoint>();
				//Represents the hull returned by the right half
				ArrayList<AbtPoint> rhull = new ArrayList<AbtPoint>();
				
				// Recursively call the MDivide function for the left and right halves
				lhull=MDivide(lefty,g);
				rhull=MDivide(righty,g);
				/*Call the combiner function UpperLower on the hulls 
				 * returned by the left and right halves*/
				chull=UpperLower(lhull,rhull,g);
				//Return the convex hull
				return chull;
		 }
	}

	//This function combines two convex hulls
	private ArrayList<AbtPoint> UpperLower(ArrayList<AbtPoint> lefty, ArrayList<AbtPoint> righty, Graphics g) 
	{
		/*Convert the left and right hulls into doubly connected circular lists
		 * and then call the purge function with both the circular lists as arguments*/
		return purge(Circularize(lefty),Circularize(righty),g);
	}
	
	/*This function converts the input, which is a convex hull returned by another function,
	 * into a circular doubly linked list*/
	private CircularHull Circularize (ArrayList<AbtPoint> pList)
	{
		 CircularHull chu = new CircularHull ();//Circular list
		 
	/*first transform the input into the proper order by calling the ProperList function
	 * then add each point of the proper list to the circular list */
		 for (AbtPoint p : ProperList(pList))
				chu.insertAtEnd(p);
		 //return the circular list
		 return chu;
	}
	
	/*This function orders the elements of the input list properly. The correct order 
	 * is: Leftmost Point, all the points in the UpperHull, Rightmost Point, all the 
	 * points in the LowerHull. Note that the input for this function represents 
	 * the points in the convex hull returned by some other function*/
	private ArrayList<AbtPoint> ProperList (ArrayList<AbtPoint> pList)
	{
		int Rex=0,Lex=0;
		
		//Find out the left extreme
		for (int i=0; i<pList.size(); i++)
		 {
			if(pList.get(i).getX()<pList.get(Lex).getX())
				 Lex=i;
			if(pList.get(i).getX()==pList.get(Lex).getX())
			{
				if(pList.get(i).getY()<pList.get(Lex).getY())
					Lex=i;
			}
		 }
		//Find out the right extreme
		 for (int i=0; i<pList.size(); i++)
		 {
			 if(pList.get(i).getX()>pList.get(Rex).getX())
				 Rex=i;
		 
			 if(pList.get(i).getX()==pList.get(Rex).getX())
			 {
				 if(pList.get(i).getY()>pList.get(Rex).getY())
					 Rex=i;
			 }	 
		 }
		 
		 ArrayList<AbtPoint> UpperHull = new ArrayList<AbtPoint>();//UpperHull
			
		 ArrayList<AbtPoint> LowerHull = new ArrayList<AbtPoint>();//LowerHull
		
	AbtPoint R = new AbtPoint(pList.get(Rex).getX(),pList.get(Rex).getY());//Rightmost point
		
	AbtPoint L = new AbtPoint(pList.get(Lex).getX(),pList.get(Lex).getY());//Leftmost point
		
	//Populate the upper and lower hulls as per the orientation test
		for (AbtPoint p : pList)
		{
			if((p.getY()==L.getY()&&p.getX()==L.getX()) || (p.getY()==R.getY()&&p.getX()==R.getX()))
				continue;
			
			else
			{
				if(orientation(L,R,p)>0)
					LowerHull.add(p);
				else
					UpperHull.add(p);
			}
		}
		
		//Sort the upper and lower hulls
		Collections.sort(UpperHull, new AbtComparator());
		Collections.sort(LowerHull, new AbtComparator());
		/*Note that we want the lower hull to be sorted in the reverse order of the 
		 * upper hull. So we reverse the sorted lower hull*/
		ArrayList<AbtPoint> properlower = new ArrayList<AbtPoint>();
		
		for (int i = LowerHull.size()-1; i>-1; i--)
			properlower.add(LowerHull.get(i));
		//The final hull
		 ArrayList<AbtPoint> FinalHull = new ArrayList<AbtPoint>();
		 //Insert into the final hull
		 FinalHull.add(L);
		 FinalHull.addAll(UpperHull);
		 FinalHull.add(R);
		 FinalHull.addAll(properlower);
		 //return the final hull
		 return FinalHull;
	}
		
	/*This function creates the combined convex hull of two existing hulls by computing the 
	 * common tangents. Note that the inputs to this function are in the form of 
	 * circular doubly linked lists. Also, the function takes the Graphics object as input
	 * so that we can draw things dynamically*/
private ArrayList<AbtPoint> purge(CircularHull leftchu, CircularHull rightchu, Graphics g)
{
	//This set represents the points in the final convex hull
	HashSet<AbtPoint> hps = new HashSet<AbtPoint>();
	
	AbtPoint RexOfLeft = new AbtPoint(Integer.MIN_VALUE,0);//Right extreme of left hull
	AbtPoint LexOfRight = new AbtPoint(Integer.MAX_VALUE,0);//Left extreme of right hull
	
	//We start with the left hull
	Node ln=leftchu.start;
	//We want to find the RexOfLeft 
	g.setColor(Color.yellow);
	//Also, we outline this hull with yellow lines
	for (int i=0; i<leftchu.getSize(); i++)
	{
		g.drawLine(ln.getData().getX(), ln.getData().getY(), ln.next.getData().getX(), ln.next.getData().getY());
		if(ln.getData().getX()>RexOfLeft.getX())
		{
			RexOfLeft.setX(ln.getData().getX());
			RexOfLeft.setY(ln.getData().getY());
		}
		
		if(ln.getData().getX()==RexOfLeft.getX())
		{
			if(ln.getData().getY()<RexOfLeft.getY())
			{
				RexOfLeft.setX(ln.getData().getX());
				RexOfLeft.setY(ln.getData().getY());
			}
		}
		
		ln=ln.next;
	}
	g.drawLine(ln.getData().getX(), ln.getData().getY(), ln.next.getData().getX(), ln.next.getData().getY());
	
	//We do similar processing for the right hull and find the LexOfRight
	Node rn=rightchu.start;
	for (int i=0; i<rightchu.getSize(); i++)
	{
		g.drawLine(rn.getData().getX(), rn.getData().getY(), rn.next.getData().getX(), rn.next.getData().getY());
		if(rn.getData().getX()<LexOfRight.getX())
		{
			LexOfRight.setX(rn.getData().getX());
			LexOfRight.setY(rn.getData().getY());
		}
		if(rn.getData().getX()==LexOfRight.getX())
		{
			if(rn.getData().getY()<LexOfRight.getY())
			{
				LexOfRight.setX(rn.getData().getX());
				LexOfRight.setY(rn.getData().getY());
			}
		}
		rn=rn.next;
	}
	
	g.drawLine(rn.getData().getX(), rn.getData().getY(), rn.next.getData().getX(), rn.next.getData().getY());
	
	try {Thread.sleep(1000);} 
	catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	
	//Start at the left hull and move forward until you get to RexOfLeft
	Node lt=leftchu.start;
	Node ltemp=new Node();
	
	while(true)
	{
		ltemp=lt;
		if(ltemp.getData().getX()==RexOfLeft.getX() && ltemp.getData().getY()==RexOfLeft.getY())
			break;
		else
			lt=lt.next;
	}
	
	//Start at the right hull and move forward until you get to LexOfRight
	Node rt=rightchu.start;
	Node rtemp=new Node();
	
	while(true)
	{
		rtemp=rt;
		if(rtemp.getData().getX()==LexOfRight.getX() && rtemp.getData().getY()==LexOfRight.getY())
			break;
		else
			rt=rt.next;
	}
	
	//These flags will indicate that the tangents have been calculated successfully
	boolean lowerDone=false;
	boolean upperDone=false;
	/**************************Lower Tangent****************************/
	
	do
	{
	
	AbtPoint L = ltemp.getData();//Rightmost point of the Left hull
	AbtPoint R = rtemp.getData();//Leftmost point of the Right hull
	AbtPoint Ln = ltemp.next.getData();//Next node of L
	AbtPoint Rp = rtemp.prev.getData();//Previous node of R
	
	//Draw a blinking magenta line between L and R
	g.setColor(Color.MAGENTA);
	g.drawLine(L.getX(), L.getY(), R.getX(), R.getY());
	try {Thread.sleep(1000);} 
	catch(InterruptedException ex) {Thread.currentThread().interrupt();}
  	g.setColor(Color.black);
  	g.drawLine(L.getX(), L.getY(), R.getX(), R.getY());
	
  	/*if R,L and Ln makes a right turn and L,R and Rp makes a left turn at the same time
  	 * then lower hull is done*/
	if (orientation(R,L,Ln)==1 && orientation(L,R,Rp)==-1)
	
		lowerDone=true;
	
	//Keeping moving forward in the left hull until R,L and Ln makes a right turn
	if(orientation(R,L,Ln)!=1)
	
		ltemp=ltemp.next;
	//Keeping moving backwards in the right hull until L,R and Rp makes a left turn
	if(orientation(L,R,Rp)!=-1)
	
		rtemp=rtemp.prev;
		
	}while(!lowerDone);
	
	/*by this time, ltemp is the left end point of the lower tangent and
	 * rtemp is the right end point*/
	Node ltl = ltemp;
	Node ltr = rtemp;
	
	//Draw a magenta line for the lower tangent
		g.setColor(Color.MAGENTA);
		g.drawLine(ltl.getData().getX(), ltl.getData().getY(), ltr.getData().getX(), ltr.getData().getY());
		
	//Add the lower tangent end points to the hull
	hps.add(ltl.getData());
	hps.add(ltr.getData());
	
	/*Similar processing for the upper hull with the only difference being that 
	 * list traversals and the orientation test orders are reversed*/
	/**********************************Upper Tangent**************************************/
	
	while(true)
	{
		ltemp=lt;
		if(ltemp.getData().getX()==RexOfLeft.getX() && ltemp.getData().getY()==RexOfLeft.getY())
			break;
		else
			lt=lt.next;
	}
	
	while(true)
	{
		rtemp=rt;
		if(rtemp.getData().getX()==LexOfRight.getX() && rtemp.getData().getY()==LexOfRight.getY())
			break;
		else
			rt=rt.next;
	}
	
	do
	{
		AbtPoint L = ltemp.getData();
		AbtPoint R = rtemp.getData();
		AbtPoint Lp = ltemp.prev.getData();
		AbtPoint Rn = rtemp.next.getData();
				
		g.setColor(Color.MAGENTA);
		g.drawLine(L.getX(), L.getY(), R.getX(), R.getY());
		try {Thread.sleep(1000);} 
		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	  	g.setColor(Color.black);
	  	g.drawLine(L.getX(), L.getY(), R.getX(), R.getY());
	  	
		if (orientation(R,L,Lp)==-1 && orientation(L,R,Rn)==1)
			upperDone=true;
		
		if(orientation(R,L,Lp)!=-1)
		
			ltemp=ltemp.prev;
		
		if(orientation(L,R,Rn)!=1)
		
			rtemp=rtemp.next;
		
		}while(!upperDone);
	
	Node utl = ltemp;
	Node utr = rtemp;
	
	g.setColor(Color.MAGENTA);
	g.drawLine(utl.getData().getX(), utl.getData().getY(), utr.getData().getX(), utr.getData().getY());
	
	hps.add(utl.getData());
	hps.add(utr.getData());
	
	//The final list of all the hull points
	ArrayList<AbtPoint> answer = new ArrayList<AbtPoint>();
	
	while(utl != ltl)
	{
		hps.add(utl.prev.getData());
		utl=utl.prev;
	}
	while(utr != ltr)
	{
		hps.add(utr.next.getData());
		utr=utr.next;
	}
	
	//Populate the final list 
	answer.addAll(hps);
	//Transform the final list in the proper order
	ArrayList<AbtPoint> properanswer = ProperList(answer);
	
	//Remove the yellow lines from the left hull and re-color the points red
	Node lnn=leftchu.start;
	for (int i=0; i<leftchu.getSize(); i++)
	{
		g.setColor(Color.black);
		g.drawLine(lnn.getData().getX(), lnn.getData().getY(), lnn.next.getData().getX(), lnn.next.getData().getY());
		g.setColor(Color.red);
		g.fillOval(lnn.getData().getX()-4, lnn.getData().getY()-4, 10, 10);
		g.fillOval(lnn.next.getData().getX()-4, lnn.next.getData().getY()-4, 10, 10);
		
		lnn=lnn.next;
	}
	g.setColor(Color.black);
	g.drawLine(lnn.getData().getX(), lnn.getData().getY(), lnn.next.getData().getX(), lnn.next.getData().getY());
	g.setColor(Color.red);
	g.fillOval(lnn.getData().getX()-4, lnn.getData().getY()-4, 10, 10);
	
	//Remove the yellow lines from the right hull and re-color the points red
	Node rnn=rightchu.start;
	for (int i=0; i<rightchu.getSize(); i++)
	{
		g.setColor(Color.black);
		g.drawLine(rnn.getData().getX(), rnn.getData().getY(), rnn.next.getData().getX(), rnn.next.getData().getY());
		g.setColor(Color.red);
		g.fillOval(rnn.getData().getX()-4, rnn.getData().getY()-4, 10, 10);
		g.fillOval(rnn.next.getData().getX()-4, rnn.next.getData().getY()-4, 10, 10);
		
		rnn=rnn.next;
	}
	g.setColor(Color.black);
	g.drawLine(rnn.getData().getX(), rnn.getData().getY(), rnn.next.getData().getX(), rnn.next.getData().getY());
	g.setColor(Color.red);
	g.fillOval(rnn.getData().getX()-4, rnn.getData().getY()-4, 10, 10);
	
	g.setColor(Color.white);
	
	/*For the proper final list of hull points, draw the convex hull as 
	 * white lines and blue points*/
	int y;
	for(y=0;y<properanswer.size()-1;y++)
		g.drawLine(properanswer.get(y).getX(),properanswer.get(y).getY(), properanswer.get(y+1).getX(), properanswer.get(y+1).getY());
	g.drawLine(properanswer.get(0).getX(), properanswer.get(0).getY(), properanswer.get(properanswer.size()-1).getX(), properanswer.get(properanswer.size()-1).getY());
	
	//return the proper final list
	return properanswer;
}

/*This function calculates the convex hull as per the incremental algorithm. It takes
 * three arguments: The current convex hull, the new point and the graphics object*/

public ArrayList<AbtPoint> InOrOut(ArrayList<AbtPoint> inps, AbtPoint noob, Graphics g) 
{
	 //This will be the current hull
	 ArrayList<AbtPoint> properhull = new ArrayList<AbtPoint>(); 
	 //This will be the final hull
	 ArrayList<AbtPoint> finalhull = new ArrayList<AbtPoint>();
	 //These would be the edges that must be removed from the current hull, if any
	 ArrayList<AbtPoint> blues = new ArrayList<AbtPoint>();
	 //These would be the two new tangents from the new point, if any
	 ArrayList<AbtPoint> tangents = new ArrayList<AbtPoint>();
	
	 // If the current hull is just a line or a point, then add the new point 
	 //	to the hull and return
	 if(inps.size()<3)
	{
		//finalhull becomes the current hull
		finalhull = inps;
		//add the new point to the finalhull
		finalhull.add(noob);
		//add the new point to the current set of input points
		input.add(noob);
		
		//Now draw the hull, the hull points and the remaining points
    	 g.setColor(Color.white);
	     
	     int i=0;
	     for (; i<finalhull.size()-1; i++)
	      
	    	 g.drawLine(finalhull.get(i).getX(), finalhull.get(i).getY(), finalhull.get(i+1).getX(), finalhull.get(i+1).getY());
	     
	     g.drawLine(finalhull.get(i).getX(), finalhull.get(i).getY(), finalhull.get(0).getX(), finalhull.get(0).getY());
	     
	     g.setColor(Color.red);
	     g.fillOval(noob.getX()-4, noob.getY()-4, 10, 10);
	}

//However, if the current hull has more than 2 points then we must proceed as follows
	else
	{
		//Circular list representing the hull. We will traverse this list to
		// find out the edges that must be removed and the tangent points if any
		 CircularHull chu = new CircularHull ();
		 // First, arrange the current hull points in the proper circular order
		 properhull = ProperList(inps);
		// Now populate the circular list in the correct order
		 for (AbtPoint p : properhull)
				chu.insertAtEnd(p);
				
		 // Now we traverse the circular list representing the current hull
		Node s = chu.start;
		Node t;
		
		t=s;
		
		while(true)
		{
			//This point represents the current point under consideration
			AbtPoint c = t.getData();
			// This point represents the point next to the current
			AbtPoint n = t.next.getData();
			// This point represents the point previous to the current point
			AbtPoint p = t.prev.getData();
						
			// If the current point, the next point and the new point
			// make a right turn, then we color the edge between the current point 
			// and the next as green
			if(orientation(c,n,noob)==1)
			{
				 try {Thread.sleep(500);} 
	    			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
				
				g.setColor(Color.green); 
	    		g.drawLine(c.getX(), c.getY(), n.getX(), n.getY());
			}
			
			// If the current point, the next point and the new point
			// make a left turn, then we color the edge between the current point 
			// and the next as blue
			if(orientation(c,n,noob)==-1)
			{
				if(!blues.contains(c))blues.add(c);
				if(!blues.contains(n))blues.add(n);
				
				 try {Thread.sleep(500);} 
	    			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	    		
				 g.setColor(Color.blue); 
	    		 g.drawLine(c.getX(), c.getY(), n.getX(), n.getY());
			}
			
		// Now, note that if the orientation of the current point, the next point 
		//	and the new point is different from the orientation of the previous point
		// the current point and the new point, then the current point must correspond
		// to on of the tangents points on the hull with respect to the new point. The
		// new point of course, is outside of the hull for this to happen.
			if(orientation(c,n,noob)!=orientation(p,c,noob))
				tangents.add(c);
			
			// Move the pointer forward for traversal of the list
			t=t.next;
			//When we get to the start position again, break out of the loop
			if(t.equals(s))
				break;
			
		}
		
		// If we did not find any blue edges then it means that all the edges were
		// green. This means that the new point did not change the orientation of any 
		// hull edge, so the new point must be inside the hull
		 if(blues.isEmpty())
	     {
			 // Add the new point to the existing set of input points
			 input.add(noob);
			 
	    	 try {Thread.sleep(500);} 
 			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	    	 
	    	 // the current hull becomes the final hull
	    	 finalhull = properhull;
	    	 
	    	 // draw the hull and the points
	    	 g.setColor(Color.white);
		     
		     int i=0;
		     for (; i<finalhull.size()-1; i++)
		      
		    	 g.drawLine(finalhull.get(i).getX(), finalhull.get(i).getY(), finalhull.get(i+1).getX(), finalhull.get(i+1).getY());
		     
		     g.drawLine(finalhull.get(i).getX(), finalhull.get(i).getY(), finalhull.get(0).getX(), finalhull.get(0).getY());
		     
		     g.setColor(Color.red);
		     g.fillOval(noob.getX()-4, noob.getY()-4, 10, 10);
		     
	     }
		 
		 // However, if we found any blue edges, then it means that there must be 
		 // some edges that have a different orientation with the new point than
		 // the other edges.
		 else
		 {	
		 // Here we accumulate the points on the hull that would be discarded, if any
			 ArrayList<AbtPoint> waste = new ArrayList<AbtPoint>();

	   // Now the points that must be discarded are the points that contribute to the 
	  // blue edges except the points that are the tangent points. So we add such
	 // points to the waste list
		for(AbtPoint p : properhull)
			if(!tangents.contains(p) && blues.contains(p))
				waste.add(p);
		
		// Now, there can be only two tangent points
		 AbtPoint ft = tangents.get(0);
	     AbtPoint st = tangents.get(1);
		 
	     //color a magenta edge between the tangent points and the new point
	     g.setColor(Color.magenta);
	    
	     g.drawLine(ft.getX(), ft.getY(), noob.getX(), noob.getY());
	     
	     try {Thread.sleep(500);} 
			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	    
	     g.drawLine(st.getX(), st.getY(), noob.getX(), noob.getY());
		
	     // Remove all the waste points from the current hull
	     properhull.removeAll(waste);
		
	     // Remember that the current hull edges are shown in white. We remove
	     // the edges that correspond to the waste points by coloring them
	     // black(same as the background)
	        try {Thread.sleep(500);} 
			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	     
	     int i=0;
	     for (; i<blues.size()-1; i++)
	     {
	    	 try {Thread.sleep(500);} 
			catch(InterruptedException ex) {Thread.currentThread().interrupt();}	
	    	 g.setColor(Color.black); 
	    	 g.drawLine(blues.get(i).getX(), blues.get(i).getY(), blues.get(i+1).getX(), blues.get(i+1).getY());
	    	 
	    	 g.setColor(Color.red);
	    	 g.fillOval(blues.get(i).getX()-4, blues.get(i).getY()-4, 10, 10);
	     }
	     
	     g.setColor(Color.black); 
	     g.drawLine(blues.get(i).getX(), blues.get(i).getY(), blues.get(0).getX(), blues.get(0).getY());
	     g.setColor(Color.red);
    	 g.fillOval(blues.get(i).getX()-4, blues.get(i).getY()-4, 10, 10);
    	 
    	 // Now, add the new point to the current hull
	    properhull.add(noob);
					
	    // Now we form the final hull by arranging the points of the current hull
	    // in the proper circular order
		finalhull = ProperList(properhull);
		
		try {Thread.sleep(1000);} 
		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
     
		// Now draw the newly formed hull
		g.setColor(Color.white);
     
     i=0;
     for (; i<finalhull.size()-1; i++)
      
    	 g.drawLine(finalhull.get(i).getX(), finalhull.get(i).getY(), finalhull.get(i+1).getX(), finalhull.get(i+1).getY());
     
     g.drawLine(finalhull.get(i).getX(), finalhull.get(i).getY(), finalhull.get(0).getX(), finalhull.get(0).getY());
     
     g.setColor(Color.blue);
   	 for(AbtPoint p : finalhull)
   		 g.fillOval(p.getX()-4, p.getY()-4, 10, 10);
		
   	 // Add the new point to the existing set of input points
		input.add(noob);
		
		 }
		
	}
	 
	 // return the final hull
	 return finalhull;
}
//This function calculates the distance between a line and a point
public int distance(AbtPoint A, AbtPoint B, AbtPoint C) {
	int ABx = B.getX()-A.getX();
	int ABy = B.getY()-A.getY();
	int num = ABx*(A.getY()-C.getY())-ABy*(A.getX()-C.getX());
	if (num < 0) num = -num;
	return num;
}
//This function is the orientation test
public int orientation(AbtPoint A, AbtPoint B, AbtPoint P)
	   {
		    int val = (B.getX()-A.getX())*(P.getY()-A.getY()) - (B.getY()-A.getY())*(P.getX()-A.getX());
		    return (val>0)?1:-1;
	   }

//Paint function
	@SuppressWarnings("unchecked")
	public void paint( Graphics g )
	   {
		   super.paint(g);
		   
	    	   Dimension d = getSize();
			   g.setColor(Color.black);
			   g.fillRect(0, 0, d.width, d.height);
			  
			   //Draw the input
			  g.setColor( Color.red );
			
		   for (AbtPoint p : input)
		    
		    	  g.fillOval(p.getX()-4, p.getY()-4, 10, 10);
		   
		   if(JarvisDraw)
		   {
			   //Call JarvisFun and pass the Graphics as an argument
			   Jarvisfun(g);
			   //Set the JarvisDraw flag to false as our job is done
			   JarvisDraw=false;
		   }
   
		   if(QuickDraw)
			 {
			 //Call QuickFun and pass the Graphics and the input as an argument
			   hullPoints=Quickfun((ArrayList<AbtPoint>)input.clone(),g);
			 //Set the QuickDraw flag to false as our job is done
			   QuickDraw=false;
			 }
		   
		   if(IncrementalDraw)
		   {
			   g.setColor(Color.blue);
			   	 for(AbtPoint p : hullPoints)
			   		 g.fillOval(p.getX()-4, p.getY()-4, 10, 10);
			   	 
			   	   int i;
				   g.setColor(Color.white);
				     
				     for ( i=0 ; i<hullPoints.size()-1 ; i++ )
				      
				    	 g.drawLine(hullPoints.get(i).getX(), hullPoints.get(i).getY(), hullPoints.get(i+1).getX(), hullPoints.get(i+1).getY());
				     
				     g.drawLine(hullPoints.get(i).getX(), hullPoints.get(i).getY(), hullPoints.get(0).getX(), hullPoints.get(0).getY());
			   	
				    // IncrementalDraw = false;
		   }
		   
		   	if(MergeDraw)
		   	{
		   		//Clone the input list
		   		ArrayList <AbtPoint> minput =  (ArrayList<AbtPoint>)input.clone();
		 		//Sort the clone
		 		Collections.sort(minput, new AbtComparator());
		 		//Call the functions to return the hull
		 		hullPoints = ProperList(MDivide(minput,g)); 
		 		   
		 		for(AbtPoint ph: hullPoints)
				{
				   g.setColor(Color.blue);
				   g.fillOval(ph.getX()-4, ph.getY()-4, 10, 10);
				}
		 		//Set the MergeDraw flag to false as our job is done
		 		MergeDraw = false;
		   	}
	   }
	
	//This method is called whenever the mouse is moved
	public void mouseMoved(MouseEvent e) 
	{
		//Whenever the mouse is moved, display cursor position
		showStatus(e.getX()+","+e.getY());
	}

	//This method is called whenever a mouse button is pressed
	@SuppressWarnings("unchecked")
	public void mousePressed(MouseEvent e) 
	{
		// This represents the current graphics object
		 Graphics g =getGraphics();
		
		 boolean me=false;/*This flag will indicate whether we have found a point 
		 					in the point which the user wants to delete*/
         
         //For Right Click
        if (e.isMetaDown()) 
		{
        	/*Search the input for the point whose coordinates 
        	 * are closest to the current mouse event*/
               for ( int i = 0; i < input.size(); ++i )
               {
                   AbtPoint A = input.get(i);
               
               if (((A.getX() >= e.getX()-5) && (A.getX() <= e.getX()+5)) &&
                    ((A.getY() >= e.getY()-5) && (A.getY() <= e.getY()+5)))
               {
            	  /*We have found such a point in the input, so mark its position
            	   *  and set the me flag to true*/ 
                  noob=input.get(i);
                  me=true;
               }
             }
           
           /*If we found a point in the input which the user wants to delete
            * then remove this point from the input list*/
           if (me) 
           {
        	  // Remove the new point from the current set of input points	        
  	    	 input.remove(noob);
  	    	 
  	    	 // color the new point as black (same as the background)
  	    	 g.setColor(Color.black);
      	     
  	    	 g.fillOval(noob.getX()-4, noob.getY()-4, 10, 10);
  	    	
      	        //If IncrementalDraw is true, then recompute the hull 
               if(IncrementalDraw)
               {
            	   // If the point deleted is on the current hull, thenwe proceed as
            	   // follows
            	   if(hullPoints.contains(noob))
            	   {
            		   // Color the new point as yellow
            		   g.setColor(Color.yellow);
            		   g.fillOval(noob.getX()-4, noob.getY()-4, 10, 10);
            		   
            		   // color the rest of the points as red
            		 g.setColor(Color.red);
  				   	 for(AbtPoint p : input)
  				   		 g.fillOval(p.getX()-4, p.getY()-4, 10, 10);
  				   	 
  				   	 // color the hull edges as black (same as the background)
            		   int i;
            		   
            		   g.setColor(Color.black);
					     
					     for ( i=0 ; i<hullPoints.size()-1 ; i++ )
					      
					    	 g.drawLine(hullPoints.get(i).getX(), hullPoints.get(i).getY(), hullPoints.get(i+1).getX(), hullPoints.get(i+1).getY());
					     
					     g.drawLine(hullPoints.get(i).getX(), hullPoints.get(i).getY(), hullPoints.get(0).getX(), hullPoints.get(0).getY());
					     
					     // Calculate the new hull by Quick Hull method
            		  hullPoints = Quickfun((ArrayList<AbtPoint>)input.clone(), g);
            	   
            		  // draw the new hull and the points
            	   g.setColor(Color.blue);
				   	 for(AbtPoint p : hullPoints)
				   		 g.fillOval(p.getX()-4, p.getY()-4, 10, 10);
				   	 
				   	     g.setColor(Color.white);
					     
					     for ( i=0 ; i<hullPoints.size()-1 ; i++ )
					      
					    	 g.drawLine(hullPoints.get(i).getX(), hullPoints.get(i).getY(), hullPoints.get(i+1).getX(), hullPoints.get(i+1).getY());
					     
					     g.drawLine(hullPoints.get(i).getX(), hullPoints.get(i).getY(), hullPoints.get(0).getX(), hullPoints.get(0).getY());
					     
   				   repaint();
               }
             }
               me=false;
               noob=null;
           }
		}
		
      //For Left Click
		 else 
		 {
			//If IncrementalDraw is true, then recompute the hull 
			if(IncrementalDraw)
			{
				// noob variable store the new point
				noob  = new AbtPoint(e.getX(),e.getY());
				 // Color the new point as yellow
				g.setColor(Color.yellow);
   	    	    g.fillOval(noob.getX()-4, noob.getY()-4, 10, 10);
   	    	   
   	    	    // Calculate the new hull as per the incremental algorithm
				hullPoints = InOrOut((ArrayList<AbtPoint>)hullPoints.clone(),noob,g);
				repaint();
			}
			// If IncrementalDraw is not true then we simply add the new point to the 
			// current set of input points
			else
				 input.add(new AbtPoint(e.getX(),e.getY()));
		 }
		
		repaint();
	}
	
	public void mouseDragged(MouseEvent arg0){}
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0){}
	public void mouseClicked(MouseEvent e){}
	public void mouseReleased(MouseEvent arg0){}
}