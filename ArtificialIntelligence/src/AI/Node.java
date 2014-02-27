package AI;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a node of the Tree<T> class. The Node<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 */
public class Node<T> {
 
    public T data;
    public Integer player;
    public Set<Point> whitePiece;
    public Set<Point> blackPiece;
    public Node<T> parent;
    public List<Node<T>> children;
    public Box[][] board;
    public Integer width;
    public Integer height;
 
    /**
     * Default ctor.
     */
    public Node() {
        super();
    }
 
    /**
     * Convenience ctor to create a Node<T> with an instance of T.
     * @param data an instance of T.
     */
    public Node(T data) {
        this();
        setData(data);
    }
    
    public Node(T data, Integer player, Set<Point> white, Set<Point> black, Box[][] board, Integer width, Integer height){
        this();
        setData(data);
        setPlayer(player);
        setWhitePiece(white);
        setBlackPiece(black);
        setBoard(board,width,height);
    }
    
    public Node(T data, Integer player, Node<T> parent, Set<Point> white, Set<Point> black, Box[][] board, Integer width, Integer height){
        this();
        setData(data);
        setPlayer(player);
        setParent(parent);
        setWhitePiece(white);
        setBlackPiece(black);
        setBoard(board,width,height);
    }
    
    /**
     * Return the children of Node<T>. The Tree<T> is represented by a single
     * root Node<T> whose children are represented by a List<Node<T>>. Each of
     * these Node<T> elements in the List can have children. The getChildren()
     * method will return the children of a Node<T>.
     * @return the children of Node<T>
     */
    public List<Node<T>> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node<T>>();
        }
        return this.children;
    }
 
    /**
     * Sets the children of a Node<T> object. See docs for getChildren() for
     * more information.
     * @param children the List<Node<T>> to set.
     */
    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }
 
    /**
     * Returns the number of immediate children of this Node<T>.
     * @return the number of immediate children.
     */
    public int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }
     
    /**
     * Adds a child to the list of children for this Node<T>. The addition of
     * the first child will create a new List<Node<T>>.
     * @param child a Node<T> object to set.
     */
    public void addChild(Node<T> child) {
        if (children == null) {
            children = new ArrayList<Node<T>>();
        }
        children.add(child);
    }
     
    /**
     * Inserts a Node<T> at the specified position in the child list. Will     * throw an ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the Node<T> object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void insertChildAt(int index, Node<T> child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChild(child);
            return;
        } else {
            children.get(index); //just to throw the exception, and stop here
            children.add(index, child);
        }
    }
     
    /**
     * Remove the Node<T> element at index index of the List<Node<T>>.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }
 
    public T getData() {
        return this.data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
     
    public Integer getPlayer() {
		return player;
	}

	public void setPlayer(Integer player) {
		this.player = player;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Integer getNbWhite() {
		return whitePiece.size();
	}
	
	public Integer getNbBlack() {
		return blackPiece.size();
	}


	public Box[][] getBoard() {
		return board;
	}

	public void setBoard(Box[][] board, Integer width, Integer height) {
		this.width = width;
		this.height = height;
		this.board = new Box[width][height];
		for(int i = 0; i < width; i++)
			for(int j = 0 ; j < height; j++){
				this.board[i][j] = new BoxImpl();
				this.board[i][j].setState(board[i][j].getState());
			}
	}
	
	public Box[][] getCopyOfBoard(){
		Box[][] newBoard = new Box[width][height];
		for(int i = 0; i < width;i++)
			for(int j = 0; j < height; j++){
				newBoard[i][j] = new BoxImpl();
				newBoard[i][j].setState(this.board[i][j].getState());
			}
		
		return newBoard;
	}
	
	public Set<Point> getWhitePiece() {
		return whitePiece;
	}

	public void setWhitePiece(Set<Point> whitePiece) {
		this.whitePiece = whitePiece;
	}
	
	public Set<Point> getCopyOfWhitePiece(){
		Set<Point> newWhitePiece = new HashSet<Point>();
		for(Point p : this.whitePiece){
			Point f = new Point(p.x,p.y);
			newWhitePiece.add(f);
		}
		return newWhitePiece;
	}

	public Set<Point> getBlackPiece() {
		return blackPiece;
	}

	public void setBlackPiece(Set<Point> blackPiece) {
		this.blackPiece = blackPiece;
	}
	
	public Set<Point> getCopyOfBlackPiece(){
		Set<Point> newBlackPiece = new HashSet<Point>();
		for(Point p : this.blackPiece){
			Point f = new Point(p.x,p.y);
			newBlackPiece.add(f);
		}
		return newBlackPiece;
	}

	public String printBoard(){
		String res = new String("");
		for(int i = 0; i < this.width; i++)
			res = res +" _";
		for(int j = 0; j < this.height; j++){
			res = res + "\n";
			res = res + "|";
			for(int i = 0; i < this.width; i++){
				if(this.board[i][j].isP1Piece())
					res += "1|";
				else if(this.board[i][j].isP2Piece())
					res += "2|";
				else
					res +=" |";
			}
			res +="\n" ;
			for(int i = 0; i < this.width; i++)
				res += " _";
		}
		res += "\n";
		return res;

	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(getData().toString()).append(",[");
        int i = 0;
        for (Node<T> e : getChildren()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(e.getData().toString());
            sb.append("White : " +e.getNbWhite() + " , ");
            sb.append("Black : " +e.getNbBlack() + " ");
           // sb.append("Board : \n" + e.printBoard());
            i++;
        }
        sb.append("]").append("}");
        return sb.toString();
    }
}