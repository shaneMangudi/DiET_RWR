/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.textmanipulationmodules.CyclicRandomTextGenerators;

import diet.utils.VectorToolkit;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Greg
 */
public class CyclicRandomParticipantIDGeneratorGROOP implements IRandomParticipantIDGenerator {

	Random r = new Random();

	public static String[] acceptableID = { "RRRR1", "RRRR2", "RRRR3", "RRRR4", "RRRR5", "RRRR6", "RRRR7", "RRRR8",
			"RRRR9", "RRRR0", "RRRRX", "RRRRY", "LLLL1", "LLLL2", "LLLL3", "LLLL4", "LLLL5", "LLLL6", "LLLL7", "LLLL8",
			"LLLL9", "LLLL0", "LLLLX", "LLLLY",

			"111111", "222222", "333333", "444444", "555555", "666666", "777777", "888888", "AAAAAA", "BBBBBB",
			"CCCCCC", "DDDDDD", "EEEEEE", "FFFFFF", "GGGGGG", "HHHHHH", "IIIIII", "JJJJJJ", "KKKKKK", "LLLLLL",
			"MMMMMM", "NNNNNN", "OOOOOO", "PPPPPP", "QQQQQQ", "RRRRRR", "SSSSSS", "TTTTTT", "UUUUUU", "VVVVVV",
			"WWWWWW", "XXXXXX", "YYYYYY", "ZZZZZZ", "1111A", "1111B",

			"2222A", "2222B",

			"3333A", "3333B",

			"4444A", "4444B",

			"5555A", "5555B",

			"6666A", "6666B",

			"7777A", "7777B",

			"8888A", "8888B",

			"9999A", "9999B",

			"0000A", "0000B",

			"XXXXA", "XXXXB",

			"YYYYA", "YYYYB",

			"AAAA1", "AAAA2", "AAAA3", "AAAA4", "AAAA5", "AAAA6", "AAAA7", "AAAA8", "AAAA9", "AAAA0", "AAAAX",

			"BBBB1", "BBBB2", "BBBB3", "BBBB4", "BBBB5", "BBBB6", "BBBB7", "BBBB8", "BBBB9", "BBBB0", "BBBBX"

	};

	static Vector v2 = new Vector(Arrays.asList(acceptableID));

	public CyclicRandomParticipantIDGeneratorGROOP() {
		System.err.println("GENERATOR");
	}

	public synchronized String getNextOLD() {
		if (v2.size() == 0)
			return "NOMOREIDSTOGENEREATE";
		Object o = v2.elementAt(0);
		System.err.println("---IDGENERATOBEGINNING THE 1st LIST HHERE ");
		VectorToolkit.list(v2);
		v2.removeElement(o);
		System.err.println("---IDGENERATORBEGINNING The 2nd list HERE RETURNING :" + (String) o);
		VectorToolkit.list(v2);

		return (String) o;
	}

	@Override
	public String getNext() {

		if (v2.size() == 0)
			return "NOMOREIDSTOGENEREATE";
		Object o = v2.elementAt(r.nextInt(v2.size()));
		v2.removeElement(o);
		return (String) o;
	}

}
