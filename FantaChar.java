import java.io.*;
import java.lang.Exception;
class FantaChar
{
	//	static char[] cadena;
	//
	// ler arraichar
		public static void LER( char[] cad ) 
		{
			// cadena = new char[ 50 ]; // de 50 caracteres
			
			int i = 0, 
				car;

			try {
//				System.out.println( cad.length );
				System.out.print( "Introducir texto: " );
				while ( i < cad.length -1 )
				{
					car = System.in.read(); 
					if ( car == 13 || car == 10 ) break;

					cad[i] = (char)car;
					i++;
				}
			}
			catch( IOException ignorada ) 
			{}
			// Modificación propuesta
			// Sustituye el último carácter introducido (int)13 [ intro ] 
			// por '\0' de forma que éste marca el final válido de la cadena.
			cad[ i ] = '\0';
		}
	//
	// LONGITUD_DE_LA_CADENA
		private static int LONGITUD_DE_LA_CADENA( char[] cat )
		{
			int caracteres = 0;
			while ( cat[ caracteres ] != '\0' )
			{
				// Si el caracter no es '\0' suma un carácter
				caracteres++;
			}
			// devuelve el número de caracteres distintos de '\0'
			return caracteres;
		}
	//
	// VER
		public static void VER( char[] cadena )
		{
			int i = 0;
			while ( cadena[ i ] != '\0' ) 
			{
				System.out.print( cadena[ i ] );
				i++;	
			}
			System.out.println();
		}
	//
	// BUSCAR
		public static int BUSCAR( char[] cadena, char caracter )
		{
			int i = 0;
			while ( cadena[ i ] != '\0' )
			{
				if ( cadena[ i ] == caracter )
				{
					return i;
				}
				i++;
			}
			return -1;
		}
	//
	// COMPARAR CADENAS. Devolverá true si hace match y false en cualquier otro caso.
		private static boolean COMPARA_CHARS( int i, char[] cat2find, char[] cadena, int p )
		{
			int fin_cat = p; // Longitud a comparar

			for ( int c = 0; c < fin_cat; c++ ) 
			{
				if ( cat2find[ c ] != cadena[ i ] )
				{
					return false;
				}
			// DEBUG. System.out.println( cat2find[c] + " coincide con " + cadena[i] );
				i++;
			}

			return true;
		}
	//
	// BUSCAR_CAD
		public static int BUSCAR_CAD( char[] cat2find, char[] cadena )
		{
			int i = 0; 		// contador en la cadena principal
			int p = LONGITUD_DE_LA_CADENA( cat2find );		// longitud válida de la cadena a buscar
			/*	
		 	 * while ( cat2find[p] != '\0' )
			 * {
			 * 	p++;
			 * } 
			//*/
			System.out.println( "Longitud a de la cadena a buscar: " + p  );
			while ( cadena[ i ] != '\0' )
			{
				if ( cadena[ i ] == cat2find[ 0 ] )
				{
				// DEBUG. System.out.println("Encontrada coincidencia en el primer carácter...");
					if ( COMPARA_CHARS( i, cat2find, cadena, p ) )
					{
						return i;
					}
				}
				i++;
			}
			return -1;
		}
	//
	// REMPLAZA
		private static void REMPLAZA( char[] cadena, char[] cat2find, char[] catSustituta )
		{
			// Posición inicial donde empezar a sustituir
			int posicion = BUSCAR_CAD( cat2find, cadena ); 

			int longitudSustituta = LONGITUD_DE_LA_CADENA( catSustituta );
			int longitudcat2find = LONGITUD_DE_LA_CADENA( cat2find ); 
			
			int diferencia = longitudSustituta - longitudcat2find;

			if ( diferencia == 0 ) // Las cadenas son del mismo tamaño.
			{
				for ( int i = 0; i < longitudSustituta; i++ )
				{
					cadena[ posicion ] = catSustituta[ i ];
					posicion++;
				}
			}
			else // Las cadenas con de distinto tamaño.
			{
				if ( DESPLAZA( cadena, posicion, diferencia, longitudcat2find ) ) // Solo entra si DESPLAZA() devuelve true
				{
					for ( int i = 0; i < longitudSustituta; i++ )
					{
						cadena[ posicion ] = catSustituta[ i ];
						posicion++;
					}
				}
			}
		}

		//
		// DESPLAZA_CADENA
		private static boolean DESPLAZA( char[] cadena, int inicioDesplazamiento, int desplazamiento, int long2Find )
		{	
			int lastChar = LONGITUD_DE_LA_CADENA( cadena );

			if ( desplazamiento > 0 )
			{
				for ( int i = lastChar; i > inicioDesplazamiento; i-- )
				{
					cadena[ i + desplazamiento ] = cadena[ i ];
				}
				return true;
			}	
			else if ( desplazamiento < 0 )
			{
				int pi = inicioDesplazamiento + long2Find;
				int pf = pi + desplazamiento;
				for ( int i = pf; i < lastChar; i++ )
				{
					cadena[ i ] = cadena[ pi ];
					pi++;
				}
	
				for ( int i = lastChar - desplazamiento; i < long2Find; i++ )
				{
					cadena[ i ] = '\0';
				}
				return true;
			}
			return false;
		}
	
	public static void main( String[] args )
	{	
		char[] cadena = new char[50];
		char[] busqueda = new char[50];
		char[] sustituta = new char[50];
		
		LER( cadena );
		VER( cadena );
		
		System.out.println( "Se ha encontrado el carácter en posición: " + BUSCAR( cadena, 'a' ) );
		
		LER( busqueda );
		System.out.println( "Se ha encontrado la cadena en posicion: " + BUSCAR_CAD( busqueda, cadena ) );
		
		System.out.println( "Cadena a sustituir? " );
		LER( busqueda );
		
		System.out.println( "Cadena a sustituta? " );
		LER( sustituta );
		REMPLAZA( cadena, busqueda, sustituta );
		
		System.out.println( "Resultado: " );
		VER( cadena );
	} 
}
