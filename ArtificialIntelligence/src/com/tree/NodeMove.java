package com.tree;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.board.Board;
import com.board.Box;
import com.board.BoxImpl;

/**
 * Represents a node of the Tree<T> class. The Node<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 */
public class NodeMove<T> {
 
    private T data;
    private Integer player;
    private NodeMove<T> parent;
    private List<NodeMove<T>> children;
    private Board currentBoard;
    private T bestMove;
 
    /**
     * Default ctor.
     */
    public NodeMove() {
        super();
    }
 
    /**
     * Convenience ctor to create a Node<T> with an instance of T.
     * @param data an instance of T.
     */
    public NodeMove(T data) {
        this();
        setData(data);
        bestMove = null;
    }
    
    public NodeMove(T data, Integer player, Board board){
        this();
        setData(data);
        setPlayer(player);
        setBoard(board);      
        bestMove = null;
        parent = null;

    }
    
    public NodeMove(T data, Integer player, Board board, NodeMove<T> parent){
        this();
        setData(data);
        setPlayer(player);
        setParent(parent);
        setBoard(board);      
        bestMove = null;

    }
    
    /**
     * Return the children of Node<T>. The Tree<T> is represented by a single
     * root Node<T> whose children are represented by a List<Node<T>>. Each of
     * these Node<T> elements in the List can have children. The getChildren()
     * method will return the children of a Node<T>.
     * @return the children of Node<T>
     */
    public List<NodeMove<T>> getChildren() {
        if (this.children == null) {
            return new ArrayList<NodeMove<T>>();
        }
        return this.children;
    }
 
    /**
     * Sets the children of a Node<T> object. See docs for getChildren() for
     * more information.
     * @param children the List<Node<T>> to set.
     */
    public void setChildren(List<NodeMove<T>> children) {
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
    public void addChild(NodeMove<T> child) {
        if (children == null) {
            children = new ArrayList<NodeMove<T>>();
        }
        children.add(child);
    }
     
    /**
     * Inserts a Node<T> at the specified position in the child list. Will     * throw an ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the Node<T> object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void insertChildAt(int index, NodeMove<T> child) throws IndexOutOfBoundsException {
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

	public NodeMove<T> getParent() {
		return parent;
	}

	public void setParent(NodeMove<T> parent) {
		this.parent = parent;
	}


	public Board getBoard() {
		return currentBoard;
	}

	public void setBoard(Board board) {
		this.currentBoard = board;
	}
	

	public T getBestMove() {
		return bestMove;
	}

	public void setBestMove(T bestMove) {
		this.bestMove = bestMove;
	}

	public String printBoard(){
		String res = new String("");
		/*for(int i = 0; i < this.width; i++)
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
		res += "\n";*/
		return res;

	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(getData().toString()).append(",[");
        int i = 0;
        for (NodeMove<T> e : getChildren()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(e.getData().toString());
            sb.append("White : " +e.getBoard().getNbWhitePiece() + " , ");
            sb.append("Black : " +e.getBoard().getNbBlackPiece() + " ");
            sb.append("Board : \n" + e.printBoard());
            i++;
        }
        sb.append("]").append("}");
        return sb.toString();
    }
}