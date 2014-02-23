package jnt;
import jnt.BenchMarkResult.MonteCarlo;
import jnt.scimark2.Constants;
import jnt.scimark2.Random;
import jnt.scimark2.kernel;


/**
SciMark2: A Java numerical benchmark measuring performance
of computational kernels for FFTs, Monte Carlo simulation,
sparse matrix computations, Jacobi SOR, and dense LU matrix
factorizations.  

http://math.nist.gov/scimark2/index.html
 */

public class BenchMark {

	private BenchMarkResultEvent event;
	
	public BenchMark(BenchMarkResultEvent event) {
		this.event = event;
	}
	
	public void launch(){
		
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