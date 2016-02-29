import java.io.*;
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
//			System.out.println( cad.length );
			System.out.print( "Introducir texto: " );
			while ( ( car = System.in.read() ) != '\n' && i < cad.length )
			{
				cad[i] = (char)car;
				i++;
			}
			}
			catch( IOException ignorada ) 
			{}
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
				System.out.println( cat2find[c] + " coincide con " + cadena[i] );
				i++;
			}

			return true;
		}
	//
	// BUSCAR_CAD
		public static int BUSCAR_CAD( char[] cat2find, char[] cadena )
		{
			int i = 0; 		// contador en la cadena principal
			int p = 0;		// longitud válida de la cadena a buscar
			while ( cat2find[p] != '\0' )
			{
				p++;
			}
			System.out.println( "Longitud a de la cadena a buscar: " + p  );
			while ( cadena[ i ] != '\0' )
			{
				if ( cadena[ i ] == cat2find[ 0 ] )
				{
					System.out.println("Encontrada coincidencia en el primer carácter...");
					if ( COMPARA_CHARS( i, cat2find, cadena, p ) )
					{
						return i;
					}
				}
				i++;
			}
			return -1;
		}
	
	public static void main( String[] args )
	{	
		char[] cadena = new char[50];
		char[] busqueda = new char[50];
		LER( cadena );
		VER( cadena );
		System.out.println( "Se ha encontrado el carácter en posición: " + BUSCAR( cadena, 'a' ) );
		LER( busqueda );
		System.out.println( "Se ha encontrado la cadena en posicion: " + BUSCAR_CAD( busqueda, cadena ) );
	}
}
