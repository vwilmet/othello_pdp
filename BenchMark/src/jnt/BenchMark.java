package jnt;

import jnt.scimark2.Constants;
import jnt.scimark2.Random;
import jnt.scimark2.kernel;

/**
 * @mainpage Module BenchMark
 *
 * SciMark 2.0 (http://math.nist.gov/scimark2/) is a Java benchmark for scientific and numerical computing. It measures several computational kernels and reports a composite score in approximate Mflops/s. This benchmark was developed at the US National Institute of Standards and Technology (NIST). Part of the benchmark can also be found in the Java Grande Forum Benchmark Suite (http://www.epcc.ed.ac.uk/javagrande/javag.html). This benchmark contains codes on FFT, SOR (Successive Over-Relaxation over a 2D grid), Monte-Carlo integration, Sparse matmult (Sparse matrix vector multiplications) and LU factorization. We have chosen this benchmark because the same benchmark is available both in Java and C, allowing us to compare the two languages. There are many other Java benchmarks available, see http://www.epcc.ed.ac.uk/javagrande/links.html.
 * 
 * Ce module a pour but de lancer une batterie de calcul différent afin de pousser la machine à son max. A la suite de ces calculs un indice générale est calculé, correspondant à la moyenne de tous le autre calculs, permettant de connaitre les "caractéritiques/puissance" de la machine
 * 
 * <b>Exemple de BONNE utilisation du module : </b>
 * 
 * 	BenchMarkResultEvent event = new BenchMarkResultEvent() {
 *			
 *			@Override
 *			public void onStart() {
 *				System.out.println("BenchMark démarré!");
 *			}
 *			
 *			@Override
 *			public void onProgress(int progress) {
 *				System.out.println("Pourcentage : " + progress + "%");
 *			}
 *
 *			@Override
 *			public void onEnd(BenchMarkResult result) {
 *				System.out.println("BenchMark Terminé!");
 *				System.out.println(result.toString());
 *			}
 *		};
 *		
 *		BenchMark b = new BenchMark(event);
 *		b.launch();
 */

/**
SciMark2: A Java numerical benchmark measuring performance
of computational kernels for FFTs, Monte Carlo simulation,
sparse matrix computations, Jacobi SOR, and dense LU matrix
factorizations.  

http://math.nist.gov/scimark2/index.html
 */

/**
 * Ce module de BenchMark est une reprise de celui créée par Roldan Pozo et Bruce Miller
 * @see <a href="http://math.nist.gov/scimark2/">Site web</a>
 * <br/>
 * Classe qui va permettre de gérer le module de BenchMarking
 * @author <ul>
 *         <li>Morgane Badré</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class BenchMark {

	/**
	 * Interface qui permet la comuniquation ainsi que de d'informer de l'avancement des calculs du BenchMarking
	 */
	private BenchMarkResultEvent event;

	/**
	 * Constructeur qui neccessite l'utilisation de l'interface afin de communiqué les résultats du calcul
	 * <b>Attention : </b>Vous devez fournir une référence valide pour l'objet en paramètre sinon il seras impossible de connaitre l'avancement du calcul général et de récupèrer les valeurs finales
	 * @param event L'interface de communication avec le BenchMark
	 */
	public BenchMark(BenchMarkResultEvent event) {
		this.event = event;
	}

	/**
	 * Méthode à appeler afin de lancer le BenchMarking <br/>
	 * Cette méthode renvoie à travers l'objet passé en paramètre du constructeur
	 * de l'interface {@link jnt.BenchMarkResultEvent} <br/>
	 * Si l'interface de communication n'est pas valide alors le BenchMarking ne se lance pas et s'arrète immédiatement
	 * 
	 * 
	 * 
	 */
	public void launch(){

		if(event == null) return;

		event.onStart();
		event.onProgress(0);
		double min_time = Constants.RESOLUTION_DEFAULT;
		Random R = new Random(Constants.RANDOM_SEED);

		int FFT_size = Constants.FFT_SIZE;
		int SOR_size =  Constants.SOR_SIZE;
		int Sparse_size_M = Constants.SPARSE_SIZE_M;
		int Sparse_size_nz = Constants.SPARSE_SIZE_nz;
		int LU_size = Constants.LU_SIZE;
		double res[] = new double[6];
		BenchMarkResult result = new BenchMarkResult();

		event.onProgress(12);
		res[1] = kernel.measureFFT( FFT_size, min_time, R);
		event.onProgress(25);
		res[2] = kernel.measureSOR( SOR_size, min_time, R);
		event.onProgress(37);
		res[3] = kernel.measureMonteCarlo(min_time, R);
		event.onProgress(50);
		res[4] = kernel.measureSparseMatmult( Sparse_size_M, Sparse_size_nz, min_time, R);
		event.onProgress(62);
		res[5] = kernel.measureLU( LU_size, min_time, R);
		event.onProgress(74);
		res[0] = (res[1] + res[2] + res[3] + res[4] + res[5]) / 5;
		event.onProgress(86);

		result.fft = new BenchMarkResult.FFT(FFT_size, res[1]);
		result.sor = new BenchMarkResult.SOR(SOR_size, res[2]);
		result.monteCarlo = new BenchMarkResult.MonteCarlo(res[3]);
		result.sparseMatmult = new BenchMarkResult.SparseMatmult(Sparse_size_M, Sparse_size_nz, res[4]);
		result.lu = new BenchMarkResult.LU(LU_size, res[5]);
		result.globalScore = res[0];

		event.onProgress(100);
		event.onEnd(result);
	}



}
