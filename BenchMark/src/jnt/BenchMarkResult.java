package jnt;

/**
 * Classe qui va contenir les résultats du BenchMarking
 * @author <ul>
 *         <li>Morgane Badré</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class BenchMarkResult {

	/**
	 * Objet de la classe : {@link jnt.BenchMarkResult.FFT}
	 */
	public FFT fft;
	/**
	 * Objet de la classe : {@link jnt.BenchMarkResult.SOR}
	 */
	public SOR sor;
	/**
	 * Objet de la classe : {@link jnt.BenchMarkResult.MonteCarlo}
	 */
	public MonteCarlo monteCarlo;
	/**
	 * Objet de la classe : {@link jnt.BenchMarkResult.SparseMatmult}
	 */
	public SparseMatmult sparseMatmult;
	/**
	 * Objet de la classe : {@link jnt.BenchMarkResult.LU}
	 */
	public LU lu;
	/**
	 * Réel qui va contenir le résultat final. Celui ci est calculé en faisant la moyenne de tous les autres résultats
	 */
	public double globalScore;

	/**
	 * Setter
	 * @param fft 
	 */
	public void setFFT(FFT fft){
		this.fft = fft;
	}

	/**
	 * Setter
	 * @param sor
	 */
	public void setSOR(SOR sor){
		this.sor = sor;
	}

	/**
	 * Setter
	 * @param monteCarlo
	 */
	public void setMonteCarlo(MonteCarlo monteCarlo){
		this.monteCarlo = monteCarlo;
	}

	/**
	 * Setter
	 * @param sparseMatmult
	 */
	public void setSparseMatmult(SparseMatmult sparseMatmult){
		this.sparseMatmult = sparseMatmult;
	}

	/**
	 * Setter
	 * @param lu
	 */
	public void setLU(LU lu){
		this.lu = lu;
	}

	/**
	 * Setter du score maximal
	 * @param score Le score global final
	 */
	public void setScrore(double score){
		this.globalScore = score;
	}

	/**
	 * Méthode pour retourner en chaîne de caractères les résultats de chaque calcul
	 */
	public String toString(){
		return 	"SciMark 2.0a" + "\n\n" +
				"Score : " + globalScore + "\n" +
				"FFT(size:" + fft.size + ") : " + (fft.value == 0.0 ? "Erreur! Résultat invalide!" : fft.value) + "\n" +
				"SOR(" + sor.size + "x" + sor.size + ") : " + sor.value + "\n" +
				"Monte Carlo : " + monteCarlo.value + "\n" +
				"Sparse matmult(" + sparseMatmult.size_N + "x" + sparseMatmult.size_nz + ") : " + sparseMatmult.value + "\n" +
				"LU(" + lu.size + "x" + lu.size + ") : " + (lu.value == 0.0 ? "Erreur! Résultat invalide!" : lu.value) + "\n";
	}

	/**
	 * Classe contenant le résultat de la "transformée de Fourier rapide"
	 * @see <a href="http://fr.wikipedia.org/wiki/Transform%C3%A9e_de_Fourier_rapide">Wikipedia</a>
	 * @author <ul>
	 *         <li>Morgane Badré</li>
	 *         <li>Vincent Wilmet</li>
	 *         </ul>
	 * @version 1.0
	 */
	public static class FFT{
		/**
		 * Entier contenant la taille qui est nécessaire pour le calcul
		 */
		public int size;
		/**
		 * Réel contenant le résultat de ce calcul
		 */
		public double value;

		/**
		 * Constructeur permettant d'initialiser les champs de la classe
		 * @param size La taille pour le calcul
		 * @param value La valeur 
		 */
		public FFT(int size, double value) {
			this.size = size;
			this.value = value;
		}
	}

	/**
	 * Classe contenant le résultat de la méthode de surrelaxation successive pour résoudre un système d'équations linéaires
	 * @see <a href="http://fr.wikipedia.org/wiki/M%C3%A9thode_de_surrelaxation_successive">Wikipedia</a>
	 * @author <ul>
	 *         <li>Morgane Badré</li>
	 *         <li>Vincent Wilmet</li>
	 *         </ul>
	 * @version 1.0
	 */
	public static class SOR{
		/**
		 * Entier contenant la taille qui est nécessaire pour le calcul
		 */
		public int size;
		/**
		 * Réel contenant le résultat de ce calcul
		 */
		public double value;

		/**
		 * Constructeur permettant d'initialiser les champs de la classe
		 * @param size La taille pour le calcul
		 * @param value La valeur 
		 */
		public SOR(int size, double value) {
			this.size = size;
			this.value = value;
		}
	}
	
	/**
	 * Classe contenant le résultat de la méthode de Monte-Carlo qui vise à calculer une valeur numérique en utilisant des procédés aléatoires
	 * @see <a href="http://fr.wikipedia.org/wiki/M%C3%A9thode_de_Monte-Carlo">Wikipedia</a>
	 * @author <ul>
	 *         <li>Morgane Badré</li>
	 *         <li>Vincent Wilmet</li>
	 *         </ul>
	 * @version 1.0
	 */
	public static class MonteCarlo{
		/**
		 * Réel contenant le résultat de ce calcul
		 */
		public double value;
		/**
		 * Constructeur permettant d'initialiser le champs de la classe
		 * @param value La valeur 
		 */
		public MonteCarlo(double value) {
			this.value = value;
		}
	}

	/**
	 * Classe contenant le résultat de la multiplication de matrice vectoriel creuse
	 * @see <a href="http://www.cs.cmu.edu/~scandal/cacm/node9.html">Site Web</a>
	 * @author <ul>
	 *         <li>Morgane Badré</li>
	 *         <li>Vincent Wilmet</li>
	 *         </ul>
	 * @version 1.0
	 */
	public static class SparseMatmult{
		/**
		 * Entiers contenant la taille qui sont nécessaires pour le calcul
		 */
		public int size_N, size_nz;
		/**
		 * Réel contenant le résultat de ce calcul
		 */
		public double value;

		/**
		 * Constructeur permettant d'initialiser le champs de la classe
		 * @param value La valeur 
		 * @param size_N La taille N
		 * @param size_nz La taille NZ
		 */
		public SparseMatmult(int size_N, int size_nz, double value) {
			this.size_N = size_N;
			this.size_nz = size_nz;
			this.value = value;
		}
	}

	/**
	 * Classe contenant le résultat de la méthode de décomposition d'une matrice
	 * @see <a href="http://fr.wikipedia.org/wiki/D%C3%A9composition_LU">Wikipedia</a>
	 * @author <ul>
	 *         <li>Morgane Badré</li>
	 *         <li>Vincent Wilmet</li>
	 *         </ul>
	 * @version 1.0
	 */
	public static class LU{
		/**
		 * Entier contenant la taille qui est nécessaire pour le calcul
		 */
		public int size;
		/**
		 * Réel contenant le résultat de ce calcul
		 */
		public double value;
		/**
		 * Constructeur permettant d'initialiser les champs de la classe
		 * @param size La taille pour le calcul
		 * @param value La valeur 
		 */
		public LU(int size, double value) {
			this.size = size;
			this.value = value;
		}
	}
}
