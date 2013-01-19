package coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class InputParser {

	public static Vector<Coordinate> parse(String fileName){
		File file = new File(fileName);
		Vector<Coordinate> output = new Vector<Coordinate>();
 
        try {
 
            Scanner scanner = new Scanner(file);
 
            while (scanner.hasNextLine()) {
            	Coordinate c = getCoord(scanner.nextLine());
            	
                output.add(c);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
		return output;
	}
	
	private static Coordinate getCoord(String line){
		String[] r = line.split("\\s+");
		
		int i = r[0].length() == 0 ? 1 : 0;
		
		return new Coordinate(Double.parseDouble(r[i]), Double.parseDouble(r[i + 1]), Double.parseDouble(r[i + 2]));
	}
	
	public static void main(String[] args){
		Vector<Coordinate> r = parse("pete1.txt");
		for(Coordinate c : r){
			System.out.println(c.toString());
		}
	
	}
	
}
