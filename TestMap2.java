package javaConcurrency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class TestMap2 {
	public static void main (String[] args) {

		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
		FileReader fr;
		BufferedReader in;
		String line;

		try {
			fr = new FileReader("C:\\Users\\Administrator\\Desktop\\data1G.txt");
//			fr = new FileReader("D:\\Download\\Data1G");
			in = new BufferedReader(fr);
			try {
				int size = Integer.parseInt(in.readLine());
				int i = 1;
				while((line = in.readLine()) != null) {
					map.put(String.valueOf(i), line);
					i++;
					System.out.println(map);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
//		System.out.println("parallel: "+ForkJoinPool.getCommonPoolParallelism());


//		map.put("foo", "1");
//		map.put("han", "2");
//		map.put("r2", "3");
//		map.put("c3", "4");
		//String result = map.reduce(1,
		//    (key, value) -> {
		//        System.out.println("Transform: " + Thread.currentThread().getName());
		//        return key + "=" + value;
		//    },
		//    (s1, s2) -> {
		//        System.out.println("Reduce: " + Thread.currentThread().getName());
		//        return s1 + ", " + s2;
		//    });

		long result = (int) map.reduceValuesToInt(4,
				(value) ->  {return Integer.parseInt(value);},
				0,
				(left, right) -> {
					int total = left + right;
					System.out.println("Result: "+total);
					return total;
				});
		System.out.println(result);
	}

}


// Result: r2=d2, c3=p0, han=solo, foo=bar
