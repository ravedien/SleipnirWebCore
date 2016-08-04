import java.util.ArrayList;
import java.util.List;

public class Question3 {
	public static void main(String[] args){
		final long start = System.nanoTime();
		String s = "ABBCC";
		String []rules={"AB","BA","CB","BC","AA","CC"};
		String []results={"AA","BA","CC","CC","A","C"};
		List<Integer> combi = new ArrayList<Integer>();
		while(true){
			for(int cnt = 0; cnt<rules.length;cnt++){
				if(s.contains(rules[cnt])){
					combi.add(cnt);
				}
			}
			if(combi.isEmpty()){
				System.out.println(s);
				break;
			}else if(combi.size()==1){
				s=s.replaceFirst(rules[combi.get(0)], results[combi.get(0)]);
			}else{
				int randomNum = randomWithRange(0,combi.size()-1);
				s=s.replaceFirst(rules[combi.get(randomNum)], results[combi.get(randomNum)]);
			}
			combi.clear();
		}
		final long end = System.nanoTime();
		System.out.println("Took: " + ((end - start) / 1000000) + "ms");
	}
	
	static int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
}
