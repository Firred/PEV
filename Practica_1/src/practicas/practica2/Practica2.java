package practicas.practica2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.Cromosoma;
import common.cruce.practica2.CodificacionOrdinal;
import common.cruce.practica2.CruceCiclos;
import common.genes.Gen;
import common.genes.GenInt;
import common.mutacion.practica2.Heuristica;
import common.mutacion.practica2.Inversion;
import practicas.Problema;
import practicas.ProblemaNoBinario;

public class Practica2 extends ProblemaNoBinario<Integer>{
	private final static int[][] _DIST = {
			
            {},

            {171},

            {369,  294},

            {366,  537,   633},

            {525,  696,   604,   318},

            {540,  515,   809,   717,   1022},

            {646,  817,   958,   401,   694,   620},

            {488,  659,   800,   243,   536,   583,   158},

            {504,  675,   651,   229,   89,    918,   605,   447},

            {617,  688,   484,   618,   342,   1284,  1058,  900,   369},

            {256,  231,   525,   532,   805,   284,   607,   524,   701,   873},

            {207,  378,   407,   256,   318,   811,   585,   427,   324,   464,   463},

            {354,  525,   332,   457,   272,   908,   795,   637,   319,   263,   610,   201},

            {860,  1031,  1172,  538,   772,   1118,  644,   535,   683,   1072,  1026,  799,   995},

            {142,  313,   511,   282,   555,   562,   562,   404,   451,   708,   305,   244,   445,   776},

            {640,  615,   909,   817,   1122,  100,   720,   683,   1018,  1384,  384,   911,   1008,  1218,  662},

            {363,  353,   166,   534,   438,   868,   829,   671,   485,   335,   584,   278,   166,   1043,  479,   968},

            {309,  480,   621,   173,   459,   563,   396,   238,   355,   721,   396,   248,   458,   667,   486,   663,   492},

            {506,  703,   516,   552,   251,   1140,  939,   781,   323,   219,   856,   433,   232,   1006,  677,   1240,  350,   690},

            {495,  570,   830,   490,   798,   274,   322,   359,   694,   1060,  355,   587,   797,   905,   406,   374,   831,   339,   1029},

            {264,  415,   228,   435,   376,   804,   730,   572,   423,   367,   520,   179,   104,   944,   380,   904,   99,    393,   336,   732},

            {584,  855,   896,   255,   496,   784,   359,   201,   407,   796,   725,   511,   733,   334,   500,   884,   761,   391,   730,   560,   668},

            {515,  490,   802,   558,   866,   156,   464,   427,   762,   1128,  259,   655,   865,   973,   472,   256,   861,   407,   1097,  118,   779,   628},

            {578,  653,   899,   358,   676,   468,   152,   115,   595,   999,   455,   526,   736,   650,   464,   568,   770,   278,   968,   244,   671,   316,   312},

            {762,  933,   1074,  440,   674,   1020,  546,   437,   585,   974,   928,   696,   897,   98,    678,   1120,  945,   569,   908,   807,   846,   236,   875,   352},

            {251,  422,   563,   115,   401,   621,   395,   237,   297,   663,   417,   190,   400,   609,   167,   721,   434,   58,    632,   397,   335,   333,   465,   336,   551},

            {473,  482,   219,   644,   436,   997,   939,   781,   506,   265,   713,   388,   187,   1153,  615,   1097,  129,   602,   313,   941,   209,   877,   1009,  880,   1055,  544},

            {150,  75,    219,   516,   675,   590,   796,   638,   654,   613,   306,   357,   444,   1010,  292,   690,   278,   459,   628,   611,   340,   734,   583,   694,   912,   401,   407}

            };
	
	private final static String[] CIUDADES = {
			"Albacete", "Alicante", "Almeria", "Avila", "Badajoz", "Barcelona", "Bilbao", "Burgos", "Caceres", "Cadiz", "Castellon",
			"Ciudad Real", "Cordoba", "A Coruna", "Cuenca", "Gerona", "Granada", "Guadalajara", "Huelva", "Huesca", "Jaen",
			"Leon", "Lerida", "Logrono", "Lugo", "Madrid", "Malaga", "Murcia"
			//"Orense", "Oviedo", "Palencia", "Pamplona", "Pontevedra", "Salamanca", "S.Sebastian", "Santander", "Segovia", "Sevilla",
			//"Soria", "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vitoria", "Zamora", "Zaragoza"
	};
	
	private final ArrayList<GenInt> lista;

	public Practica2() {
		super(true, CIUDADES.length);
		lista = crearLista();
		super.setMutacion(new Inversion());
		super.setReproduccion(new CruceCiclos());
	}

	@Override
	public double evalua(Cromosoma<Integer> crom) {
		double aptitud=0;

		for(int i = 0; i < CIUDADES.length-1; i++) {
			if(crom.getGen(i).getCaracteristica() > crom.getGen(i+1).getCaracteristica())
				aptitud += _DIST[crom.getGen(i).getCaracteristica()][crom.getGen(i+1).getCaracteristica()];
			else {	
				aptitud += _DIST[crom.getGen(i+1).getCaracteristica()][crom.getGen(i).getCaracteristica()];
			}
		}
		
		return aptitud;
	}

	@Override
	public ArrayList<? extends Gen<Integer>> crearGenes(double... args) {
		ArrayList<GenInt> genes = new ArrayList<>(lista);
		
		Collections.shuffle(genes);
		
		return genes;
	}
	
	public List<GenInt> getLista() {			
		return this.lista;
	}
	
	@Override
	public String toString() {
		return "Practica 2";
	}
	
	public String cromToString(Cromosoma<Integer> crom) {
		String s = "El recorrido más corto es de: " + crom.getX() + "km." 
				+ System.lineSeparator() + "Recorrido:"
				+ System.lineSeparator();
		
		for(int i = 0; i < crom.getNumGenes()-1; i++) {
			s += CIUDADES[crom.getGen(i).getCaracteristica()] + " -> ";
		}
		
		s += CIUDADES[crom.getGen(crom.getNumGenes()-1).getCaracteristica()];
		
		return s;
	}
	
	
	private ArrayList<GenInt> crearLista() {
		ArrayList<GenInt> genes = new ArrayList<GenInt>(CIUDADES.length);
		
		for(int i = 0; i < CIUDADES.length; i++)
			genes.add(new GenInt(i));
		
		return genes;
	}
	
	
	/**
	 * Comprueba si el cromosoma esta formado correctamente.
	 * SOLO PARA PRUEBAS
	 * @param crom
	 * @return
	 */
	public static Gen<Integer> cromosomaCorrecto(Cromosoma<Integer> crom) {
		ArrayList<Gen<Integer>> aux = new ArrayList<>();
		
		aux.add(crom.getGen(0));
		
		for(int i = 1; i < crom.getNumGenes(); i++) {
			if(aux.contains(crom.getGen(i)))
				return crom.getGen(i);
			
			aux.add(crom.getGen(i));
		}
		
		return null;
	}
}
