package jnt;

public class BenchMarkResult {

	public static SystemInformations system;
	public FFT fft;
	public SOR sor;
	public MonteCarlo monteCarlo;
	public SparseMatmult sparseMatmult;
	public LU lu;
	public double globalScore;
	
	public BenchMarkResult() {}

	public void setFFT(FFT fft){
		this.fft = fft;
	}
	
	public void setSOR(SOR sor){
		this.sor = sor;
	}
	
	public void setMonteCarlo(MonteCarlo monteCarlo){
		this.monteCarlo = monteCarlo;
	}
	
	public void setSparseMatmult(SparseMatmult sparseMatmult){
		this.sparseMatmult = sparseMatmult;
	}
	
	public void setLU(LU lu){
		this.lu = lu;
	}
	
	public void setScrore(double score){
		this.globalScore = score;
	}
	
	public String toString(){
		return 	"SciMark 2.0a" + "\n\n" +
				"Score : " + globalScore + "\n" +
				"FFT(size:" + fft.size + ") : " + (fft.value == 0.0 ? "Erreur! Résultat invalide!" : fft.value) + "\n" +
				"SOR(" + sor.size + "x" + sor.size + ") : " + sor.value + "\n" +
				"Monte Carlo : " + monteCarlo.value + "\n" +
				"Sparse matmult(" + sparseMatmult.size_N + "x" + sparseMatmult.size_nz + ") : " + sparseMatmult.value + "\n" +
				"LU(" + lu.size + "x" + lu.size + ") : " + (lu.value == 0.0 ? "Erreur! Résultat invalide!" : lu.value) + "\n";
	}
	
	
	public static class FFT{
		public int size;
		public double value;
		
		public FFT(int size, double value) {
			this.size = size;
			this.value = value;
		}
	}
	
	public static class SOR{
		public int size;
		public double value;
		
		public SOR(int size, double value) {
			this.size = size;
			this.value = value;
		}
	}
	
	public static class MonteCarlo{
		public double value;
		
		public MonteCarlo(double value) {
			this.value = value;
		}
	}
	
	public static class SparseMatmult{
		public int size_N, size_nz;
		public double value;
		
		public SparseMatmult(int size_N, int size_nz, double value) {
			this.size_N = size_N;
			this.size_nz = size_nz;
			this.value = value;
		}
	}
	
	public static class LU{
		public int size;
		public double value;
		
		public LU(int size, double value) {
			this.size = size;
			this.value = value;
		}
	}
	
	private static class SystemInformations{
		
		public static String javaVendor(){
			return System.getProperty("java.vendor");
		}
		
		public static String javaVersion(){
			return System.getProperty("java.version");
		}
		
		public static String osArch(){
			return System.getProperty("os.arch");
		}
		
		public static String osName(){
			return System.getProperty("os.name");
		}
		
		public static String osVersion(){
			return System.getProperty("os.version");
		}
	}


	public static void main(String[] args) {

		/* Benchmark 5 kernels with individual Mflops.
		 "results[0]" has the average Mflop rate.
		 */
		BenchMarkResultEvent event = new BenchMarkResultEvent() {
			
			@Override
			public void onStart() {
				System.out.println("BenchMark démarre!");
			}
			
			@Override
			public void onProgress(int progress) {
				System.out.println("Pourcentage : " + progress + "%");
			}
			
			@Override
			public void onEnd(BenchMarkResult result) {
				System.out.println("BenchMark Terminé!");
				System.out.println(result.toString());
			}
		};
		
		BenchMark b = new BenchMark(event);
		b.launch();
		
	}

}
