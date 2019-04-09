package common.genes;

public abstract interface Gen_Bi {
	public abstract void setAlelo(boolean b, int pos);
	public abstract GenBi generarGen(int tipo);
	public abstract Boolean getGenes_Bool(int n1);
}
