package actividad1x02;

import java.io.IOException;
import java.util.Random;

public class GeneraAleatorio {
	public static void main(String[] args) throws IOException{
		Random rand = new Random();
		System.out.println(rand.nextInt(10));
	}
}
