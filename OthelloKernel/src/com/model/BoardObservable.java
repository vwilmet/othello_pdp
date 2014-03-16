package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.model.piece.Piece;

public class BoardObservable extends Observable implements Board {

	private Board board;
	
	public BoardObservable(Board board) {
		this.board = board;
	}
	
	private void notifierObservateurs() {
		setChanged();
		notifyObservers();
	}
	
	@Override
	public int getSizeX() {
		return this.board.getSizeX();
	}

	@Override
	public int getSizeY() {
		return this.board.getSizeY();
	}

	@Override
	public Piece[][] getBoard() {
		return this.board.getBoard();
	}

	@Override
	public List<Piece> getInitialPiece() {
		return this.board.getInitialPiece();
	}

	@Override
	public void reverse(int i, int j) {
		this.board.reverse(i, j);
		notifierObservateurs();
	}

	@Override
	public void setBlackPiece(int i, int j) {
		this.board.setBlackPiece(i, j);
		notifierObservateurs();
	}
	
	@Override
	public void setWhitePiece(int i, int j) {
		this.board.setWhitePiece(i, j);
		notifierObservateurs();
	}

	@Override
	public void setEmptyPiece(int i, int j) {
		this.board.setEmptyPiece(i, j);
		notifierObservateurs();
	}
	
	@Override
	public String toString() {
		return this.board.toString();
	}

	@Override
	public void setPiecePlayable(int i, int j) {
		this.board.setPiecePlayable(i, j);
		notifierObservateurs();
	}
	
	@Override
	public void setPieceNotPlayable(int i, int j) {
		this.board.setPieceNotPlayable(i, j);
		notifierObservateurs();
	}

	@Override
	public ArrayList<Piece> getBlackPieces() {
		return this.board.getBlackPieces();
	}

	@Override
	public ArrayList<Piece> getWhitePieces() {
		return this.board.getWhitePieces();
	}

	@Override
	public ArrayList<Piece> getPlayablePieces() {
		return this.board.getPlayablePieces();
	}

	@Override
	public void resetPlayablePosition() {
		this.board.resetPlayablePosition();
		notifierObservateurs();
	}

	@Override
	public Board clone() {
		return new BoardObservable(this.board.clone());
	}
}
